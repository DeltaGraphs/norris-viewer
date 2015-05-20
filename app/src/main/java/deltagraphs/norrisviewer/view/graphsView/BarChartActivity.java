package deltagraphs.norrisviewer.view.graphsView;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import deltagraphs.norrisviewer.R;
import deltagraphs.norrisviewer.presenter.graphsPresenter.BarChartPresenter;
import deltagraphs.norrisviewer.presenter.graphsPresenter.BarChartPresenterImpl;
import lecho.lib.hellocharts.formatter.SimpleAxisValueFormatter;
import lecho.lib.hellocharts.model.*;
import lecho.lib.hellocharts.view.ColumnChartView;
import lecho.lib.hellocharts.gesture.ZoomType;
import lecho.lib.hellocharts.listener.ColumnChartOnValueSelectListener;
import lecho.lib.hellocharts.model.Axis;
import lecho.lib.hellocharts.model.Column;
import lecho.lib.hellocharts.model.ColumnChartData;
import lecho.lib.hellocharts.model.SubcolumnValue;
import lecho.lib.hellocharts.util.ChartUtils;
import lecho.lib.hellocharts.view.Chart;
import lecho.lib.hellocharts.view.ColumnChartView;

public class BarChartActivity extends ActionBarActivity implements deltagraphs.norrisviewer.view.graphsView.BarChartView {

    private static final int DEFAULT_DATA = 0;
    private static final int SUBCOLUMNS_DATA = 1;
    private static final int STACKED_DATA = 2;
    private static final int NEGATIVE_SUBCOLUMNS_DATA = 3;
    private static final int NEGATIVE_STACKED_DATA = 4;

    private BarChartPresenter barChartPresenter;

    private ColumnChartView chart;
    private ColumnChartData data;

    private String sourceTitle;
    private String sourceURL;

    private boolean hasAxes = true;
    private boolean hasAxesNames = true;
    private boolean hasLabels = false;
    private boolean hasLabelForSelected = false;
    private boolean hasLegend = true;
    private int dataType = DEFAULT_DATA;

    Axis axisY;
    Axis axisX;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle extras = getIntent().getExtras();

        sourceURL = extras.getString("EXTRA_SOURCE_URL");
        sourceTitle = extras.getString("EXTRA_SOURCE_TITLE");

        barChartPresenter = new BarChartPresenterImpl(this, sourceURL);
        setTitle(sourceTitle);
        setContentView(R.layout.bar_chart);
        chart = (ColumnChartView) findViewById(R.id.chart);
        chart.setOnValueTouchListener(new ValueTouchListener());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_bar_chart, menu);
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
    public void setHeaders(ArrayList<String> headers) {

    }

    @Override
    public void setBackground(String bg) {

    }

    @Override
    public void setBarOrientation(String orientation) {

    }

    @Override
    public void setSortable(Boolean choise) {

    }

    @Override
    public void setGrid(Boolean horizontal, Boolean vertical) {
        axisX.setHasSeparationLine(vertical);
        axisY.setHasSeparationLine(horizontal);
    }

    @Override
    public void setLegendOnPoint(Boolean legend) {
        hasLegend = legend;
    }

    private class ValueTouchListener implements ColumnChartOnValueSelectListener {

        @Override
        public void onValueSelected(int columnIndex, int subcolumnIndex, SubcolumnValue value) {
            if(hasLegend) {
                //Toast.makeText(getActivity(), "Selected: " + value, Toast.LENGTH_SHORT).show();
            }
        }

        @Override
        public void onValueDeselected() {
            // TODO Auto-generated method stub

        }

    }
}
