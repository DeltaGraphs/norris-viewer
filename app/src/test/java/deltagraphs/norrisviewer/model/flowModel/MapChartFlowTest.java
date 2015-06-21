package deltagraphs.norrisviewer.model.flowModel;

import junit.framework.TestCase;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.*;

/*
 * Name : { Nome del file }.java
 * Module : deltagraphs.norrisviewer.model.flowModel
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

public class MapChartFlowTest extends TestCase {
    JSONObject data;
    MapChartFlow flow;

    @Before
    public void testCreateFlow() throws Exception {
        String dataString = "{\"data\":[{ID:\"flow1\",records:[{norrisRecordID:\"flow1_1434809721998_4\",markerID:\"835\",value:[45.420070648193,11.878535270691]},{norrisRecordID:\"flow1_1434809721998_7\",markerID:\"880\", value:[45.414321899414,11.875240325928]}]}]}";
        data = new JSONObject(dataString);
        flow = new MapChartFlow(data.getJSONArray("data").getJSONObject(0));
        flow.createFlow(data.getJSONArray("data").getJSONObject(0));
        Assert.assertEquals(2, flow.getRecordSize());

    }
/*
    @Test
    public void testUpdateFlow() throws Exception {
        String dataString = "{data:[{ID:\"flow1\",records:[{norrisRecordID:\"flow1_1434809721998_4\",markerID:835,value:[45.420070648193,11.878535270691]},{norrisRecordID:\"flow1_1434809721998_7\",markerID:880, value:[45.414321899414,11.875240325928]}]}]}";
        data = new JSONObject(dataString);
        flow = new MapChartFlow(data.getJSONArray("data").getJSONObject(0));
        String a = "{\"ID\":\"flusso1\",\"name\":\"\",\"marker\":{\"type\":'shape',\"shape\":'circle',\"icon\":null,\"text\":null,\"color\":\"#000\"}\"trace\":{\"type\":\"none\"\"coordinates\":[[123.241,-2.3],[123.241,-2.3],[...]]\"stokeColor\":\"#000\"\"fillColor\":\"#000\"}\"maxItems\"=50}";
        JSONObject data2 = new JSONObject(a);
        flow.createFlow(data.getJSONArray("data").getJSONObject(0));
        flow.updateFlow(data2);
        Assert.assertEquals(flow.getFlowName(), "ciao");
        Assert.assertEquals(flow.getMarkerColour(), "blue");
        Assert.assertEquals(flow.getMarkerType(), "shape");
        Assert.assertEquals(flow.getMarkerProperty("shape"), "circle");
        Assert.assertEquals(flow.getRecordId(0), "flow1_1434809721998_4");
        Assert.assertEquals(flow.getTraceFillColour(), "#000");
        Assert.assertEquals(flow.getTraceStrokeColour(), "#000");
        Assert.assertEquals(flow.getTraceType(), "none");
    }

    @Test
    public void testDeleteRecordList() throws Exception {
        flow.addRecord(new JSONObject("{ norrisRecordID : \"flusso1201505081\", value: [\"1\", 25]}"));
        flow.deleteRecordList();
        Assert.assertTrue("", flow.getRecordSize() == 0);
    }

    @Test
    public void testAddRecord() throws Exception {
        if (flow.getRecordSize() != 0)
            flow.deleteRecordList();
        flow.addRecord(new JSONObject("{ norrisRecordID : \"flusso1201505081\", value: [\"1\", 25]}"));
        Assert.assertEquals(1, flow.getRecordSize());
    }

    @Test
    public void testAddRecords() throws Exception {
        if (flow.getRecordSize() != 0)
            flow.deleteRecordList();
        flow.addRecord(new JSONObject("{ norrisRecordID : \"flusso1201505081\", value: [\"1\", 25]}"));
        flow.addRecord(new JSONObject("{ norrisRecordID : \"flusso1201505080\", value: [\"2\", 20]}"));
        Assert.assertEquals(2, flow.getRecordSize());
    }

    @Test
    public void testUpdateRecord() throws Exception {
        if (flow.getRecordSize() != 0)
            flow.deleteRecordList();
        flow.addRecord(new JSONObject("{ norrisRecordID : \"flusso1201505081\", value: [\"1\", 25]}"));
        flow.updateRecord(new JSONObject("{ norrisRecordID : \"flusso1201505081\", value: [\"2\", 20]}"));
        Assert.assertEquals(flow.getRecordId(0), "flusso1201505081");
        Assert.assertEquals(flow.getRecordId(0), "2");
        Assert.assertEquals(flow.getRecordLatitude(0), 20.0, 0);
        Assert.assertEquals(flow.getRecordLongitude(0), 20.0, 0);
        Assert.assertEquals(flow.getRecordMarkerId(0), "");
    }

    @Test
    public void testDeleteRecord() throws Exception {
        if (flow.getRecordSize() != 0)
            flow.deleteRecordList();
        flow.addRecord(new JSONObject("{ norrisRecordID : \"flusso1201505081\", value: [\"1\", 25]}"));
        flow.deleteRecord(new JSONObject("{ norrisRecordID : \"flusso1201505081\"}"));
        Assert.assertEquals(0, flow.getRecordSize());
    }
    */
}