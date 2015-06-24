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

import java.util.ArrayList;

import deltagraphs.norrisviewer.model.flowModel.FlowModel;
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

    public Boolean isNull() {
        return (connected == null);
    }

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

}

class viewMock implements BarChartView{

    String orientation;

    @Override
    public void setBarOrientation(String orientation) {
        this.orientation= orientation;
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
    }

    @Test
    public void testStartConnection() throws Exception{
        barChartPresenter.startConnection();
//        Assert.assertEquals((boolean)barChartPresenter.getGraphSocket().isConnected(),true);
    }

    @Test
    public void testStopConnection() throws Exception{
        barChartPresenter.startConnection();
        barChartPresenter.stopConnection();
        Assert.assertEquals((boolean) barChartPresenter.getGraphSocket().isConnected(), false);
    }
}
