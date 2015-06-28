package deltagraphs.norrisviewer.view.graphsView;

import android.content.Intent;
import android.os.Bundle;
import android.test.ActivityTestCase;
import android.test.ActivityInstrumentationTestCase2;
import android.test.UiThreadTest;


import junit.framework.TestCase;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;

import deltagraphs.norrisviewer.model.graphsModel.Table;

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
 */
@Config(emulateSdk = 18)
//manifest = "app/src/main/AndroidManifest.xml")
@RunWith(RobolectricTestRunner.class)
public class TableActivityTest extends ActivityInstrumentationTestCase2<TableActivity> {

    TableActivity table;

    public TableActivityTest() {
        super(TableActivity.class);
    }

    @Before
    public void setUp() throws Exception {
        super.setUp();
        table = new TableActivity();

        //table = Robolectric.buildActivity(TableActivity.class).create().get();

        //TableActivity activity = getActivity();
        //Intent intent = new Intent();
        //setActivityInitialTouchMode(true);
        //mLineChartActivity = (LineChartActivity)getActivity();
        //intent = new Intent(getActivity(), TableActivity.class);
    }

    @Test
    public void testOnCreate() throws Exception {
        /*TableActivity activity = getActivity();
        Intent intent = new Intent(getActivity(), TableActivity.class);
        intent.putExtra("EXTRA_SOURCE_URL", "");
        intent.putExtra("EXTRA_SOURCE_TITLE", "table");
        Bundle bundle = activity.getIntent().getExtras();
        //startActivity(intent);
        activity.onCreate(bundle);
        assertNotNull(activity);*/
        assertNotNull(table);
        //TableActivity activity = Robolectric.setupActivity(TableActivity.class);
        //assertNotNull(activity);
       // table.

    }

    public void testOnResume() throws Exception {
        TableActivity activity = getActivity();
        activity.onCreate(new Bundle());
        activity.onPause();
        activity.onResume();
        assertNotNull(activity);
    }
/*
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

    public void testSetSortByCol() throws Exception {

    }

    public void testSetSortOrder() throws Exception {

    }

    public void testSetHeaders() throws Exception {

    }

    public void testSetData() throws Exception {

    }*/
}