package deltagraphs.norrisviewer.view.graphsView;

import android.graphics.Typeface;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.WindowManager;
import android.widget.SeekBar;
import android.widget.TextView;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.HorizontalBarChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;
import com.github.mikephil.charting.utils.Highlight;
import com.github.mikephil.charting.utils.ValueFormatter;

import java.util.ArrayList;

import deltagraphs.norrisviewer.R;
import deltagraphs.norrisviewer.model.flowModel.BarChartFlow;
import deltagraphs.norrisviewer.model.flowModel.FlowModel;
import deltagraphs.norrisviewer.presenter.graphsPresenter.BarChartPresenter;
import deltagraphs.norrisviewer.presenter.graphsPresenter.BarChartPresenterImpl;

public class HorizontalBarChartActivity extends ActionBarActivity implements BarChartView,SeekBar.OnSeekBarChangeListener, OnChartValueSelectedListener {

    protected HorizontalBarChart hBarChart;
    protected BarChart vBarChart;
    private SeekBar mSeekBarX, mSeekBarY;
    private TextView tvX, tvY;
    private BarChartPresenter barChartPresenter;
    private String sourceTitle;
    private String sourceURL;

    private String[] mMonths = {"Gen","Feb","Mar","Apr","Mag","Giu","Lug","Ago","Set","Ott","Nov","Dic"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_horizontal_bar_chart);
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            sourceURL = extras.getString("EXTRA_SOURCE_URL");
            sourceTitle = extras.getString("EXTRA_SOURCE_TITLE");
        }
        setTitle(sourceTitle);
        barChartPresenter = new BarChartPresenterImpl(this, sourceURL);



        // hBarChart.setDrawLegend(false);
    }


    private void initializeHorizontalBarChart(){
        tvX = (TextView) findViewById(R.id.tvXMax);
        tvY = (TextView) findViewById(R.id.tvYMax);

        mSeekBarX = (SeekBar) findViewById(R.id.seekBar1);
        mSeekBarY = (SeekBar) findViewById(R.id.seekBar2);

        hBarChart = (HorizontalBarChart) findViewById(R.id.chart1);
        hBarChart.setOnChartValueSelectedListener(this);
        // hBarChart.setHighlightEnabled(false);

        hBarChart.setDrawBarShadow(false);

        hBarChart.setDrawValueAboveBar(true);

        hBarChart.setDescription("");

        // if more than 60 entries are displayed in the chart, no values will be
        // drawn
        hBarChart.setMaxVisibleValueCount(60);

        // scaling can now only be done on x- and y-axis separately
        hBarChart.setPinchZoom(false);

        // draw shadows for each bar that show the maximum value
        // hBarChart.setDrawBarShadow(true);

        // hBarChart.setDrawXLabels(false);

        hBarChart.setDrawGridBackground(false);

        // hBarChart.setDrawYLabels(false);

        XAxis xl = hBarChart.getXAxis();
        xl.setPosition(XAxis.XAxisPosition.BOTTOM);
        xl.setDrawAxisLine(true);
        xl.setDrawGridLines(true);
        xl.setGridLineWidth(0.3f);

        YAxis yl = hBarChart.getAxisLeft();
        yl.setDrawAxisLine(true);
        yl.setDrawGridLines(true);
        yl.setGridLineWidth(0.3f);
//        yl.setInverted(true);

        YAxis yr = hBarChart.getAxisRight();
        yr.setDrawAxisLine(true);
        yr.setDrawGridLines(false);
//        yr.setInverted(true);

        setData(12, 50);
        hBarChart.animateY(2500);

        // setting data
        mSeekBarY.setProgress(50);
        mSeekBarX.setProgress(12);

        mSeekBarY.setOnSeekBarChangeListener(this);
        mSeekBarX.setOnSeekBarChangeListener(this);

        Legend l = hBarChart.getLegend();
        l.setPosition(Legend.LegendPosition.BELOW_CHART_LEFT);
        l.setFormSize(8f);
        l.setXEntrySpace(4f);
    }

    private void initializeBarChart(){
        tvX = (TextView) findViewById(R.id.tvXMax);
        tvY = (TextView) findViewById(R.id.tvYMax);

        mSeekBarX = (SeekBar) findViewById(R.id.seekBar1);
        mSeekBarY = (SeekBar) findViewById(R.id.seekBar2);

        vBarChart = (HorizontalBarChart) findViewById(R.id.chart1);
        vBarChart.setOnChartValueSelectedListener(this);
        // vBarChart.setHighlightEnabled(false);

        vBarChart.setDrawBarShadow(false);

        vBarChart.setDrawValueAboveBar(true);

        vBarChart.setDescription("");

        // if more than 60 entries are displayed in the chart, no values will be
        // drawn
        vBarChart.setMaxVisibleValueCount(60);

        // scaling can now only be done on x- and y-axis separately
        vBarChart.setPinchZoom(false);

        // draw shadows for each bar that show the maximum value
        // vBarChart.setDrawBarShadow(true);

        // vBarChart.setDrawXLabels(false);

        vBarChart.setDrawGridBackground(false);

        // vBarChart.setDrawYLabels(false);

        XAxis xl = vBarChart.getXAxis();
        xl.setPosition(XAxis.XAxisPosition.BOTTOM);
        xl.setDrawAxisLine(true);
        xl.setDrawGridLines(true);
        xl.setGridLineWidth(0.3f);

        YAxis yl = vBarChart.getAxisLeft();
        yl.setDrawAxisLine(true);
        yl.setDrawGridLines(true);
        yl.setGridLineWidth(0.3f);
//        yl.setInverted(true);

        YAxis yr = vBarChart.getAxisRight();
        yr.setDrawAxisLine(true);
        yr.setDrawGridLines(false);
//        yr.setInverted(true);

        setData(12, 50);
        vBarChart.animateY(2500);

        // setting data
        mSeekBarY.setProgress(50);
        mSeekBarX.setProgress(12);

        mSeekBarY.setOnSeekBarChangeListener(this);
        mSeekBarX.setOnSeekBarChangeListener(this);

        Legend l = vBarChart.getLegend();
        l.setPosition(Legend.LegendPosition.BELOW_CHART_LEFT);
        l.setFormSize(8f);
        l.setXEntrySpace(4f);
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

    @Override
    public void setAxis(char axisXorY, String name, String appearance, float maxIndex, float minIndex, int ticks, int scale) {

    }

    @Override
    public void setHeaders(ArrayList<String> headers) {

    }

    @Override
    public void setBackground(String bg) {

    }

    @Override
    public void setBarOrientation(String orientation) {
        if(orientation=="V")
            initializeBarChart();
        else
            initializeHorizontalBarChart();
    }

    @Override
    public void setSortable(Boolean choise) {

    }

    @Override
    public void setGrid(Boolean grid) {

    }

    @Override
    public void setLegendOnPoint(Boolean legend) {

    }

    @Override
    public void setData(ArrayList<FlowModel> flowList, String signal) {
        for(int i=0; i<flowList.size(); i++){
            flowList.get(i).getFlowId();
            flowList.get(i).getFlowName();
            BarChartFlow barChartFlow = (BarChartFlow) flowList.get(i);
            barChartFlow.getFlowColour();
            for(int j =0; j< barChartFlow.getRecordSize(); j++) {
                barChartFlow.getRecordId(j);
                barChartFlow.getRecordIndex(j);
                barChartFlow.getRecordValue(j);
            }
        }
    }

    @Override
    public void onValueSelected(Entry e, int dataSetIndex, Highlight h) {

    }

    @Override
    public void onNothingSelected() {

    }

    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {

    }

    private void setData(int count, float range) {

        ArrayList<String> xVals = new ArrayList<String>();
        for (int i = 0; i < count; i++) {
            xVals.add(mMonths[i % 12]);
        }

        ArrayList<BarEntry> yVals1 = new ArrayList<BarEntry>();

        for (int i = 0; i < count; i++) {
            float mult = (range + 1);
            float val = (float) (Math.random() * mult);
            yVals1.add(new BarEntry(val, i));
        }

        BarDataSet set1 = new BarDataSet(yVals1, "DataSet");
        set1.setBarSpacePercent(35f);

        ArrayList<BarDataSet> dataSets = new ArrayList<BarDataSet>();
        dataSets.add(set1);

        BarData data = new BarData(xVals, dataSets);
        data.setValueTextSize(10f);

        hBarChart.setData(data);
    }
}
