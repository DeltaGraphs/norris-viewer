package deltagraphs.norrisviewer.view.graphsView;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import deltagraphs.norrisviewer.R;
import deltagraphs.norrisviewer.model.flowModel.*;
import deltagraphs.norrisviewer.presenter.graphsPresenter.*;
import lecho.lib.hellocharts.listener.LineChartOnValueSelectListener;
import lecho.lib.hellocharts.model.LineChartData;
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
    // Map< flowIndex, lineIndex>
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

    private Axis axisX;
    private Axis axisY;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.line_chart);
        chart = (LineChartView) findViewById(R.id.chart);
        chart.setOnValueTouchListener(new ValueTouchListener());
        previewChart = (PreviewLineChartView) findViewById(R.id.chart_preview);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            sourceURL = extras.getString("EXTRA_SOURCE_URL");
            sourceTitle = extras.getString("EXTRA_SOURCE_TITLE");
        }
        sourceURL = "http://norris-nrti-dev.herokuapp.com/page1/line1";
        setTitle(sourceTitle);
        lineChartPresenter = new LineChartPresenterImpl(this, sourceURL);
        data = new LineChartData();

        chart.setZoomEnabled(false);
        chart.setScrollEnabled(false);

        previewChart.setLineChartData(previewData);
        previewChart.setViewportChangeListener(new ViewportListener());

        previewX(true);
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
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void setInitialState(){
        hasAxes = true;
        hasAxesNames = true;
        hasLabels = false;
        hasLabelForSelected = false;
        dataType = DEFAULT_DATA;
        chart.setValueSelectionEnabled(hasLabelForSelected);
    }


    @Override
    public void setAxis(char axisXorY, String name, String appearance, float maxIndex, float minIndex, int ticks, int scale) {
        if (hasAxes) {

            float step = (maxIndex-minIndex)/ticks;

            if(axisXorY == 'x') {
                axisX = Axis.generateAxisFromRange(minIndex,maxIndex,step);
                axisX.setHasLines(true);
                if (hasAxesNames) {
                    axisX.setName(name);
                }

                data.setAxisXBottom(axisX);
            }
            if(axisXorY == 'y') {
                axisY = Axis.generateAxisFromRange(minIndex,maxIndex,step);
                axisY.setHasLines(true);
                if (hasAxesNames) {
                    axisY.setName(name);
                }
                data.setAxisYLeft(axisY);
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

    //se il flusso esiste già ma non è stato aggiornato
    //List<PointValue> oldValues = lines.get(indice_flusso).getValues();
    //List<PointValue> newValues = oldValues
    //e poi creo la linea

    //se il flusso esiste già ed è stato aggiornato
    //List<PointValue> oldValues lines.get(indice_flusso).getValues();
    //List<PointValue> newValues
    //dentro al for si farà newValues.add(nuovi_valori)
    //Line line = new Line(values);

    //se il flusso viene creato
    //indexesList.add(flowList.get(i).getFlowId());
    //si fa tutto normalmente

    //se il flusso viene eliminato
    //indexesList.remove(flowList.get(i).getFlowId());
    //non viene inserita la linea nel data model

    //ogni volta viene ricreato tutto il data model del grafico
    //bisogna capire se è troppo pesante

    @Override
    public void setData(ArrayList<FlowModel> flowList, String signal) {
        String color = "";
        lines = new ArrayList<Line>();
        for (int i = 0; i < flowList.size(); i++) {

            String flowId = flowList.get(i).getFlowId();

            //flowList.get(i).getFlowName();

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
                values.add(new PointValue(x, y));
            }

            Line line = new Line(values);
            setLineColor(line, color);

            line.setHasLabels(hasLabels);
            lines.add(line);
        }
        data.setLines(lines);
        previewData = new LineChartData(data);
        //previewData.getLines().get(0).setColor(ChartUtils.DEFAULT_DARKEN_COLOR);

        chart.setLineChartData(data);
        previewChart.setLineChartData(previewData);
        Log.d("LineActivity", "line chart data settato");
    }


    private void setLineColor(Line line, String color){
        if(color != null && color != "random")
            line.setColor(Color.parseColor(color));
        else
            line.setColor(ChartUtils.DEFAULT_COLOR);
    }

    private class ValueTouchListener implements LineChartOnValueSelectListener {

        @Override
        public void onValueSelected(int lineIndex, int pointIndex, PointValue value) {
            Toast.makeText(getApplicationContext(), "VALUE:\n" + "x = " + value.getX() + "\ny = " + value.getY(), Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onValueDeselected() {
            // TODO Auto-generated method stub

        }

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
