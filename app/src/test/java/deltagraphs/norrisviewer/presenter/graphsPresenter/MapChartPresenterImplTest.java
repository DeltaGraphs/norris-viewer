package deltagraphs.norrisviewer.presenter.graphsPresenter;/*
 * Name : {Nome del file}.java
 * Module : deltagraphs.norrisviewer.presenter.graphsPresenter
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

import junit.framework.Assert;
import junit.framework.TestCase;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

import deltagraphs.norrisviewer.model.flowModel.FlowModel;
import deltagraphs.norrisviewer.model.graphsModel.MapChartImpl;
import deltagraphs.norrisviewer.view.graphsView.MapChartActivity;

class MapChartMock extends MapChartImpl{
    public MapChartMock(Observer chartPresenter) {
        super(chartPresenter);
    }
}

class MockMapChartActivity extends MapChartActivity{
    Boolean data = new Boolean(false);
    Boolean cameraPosition = new Boolean(false);
    Boolean zoom = new Boolean(false);
    Boolean legendOnPoint = new Boolean(false);
    String mapType;

    @Override
    public void setMapType(String type) {
        mapType = type;
    }

    @Override
    public void setZoom(float width, float height) {
        zoom = true;
    }

    @Override
    public void setData(ArrayList<FlowModel> flowList, String signal) {
        data = true;
    }

    @Override
    public void cameraPosition(float lat, float lng) {
        cameraPosition = true;
    }

    @Override
    public void setLegendOnPoint(Boolean legend){
        legendOnPoint = legend;
    }

    public boolean getData(){ return data; }
    public boolean getZoom(){ return zoom; }
    public boolean getCameraPosition(){ return cameraPosition; }
    public Boolean getLegendOnPoint(){ return legendOnPoint; }
    public String getType(){ return mapType; }

}

public class MapChartPresenterImplTest extends TestCase {
    
    MapChartPresenterImpl mapChartPresenter;

    @Before
    public void setUp() throws Exception {
        super.setUp();
        mapChartPresenter = new MapChartPresenterImpl(new MockMapChartActivity(),"http://norris-nrti-dev.herokuapp.com/norris/bar1");
        mapChartPresenter.graphSocket = new Mock();
    }

    @Test
    public void testStartConnection() throws Exception{
        mapChartPresenter.startConnection();
        Assert.assertEquals((boolean) mapChartPresenter.getGraphSocket().isConnected(), true);
    }

    @Test
    public void testStopConnection() throws Exception{
        mapChartPresenter.startConnection();
        mapChartPresenter.stopConnection();
        Assert.assertEquals((boolean) mapChartPresenter.getGraphSocket().isConnected(), false);
    }

    @Test
    public void testDestroyConnection() throws Exception{
        mapChartPresenter.startConnection();
        mapChartPresenter.destroyConnection();
        Assert.assertEquals((boolean) mapChartPresenter.getGraphSocket().isConnected(), false);
    }


    @Test
    public void testSetGraphParameters() throws Exception{
        mapChartPresenter.mapChartInstance = new MapChartMock(mapChartPresenter);
        String signal = "configGraph";
        mapChartPresenter.update((Observable) mapChartPresenter.mapChartInstance, signal);
        Assert.assertTrue(((MockMapChartActivity) mapChartPresenter.graphView).getData());
        Assert.assertTrue(((MockMapChartActivity) mapChartPresenter.graphView).getCameraPosition());
        Assert.assertTrue(((MockMapChartActivity) mapChartPresenter.graphView).getZoom());
        Assert.assertTrue(((MockMapChartActivity) mapChartPresenter.graphView).getLegendOnPoint());
        Assert.assertEquals(((MockMapChartActivity) mapChartPresenter.graphView).getType(), "terrain");
    }
}
