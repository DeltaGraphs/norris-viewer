package deltagraphs.norrisviewer.view.graphsView;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Gravity;
import android.view.WindowManager;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import java.util.ArrayList;

import deltagraphs.norrisviewer.R;
import deltagraphs.norrisviewer.model.flowModel.FlowModel;
import deltagraphs.norrisviewer.model.flowModel.TableFlow;
import deltagraphs.norrisviewer.model.graphsModel.Table;
import deltagraphs.norrisviewer.presenter.graphsPresenter.TablePresenter;
import deltagraphs.norrisviewer.presenter.graphsPresenter.TablePresenterImpl;

/*
* Name : TableActivity.java
* Module : norrisviewer::view::graphsView
* Location : norrisviewer\view\graphsView
*
* History :

* Version Date Programmer Description
* ===============================================================
*
* 1.0.0 2015-06-29 Matteo Furlan Approve
*
* 0.6.0 2015-06-27 Enrico Savoca Verify
*
* 0.5.1 2015-06-15 Davide Trivellato Fixes to table settings
*
* 0.5.0 2015-06-15 Davide Trivellato Several changes to setData(ArrayList<FlowModel> flowList, int numOfColumns)
*
* 0.4.0 2015-06-14 Davide Trivellato Several changes to sort(ArrayList<FlowModel> flowList, int numOfColumns)
*
* 0.3.0 2015-06-13 Davide Trivellato Add sort(ArrayList<FlowModel> flowList, int numOfColumns)
*
* 0.2.0 2015-06-12 Davide Trivellato Update setData(ArrayList<FlowModel> flowList, int numOfColumns)
*
* 0.1.1 2015-06-11 Davide Trivellato Update setData(ArrayList<FlowModel> flowList) to setData(ArrayList<FlowModel> flowList, int numOfColumns)
*
* 0.1.0 2015-06-11 Davide Trivellato Coding of methods and attributes
*
* 0.0.1 2015-06-09 Davide Trivellato Creation of the file
*
* ===============================================================
*
*/

public class TableActivity extends ActionBarActivity implements TableView {

    private String sourceURL;
    private String sourceTitle;
    private TablePresenter tablePresenter;
    private String headers[];

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
        setTitle(sourceTitle);
        setContentView(R.layout.activity_table);
    }

    //manage the resuming action from another activity
    @Override
    public void onResume() {
        super.onResume();
        tablePresenter = new TablePresenterImpl(this, sourceURL);
        tableRowParams.weight = 1;
    }

    //manage the onPause event
    @Override
    public void onPause() {
        super.onPause();
        tablePresenter.stopConnection();
    }

    //manage the onDestroy event
    @Override
    public void onDestroy() {
        tablePresenter.stopConnection();
        tablePresenter.stopListening();
        super.onDestroy();
    }

    // set the headers of the table
    @Override
    public void setHeaders(String[] headers) {
        this.headers = headers;
        if (this.headers.length == 0) {
            this.headers = new String[1];
            this.headers[0] = "La tabella e' vuota";
        }
    }

    /**
     * This method provides the ability to view or not border lines of the cells of the table.
     */
    @Override
    public void setBorder(boolean hBorder, boolean vBorder) {
        int xBorder = 0;
        int yBorder = 0;
        if (hBorder == true)
            xBorder = 1;
        if (vBorder == true)
            yBorder = 1;
        tableRowParams.setMargins(yBorder, xBorder, yBorder, xBorder);
    }

    //set the data in the table. It is extracted from the table model
    @Override
    public void setData(ArrayList<FlowModel> flowList, int numOfColumns, Table tableInstance) {
        TableLayout tableLayout = (TableLayout) findViewById(R.id.table);
        tableLayout.removeAllViews();
        TableLayout.LayoutParams tableLayoutParams = new TableLayout.LayoutParams();
        TableRow headerRow = new TableRow(this);
        for (int j = 0; j < headers.length; j++) {
            TextView textView = new TextView(this);
            textView.setBackgroundColor(Color.LTGRAY);
            textView.setGravity(Gravity.CENTER);
            textView.setText(headers[j]);
            headerRow.addView(textView, tableRowParams);
        }
        tableLayout.addView(headerRow, tableLayoutParams);

        for (int i = 0; i < flowList.size(); i++) {
            TableFlow tableFlow = (TableFlow) flowList.get(i);
            for (int j = 0; j < tableFlow.getRecordSize(); j++) {
                // creation of a new row
                TableRow tableRow = new TableRow(this);

                for (int indexCol = 0; indexCol < numOfColumns; indexCol++) {
                    TextView textView = new TextView(this);
                    if ((indexCol % 2) == 0) {
                        textView.setBackgroundColor(Color.parseColor(tableInstance.getRowEvenBGColour(indexCol)));
                        textView.setTextColor(Color.parseColor(tableInstance.getRowEvenTC(indexCol)));
                    } else {
                        textView.setBackgroundColor(Color.parseColor(tableInstance.getRowOddBGColour(indexCol)));
                        textView.setTextColor(Color.parseColor(tableInstance.getRowOddTC(indexCol)));
                    }
                    String bg =  tableFlow.getCellBGColour(j, indexCol);
                    String tc =  tableFlow.getCellTColour(j, indexCol);
                    if(!bg.equals("null"))
                        textView.setBackgroundColor(Color.parseColor(tableFlow.getCellBGColour(j, indexCol)));
                    if(!tc.equals("null"))
                        textView.setTextColor(Color.parseColor(tableFlow.getCellTColour(j, indexCol)));
                    textView.setGravity(Gravity.CENTER);
                    textView.setText(tableFlow.getCellData(j, indexCol));
                    // add a cell to the new row
                    tableRow.addView(textView, tableRowParams);
                }
                // the new row is added to the table
                tableLayout.addView(tableRow, tableLayoutParams);
            }
        }
    }

}
