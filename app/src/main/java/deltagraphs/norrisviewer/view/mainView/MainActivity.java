package deltagraphs.norrisviewer.view.mainView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.FrameLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import deltagraphs.norrisviewer.R;

import java.util.ArrayList;
import java.util.List;

import deltagraphs.norrisviewer.model.pageModel.PageModel;
import deltagraphs.norrisviewer.presenter.SocketManager;
import deltagraphs.norrisviewer.presenter.mainPresenter.MainPresenter;
import deltagraphs.norrisviewer.presenter.mainPresenter.MainPresenterImpl;
import deltagraphs.norrisviewer.view.graphsView.BarChartActivity;
import deltagraphs.norrisviewer.view.graphsView.LineChartActivity;
import deltagraphs.norrisviewer.view.graphsView.MapChartActivity;
import deltagraphs.norrisviewer.view.graphsView.TableActivity;
import deltagraphs.norrisviewer.view.mainView.MainView;
import lecho.lib.hellocharts.view.AbstractChartView;
import lecho.lib.hellocharts.view.ColumnChartView;
import lecho.lib.hellocharts.view.LineChartView;
import lecho.lib.hellocharts.view.PreviewLineChartView;

/*
 * Name : MainActivity.java
 * Module : norrisviewer::view::mainView
 * Location : norrisviewer\view\mainView
 *
 * History :

 * Version Date Programmer Description
 * ===============================================================
 *
 * 0.1.0 2015-05-13 Davide Trivellato Codifica di tutti gli attributi e i metodi
 *
 * 0.0.1 2015-05-13 Davide Trivellato Creazione file
 *
 * ===============================================================
 *
 */

public class MainActivity extends ActionBarActivity implements MainView,PageNavigationFragment.NavigationDrawerCallbacks {

    public static String[] pagesList= {"ciao","balcky","furlan"};
    private String title;
    private MainPresenter presenter;


    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        presenter = new MainPresenterImpl(this);
        showDialog();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
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
            showDialog();
            return true;
        }

        if (id == R.id.action_credits) {
            Context context = getApplicationContext();
            CharSequence text = "Credits to DeltaGraphs 2015";
            int duration = Toast.LENGTH_SHORT;

            Toast toast = Toast.makeText(context, text, duration);
            toast.show();

            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onStart(){
        super.onStart();
    }

    @Override
    public void onRestart(){
        super.onRestart();
    }

    @Override
    public void onPause(){
        super.onPause();
    }

    @Override
    public void onResume(){
        super.onResume();
    }

    @Override
    public void onStop(){
        super.onStop();
    }

    @Override
    public void onDestroy(){
        super.onDestroy();
    }

    @Override
    public void onBackPressed(){
        super.onBackPressed();
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void showDialog(){
        presenter.showDialog(this, presenter);
    }

    public void showGraphs(){

    }

    public void onGraphSelected(){

    }

    public void onSettingsSelected(){

    }

    public PageNavigationFragment getFragment(int id){
        return (PageNavigationFragment) getSupportFragmentManager().findFragmentById(R.id.navigation_drawer);
    }

    public DrawerLayout findDrawer(int id){
        return (DrawerLayout) findViewById(R.id.drawer_layout);
    }

    public FragmentManager getSupportManager(){
        return getSupportFragmentManager();
    }

    public void setMainView(){
        setContentView(R.layout.activity_main);
    }

    @Override
    public void onNavigationDrawerItemSelected(int position) {

    }




    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment implements AdapterView.OnItemClickListener {

        private ListView listView;
        private ChartAdapter adapter;
        private PageModel pageModel;
        private List<ChartDescription> graphsList = new ArrayList<ChartDescription>();


        /**
         * The fragment argument representing the section number for this
         * fragment.
         */
        private static final String ARG_SECTION_NUMBER = "section_number";

        /**
         * Returns a new instance of this fragment for the given section
         * number.
         */


        public static PlaceholderFragment newInstance(int sectionNumber, PageModel p) {
            PlaceholderFragment fragment = new PlaceholderFragment();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            fragment.setArguments(args);
            fragment.pageModel = p;
            return fragment;
        }

        public PlaceholderFragment() {}

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_main, container, false);
            listView = (ListView) rootView.findViewById(android.R.id.list);
            graphsList = generateDescriptions();
            adapter = new ChartAdapter(getActivity(), 0, graphsList);
            listView.setAdapter(adapter);
            listView.setOnItemClickListener(this);
            return rootView;
        }
        /*
                @Override
                public void onAttach(Activity activity) {
                    super.onAttach(activity);
                    ((MainActivity) activity).onSectionAttached(
                            getArguments().getInt(ARG_SECTION_NUMBER));
                }
        */
        @Override
        public void onItemClick(AdapterView<?> adapter, View view, int position, long id) {
            Intent intent;
            String graphType = graphsList.get(position).getType();
            switch (graphType) {
                case "lineChart":
                    intent = new Intent(getActivity(), LineChartActivity.class);
                    intent.putExtra("EXTRA_SOURCE_URL", graphsList.get(position).getUrl());
                    intent.putExtra("EXTRA_SOURCE_TITLE", graphsList.get(position).getName());
                    startActivity(intent);
                    break;

                case "BarChart":
                    intent = new Intent(getActivity(), BarChartActivity.class);
                    intent.putExtra("EXTRA_SOURCE_URL", graphsList.get(position).getUrl());
                    startActivity(intent);
                    break;

                case "MapChart":
                    intent = new Intent(getActivity(), MapChartActivity.class);
                    intent.putExtra("EXTRA_SOURCE_URL", "http://norris-nrti-dev.herokuapp.com/page1/map1");
                    intent.putExtra("EXTRA_SOURCE_TITLE", graphsList.get(position).getName());
                    //intent.putExtra("EXTRA_SOURCE_URL", graphsList.get(position).getUrl());
                    startActivity(intent);
                    break;
                case "Table":
                    intent = new Intent(getActivity(), TableActivity.class);
                    intent.putExtra("EXTRA_SOURCE_URL", graphsList.get(position).getUrl());
                    startActivity(intent);
                    break;
                default:
                    break;
            }
        }


        // this is generated after socket connection with the graphs' list

        private List<ChartDescription> generateDescriptions() {
            List<ChartDescription> list = new ArrayList<ChartDescription>();
/*
            //number of page needed
            int PAGE = 1;

            int size = pageModel.getItemListSize(PAGE);
            ArrayList<PageItem> itemList = pageModel.getItemList(PAGE);
            for(int i=0; i<size; i++) {
                String itemName = itemList.get(i).getName();
                String itemType = itemList.get(i).getType();
                String itemUrl = itemList.get(i).getUrl();
                list.add(new ChartDescription(itemName, itemType, itemUrl, ChartType.COLUMN_CHART));
            }
            */

            list.add(new ChartDescription("BalbyChartBar", "bablgbn", "asdasd", ChartType.COLUMN_CHART));
            list.add(new ChartDescription("linebalby", "", "", ChartType.LINE_CHART));
            list.add(new ChartDescription("MappaDemmedda", "ciao", "Colpa di ross", ChartType.MAP_CHART));
            list.add(new ChartDescription("tabbbbella", "tabble", "url", ChartType.TABLE));


            return list;
        }

    }



    public static class ChartAdapter extends ArrayAdapter<ChartDescription> {

        public ChartAdapter(Context context, int resource, List<ChartDescription> objects) {
            super(context, resource, objects);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder holder;

            if (convertView == null) {
                convertView = View.inflate(getContext(), R.layout.list_item_sample, null);

                holder = new ViewHolder();
                holder.name = (TextView) convertView.findViewById(R.id.name);
                holder.type = (TextView) convertView.findViewById(R.id.type);
                holder.chartLayout = (FrameLayout) convertView.findViewById(R.id.chart_layout);

                convertView.setTag(holder);
            } else {
                holder = (ViewHolder) convertView.getTag();
            }

            ChartDescription item = getItem(position);

            String graphUrl = item.getUrl();

            holder.chartLayout.setVisibility(View.VISIBLE);
            holder.chartLayout.removeAllViews();
            AbstractChartView chart;
            switch (item.chartType) {
                case LINE_CHART:
                    chart = new LineChartView(getContext());
                    holder.chartLayout.addView(chart);
                    break;
                case COLUMN_CHART:
                    chart = new ColumnChartView(getContext());
                    holder.chartLayout.addView(chart);
                    break;
                case PREVIEW_LINE_CHART:
                    chart = new PreviewLineChartView(getContext());
                    holder.chartLayout.addView(chart);
                    break;
                case MAP_CHART:
                    chart = new PreviewLineChartView(getContext());
                    holder.chartLayout.addView(chart);
                    break;
                case TABLE:
                    chart = new PreviewLineChartView(getContext());
                    holder.chartLayout.addView(chart);
                    break;
                default:
                    chart = null;
                    holder.chartLayout.setVisibility(View.GONE);
                    break;
            }

            if (null != chart) {
                chart.setInteractive(false);// Disable touch handling for chart on the ListView.
            }
            holder.name.setText(item.name);
            holder.type.setText(item.type);

            return convertView;
        }

        private class ViewHolder {

            TextView name;
            TextView type;
            FrameLayout chartLayout;
        }

    }

    public enum ChartType {
        LINE_CHART, COLUMN_CHART, PREVIEW_LINE_CHART, MAP_CHART, TABLE, OTHER
    }

    public static class ChartDescription {
        private String name;
        private String type;
        private String url;
        private ChartType chartType;

        public String getUrl(){return url;}
        public String getName() {return name;}
        public String getType() {return type;}

        public ChartDescription(String text1, String text2, String url, ChartType chartType) {
            this.name = text1;
            this.type = text2;
            this.chartType = chartType;
        }
    }


}
