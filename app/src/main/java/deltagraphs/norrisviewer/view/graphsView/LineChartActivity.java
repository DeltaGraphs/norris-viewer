package deltagraphs.norrisviewer.view.graphsView;

import java.net.URISyntaxException;

import android.content.res.Configuration;
import android.util.Log;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;

import lecho.lib.hellocharts.model.Viewport;

import com.github.nkzawa.emitter.Emitter;
import com.github.nkzawa.socketio.client.IO;
import com.github.nkzawa.socketio.client.Socket;
import lecho.lib.hellocharts.view.LineChartView;
import lecho.lib.hellocharts.view.PreviewLineChartView;
import lecho.lib.hellocharts.model.LineChartData;
import org.json.JSONException;
import org.json.JSONObject;
import deltagraphs.norrisviewer.presenter.graphsPresenter.*;

/*
 * Name : LineChartActivity.java
 * Module : deltagraphs.norrisviewer.view.graphsView
 * Location : norrisviewer\view\graphsView
 *
 * History :

 * Version Date Programmer Description
 * ===============================================================
 *
 * 0.1.0 2015-05-17 Enrico Savoca Codifica di tutti gli attributi e i metodi
 *
 * 0.0.1 2015-05-17 Enrico Savoca Creazione file
 *
 * ===============================================================
 *
 */

public class LineChartActivity extends ActionBarActivity implements LineChartView {

    private LineChartPresenter lineChartPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.line_chart);
        lineChartPresenter = new LineChartPresenterImpl(this);
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
}
