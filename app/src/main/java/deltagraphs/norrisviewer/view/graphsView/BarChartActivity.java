package deltagraphs.norrisviewer.view.graphsView;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import deltagraphs.norrisviewer.R;
import deltagraphs.norrisviewer.presenter.graphsPresenter.BarChartPresenter;
import deltagraphs.norrisviewer.presenter.graphsPresenter.BarChartPresenterImpl;
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

    private BarChartPresenter barChartPresenter;

    private ColumnChartView chart;
    private ColumnChartData barChartData;

    private CharSequence sourceTitle;
    private String sourceURL;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            sourceURL = extras.getString("EXTRA_SOURCE_URL");
            sourceTitle = extras.getString("EXTRA_SOURCE_TITLE");
        }
        barChartPresenter = new BarChartPresenterImpl(this, sourceURL);
        setTitle(sourceTitle);
        setContentView(R.layout.bar_chart);
        chart = (ColumnChartView) findViewById(R.id.chart);
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
    }

}
