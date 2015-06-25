package deltagraphs.norrisviewer.model.graphsModel;

import android.util.Log;

import junit.framework.TestCase;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Assert;
import org.junit.Test;

import java.util.Observable;
import java.util.Observer;

import deltagraphs.norrisviewer.model.flowModel.FlowModel;
import deltagraphs.norrisviewer.model.flowModel.MapChartFlow;


/*
 * Name : GraphTest.java
 * Module : deltagraphs.norrisviewer.model.graphsModel
 * Location : norrisviewer { Percorso in cui \'e presente il file }
 *
 * History :

 * Version Date Programmer Description
 * ===============================================================
 * 
 * 0.1.0 2015-05-X Enrico Savoca Codifica di tutti gli attributi e i metodi
 *
 * 0.0.1 2015-05-X Enrico Savoca Creazione file
 *
 * ===============================================================
 *
 */
class MockFlow extends FlowModel{
    public boolean createFlow = false;
    public boolean updateFlow = false;
    public boolean addRecords = false;
    public boolean deleteRecord = false;
    public boolean updateRecord = false;
    public boolean deleteRecordList = false;

    MockFlow(JSONObject x){
        try {
            flowId = x.getString("ID");
            if(x.has("name"))
                flowName = x.getString("name");
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void createFlow(JSONObject data) {
        createFlow = true;
    }

    @Override
    public void updateFlow(JSONObject data) {
        updateFlow = true;
    }

    @Override
    public void deleteRecordList() {
        deleteRecordList = true;
    }

    @Override
    public void addRecord(JSONObject data) {

    }

    @Override
    public void addRecords(JSONArray data, boolean insertOnTop) {
        addRecords = true;
    }

    @Override
    public void updateRecord(JSONObject data) {
        updateRecord = true;
    }

    @Override
    public void deleteRecord(JSONObject data) {
        deleteRecord = true;
    }
}

class Mock extends Graph{
    public boolean updateParameters = false;

    @Override
    public void setParameters(JSONObject data) {
    try {
            JSONArray jsonFlows = data.getJSONArray("flows");
            JSONObject flow = jsonFlows.getJSONObject(0);
            addFlow(flow);
        } catch (JSONException e) {
                e.printStackTrace();
        }
    }
    @Override
    public void updateParameters(JSONObject data) {
        updateParameters = true;
    }

    @Override
    public void addFlow(JSONObject flow) {
        flowList.add(new MockFlow(flow));
    }
}

class MockObserver implements Observer {

    @Override
    public void update(Observable observable, Object data) {

    }
}




public class GraphTest extends TestCase {
    Mock mockGraph = new Mock();
    JSONObject jsonFlow;

    @Test
    public void testSetGraph() throws Exception {
        jsonFlow = new JSONObject("{properties:{flows:[{ID: \"ciao\"}]}, data:[{ID:\"norrisID1\"}]}");
        assertEquals(mockGraph.isConfigured(), false);
        mockGraph.setGraph(jsonFlow, "configGraph");
        assertEquals(mockGraph.getFlowList().size(), 1);
        assertEquals(mockGraph.isConfigured(), true);

        mockGraph.setGraph(jsonFlow, "updateGraphProp");
        assertTrue(mockGraph.updateParameters);

        jsonFlow = new JSONObject("{properties:{ID: \"ola\"} }");
        mockGraph.setGraph(jsonFlow, "insertFlow");
        assertEquals(mockGraph.getFlowList().size(), 2);
        assertTrue(((MockFlow) mockGraph.flowList.get(1)).createFlow);

        jsonFlow = new JSONObject("{ID: \"ciao\"}");
        mockGraph.setGraph(jsonFlow, "updateFlowProp");
        assertTrue(((MockFlow) mockGraph.flowList.get(0)).updateFlow);

        jsonFlow = new JSONObject("{action:\"insertRecords\", ID:\"ciao\", records:[{}] }");
        mockGraph.setGraph(jsonFlow, "updateFlowData");
        assertTrue(((MockFlow) mockGraph.flowList.get(0)).addRecords);

        jsonFlow = new JSONObject("{action:\"deleteRecord\", ID:\"ciao\", records:[{}] }");
        mockGraph.setGraph(jsonFlow, "updateFlowData");
        assertTrue(((MockFlow) mockGraph.flowList.get(0)).deleteRecord);

        jsonFlow = new JSONObject("{action:\"updateRecord\", ID:\"ciao\", records:[{}] }");
        mockGraph.setGraph(jsonFlow, "updateFlowData");
        assertTrue(((MockFlow) mockGraph.flowList.get(0)).updateRecord);

        ((MockFlow) mockGraph.flowList.get(0)).createFlow = false;
        jsonFlow = new JSONObject("{action:\"replaceData\", ID:\"ciao\", records:[{}] }");
        mockGraph.setGraph(jsonFlow, "updateFlowData");
        assertTrue(((MockFlow) mockGraph.flowList.get(0)).createFlow);
        assertTrue(((MockFlow) mockGraph.flowList.get(0)).deleteRecordList);

        jsonFlow = new JSONObject("{ID: \"ciao\"}");
        mockGraph.setGraph(jsonFlow, "deleteFlow");
        assertEquals(mockGraph.getFlowList().size(), 1);
    }

    @Test
    public void testAddFlow() throws Exception {
        jsonFlow = new JSONObject("{ID: \"ciao\"}");
        mockGraph.addFlow(jsonFlow);
        Assert.assertEquals(mockGraph.getFlowList().size(), 1);
    }

    @Test
    public void testUpdateFlow() throws Exception {
        jsonFlow = new JSONObject("{ID: \"ciao\"}");
        mockGraph.addFlow(jsonFlow);
        jsonFlow = new JSONObject("{ID: \"ciao\", name:\"nuovoNome\"}");
        mockGraph.updateFlow(jsonFlow);
        assertTrue(((MockFlow) mockGraph.flowList.get(0)).updateFlow);
    }


    @Test
    public void testDeleteFlow() throws Exception {
        jsonFlow = new JSONObject("{ID: \"ciao\"}");
        mockGraph.addFlow(jsonFlow);
        jsonFlow = new JSONObject("{ID: \"ola\"}");
        mockGraph.addFlow(jsonFlow);
        mockGraph.deleteFlow("ola");
        assertEquals(mockGraph.flowList.size(), 1);
        assertEquals(mockGraph.flowList.get(0).getFlowId(), "ciao");
    }

}