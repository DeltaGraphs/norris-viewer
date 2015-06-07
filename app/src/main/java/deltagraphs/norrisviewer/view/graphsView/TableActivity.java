package deltagraphs.norrisviewer.view.graphsView;

import android.content.Context;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridLayout;
import android.widget.GridView;
import android.widget.TableLayout;
import android.widget.TextView;

import deltagraphs.norrisviewer.R;
import deltagraphs.norrisviewer.model.flowModel.FlowModel;
import deltagraphs.norrisviewer.model.flowModel.TableFlow;

import com.inqbarna.tablefixheaders.TableFixHeaders;
import com.inqbarna.tablefixheaders.adapters.BaseTableAdapter;

import java.util.ArrayList;
import java.util.List;

public class TableActivity extends ActionBarActivity implements TableView {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_table);
        setContentView(R.layout.table);

        //TableLayout tableView = new TableLayout(this);
        //GridLayout grid = new GridLayout(this);

        TableFixHeaders tableFixHeaders = (TableFixHeaders) findViewById(R.id.table);
        BaseTableAdapter baseTableAdapter = new FamilyNexusAdapter(this);
        tableFixHeaders.setAdapter(baseTableAdapter);

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
    public void setAppearance(String appearance) {

    }

    @Override
    public void setAddRowOn(String addRowOn) {

    }

    @Override
    public void setMaxItemsDisplayedPerPage(int maxItemsPerPage) {

    }

    @Override
    public void setSortable(Boolean Sortable) {

    }

    @Override
    public void setSortByCol(String sortingColumn) {

    }

    @Override
    public void setSortOrder(String sortOrder) {

    }

    @Override
    public void setBorderWidth(int borderWidth) {

    }

    @Override
    public void setBorderColour(String borderColour) {

    }

    @Override
    public void setHeader(int index, String value, String textColour, String bgColour) {

    }

    @Override
    public void setRowEven(int index, String textColour, String bgColour) {

    }

    @Override
    public void setRowOdd(int index, String textColour, String bgColour) {

    }


    @Override
    public void setData(ArrayList<FlowModel> flowList, int numOfColumns, String signal) {
        for (int i = 0; i < flowList.size(); i++) {
            flowList.get(i).getFlowId();
            flowList.get(i).getFlowName();
            TableFlow tableFlow = (TableFlow) flowList.get(i);
            tableFlow.getMaxItems();
            for (int j = 0; j < tableFlow.getRecordSize(); j++) {
                tableFlow.getRecordId(j);
                for (int indexCol = 0; indexCol < numOfColumns; indexCol++) {
                    tableFlow.getCellBackgroundColour(j, indexCol);
                    tableFlow.getCellData(j, indexCol);
                    tableFlow.getCellTextColour(j, indexCol);
                }
            }
        }
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

        private Nexus(String name, String company, String version, String api, String storage, String inches, String ram) {
            data = new String[]{
                    name,
                    company,
                    version,
                    api,
                    storage,
                    inches,
                    ram};
        }
    }

    public class FamilyNexusAdapter extends BaseTableAdapter {

        private final NexusTypes familys[];
        private final String headers[] = {
                "Name",
                "Company",
                "Version",
                "API",
                "Storage",
                "Size",
                "RAM",
        };

        private final int[] widths = {
                120,
                100,
                140,
                60,
                70,
                60,
                60,
        };
        private final float density;

        public FamilyNexusAdapter(Context context) {
            familys = new NexusTypes[]{
                    new NexusTypes("Mobiles"),
                    new NexusTypes("Tablets"),
                    new NexusTypes("Others"),
            };

            density = context.getResources().getDisplayMetrics().density;

            familys[0].list.add(new Nexus("Nexus One", "HTC", "Gingerbread", "10", "512 MB", "3.7\"", "512 MB"));
            familys[0].list.add(new Nexus("Nexus S", "Samsung", "Gingerbread", "10", "16 GB", "4\"", "512 MB"));
            familys[0].list.add(new Nexus("Galaxy Nexus (16 GB)", "Samsung", "Ice cream Sandwich", "15", "16 GB", "4.65\"", "1 GB"));
            familys[0].list.add(new Nexus("Galaxy Nexus (32 GB)", "Samsung", "Ice cream Sandwich", "15", "32 GB", "4.65\"", "1 GB"));
            familys[0].list.add(new Nexus("Nexus 4 (8 GB)", "LG", "Jelly Bean", "17", "8 GB", "4.7\"", "2 GB"));
            familys[0].list.add(new Nexus("Nexus 4 (16 GB)", "LG", "Jelly Bean", "17", "16 GB", "4.7\"", "2 GB"));
            familys[1].list.add(new Nexus("Nexus 7 (16 GB)", "Asus", "Jelly Bean", "16", "16 GB", "7\"", "1 GB"));
            familys[1].list.add(new Nexus("Nexus 7 (32 GB)", "Asus", "Jelly Bean", "16", "32 GB", "7\"", "1 GB"));
            familys[1].list.add(new Nexus("Nexus 10 (16 GB)", "Samsung", "Jelly Bean", "17", "16 GB", "10\"", "2 GB"));
            familys[1].list.add(new Nexus("Nexus 10 (32 GB)", "Samsung", "Jelly Bean", "17", "32 GB", "10\"", "2 GB"));
            familys[2].list.add(new Nexus("Nexus Q", "--", "Honeycomb", "13", "--", "--", "--"));
        }

        @Override
        public int getRowCount() {
            return 14;
        }

        @Override
        public int getColumnCount() {
            return 6;
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
                string = getFamily(row).name;
            } else {
                string = "";
            }
            ((TextView) convertView.findViewById(android.R.id.text1)).setText(string);
            return convertView;
        }

        @Override
        public int getWidth(int column) {
            return Math.round(widths[column + 1] * density);
        }

        @Override
        public int getHeight(int row) {
            final int height;
            if (row == -1) {
                height = 35;
            } else if (isFamily(row)) {
                height = 25;
            } else {
                height = 45;
            }
            return Math.round(height * density);
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
                row -= familys[family].size() + 1;
                family++;
            }
            return row == 0;
        }

        private NexusTypes getFamily(int row) {
            int family = 0;
            while (row >= 0) {
                row -= familys[family].size() + 1;
                family++;
            }
            return familys[family - 1];
        }

        private Nexus getDevice(int row) {
            int family = 0;
            while (row >= 0) {
                row -= familys[family].size() + 1;
                family++;
            }
            family--;
            return familys[family].get(row + familys[family].size());
        }

        @Override
        public int getViewTypeCount() {
            return 5;
        }
    }


}
