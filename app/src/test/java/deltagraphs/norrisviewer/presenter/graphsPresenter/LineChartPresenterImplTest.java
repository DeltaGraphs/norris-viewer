package deltagraphs.norrisviewer.presenter.graphsPresenter;/*
 * Name : {Nome del file}.java
 * Module : deltagraphs.norrisviewer.presenter.graphsPresenter
 * Location : norrisviewer { Percorso in cui \'e presente il file }
 *
 * History :

 * Version      Date        Programmer         Description
 * ===============================================================
 * 
 * 0.1.0 2015-05-24 Davide Trivellato Codifica di tutti gli attributi e metodi
 *
 * 0.0.1 2015-05-24 Davide Trivellato Creazione file
 *
 * ===============================================================
 *
 */

import junit.framework.Assert;
import junit.framework.TestCase;

import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

import deltagraphs.norrisviewer.model.flowModel.FlowModel;
import deltagraphs.norrisviewer.model.graphsModel.AxisModel;
import deltagraphs.norrisviewer.model.graphsModel.LineChart;
import deltagraphs.norrisviewer.model.graphsModel.LineChartImpl;
import deltagraphs.norrisviewer.view.graphsView.LineChartActivity;



class AxisModelMock extends AxisModel{
    private String name;
    private int ticks;

    AxisModelMock(JSONObject json){
        super(json);
        name = "";
        ticks = 0;
    }

    public String getName() {
        return name;
    }
    public int getTicks() {
        return ticks;
    }
}


class LineChartMock extends LineChartImpl {

    public LineChartMock(Observer chartPresenter) {
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

    @Override
    public String getBackground() {
        return null;
    }

    @Override
    public Boolean getViewFinder() {
        return true;
    }

    @Override
    public Boolean getHorizontalGrid() {
        return true;
    }

    @Override
    public Boolean getVerticalGrid() {
        return true;
    }

    @Override
    public ArrayList<FlowModel> getFlowList() {
        return null;
    }
}

class mockActivity extends LineChartActivity{
    boolean axis = false;
    boolean grid = false;
    boolean data = false;

    boolean getAxis(){ return axis; }
    boolean getGrid(){ return grid; }
    boolean getData(){ return data; }

    public void setAxis(char axisXorY, String name, int ticks){
        axis = true;
    }

    public void setGrid(Boolean horizontal, Boolean vertical){
        grid = true;
    }

    public void setData(ArrayList<FlowModel> flowList, String signal) {
        data = true;
    }
}

public class LineChartPresenterImplTest extends TestCase {

    public LineChartPresenterImpl lineChartPresenter;
    public Observer x;



    @Before
    public void setUp() throws Exception {
        super.setUp();
        lineChartPresenter = new LineChartPresenterImpl(new mockActivity(),"http://norris-nrti-dev.herokuapp.com/norris/bar1");
        lineChartPresenter.graphSocket = new Mock();
    }

    @Test
    public void testStartConnection() throws Exception{
        lineChartPresenter.startConnection();
        Assert.assertEquals((boolean) lineChartPresenter.getGraphSocket().isConnected(), true);
    }

    @Test
    public void testStopConnection() throws Exception{
        lineChartPresenter.startConnection();
        lineChartPresenter.stopConnection();
        Assert.assertEquals((boolean) lineChartPresenter.getGraphSocket().isConnected(), false);
    }

    @Test
    public void testDestroyConnection() throws Exception{
        lineChartPresenter.startConnection();
        lineChartPresenter.destroyConnection();
        Assert.assertEquals((boolean) lineChartPresenter.getGraphSocket().isNull(), false);
        Assert.assertEquals((boolean) lineChartPresenter.getGraphSocket().isConnected(), false);
    }

    @Test
    public void testSetGraphParameters() throws Exception{
        lineChartPresenter.lineChartInstance = new LineChartMock(lineChartPresenter);
        String signal = "configGraph";
        lineChartPresenter.update((Observable)lineChartPresenter.lineChartInstance, signal);
        Assert.assertTrue(((mockActivity) lineChartPresenter.graphView).getAxis());
        Assert.assertTrue(((mockActivity)lineChartPresenter.graphView).getData());
        Assert.assertTrue(((mockActivity)lineChartPresenter.graphView).getGrid());
    }
}
