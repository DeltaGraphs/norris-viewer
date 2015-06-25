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

import junit.framework.TestResult;

import org.junit.Before;
import org.junit.Test;

public class LineChartActivityTest extends ActivityInstrumentationTestCase2 {

    LineChartActivity mLineChartActivity;

    public LineChartActivityTest(Class activityClass) {
        super(activityClass);
    }

    @Before
    protected void setUp() throws Exception {
        super.setUp();
        setActivityInitialTouchMode(true);
        mLineChartActivity = (LineChartActivity)getActivity();
        mLineChartActivity.onCreate(new Bundle());
    }

    @Test
    public void testOnCreateOptionsMenu(){
       // mLineChartActivity.onCreateOptionsMenu()
    }
}
