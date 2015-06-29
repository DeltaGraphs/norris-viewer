package deltagraphs.norrisviewer.view.graphsView;

/*
 * Name : LineChartActivity.java
 * Module : norrisviewer::view::graphsView
 * Location : norrisviewer\view\graphsView
 *
 * History :

 * Version   Date       Programmer              Description
 * ==========================================================================================
 *
 * 0.3.2 2015-06-15 Davide Trivellato Update legend style
 *
 * 0.3.1 2015-06-03 Davide Trivellato Fix ViewFinder
 *
 * 0.3.0 2015-06-02 Davide Trivellato Coding of menu, axis and flow colours
 *
 * 0.2.0 2015-06-02 Davide Trivellato Implementation of ViewFinder and legend
 *
 * 0.1.0 2015-06-01 Davide Trivellato Coding of methods and attributes
 *
 * 0.0.1 2015-05-31 Davide Trivellato Creation of the file
 *
 * =========================================================================================
 *
 */

import android.content.Context;
import android.graphics.Color;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import deltagraphs.norrisviewer.R;
import deltagraphs.norrisviewer.model.flowModel.*;
import deltagraphs.norrisviewer.presenter.graphsPresenter.*;
import lecho.lib.hellocharts.listener.LineChartOnValueSelectListener;
import lecho.lib.hellocharts.view.PreviewLineChartView;

import lecho.lib.hellocharts.gesture.ZoomType;
import lecho.lib.hellocharts.listener.ViewportChangeListener;
import lecho.lib.hellocharts.model.Axis;
import lecho.lib.hellocharts.model.Line;
import lecho.lib.hellocharts.model.LineChartData;
import lecho.lib.hellocharts.model.PointValue;
import lecho.lib.hellocharts.model.Viewport;
import lecho.lib.hellocharts.util.ChartUtils;
import lecho.lib.hellocharts.view.LineChartView;


public class LineChartActivity extends ActionBarActivity implements deltagraphs.norrisviewer.view.graphsView.LineChartView {



    private List<Line> lines;
    private List<Line> previewLines;

    private LineChartPresenter lineChartPresenter;

    //line chart with view finder

    private LineChartView chart;
    private PreviewLineChartView previewChart;
    private LineChartData data;
    private LineChartData previewData;

    private String sourceTitle;
    private String sourceURL;

    private boolean hasAxes = true;
    private boolean hasAxesNames = true;
    private boolean hasLabels = false;
    private boolean hasViewFinder = false;

    private Axis axisX;
    private Axis axisY;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.line_chart);
        //set the view layout for the graph
        chart = (LineChartView) findViewById(R.id.chart);
        //set the view layout for the view finder
        previewChart = (PreviewLineChartView) findViewById(R.id.chart_preview);
        //set a listener for the graph
        chart.setOnValueTouchListener(new ValueTouchListener());
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            sourceURL = extras.getString("EXTRA_SOURCE_URL");
            sourceTitle = extras.getString("EXTRA_SOURCE_TITLE");
        }
        setTitle(sourceTitle);
        //lineChartPresenter = new LineChartPresenterImpl(this, sourceURL);
        data = new LineChartData();
        previewData = new LineChartData();

        //disable the zoom and the scrolling
        chart.setZoomEnabled(false);
        chart.setScrollEnabled(false);

        //set a listener for the view finder
        previewChart.setViewportChangeListener(new ViewportListener());
        //set the line color for the view finder
        previewChart.setPreviewColor(Color.parseColor("#80CBC4"));
    }

    //manage the resuming action from another activity
    @Override
    public void onResume() {
        super.onResume();
        lineChartPresenter = new LineChartPresenterImpl(this, sourceURL);
        lineChartPresenter.startListening();
    }

    //manage the onStop event
    @Override
    public void onStop() {
        super.onStop();
        lineChartPresenter.stopConnection();
        lineChartPresenter.stopListening();
    }

    //manage the onPause event
    @Override
    public void onPause() {
        super.onPause();
        lineChartPresenter.stopConnection();
        lineChartPresenter.stopListening();
    }

    //manage the onRestart event
    @Override
    public void onRestart() {
        super.onRestart();
        lineChartPresenter.startListening();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_line_chart, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        //enable the X preview for the view finder
        if (id == R.id.action_previewX) {
            previewX(true);
            return true;
        }
        //enable the Y preview for the view finder
        if (id == R.id.action_previewY) {
            previewY();
            return true;
        }

        //enable the XY preview for the view finder
        if (id == R.id.action_previewXY) {
            previewXY();
            return true;
        }

        //shows Deltagraphs informations in a toast box
        if (id == R.id.action_credits) {
            Context context = getApplicationContext();
            CharSequence text = "Credits to DeltaGraphs 2015";
            int duration = Toast.LENGTH_SHORT;

            Toast toast = Toast.makeText(context, text, duration);
            toast.show();

            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    /*
    This method configures a single axis for the line chart
     */
    @Override
    public void setAxis(char axisXorY, String name, int ticks) {
        axisX = new Axis().setHasLines(true);
        axisY = new Axis().setHasLines(true);
        /*if (hasAxes) {

            float step = (maxIndex - minIndex) / ticks;

            if (axisXorY == 'x') {
                axisX = new Axis().setHasLines(true);
                axisX.generateAxisFromRange(minIndex, maxIndex, step);
                if (hasAxesNames) {
                    axisX.setName(name);
                }
            }
            if (axisXorY == 'y') {
                axisY = new Axis().setHasLines(true);
                axisY.generateAxisFromRange(minIndex, maxIndex, step);
                if (hasAxesNames) {
                    axisY.setName(name);
                }
            }
        } else {
            data.setAxisXBottom(null);
            data.setAxisYLeft(null);
        }*/
    }

    public void setViewFinder(Boolean isVisible){
        if(!isVisible){
            previewChart.setVisibility(View.GONE);
        }
        else{
            previewChart.setVisibility(View.VISIBLE);
        }
    }


    /*
    This method sets a grid if the option is enabled by the two variables
     */
    @Override
    public void setGrid(Boolean horizontal, Boolean vertical) {
        axisX.setHasSeparationLine(vertical);
        axisY.setHasSeparationLine(horizontal);
    }

    /*
    It's the main method. It update the view by getting data from the model and set the values on the graph
     */
    @Override
    public void setData(ArrayList<FlowModel> flowList, String signal) {
        String color = "";
        lines = new ArrayList<Line>();
        previewLines = new ArrayList<Line>();
        for (int i = 0; i < flowList.size(); i++) {

            String flowId = flowList.get(i).getFlowId();

            String flowName = flowList.get(i).getFlowName();

            LineChartFlow lineChartFlow = (LineChartFlow) flowList.get(i);

            color = lineChartFlow.getFlowColour();
            Log.d("", color);
            List<PointValue> values = new ArrayList<PointValue>();
            //for each flow get the values.
            for (int j = 0; j < lineChartFlow.getRecordSize(); j++) {
                //lineChartFlow.getRecordId(j);
                float x = lineChartFlow.getRecordValueX(j);
                float y = lineChartFlow.getRecordValueY(j);
                //Create a point value
                PointValue pointValue = new PointValue(x, y);
                pointValue.setLabel(flowName);
                values.add(pointValue);
            }

            Line line = new Line(values);
            setLineColor(line, color);

            Line previewLine = new Line(values);
            setLineColor(previewLine, "default");

            //set labels for chart and view finder
            previewLine.setHasLabels(hasLabels);
            line.setHasLabels(hasLabels);

            lines.add(line);
            previewLines.add(previewLine);
        }

        data.setLines(lines);
        //set axes for the data
        data.setAxisXBottom(axisX);
        data.setAxisYLeft(axisY);

        previewData.setLines(previewLines);
        //set data for the chart
        chart.setLineChartData(data);
        //set data for the view finder
        previewChart.setLineChartData(previewData);
    }

    /*
    Set the color for a particular line
     */
    private void setLineColor(Line line, String color) {
        if (color != null && color != "null" && color != "default")
            line.setColor(Color.parseColor(color));
        else
            line.setColor(ChartUtils.DEFAULT_COLOR);

    }

    private class ValueTouchListener implements LineChartOnValueSelectListener {

        @Override
        public void onValueSelected(int lineIndex, int pointIndex, PointValue value) {
            char[] cLabel = value.getLabelAsChars();
            String label = new String(cLabel);

            LayoutInflater inflater = getLayoutInflater();
            View layout = inflater.inflate(R.layout.custom_toast,
                    (ViewGroup) findViewById(R.id.toast_layout_root));

            TextView title = (TextView) layout.findViewById(R.id.label);
            title.setText(label);

            TextView values = (TextView) layout.findViewById(R.id. coord_values);
            values.setText("\nx = " + value.getX() + "\ny = " + value.getY());

            Toast toast = new Toast(getApplicationContext());
            toast.setGravity(Gravity.CENTER, 0, 0);
            toast.setDuration(Toast.LENGTH_SHORT);
            toast.setView(layout);
            toast.show();
        }

        @Override
        public void onValueDeselected() {
            // TODO Auto-generated method stub

        }

    }

    public void onDestroy() {
        // Otherwise defer to system default behavior.
        super.onDestroy();
    }

    private void previewY() {
        Viewport tempViewport = new Viewport(chart.getMaximumViewport());
        float dy = tempViewport.height() / 4;
        tempViewport.inset(0, dy);
        previewChart.setCurrentViewportWithAnimation(tempViewport);
        previewChart.setZoomType(ZoomType.VERTICAL);
    }

    private void previewX(boolean animate) {
        Viewport tempViewport = new Viewport(chart.getMaximumViewport());
        float dx = tempViewport.width() / 4;
        tempViewport.inset(dx, 0);
        if (animate) {
            previewChart.setCurrentViewportWithAnimation(tempViewport);
        } else {
            previewChart.setCurrentViewport(tempViewport);
        }
        previewChart.setZoomType(ZoomType.HORIZONTAL);
    }

    private void previewXY() {
        // Better to not modify viewport of any chart directly so create a copy.
        Viewport tempViewport = new Viewport(chart.getMaximumViewport());
        // Make temp viewport smaller.
        float dx = tempViewport.width() / 4;
        float dy = tempViewport.height() / 4;
        tempViewport.inset(dx, dy);
        previewChart.setCurrentViewportWithAnimation(tempViewport);
    }

    private class ViewportListener implements ViewportChangeListener {

        @Override
        public void onViewportChanged(Viewport newViewport) {
            // don't use animation, it is unnecessary when using preview chart.
            chart.setCurrentViewport(newViewport);
        }

    }
}
