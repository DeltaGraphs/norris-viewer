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
import java.util.Collections;
import java.util.Comparator;

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
        //tablePresenter = new TablePresenterImpl(this, sourceURL);
        setContentView(R.layout.activity_new_table);
    }

    //manage the resuming action from another activity
    @Override
    public void onResume() {
        super.onResume();
        tablePresenter = new TablePresenterImpl(this, sourceURL);
        tablePresenter.startListening();
        tableRowParams.weight = 1;
    }

    //manage the onStop event
    @Override
    public void onStop() {
        super.onStop();
        tablePresenter.stopListening();
    }

    //manage the onPause event
    @Override
    public void onPause() {
        super.onPause();
    }

    //manage the onRestart event
    @Override
    public void onRestart() {
        super.onRestart();
    }

    //manage the onDestroy event
    @Override
    public void onDestroy() {
        super.onDestroy();
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

    // set the table to be sorted by a defined column
    @Override
    public void setSortByCol(String sortingColumn) {
        int col = 0;
        if (col >= headers.length)
            col = -1;
        else{
            for (col = 0;((col < headers.length)) && (!(headers[col].equals(sortingColumn))); col++) ;
        }
        //while(!(headers[col].equals(sortingColumn)))

        this.sortingColumn = col;
    }

    // set if the table must be sorted in ascendent or descendent order
    @Override
    public void setSortOrder(String sortOrder) {
        order = sortOrder;
    }


    @Override
    public void setHeaders(String[] headers) {
        this.headers = headers;
        if(this.headers.length == 0) {
            this.headers = new String[1];
            this.headers[0] = "La tabella e' vuota";
        }for(int i=0; i<headers.length; i++)
            Log.d("", headers[i]);
    }

    @Override
    public void setData(ArrayList<FlowModel> flowList, int numOfColumns) {
       // if (sortingColumn != -1) {
       //     setSortedTable(flowList, numOfColumns);
       // }else{
            setTable(flowList, numOfColumns);
       // }
    }

    private void setTable(ArrayList<FlowModel> flowList, int numOfColumns) {
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
                    Log.d("", j + " " + indexCol + " " + tableFlow.getCellData(j, indexCol));
                    // 5) add textView to tableRow
                    tableRow.addView(textView, tableRowParams);
                }
                // 6) add tableRow to tableLayout
                tableLayout.addView(tableRow, tableLayoutParams);
            }
        }
    }


    class Value {
        private String data;
        private String background = "FFFFFFFF";
        private String textColour = "00000000";

        // Value constructor.
        // It's used when a new Value is added to the record list
        // The new marker type is initialized with the passed parameters.
        Value(String data, String bg, String tC) {
            this.data = data;
            background = bg;
            textColour = tC;
        }
    }

    // the following method is used to sort the table. It uses java library's methods and at the end of it
    // the list of records is defined on the ordered table.
    private void setSortedTable(ArrayList<FlowModel> flowList, int numOfColumns) {
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


        ArrayList<ArrayList<Value>> table = new ArrayList<ArrayList<Value>>();
        for (int i = 0; i < flowList.size(); i++) {
            TableFlow tableFlow = (TableFlow) flowList.get(i);
            for (int j = 0; j < tableFlow.getRecordSize(); j++) {
                ArrayList<Value> row = new ArrayList<Value>();
                for (int indexCol = 0; indexCol < numOfColumns; indexCol++) {
                    String data = tableFlow.getCellData(j, indexCol);
                    String bg = tableFlow.getCellBGColour(j, indexCol);
                    String tc = tableFlow.getCellTColour(j, indexCol);
                    row.add(new Value(data, bg, tc));
                }
                table.add(row);
            }
        }

        Collections.sort(table, new Comparator<ArrayList<Value>>() {
            @Override
            public int compare(ArrayList<Value> a, ArrayList<Value> b) {
                return a.get(sortingColumn).data.compareTo(b.get(sortingColumn).data);
            }
        });

        int rows = table.size() / headers.length;
        for (int i = 0; i < table.size(); i++) {
            for (int j = 0; j < table.get(i).size(); j++) {

                TableRow tableRow = new TableRow(this);

                for (int indexCol = 0; indexCol < numOfColumns; indexCol++) {//for each column
                    // 4) create textView
                    TextView textView = new TextView(this);

                    textView.setBackgroundColor(Color.parseColor(table.get(i).get(j).background));
                    textView.setTextColor(Color.parseColor(table.get(i).get(j).textColour));

                    textView.setGravity(Gravity.CENTER);
                    textView.setText(table.get(i).get(j).data);

                    // 5) add textView to tableRow
                    tableRow.addView(textView, tableRowParams);
                }
                // 6) add tableRow to tableLayout
                tableLayout.addView(tableRow, tableLayoutParams);
            }
        }
    }

}
