package deltagraphs.norrisviewer.view.graphsView;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import deltagraphs.norrisviewer.R;
import deltagraphs.norrisviewer.model.flowModel.*;
import deltagraphs.norrisviewer.presenter.graphsPresenter.*;
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
    private Map<String, Integer> indexesMap;

    private LineChartPresenter lineChartPresenter;

    //line chart con view finder

    private LineChartView chart;
    private PreviewLineChartView previewChart;
    private LineChartData data;

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
        previewChart = (PreviewLineChartView) findViewById(R.id.chart_preview);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            sourceURL = extras.getString("EXTRA_SOURCE_URL");
            sourceTitle = extras.getString("EXTRA_SOURCE_TITLE");
        }
        setTitle(sourceTitle);
        lineChartPresenter = new LineChartPresenterImpl(this, sourceURL);
        lines = new ArrayList<Line>();
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

    public void onValueSelected(){

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

    @Override
    public void setData(ArrayList<FlowModel> flowList, String signal) {
        String color = "";
        for(int i=0; i<flowList.size(); i++) {

            indexesMap.put(flowList.get(i).getFlowId(), 10000);

            flowList.get(i).getFlowName();
            LineChartFlow lineChartFlow = (LineChartFlow) flowList.get(i);
            color = lineChartFlow.getFlowColour();
            lineChartFlow.getMarker();
            lineChartFlow.getInterpolation();
            List<PointValue> values = new ArrayList<PointValue>();
            for(int j =0; j< lineChartFlow.getRecordSize(); j++) {
                //lineChartFlow.getRecordId(j);
                float x = lineChartFlow.getRecordValueX(j);
                float y = lineChartFlow.getRecordValueY(j);
                values.add(new PointValue(x, y));
            }
            Line line = new Line(values);
            switch (color){
                case "green":
                    line.setColor(ChartUtils.COLOR_GREEN);
                    break;
                case "blue":
                    line.setColor(ChartUtils.COLOR_BLUE);
                    break;
                case "orange":
                    line.setColor(ChartUtils.COLOR_ORANGE);
                    break;
                case "red":
                    line.setColor(ChartUtils.COLOR_RED);
                    break;
                case "violet":
                    line.setColor(ChartUtils.COLOR_VIOLET);
                    break;
                default:
                    line.setColor(ChartUtils.DEFAULT_COLOR);
                    break;
            }
            line.setHasLabels(hasLabels);
            lines.add(line);
        }
    }
}
