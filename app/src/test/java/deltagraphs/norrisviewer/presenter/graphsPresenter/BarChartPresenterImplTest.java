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

import org.json.JSONObject;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

import deltagraphs.norrisviewer.model.flowModel.FlowModel;
import deltagraphs.norrisviewer.model.graphsModel.AxisModel;
import deltagraphs.norrisviewer.model.graphsModel.BarChartImpl;
import deltagraphs.norrisviewer.presenter.SocketManager;
import deltagraphs.norrisviewer.presenter.mainPresenter.MainPresenterImpl;
import deltagraphs.norrisviewer.view.graphsView.BarChartActivity;
import deltagraphs.norrisviewer.view.graphsView.BarChartView;
import deltagraphs.norrisviewer.view.mainView.MainActivity;

class Mock extends SocketManager{
    Boolean connected;

    Mock(){
        connected=new Boolean(false);
    }

    Mock(String url){
        super(url);
        connected=new Boolean(false);
    }

    @Override
    public Boolean isNull() {
        return (connected == null);
    }

    @Override
    public Boolean isConnected() {
        return connected;
    }

    @Override
    public void startConnection(){
        connected = true;
    }

    @Override
    public void stopConnection(){
        connected = false;
    }

    @Override
    public void destroyConnection(){
        connected = false;
    }
}

class BarChartMock extends BarChartImpl {

    public BarChartMock(Observer chartPresenter) {
        super(chartPresenter);
    }

    @Override
    public AxisModel getAxisX() {
        JSONObject json=new JSONObject();
        return new AxisModelMock(json);
    }

    @Override
    public AxisModel getAxisY() {
        JSONObject json=new JSONObject();
        return new AxisModelMock(json);
    }
}

class mockBarChartActivity implements BarChartView{

    String orientation;

    @Override
    public void setBarOrientation(String orientation) {
        this.orientation= orientation;
    }

    public String getBarOrientation(){
        return orientation;
    }

    @Override
    public void setData(ArrayList<FlowModel> flowList, String signal, ArrayList<String> headers) {

    }
}


public class BarChartPresenterImplTest extends TestCase {

    BarChartPresenterImpl barChartPresenter;




    @Before
    public void setUp() throws Exception {
        super.setUp();
        barChartPresenter = new BarChartPresenterImpl(new BarChartActivity(),"http://norris-nrti-dev.herokuapp.com/norris/bar1");
        barChartPresenter.graphSocket = new Mock();
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

    @Test
    public void testDestroyConnection() throws Exception{
        barChartPresenter.startConnection();
        barChartPresenter.stopConnection();
        barChartPresenter.destroyConnection();
        Assert.assertEquals((boolean) barChartPresenter.getGraphSocket().isNull(), false);
    }

    @Test
    public void testSetGraphParameters() throws Exception{
        barChartPresenter.barChartInstance = new BarChartMock(barChartPresenter);
        String signal = "configGraph";
        barChartPresenter.update((Observable) barChartPresenter.barChartInstance, signal);
        Assert.assertEquals(((mockBarChartActivity) barChartPresenter.graphView).getBarOrientation(),"horizontal");
    }
}
