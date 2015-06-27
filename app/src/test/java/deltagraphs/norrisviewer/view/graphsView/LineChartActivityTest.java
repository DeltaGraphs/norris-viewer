package deltagraphs.norrisviewer.view.graphsView;/*
 * Name : {Nome del file}.java
 * Module : deltagraphs.norrisviewer.view.graphsView
 * Location : norrisviewer { Percorso in cui \'e presente il file }
 *
 * History :

 * Version      Date        Programmer         Description
 * ===============================================================
 * 
 * 0.1.0 2015-05-25 Davide Trivellato Codifica di tutti gli attributi e metodi
 *
 * 0.0.1 2015-05-25 Davide Trivellato Creazione file
 *
 * ===============================================================
 *
 */


import android.os.Bundle;
import android.test.ActivityInstrumentationTestCase2;
import android.view.Menu;

import com.example.app.test.RobolectricGradleTestRunner;

import junit.framework.TestResult;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.annotation.Config;

@Config(emulateSdk = 18, manifest = "AndroidManifest.xml")
@RunWith(RobolectricGradleTestRunner.class)
public class LineChartActivityTest extends ActivityInstrumentationTestCase2<LineChartActivity> {

    LineChartActivity mLineChartActivity;

    public LineChartActivityTest() {
        super(LineChartActivity.class);
    }

    @Before
    public void setUp() throws Exception {
        super.setUp();
        mLineChartActivity = new LineChartActivity();
        mLineChartActivity = Robolectric.buildActivity(LineChartActivity.class).create().get();
    }
/*
        @Before
    protected void setUp() throws Exception {
        super.setUp();
        //setActivityInitialTouchMode(true);
        //mLineChartActivity = (LineChartActivity)getActivity();
        //mLineChartActivity.onCreate(new Bundle());
    }
*/
    @Test
    public void testOnCreate() throws Exception {
        mLineChartActivity.onCreate(new Bundle());
        assertNotNull(mLineChartActivity);
    }

}
