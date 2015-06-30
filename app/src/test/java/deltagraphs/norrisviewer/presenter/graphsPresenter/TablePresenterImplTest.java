package deltagraphs.norrisviewer.presenter.graphsPresenter;/*
 * Name : {Nome del file}.java
 * Module : deltagraphs.norrisviewer
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
import deltagraphs.norrisviewer.model.graphsModel.TableImpl;
import deltagraphs.norrisviewer.view.graphsView.TableActivity;

class TableMock extends TableImpl {
    public TableMock(Observer chartPresenter) {
        super(chartPresenter);
    }
}

public class TablePresenterImplTest extends TestCase {
   
    TablePresenterImpl tablePresenter;



    class MockTableActivity extends TableActivity {
        Boolean data = new Boolean(false);
        Boolean order = new Boolean(false);
        Boolean headers = new Boolean(false);
        Boolean sortedByCol = new Boolean(false);


        @Override
        public void setHeaders(String[] headers) {
            this.headers = true;
        }

        @Override
        public void setData(ArrayList<FlowModel> flowList, int numOfColumns) {
            data = true;
        }

        public Boolean getData(){ return data; }
        public Boolean getSortOrder(){ return order; }
        public Boolean getSortedByCol(){ return sortedByCol; }
        public Boolean getHeaders(){ return headers; }

    }

    @Before
    public void setUp() throws Exception {
        super.setUp();
        tablePresenter = new TablePresenterImpl(new MockTableActivity(),"http://norris-nrti-dev.herokuapp.com/norris/bar1");
        tablePresenter.graphSocket = new SocketMock();
    }

    @Test
    public void testStartConnection() throws Exception{
        tablePresenter.startConnection();
        Assert.assertEquals((boolean) tablePresenter.getGraphSocket().isConnected(), true);
    }

    @Test
    public void testStopConnection() throws Exception{
        tablePresenter.startConnection();
        tablePresenter.stopConnection();
        Assert.assertEquals((boolean) tablePresenter.getGraphSocket().isConnected(), false);
    }

    @Test
    public void testSetGraphParameters() throws Exception{
        tablePresenter.tableInstance = new TableMock(tablePresenter);
        String signal = "configGraph";
        tablePresenter.update((Observable) tablePresenter.tableInstance, signal);
        Assert.assertTrue(((MockTableActivity) tablePresenter.graphView).getData());
        Assert.assertTrue(((MockTableActivity) tablePresenter.graphView).getHeaders());
        Assert.assertTrue(((MockTableActivity) tablePresenter.graphView).getSortedByCol());
        Assert.assertTrue(((MockTableActivity) tablePresenter.graphView).getSortOrder());
    }
}
