package deltagraphs.norrisviewer.presenter.graphsPresenter;/*
 * Name : {Nome del file}.java
 * Module : deltagraphs.norrisviewer.presenter.graphsPresenter
 * Location : norrisviewer { Percorso in cui \'e presente il file }
 *
 * History :

 * Version      Date        Programmer         Description
 * ===============================================================
 * 
 * 0.1.0 2015-05-23 Davide Trivellato Codifica di tutti gli attributi e metodi
 *
 * 0.0.1 2015-05-23 Davide Trivellato Creazione file
 *
 * ===============================================================
 *
 */

import junit.framework.Assert;
import junit.framework.TestCase;

import org.junit.Before;
import org.junit.Test;

import deltagraphs.norrisviewer.presenter.mainPresenter.MainPresenterImpl;
import deltagraphs.norrisviewer.view.graphsView.BarChartActivity;
import deltagraphs.norrisviewer.view.mainView.MainActivity;

public class BarChartPresenterImplTest extends TestCase {

    BarChartPresenterImpl barChartPresenter;

    @Before
    public void setUp() throws Exception {
        super.setUp();
        barChartPresenter = new BarChartPresenterImpl(new BarChartActivity(),"http://norris-nrti-dev.herokuapp.com/norris/bar1");
    }

    @Test
    public void testStartConnection() throws Exception{
        barChartPresenter.startConnection();
        Assert.assertEquals((boolean)barChartPresenter.getGraphSocket().isConnected(),true);
    }

    @Test
    public void testStopConnection() throws Exception{
        barChartPresenter.startConnection();
        barChartPresenter.stopConnection();
        Assert.assertEquals((boolean) barChartPresenter.getGraphSocket().isConnected(), false);
    }
}
