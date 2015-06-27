package deltagraphs.norrisviewer.view.graphsView;

import android.content.Intent;
import android.os.Bundle;
import android.test.ActivityTestCase;
import android.test.ActivityInstrumentationTestCase2;
import android.test.UiThreadTest;

import junit.framework.TestCase;

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

public class TableActivityTest extends ActivityInstrumentationTestCase2<TableActivity> {

    public TableActivityTest() {
        super(TableActivity.class);
    }
    public void setUp() throws Exception {
        super.setUp();
        Intent intent = new Intent();

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