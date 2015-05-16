package deltagraphs.norrisviewer.presenter.mainPresenter;


    import android.app.Activity;
    import android.app.AlertDialog;
    import android.content.Context;
    import android.content.DialogInterface;
    import android.content.Intent;
    import android.os.Bundle;
    import android.support.v4.widget.DrawerLayout;
    import android.support.v7.internal.widget.AdapterViewCompat;
    import android.text.InputType;
    import android.view.LayoutInflater;
    import android.view.View;
    import android.view.ViewGroup;
    import android.widget.AdapterView;
    import android.widget.ArrayAdapter;
    import android.widget.EditText;
    import android.support.v4.app.Fragment;
    import android.support.v4.app.FragmentManager;
    import android.widget.FrameLayout;
    import android.widget.ListView;
    import android.widget.TextView;

    import com.example.deltagraphs.norrisviewer.R;

    import java.util.ArrayList;
    import java.util.List;

    import deltagraphs.norrisviewer.view.graphsView.LineChartActivity;
    import deltagraphs.norrisviewer.view.graphsView.MapChartActivity;
    import deltagraphs.norrisviewer.view.mainView.MainActivity;
    import deltagraphs.norrisviewer.presenter.SocketManager;
    import deltagraphs.norrisviewer.view.mainView.PageNavigationFragment;

    import lecho.lib.hellocharts.view.AbstractChartView;
    import lecho.lib.hellocharts.view.ColumnChartView;
    import lecho.lib.hellocharts.view.LineChartView;
    import lecho.lib.hellocharts.view.PreviewLineChartView;


/*
 * Name : MainPresenterImpl.java
 * Module : norrisviewer::presenter::mainPresenter
 * Location : norrisviewer\presenter\mainPresenter
 *
 * History :

 * Version Date Programmer Description
 * ===============================================================
 *
 * 0.0.1 2015-05-13 Davide Trivellato Creazione file
 *
 * ===============================================================
 *
 */

public class MainPresenterImpl implements MainPresenter,PageNavigationFragment.NavigationDrawerCallbacks {

    private static SocketManager mainSocket;
    private MainActivity mainView = new MainActivity();
    private PageNavigationFragment mPageNavigationFragment;

    public MainPresenterImpl(MainActivity view){
        mainSocket = new SocketManager();
        mainView = view;
        setUpNavigationFragment();
    }

    private void setUpNavigationFragment(){
        mPageNavigationFragment = (PageNavigationFragment) mainView.getSupportFragmentManager().findFragmentById(R.id.navigation_drawer);
        mPageNavigationFragment.setUp(
                R.id.navigation_drawer,
                (DrawerLayout) mainView.findViewById(R.id.drawer_layout));
    }

    public void showDialog(Context context){
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("Insert source URL");

        // Set up the input
        final EditText input = new EditText(context);
// Specify the type of input expected; this, for example, sets the input as a password, and will mask the text
        input.setInputType(InputType.TYPE_CLASS_TEXT);
        input.setText("http://5.231.33.217:3000/page1");
        builder.setView(input);

// Set up the buttons
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                mainSocket.setSocketUrl(input.getText().toString());
            }
        });
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });

        builder.show();
    }

    @Override
    public void onNavigationDrawerItemSelected(int position) {
        // update the main content by replacing fragments
        FragmentManager fragmentManager = mainView.getSupportFragmentManager();
        fragmentManager.beginTransaction()
                .replace(R.id.container, PlaceholderFragment.newInstance(position + 1))
                .commit();
    }



    //QUESTO GESTISCE IL FRAGMENT DOVE ANDRANNO INSERITI I GRAFICI



    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment implements AdapterView.OnItemClickListener {

        private ListView listView;
        private ChartSamplesAdapter adapter;

        /**
         * The fragment argument representing the section number for this
         * fragment.
         */
        private static final String ARG_SECTION_NUMBER = "section_number";

        /**
         * Returns a new instance of this fragment for the given section
         * number.
         */



        public static PlaceholderFragment newInstance(int sectionNumber) {
            PlaceholderFragment fragment = new PlaceholderFragment();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            fragment.setArguments(args);
            return fragment;
        }

        public PlaceholderFragment() {}

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_main, container, false);
            listView = (ListView) rootView.findViewById(android.R.id.list);
            adapter = new ChartSamplesAdapter(getActivity(), 0, generateSamplesDescriptions());
            listView.setAdapter(adapter);
            listView.setOnItemClickListener(this);
            return rootView;
        }

        @Override
        public void onAttach(Activity activity) {
            super.onAttach(activity);
            ((MainActivity) activity).onSectionAttached(
                    getArguments().getInt(ARG_SECTION_NUMBER));
        }

        @Override
        public void onItemClick(AdapterView<?> adapter, View view, int position, long id) {
            Intent intent;

            switch (position) {
                case 0:
                    // Line Chart;
                    intent = new Intent(getActivity(), LineChartActivity.class);
                    intent.putExtra("EXTRA_SOURCE_URL", mainSocket.getSocketUrl());
                    startActivity(intent);
                    break;

                case 1:
                    // Column Chart;
                    intent = new Intent(getActivity(), MapChartActivity.class);
                    startActivity(intent);
                    break;
                /*
                case 2:
                    // Pie Chart;
                    intent = new Intent(getActivity(), PieChartActivity.class);
                    startActivity(intent);
                    break;
                    */
                default:
                    break;
            }
        }

        private List<ChartSampleDescription> generateSamplesDescriptions() {
            List<ChartSampleDescription> list = new ArrayList<ChartSampleDescription>();

            list.add(new ChartSampleDescription("Line Chart", "", ChartType.LINE_CHART));
            list.add(new ChartSampleDescription("Map Chart", "", ChartType.COLUMN_CHART));
            list.add(new ChartSampleDescription("Preview Line Chart",
                    "Control line chart viewport with another line chart.", ChartType.PREVIEW_LINE_CHART));
            return list;
        }

    }



    public static class ChartSamplesAdapter extends ArrayAdapter<ChartSampleDescription> {

        public ChartSamplesAdapter(Context context, int resource, List<ChartSampleDescription> objects) {
            super(context, resource, objects);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder holder;

            if (convertView == null) {
                convertView = View.inflate(getContext(), R.layout.list_item_sample, null);

                holder = new ViewHolder();
                holder.text1 = (TextView) convertView.findViewById(R.id.text1);
                holder.text2 = (TextView) convertView.findViewById(R.id.text2);
                holder.chartLayout = (FrameLayout) convertView.findViewById(R.id.chart_layout);

                convertView.setTag(holder);
            } else {
                holder = (ViewHolder) convertView.getTag();
            }

            ChartSampleDescription item = getItem(position);

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
                default:
                    chart = null;
                    holder.chartLayout.setVisibility(View.GONE);
                    break;
            }

            if (null != chart) {
                chart.setInteractive(false);// Disable touch handling for chart on the ListView.
            }
            holder.text1.setText(item.text1);
            holder.text2.setText(item.text2);

            return convertView;
        }

        private class ViewHolder {

            TextView text1;
            TextView text2;
            FrameLayout chartLayout;
        }

    }

    public enum ChartType {
        LINE_CHART, COLUMN_CHART, PREVIEW_LINE_CHART, OTHER
    }

    public static class ChartSampleDescription {
        String text1;
        String text2;
        ChartType chartType;

        public ChartSampleDescription(String text1, String text2, ChartType chartType) {
            this.text1 = text1;
            this.text2 = text2;
            this.chartType = chartType;
        }
    }

}
