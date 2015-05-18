package deltagraphs.norrisviewer.view.graphsView;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import deltagraphs.norrisviewer.R;
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

    private LineChartPresenter lineChartPresenter;

    //line chart con view finder

    private LineChartView chart;
    private PreviewLineChartView previewChart;
    private LineChartData lineChartData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        String url = "";
        lineChartPresenter = new LineChartPresenterImpl(this, url);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.line_chart);
        chart = (LineChartView) findViewById(R.id.chart);
        previewChart = (PreviewLineChartView) findViewById(R.id.chart_preview);
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

    }
}
