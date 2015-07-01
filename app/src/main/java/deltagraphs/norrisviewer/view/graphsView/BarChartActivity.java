package deltagraphs.norrisviewer.view.graphsView;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.WindowManager;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.HorizontalBarChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;

import java.util.ArrayList;
import java.util.List;

import deltagraphs.norrisviewer.R;
import deltagraphs.norrisviewer.model.flowModel.BarChartFlow;
import deltagraphs.norrisviewer.model.flowModel.FlowModel;
import deltagraphs.norrisviewer.presenter.graphsPresenter.BarChartPresenter;
import deltagraphs.norrisviewer.presenter.graphsPresenter.BarChartPresenterImpl;

/*
 * Name : BarChartActivity.java
 * Module : norrisviewer::view::graphsView
 * Location : norrisviewer\view\graphsView
 *
 * History :

 * Version Date Programmer Description
 * ===============================================================
 *
 * 1.0.0 2015-06-29 Matteo Furlan Approve
 *
 * 0.5.0 2015-06-27 Enrico Savoca Verify
 *
 * 0.4.0 2015-05-28 Davide Trivellato Create the legend panel
 *
 * 0.3.0 2015-05-27 Davide Trivellato Managing bar orientation
 *
 * 0.2.0 2015-05-26 Davide Trivellato Coding of method setData(ArrayList<FlowModel> flowList, String signal, ArrayList<String> headers)
 *
 * 0.1.0 2015-05-25 Davide Trivellato Coding of methods and attributes
 *
 * 0.0.1 2015-05-23 Davide Trivellato Creation of the file
 *
 * ===============================================================
 *
 */

public class BarChartActivity extends ActionBarActivity implements BarChartView {

    protected HorizontalBarChart hBarChart;
    protected BarChart vBarChart;
    private BarChartPresenter barChartPresenter;
    private String sourceTitle;
    private String sourceURL;
    private String orientation;
    private Legend l;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // the following method hides the status bar
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        //retrieve extra information passed on changing the activity
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            sourceURL = extras.getString("EXTRA_SOURCE_URL");
            sourceTitle = extras.getString("EXTRA_SOURCE_TITLE");
        }
        setTitle(sourceTitle);
    }


    //manage the resuming action from another activity
    @Override
    public void onResume() {
        super.onResume();
        barChartPresenter = new BarChartPresenterImpl(this, sourceURL);
    }

    //manage the onPause event
    @Override
    public void onPause() {
        super.onPause();
        barChartPresenter.stopConnection();
        barChartPresenter.destroyConnection();
    }

    //manage the onDestroy event
    @Override
    public void onDestroy() {
        barChartPresenter.stopConnection();
        barChartPresenter.stopListening();
        super.onDestroy();
    }


    /*
    Initialize a vertical bar chart with its configuration
    */
    private void initializeHorizontalBarChart() {
        //load the layout relative to the horizontal bar chart
        setContentView(R.layout.activity_horizontal_bar_chart);

        hBarChart = (HorizontalBarChart) findViewById(R.id.chart1);

        //hide the bar shadow
        hBarChart.setDrawBarShadow(false);

        //set values above bars
        hBarChart.setDrawValueAboveBar(true);

        hBarChart.setTouchEnabled(false);

        hBarChart.setDescription("");
        // if more than 60 entries are displayed in the chart, no values will be
        // drawn
        hBarChart.setMaxVisibleValueCount(60);

        // scaling can now only be done on x- and y-axis separately
        hBarChart.setPinchZoom(false);

        //it doesn't display the grid on background
        hBarChart.setDrawGridBackground(false);

        //create and configure an x axis
        XAxis xl = hBarChart.getXAxis();
        xl.setPosition(XAxis.XAxisPosition.BOTTOM);
        xl.setDrawAxisLine(true);
        xl.setDrawGridLines(true);
        xl.setGridLineWidth(0.3f);

        //create and configure a left y axis
        YAxis yl = hBarChart.getAxisLeft();
        yl.setDrawAxisLine(true);
        yl.setDrawGridLines(true);
        yl.setGridLineWidth(0.3f);

        //create and configure a right y axis
        YAxis yr = hBarChart.getAxisRight();
        yr.setDrawAxisLine(true);
        yr.setDrawGridLines(false);

        //set X animation
        hBarChart.animateX(1000);
    }

    /*
    Initialize a vertical bar chart with its configuration
     */
    private void initializeBarChart() {
        //load the layout relative to the vertical bar chart
        setContentView(R.layout.activity_bar_chart);

        vBarChart = (BarChart) findViewById(R.id.chart1);

        //hide the bar shadow
        vBarChart.setDrawBarShadow(false);

        //set values above bars
        vBarChart.setDrawValueAboveBar(true);

        vBarChart.setDescription("");
        vBarChart.setTouchEnabled(false);
        // if more than 60 entries are displayed in the chart, no values will be
        // drawn
        vBarChart.setMaxVisibleValueCount(60);

        // scaling can now only be done on x- and y-axis separately
        vBarChart.setPinchZoom(false);

        //it doesn't display the grid on background
        vBarChart.setDrawGridBackground(false);

        //create and configure a left x axis
        XAxis xl = vBarChart.getXAxis();
        xl.setPosition(XAxis.XAxisPosition.BOTTOM);
        xl.setDrawAxisLine(true);
        xl.setDrawGridLines(true);
        xl.setGridLineWidth(0.3f);

        //create and configure a bottom y axis
        YAxis yl = vBarChart.getAxisLeft();
        yl.setDrawAxisLine(true);
        yl.setDrawGridLines(true);
        yl.setGridLineWidth(0.3f);

        //create and configure a right x axis
        YAxis yr = vBarChart.getAxisRight();
        yr.setDrawAxisLine(true);
        yr.setDrawGridLines(false);

        //set Y animation
        vBarChart.animateY(1000);


    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_horizontal_bar_chart, menu);
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

    /*
    This method call the initialize method depending on the orientation set.
     */

    @Override
    public void setBarOrientation(String orientation) {

        this.orientation = orientation;
        if (orientation.equals("V"))
            initializeBarChart();
        else
            initializeHorizontalBarChart();
    }

    /*
    The following method is called on an update and retrieves data from the model to configure the bar chart.
     */

    @Override
    public void setData(ArrayList<FlowModel> flowList, String signal, ArrayList<String> headers) {
        ArrayList<BarDataSet> dataSets = new ArrayList<BarDataSet>();
        BarData data = new BarData();
        //create a list to contain every flow color
        List<Integer> lColors = new ArrayList<Integer>();
        //create a list to contain every label
        List<String> lLabels = new ArrayList<String>();
        for (int i = 0; i < flowList.size(); i++) {
            flowList.get(i).getFlowId();
            //aggiunge una label alla legenda
            String name = flowList.get(i).getFlowName();
            BarChartFlow barChartFlow = (BarChartFlow) flowList.get(i);
            String color = barChartFlow.getFlowColour();

            ArrayList<BarEntry> yVals1 = new ArrayList<BarEntry>();

            //sets the Y-value for every X-value
            for (int j = 0; j < barChartFlow.getRecordSize(); j++) {
                float y = barChartFlow.getRecordValue(j);
                String id = barChartFlow.getRecordId(j);
                yVals1.add(new BarEntry(y, j));
            }

            BarDataSet set1 = new BarDataSet(yVals1, name);
            set1.setBarSpacePercent(35f);
            if (color != "null" && color != null)
                set1.setColor(Color.parseColor(color));
            else
                set1.setColor(Color.rgb((int) (Math.random() * 255), (int) (Math.random() * 255),(int)(Math.random()*255)));
            dataSets.add(set1);

            data.setValueTextSize(10f);
        }
        data = new BarData(headers, dataSets);

        if (orientation.equals("V")) {
            setLegend(lLabels);
            vBarChart.setData(data);
            vBarChart.animateY(0);
        } else {
            setLegend(lLabels);
            hBarChart.setData(data);
            hBarChart.animateX(0);
        }
    }

    /*
    *   This method configures the labels displayed on the legend for every flow and return a Legend.
    */

    private Legend setLegend(List<String> labels) {
        if (orientation.equals("V"))
            l = vBarChart.getLegend();
        else
            l = hBarChart.getLegend();
        l.setPosition(Legend.LegendPosition.BELOW_CHART_LEFT);
        l.setFormSize(8f);
        l.setXEntrySpace(4f);
        l.setLabels(labels);
        l.getLegendLabels();
        return l;
    }

}
