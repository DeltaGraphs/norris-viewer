package deltagraphs.norrisviewer.view.graphsView;

import android.graphics.Color;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.WindowManager;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import java.util.ArrayList;

import deltagraphs.norrisviewer.R;
import deltagraphs.norrisviewer.model.flowModel.FlowModel;
import deltagraphs.norrisviewer.model.flowModel.TableFlow;
import deltagraphs.norrisviewer.presenter.graphsPresenter.TablePresenter;
import deltagraphs.norrisviewer.presenter.graphsPresenter.TablePresenterImpl;

public class NewTableActivity extends ActionBarActivity implements TableView {

    private String sourceURL;
    private String sourceTitle;
    private String order = null;
    private int sortingColumn = -1;
    private TablePresenter tablePresenter;
    private String headers[];
    private int rows = 0;

    TableRow.LayoutParams tableRowParams = new TableRow.LayoutParams();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            sourceURL = extras.getString("EXTRA_SOURCE_URL");
            sourceTitle = extras.getString("EXTRA_SOURCE_TITLE");
        }
        tablePresenter = new TablePresenterImpl(this, sourceURL);
        setContentView(R.layout.activity_new_table);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_new_table, menu);
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
    public void setSortByCol(String sortingColumn) {

    }

    @Override
    public void setSortOrder(String sortOrder) {

    }

    @Override
    public void setHeaders(String[] headers) {
        this.headers = headers;
        if(this.headers.length == 0)
            this.headers[0]="La tabella è vuota";
    }

    @Override
    public void setData(ArrayList<FlowModel> flowList, int numOfColumns) {
            TableLayout tableLayout = (TableLayout) findViewById(R.id.table);
            tableLayout.removeAllViews();


            TableLayout.LayoutParams tableLayoutParams = new TableLayout.LayoutParams();

            TableRow headerRow = new TableRow(this);
            for (int j = 0; j < headers.length; j++) {
                // 4) create textView
                TextView textView = new TextView(this);
                //  textView.setText(String.valueOf(j));
                textView.setBackgroundColor(Color.LTGRAY);
                textView.setGravity(Gravity.CENTER);
                textView.setText(headers[j]);

                // 5) add textView to tableRow
                headerRow.addView(textView, tableRowParams);
            }
            tableLayout.addView(headerRow, tableLayoutParams);

        for (int i = 0; i < flowList.size(); i++) {
            TableFlow tableFlow = (TableFlow) flowList.get(i);
            for (int j = 0; j < tableFlow.getRecordSize(); j++) {

                TableRow tableRow = new TableRow(this);

                for (int indexCol = 0; indexCol < numOfColumns; indexCol++) {//for each column
                    // 4) create textView
                    TextView textView = new TextView(this);

                    textView.setBackgroundColor(Color.parseColor(tableFlow.getCellBGColour(j, indexCol)));
                    Log.d("", tableFlow.getCellTColour(j, indexCol));
                    textView.setTextColor(Color.parseColor(tableFlow.getCellTColour(j, indexCol)));

                    textView.setGravity(Gravity.CENTER);
                    textView.setText(tableFlow.getCellData(j, indexCol));

                    // 5) add textView to tableRow
                    tableRow.addView(textView, tableRowParams);
                }
                // 6) add tableRow to tableLayout
                tableLayout.addView(tableRow, tableLayoutParams);
            }
        }


    }
}
