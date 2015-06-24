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

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

import deltagraphs.norrisviewer.model.flowModel.FlowModel;
import deltagraphs.norrisviewer.model.graphsModel.AxisModel;
import deltagraphs.norrisviewer.model.graphsModel.LineChart;
import deltagraphs.norrisviewer.view.graphsView.LineChartActivity;

class AxisModelMock extends AxisModel{

}


class LineChartMock implements LineChart {

    @Override
    public AxisModel getAxisX() {
        return new AxisModelMock();
    }

    @Override
    public AxisModel getAxisY() {
        return new AxisModelMock();
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
public class LineChartPresenterImplTest extends TestCase {

    public LineChartPresenterImpl lineChartPresenter;
    public Observer x;



    @Before
    public void setUp() throws Exception {
        super.setUp();
        lineChartPresenter = new LineChartPresenterImpl(new LineChartActivity(),"http://norris-nrti-dev.herokuapp.com/norris/bar1");
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
        lineChartPresenter.lineChartInstance = new LineChartMock();
    }
}
