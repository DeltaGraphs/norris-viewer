package deltagraphs.norrisviewer.view.graphsView;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.TextView;

import com.inqbarna.tablefixheaders.TableFixHeaders;
import com.inqbarna.tablefixheaders.adapters.BaseTableAdapter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import deltagraphs.norrisviewer.R;
import deltagraphs.norrisviewer.model.flowModel.FlowModel;
import deltagraphs.norrisviewer.model.flowModel.TableFlow;
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

    private TableFixHeaders tableFixHeaders;
    private BaseTableAdapter baseTableAdapter;
    private String sourceURL;
    private String sourceTitle;
    private String order = null;
    private int sortingColumn = -1;
    private TablePresenter tablePresenter;
    private String headers[];
    private int rows = 0;

    /*
    This method is called on the creation of the activity
    and its job is to get information from
    previous activity to get title and URL.
     */
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
        baseTableAdapter = new RecordListAdapter(this);
        setContentView(R.layout.table);
        tableFixHeaders = (TableFixHeaders) findViewById(R.id.table);
        tablePresenter = new TablePresenterImpl(this, sourceURL);
    }

    /*
    This method is called when the activity is show back form a pause state.
     */
    @Override
    public void onResume() {
        tablePresenter = new TablePresenterImpl(this, sourceURL);
        super.onResume();
    }

    /*
    The following method is called when this activity
    is stopped and put on the background and
    it's in charge of destroying the socket connection
     */
    @Override
    public void onStop() {
        tablePresenter.destroyConnection();
        super.onStop();
    }

    /*
    The following method is called when this activity
    is hidden to the user and put on the background and
    it's in charge of destroying the socket connection
     */
    @Override
    public void onPause() {
        tablePresenter.destroyConnection();
        super.onPause();
    }

    /*
   The following method is called when the activity
   is restarted
    */
    @Override
    public void onRestart() {
        //mapChartPresenter.startConnection();
        super.onRestart();
    }

    /*
    This method is called when the activity is destroyed and provides
    to destroy the connection with the socket
    */
    @Override
    public void onDestroy() {
        tablePresenter.destroyConnection();
        super.onDestroy();
    }

    /* This method generate a custom menu for the
    action bar on the top of the activity  */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_table, menu);
        return true;
    }

    /* This method menage the actions user can perform
    on the menu */
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
        for (col = 0; (!(headers[col].equals(sortingColumn)) && (col < headers.length)); col++) ;
        //while(!(headers[col].equals(sortingColumn)))
        if (col >= headers.length)
            col = -1;
        this.sortingColumn = col;
    }

    // set if the table must be sorted in ascendent or descendent order
    @Override
    public void setSortOrder(String sortOrder) {
        order = sortOrder;
    }

    // set the headers of the table
    @Override
    public void setHeaders(String[] headers) {
        this.headers = headers;
    }


    // the following method is used to sort the table. It uses java library's methods and at the end of it
    // the list of records is defined on the ordered table.
    private void sort(ArrayList<FlowModel> flowList, int numOfColumns) {
        ArrayList<ArrayList<String>> table = new ArrayList<ArrayList<String>>();
        for (int i = 0; i < flowList.size(); i++) {
            TableFlow tableFlow = (TableFlow) flowList.get(i);
            for (int j = 0; j < tableFlow.getRecordSize(); j++) {
                tableFlow.getRecordId(j);
                ArrayList<String> row = new ArrayList<String>();
                for (int indexCol = 0; indexCol < numOfColumns; indexCol++) {
                    row.add(tableFlow.getCellData(j, indexCol));
                }
                table.add(row);
            }
        }

        Collections.sort(table, new Comparator<ArrayList<String>>() {
            @Override
            public int compare(ArrayList<String> a, ArrayList<String> b) {
                return a.get(sortingColumn).compareTo(b.get(sortingColumn));
            }
        });

        for (int j = 0; j < table.size(); j++) {
            String[] values = new String[numOfColumns];
            for (int i = 0; i < numOfColumns; i++) {
                values[i] = table.get(j).get(i);
            }
            ((RecordListAdapter) baseTableAdapter).families[0].list.add(new Record(values));

        }
        rows = table.size();
    }

    //set the data in the table. It is extracted from the table model
    @Override
    public void setData(ArrayList<FlowModel> flowList, int numOfColumns) {
        rows = 0;
        baseTableAdapter = new RecordListAdapter(this);
        if (sortingColumn == -1) {
            for (int i = 0; i < flowList.size(); i++) {
                TableFlow tableFlow = (TableFlow) flowList.get(i);
                for (int j = 0; j < tableFlow.getRecordSize(); j++) {
                    String[] values = new String[numOfColumns];
                    for (int indexCol = 0; indexCol < numOfColumns; indexCol++) {
                        values[indexCol] = tableFlow.getCellData(j, indexCol);
                    }
                    ((RecordListAdapter) baseTableAdapter).families[0].list.add(new Record(values));
                    rows++;
                }
            }
        } else {
            sort(flowList, numOfColumns);
        }
        tableFixHeaders.setAdapter(baseTableAdapter);
        baseTableAdapter.notifyDataSetChanged();
        tableFixHeaders.animate();
    }

    //classes added from the table library

    private class RecordList {
        private final String name;
        private final List<Record> list;

        RecordList(String name) {
            this.name = name;
            list = new ArrayList<Record>();
        }

        public int size() {
            return list.size();
        }

        public Record get(int i) {
            return list.get(i);
        }
    }

    private class Record {
        private final String[] data;

        private Record(String[] values) {
            data = values;
        }

    }


    public class RecordListAdapter extends BaseTableAdapter {

        RecordList families[];

        private final int[] widths = {
                80
        };
        private final float density;

        public RecordListAdapter(Context context) {
            families = new RecordList[]{
                    new RecordList("")
            };

            density = context.getResources().getDisplayMetrics().density;
        }

        @Override
        public int getRowCount() {
            return rows;
        }

        @Override
        public int getColumnCount() {
            return headers.length - 1;
        }

        @Override
        public View getView(int row, int column, View convertView, ViewGroup parent) {
            final View view;
            switch (getItemViewType(row, column)) {
                case 0:
                    view = getFirstHeader(row, column, convertView, parent);
                    break;
                case 1:
                    view = getHeader(row, column, convertView, parent);
                    break;
                case 2:
                    view = getFirstBody(row, column, convertView, parent);
                    break;
                case 3:
                    view = getBody(row, column, convertView, parent);
                    break;
                case 4:
                    view = getFamilyView(row, column, convertView, parent);
                    break;
                default:
                    throw new RuntimeException("wtf?");
            }
            return view;
        }

        private View getFirstHeader(int row, int column, View convertView, ViewGroup parent) {
            if (convertView == null) {
                convertView = getLayoutInflater().inflate(R.layout.item_table_header_first, parent, false);
            }
            ((TextView) convertView.findViewById(android.R.id.text1)).setText(headers[0]);
            return convertView;
        }

        private View getHeader(int row, int column, View convertView, ViewGroup parent) {
            if (convertView == null) {
                convertView = getLayoutInflater().inflate(R.layout.item_table_header, parent, false);
            }
            ((TextView) convertView.findViewById(android.R.id.text1)).setText(headers[column + 1]);
            return convertView;
        }

        private View getFirstBody(int row, int column, View convertView, ViewGroup parent) {
            if (convertView == null) {
                convertView = getLayoutInflater().inflate(R.layout.item_table_first, parent, false);
            }
            convertView.setBackgroundResource(row % 2 == 0 ? R.drawable.bg_table_color1 : R.drawable.bg_table_color2);
            ((TextView) convertView.findViewById(android.R.id.text1)).setText(getDevice(row).data[column + 1]);
            return convertView;
        }

        private View getBody(int row, int column, View convertView, ViewGroup parent) {
            if (convertView == null) {
                convertView = getLayoutInflater().inflate(R.layout.item_table, parent, false);
            }
            convertView.setBackgroundResource(row % 2 == 0 ? R.drawable.bg_table_color1 : R.drawable.bg_table_color2);
            ((TextView) convertView.findViewById(android.R.id.text1)).setText(getDevice(row).data[column + 1]);
            return convertView;
        }

        private View getFamilyView(int row, int column, View convertView, ViewGroup parent) {
            if (convertView == null) {
                convertView = getLayoutInflater().inflate(R.layout.item_table_family, parent, false);
            }
            final String string;
            if (column == -1) {
                string = getFamily(0).name;
            } else {
                string = "";
            }
            ((TextView) convertView.findViewById(android.R.id.text1)).setText(string);
            return convertView;
        }

        @Override
        public int getWidth(int column) {
            return Math.round(80 * density);
        }

        @Override
        public int getHeight(int row) {
            /*final int height;
            if (row == -1) {
                height = 35;
            } else if (isFamily(row)) {
                height = 25;
            } else {
                height = 45;
            }*/
            return Math.round(35 * density);
        }

        @Override
        public int getItemViewType(int row, int column) {
            final int itemViewType;
            if (row == -1 && column == -1) {
                itemViewType = 0;
            } else if (row == -1) {
                itemViewType = 1;
            } else if (isFamily(row)) {
                itemViewType = 4;
            } else if (column == -1) {
                itemViewType = 2;
            } else {
                itemViewType = 3;
            }
            return itemViewType;
        }

        private boolean isFamily(int row) {
            int family = 0;
            while (row > 0) {
                row -= families[family].size() + 1;
                family++;
            }
            return row == 0;
        }

        private RecordList getFamily(int row) {
            int family = 0;
            while (row >= 0) {
                row -= families[family].size() + 1;
                family++;
            }
            return families[family - 1];
        }

        private Record getDevice(int row) {
            int family = 0;
            while (row >= 0) {
                row -= families[family].size() + 1;
                family++;
            }
            family--;
            return families[family].get(row + families[family].size());
        }

        @Override
        public int getViewTypeCount() {
            return 5;
        }
    }


}
