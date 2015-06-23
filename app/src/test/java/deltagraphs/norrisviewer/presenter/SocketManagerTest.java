package deltagraphs.norrisviewer.presenter;

import android.app.Activity;

import junit.framework.Assert;
import junit.framework.TestCase;

import org.junit.Before;
import org.junit.Test;

import java.util.Observable;
import java.util.Observer;

import deltagraphs.norrisviewer.model.graphsModel.BarChart;
import deltagraphs.norrisviewer.model.graphsModel.BarChartImpl;
import deltagraphs.norrisviewer.view.graphsView.BarChartActivity;

/*
 * Name : { Nome del file }.java
 * Module : com.example.deltagraphs.norrisviewer.presenter
 * Location : norrisviewer { Percorso in cui \'e presente il file }
 *
 * History :

 * Version Date Programmer Description
 * ===============================================================
 * 
 * 0.0.1 2015-05-X davide Creazione file
 *
 * ===============================================================
 *
 */

public class SocketManagerTest extends TestCase {

    SocketManager socketManager;

    @Before
    public void setUp() throws Exception{
        super.setUp();
        socketManager = new SocketManager("http://norris-nrti-dev.herokuapp.com/norris");
    }

    @Test
    public void testSetSocketUrl() throws Exception {
        socketManager.setSocketUrl("asd");
        Assert.assertEquals(socketManager.getSocketUrl(), "asd");
    }

    @Test
    public void testStartConnection() throws Exception {
        socketManager = new SocketManager("http://norris-nrti-dev.herokuapp.com/norris");
        socketManager.startConnection();
        Assert.assertEquals((boolean)socketManager.isConnected(), true);
    }

    @Test
    public void testStopConnection() throws Exception {
        socketManager.stopConnection();
        Assert.assertEquals((boolean) socketManager.isConnected(), false);
    }

    @Test
    public void testCloseSocket() throws Exception {

    }

    @Test
    public void testStartListening() throws Exception {
        socketManager.startListening("insertFlow",new BarChartActivity(), new BarChartImpl(new Observer() {
            @Override
            public void update(Observable observable, Object data) {

            }
        }));
    }
}