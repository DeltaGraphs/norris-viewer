package deltagraphs.norrisviewer.view.graphsView;

import android.content.Intent;
import android.os.Bundle;
import android.test.ActivityInstrumentationTestCase2;
import android.test.ActivityUnitTestCase;
import android.widget.Button;
import android.widget.TextView;

import junit.framework.TestCase;

import deltagraphs.norrisviewer.R;
import deltagraphs.norrisviewer.model.graphsModel.BarChart;

/*
 * Name : { Nome del file }.java
 * Module : deltagraphs.norrisviewer.view.graphsView
 * Location : norrisviewer { Percorso in cui \'e presente il file }
 *
 * History :

 * Version Date Programmer Description
 * ===============================================================
 * 
 * 0.1.0 2015-X-X Enrico Savoca Coding of all methods and attibutes
 *
 * 0.0.1 2015-X-X Enrico Savoca Creation of the file
 *
 * ===============================================================
 *
 */public class BarChartActivityTest extends ActivityInstrumentationTestCase2<BarChartActivity> {

    private BarChartActivity mBarChartActivity;
    private TextView mFirstTestText;

    public BarChartActivityTest(Class<BarChartActivity> activityClass) {
        super(activityClass);
    }

    public BarChartActivityTest() {
        super(BarChartActivity.class);
    }

    Intent barChartIntent;

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        mBarChartActivity = getActivity();
    }

    public void testOnCreate() throws Exception {
        getActivity().onCreate(new Bundle());
        assertNotNull(getActivity());
    }

    public void testOnResume() throws Exception {

    }

    public void testOnStop() throws Exception {

    }

    public void testOnPause() throws Exception {

    }

    public void testOnRestart() throws Exception {

    }

    public void testOnDestroy() throws Exception {

    }

    public void testOnCreateOptionsMenu() throws Exception {

    }

    public void testOnOptionsItemSelected() throws Exception {

    }

    public void testSetBarOrientation() throws Exception {

    }

    public void testSetData() throws Exception {

    }
}