package deltagraphs.norrisviewer.view.graphsView;

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

    private static final int DEFAULT_DATA = 0;
    private static final int SUBCOLUMNS_DATA = 1;
    private static final int STACKED_DATA = 2;
    private static final int NEGATIVE_SUBCOLUMNS_DATA = 3;
    private static final int NEGATIVE_STACKED_DATA = 4;

    private List<Line> lines;
    private List<Line> previewLines;
    private List<String> indexesList = new ArrayList<String>();

    private LineChartPresenter lineChartPresenter;

    //line chart con view finder

    private LineChartView chart;
    private PreviewLineChartView previewChart;
    private LineChartData data;
    private LineChartData previewData;

    private String sourceTitle;
    private String sourceURL;

    private boolean hasAxes = true;
    private boolean hasAxesNames = true;
    private boolean hasLabels = false;
    private boolean hasLabelForSelected = false;
    private boolean hasViewFinder = false;
    private int dataType = DEFAULT_DATA;
    private Viewport viewport;

    private Axis axisX;
    private Axis axisY;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d("", "onCreate");
        setContentView(R.layout.line_chart);
        chart = (LineChartView) findViewById(R.id.chart);
        chart.setOnValueTouchListener(new ValueTouchListener());
        previewChart = (PreviewLineChartView) findViewById(R.id.chart_preview);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            sourceURL = extras.getString("EXTRA_SOURCE_URL");
            sourceTitle = extras.getString("EXTRA_SOURCE_TITLE");
        }
        setTitle(sourceTitle);
        lineChartPresenter = new LineChartPresenterImpl(this, sourceURL);
        data = new LineChartData();
        previewData = new LineChartData();

        chart.setZoomEnabled(false);
        chart.setScrollEnabled(false);
        viewport = new Viewport();
        previewChart.setViewportChangeListener(new ViewportListener());
        previewChart.setPreviewColor(Color.parseColor("#80CBC4"));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_line_chart, menu);
        return true;
    }

    public void setInitialState() {
        hasAxes = true;
        hasAxesNames = true;
        hasLabels = false;
        hasLabelForSelected = false;
        dataType = DEFAULT_DATA;
        chart.setValueSelectionEnabled(hasLabelForSelected);
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_previewX) {
            previewX(true);
            return true;
        }

        if (id == R.id.action_previewY) {
            previewY();
            return true;
        }

        if (id == R.id.action_previewXY) {
            previewXY();
            return true;
        }

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

    @Override
    public void setAxis(char axisXorY, String name, String appearance, float maxIndex, float minIndex, int ticks, int scale) {
        if (hasAxes) {

            float step = (maxIndex - minIndex) / ticks;

            if (axisXorY == 'x') {
                axisX = new Axis().setHasLines(true);
                axisX.generateAxisFromRange(minIndex, maxIndex, step);
                //axisX.setHasSeparationLine(true);
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
            // TODO: Insert the possibility to change the scale.
        } else {
            data.setAxisXBottom(null);
            data.setAxisYLeft(null);
        }
    }


    @Override
    public void setViewFinder(Boolean withViewFinder) {

    }

    @Override
    public void setBackground(String bg) {

    }

    @Override
    public void setGrid(Boolean horizontal, Boolean vertical) {
        axisX.setHasSeparationLine(vertical);
        axisY.setHasSeparationLine(horizontal);
    }

    @Override
    public void setLegendOnPoint(Boolean legend) {

    }

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
            //lineChartFlow.maxItems();
            //lineChartFlow.getMarker();
            //lineChartFlow.getInterpolation();

            List<PointValue> values = new ArrayList<PointValue>();

            for (int j = 0; j < lineChartFlow.getRecordSize(); j++) {
                //lineChartFlow.getRecordId(j);
                float x = lineChartFlow.getRecordValueX(j);
                float y = lineChartFlow.getRecordValueY(j);
                PointValue pointValue = new PointValue(x, y);
                pointValue.setLabel(flowName);
                values.add(pointValue);
            }

            Line line = new Line(values);
            setLineColor(line, color);

            Line previewLine = new Line(values);
            setLineColor(previewLine, "random");

            previewLine.setHasLabels(hasLabels);
            line.setHasLabels(hasLabels);

            lines.add(line);
            previewLines.add(previewLine);
        }

        //viewport = previewChart.getCurrentViewport();

        //float dx = viewport.width();
        //float dy = viewport.height();

        data.setLines(lines);
        data.setAxisXBottom(axisX);
        data.setAxisYLeft(axisY);

        previewData.setLines(previewLines);

        chart.setLineChartData(data);

        previewChart.setLineChartData(previewData);
        //previewChart.setCurrentViewport(viewport);
        //previewChart.getCurrentViewport().inset(dx,dy);
    }


    private void setLineColor(Line line, String color) {
        if (color != null && color != "random")
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
            values.setText("\nVALUE:\n" + "x = " + value.getX() + "\ny = " + value.getY());

            Toast toast = new Toast(getApplicationContext());
            toast.setGravity(Gravity.CENTER, 0, 0);
            toast.setDuration(Toast.LENGTH_SHORT);
            toast.setView(layout);
            toast.show();




            //Toast toast = new Toast(getApplicationContext());
            //toast.setGravity(Gravity.TOP| Gravity.LEFT, 0, 0);
            //toast.makeText(getApplicationContext(),"FLOW:\n" + label + "\nVALUE:\n" + "x = " + value.getX() + "\ny = " + value.getY(), Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onValueDeselected() {
            // TODO Auto-generated method stub

        }

    }

    /*@Override
    public void onBackPressed() {
        // Otherwise defer to system default behavior.
        super.onBackPressed();
        this.onDestroy();
    }*/

    public void onDestroy() {
        // Otherwise defer to system default behavior.
        super.onDestroy();
        lineChartPresenter.destroy();
    }



/*
    public class FireMissilesDialogFragment extends DialogFragment {
        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            // Use the Builder class for convenient dialog construction
            AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
            builder.setTitle("Legend");

            // Create the AlertDialog object and return it
            return builder.create();
        }
    }
    */

    private void previewY() {
        Viewport tempViewport = new Viewport(chart.getMaximumViewport());
        float dy = tempViewport.height() / 4;
        tempViewport.inset(0, dy);
        viewport = tempViewport;
        previewChart.setCurrentViewportWithAnimation(tempViewport);
        previewChart.setZoomType(ZoomType.VERTICAL);
    }

    private void previewX(boolean animate) {
        Viewport tempViewport = new Viewport(chart.getMaximumViewport());
        float dx = tempViewport.width() / 4;
        tempViewport.inset(dx, 0);
        viewport = tempViewport;
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
        viewport = tempViewport;
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
