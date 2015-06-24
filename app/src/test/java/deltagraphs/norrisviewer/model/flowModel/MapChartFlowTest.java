package deltagraphs.norrisviewer.model.flowModel;

import junit.framework.TestCase;

import org.json.JSONObject;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/*
 * Name : { Nome del file }.java
 * Module : deltagraphs.norrisviewer.model.flowModel
 * Location : norrisviewer { Percorso in cui \'e presente il file }
 *
 * History :

 * Version Date Programmer Description
 * ===============================================================
 * 
 * 0.1.0 2015-X-X Enrico Savoca Coding of all methods and attibutes
 *
 * 0.0.1 2015-X-X Enrico Savoca Creation of the file
 *
 * ===============================================================
 *
 */public class MapChartFlowTest extends TestCase {
    JSONObject data;
    MapChartFlow flow;

    public void setUp() throws Exception {
        super.setUp();
        String dataString = "{\"data\":[{ID:\"flow1\", name:\"ciao\", records:[{norrisRecordID:\"flow1_1434809721998_4\",markerID:\"835\",value:[45.420070648193,11.878535270691]},{norrisRecordID:\"flow1_1434809721998_7\",markerID:\"880\", value:[45.414321899414,11.875240325928]}]}]}";
        data = new JSONObject(dataString);
        flow = new MapChartFlow(data.getJSONArray("data").getJSONObject(0));
    }

    @Test
    public void testCreateFlow() throws Exception {
        String dataString = "{\"data\":[{ID:\"flow1\", name:\"ciao\", records:[{norrisRecordID:\"flow1_1434809721998_4\",markerID:\"835\",value:[45.420070648193,11.878535270691]},{norrisRecordID:\"flow1_1434809721998_7\",markerID:\"880\", value:[45.414321899414,11.875240325928]}]}]}";
        data = new JSONObject(dataString);
        flow = new MapChartFlow(data.getJSONArray("data").getJSONObject(0));
        flow.createFlow(data.getJSONArray("data").getJSONObject(0));
        Assert.assertEquals(2, flow.getRecordSize());

    }

    @Test
    public void testUpdateFlow() throws Exception {
        String dataString = "{\"data\":[{ID:\"flow1\", name:\"ciao\", records:[{norrisRecordID:\"flow1_1434809721998_4\",markerID:\"835\",value:[45.420070648193,11.878535270691]},{norrisRecordID:\"flow1_1434809721998_7\",markerID:\"880\", value:[45.414321899414,11.875240325928]}]}]}";
        data = new JSONObject(dataString);
        flow = new MapChartFlow(data.getJSONArray("data").getJSONObject(0));
        String a = "{\"ID\":\"flow1\",\"name\":\"right\",\"marker\":{\"type\":\"shape\",\"shape\":\"circle\",\"color\":\"blue\"},\"trace\":{\"type\":\"none\",\"coordinates\":[[123.241,-2.3],[123.241,-2.3]],\"strokeColor\":\"#000\",\"fillColor\":\"#000\"},\"maxItems\":50}]}";
        JSONObject data2 = new JSONObject(a);
        flow.createFlow(data.getJSONArray("data").getJSONObject(0));
        flow.updateFlow(data2);
        Assert.assertEquals(flow.getFlowName(), "right");
        Assert.assertEquals(flow.getMarkerColour(), "blue");
        Assert.assertEquals(flow.getMarkerType(), "shape");
        Assert.assertEquals(flow.getMarkerProperty("shape"), "circle");
        Assert.assertEquals(flow.getMarkerProperty("roba"), "default");
        Assert.assertEquals(flow.getRecordId(0), "flow1_1434809721998_4");
        Assert.assertEquals(flow.getTraceStrokeColour(), "#000");
        Assert.assertEquals(flow.getTraceCoords().size(), 2);
        Assert.assertEquals(flow.isTraceUpdated(), true);
        Assert.assertEquals(flow.isTraceUpdated(), false);
        Assert.assertEquals(flow.getTraceType(), "none");
        a = "{\"ID\":\"flow1\",\"name\":\"right\",\"marker\":{\"type\":\"text\",\"text\":\"ciao\",\"color\":\"blue\"},\"trace\":{\"type\":\"polyline\",\"coordinates\":[[121.241,-2.3]],\"strokeColor\":\"#001\",\"fillColor\":\"#000\"},\"maxItems\":50}]}";
        data2 = new JSONObject(a);
        flow.updateFlow(data2);
        Assert.assertEquals(flow.getMarkerProperty("text"), "ciao");
        Assert.assertEquals(flow.getTraceStrokeColour(), "#001");
        Assert.assertEquals(flow.getTraceCoords().size(), 1);

    }

    @Test
    public void testDeleteRecordList() throws Exception {
        flow.addRecord(new JSONObject("{ norrisRecordID : \"flusso1201505081\", markerID:\"\", value: [1, 25]}"));
        flow.deleteRecordList();
        Assert.assertTrue("", flow.getRecordSize() == 0);
    }

    @Test
    public void testAddRecord() throws Exception {
        if (flow.getRecordSize() != 0)
            flow.deleteRecordList();
        Assert.assertEquals(0, flow.getRecordSize());
        flow.addRecord(new JSONObject("{ norrisRecordID : \"flusso1201505081\", markerID:\"ola\", value: [1, 25]}"));
        Assert.assertEquals(1, flow.getRecordSize());
    }

    @Test
    public void testAddRecords() throws Exception {
        if (flow.getRecordSize() != 0)
            flow.deleteRecordList();
        flow.addRecord(new JSONObject("{ norrisRecordID : \"flusso1201505081\", markerID:\"mak\", value: [1, 25]}"));
        flow.addRecord(new JSONObject("{ norrisRecordID : \"flusso1201505080\", markerID:\"mak1\", value: [2, 20]}"));
        Assert.assertEquals(2, flow.getRecordSize());
    }

    @Test
    public void testUpdateRecord() throws Exception {
        if (flow.getRecordSize() != 0)
            flow.deleteRecordList();
        flow.addRecord(new JSONObject("{ norrisRecordID : \"flusso1201505081\", markerID:\"vero\", value: [1, 25]}"));
        flow.updateRecord(new JSONObject("{ norrisRecordID : \"flusso1201505081\", markerID:\"\", value: [2, 20]}"));
        Assert.assertEquals(flow.getRecordId(0), "flusso1201505081");
        Assert.assertEquals(flow.getRecordLatitude(0), 2, 0);
        Assert.assertEquals(flow.getRecordLongitude(0), 20.0, 0);
        Assert.assertEquals(flow.getRecordMarkerId(0), "");
        flow.updateRecord(new JSONObject("{ norrisRecordID : \"ad\", markerID:\"\", value: [2, 20]}"));

    }

    @Test
    public void testDeleteRecord() throws Exception {
        if (flow.getRecordSize() != 0)
            flow.deleteRecordList();
        flow.addRecord(new JSONObject("{ norrisRecordID : \"flusso1201505081\", markerID:\"\", value: [1, 25]}"));
        flow.deleteRecord(new JSONObject("{ norrisRecordID : \"flusso1201505081\"}"));
        Assert.assertEquals(0, flow.getRecordSize());
    }



}