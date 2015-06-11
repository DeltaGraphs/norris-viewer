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
    String headers[];
    int rows = 0;

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
        baseTableAdapter = new FamilyNexusAdapter(this);
        setContentView(R.layout.table);
        tableFixHeaders = (TableFixHeaders) findViewById(R.id.table);
        tablePresenter = new TablePresenterImpl(this, sourceURL);
    }

    @Override
    public void onResume() {
        tablePresenter = new TablePresenterImpl(this, sourceURL);
        super.onResume();
    }

    @Override
    public void onStop() {
        tablePresenter.destroyConnection();
        super.onStop();
    }

    @Override
    public void onPause() {
        tablePresenter.destroyConnection();
        super.onPause();
    }

    @Override
    public void onRestart() {
        //mapChartPresenter.startConnection();
        super.onRestart();
    }

    @Override
    public void onDestroy() {
        tablePresenter.destroyConnection();
        super.onDestroy();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_table, menu);
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
        int col = 0;
        for (col = 0; (!(headers[col].equals(sortingColumn)) && (col < headers.length)); col++) ;
        //while(!(headers[col].equals(sortingColumn)))
        if (col >= headers.length)
            col = -1;
        this.sortingColumn = col;
    }

    @Override
    public void setSortOrder(String sortOrder) {
        order = sortOrder;
    }

    @Override
    public void setHeaders(String[] headers) {
        this.headers = headers;
    }

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
            ((FamilyNexusAdapter) baseTableAdapter).families[0].list.add(new Nexus(values));

        }
        rows = table.size();
    }

    @Override
    public void setData(ArrayList<FlowModel> flowList, int numOfColumns) {
        rows = 0;
        baseTableAdapter = new FamilyNexusAdapter(this);
        if (sortingColumn == -1) {
            for (int i = 0; i < flowList.size(); i++) {
                TableFlow tableFlow = (TableFlow) flowList.get(i);
                for (int j = 0; j < tableFlow.getRecordSize(); j++) {
                    String[] values = new String[numOfColumns];
                    for (int indexCol = 0; indexCol < numOfColumns; indexCol++) {
                        values[indexCol] = tableFlow.getCellData(j, indexCol);
                    }
                    ((FamilyNexusAdapter) baseTableAdapter).families[0].list.add(new Nexus(values));
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


    private class NexusTypes {
        private final String name;
        private final List<Nexus> list;

        NexusTypes(String name) {
            this.name = name;
            list = new ArrayList<Nexus>();
        }

        public int size() {
            return list.size();
        }

        public Nexus get(int i) {
            return list.get(i);
        }
    }

    private class Nexus {
        private final String[] data;

        private Nexus(String[] values) {
            data = values;
        }

        /*private Nexus(String name, String company, String version, String api, String storage, String inches, String ram) {
            data = new String[]{
                    name,
                    company,
                    version,
                    api,
                    storage,
                    inches,
                    ram};
        }*/
    }


    public class FamilyNexusAdapter extends BaseTableAdapter {

        NexusTypes families[];

        private final int[] widths = {
                80
        };
        private final float density;

        public FamilyNexusAdapter(Context context) {
            families = new NexusTypes[]{
                    new NexusTypes("")
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

        private NexusTypes getFamily(int row) {
            int family = 0;
            while (row >= 0) {
                row -= families[family].size() + 1;
                family++;
            }
            return families[family - 1];
        }

        private Nexus getDevice(int row) {
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
