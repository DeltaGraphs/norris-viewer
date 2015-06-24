package deltagraphs.norrisviewer.model.flowModel;


import junit.framework.TestCase;
import org.json.JSONArray;
import org.json.JSONException;
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
 * 0.1.0 2015-05-X Enrico Savoca Codifica di tutti gli attributi e i metodi
 *
 * 0.0.1 2015-05-X Enrico Savoca Creazione file
 *
 * ===============================================================
 *
 */public class BarChartFlowTest {

    JSONObject data;
    BarChartFlow flow;

    @Before
    public void testCreateFlow() throws Exception {
        String dataString = "{ ID: \"ciao\", name: \"hoho\", records: [{ norrisRecordID : \"flusso1201505081\", value: [\"1\", 25]}, {norrisRecordID : \"flusso1201505082\", value: [\"2\", 15]}]}";
        data = new JSONObject(dataString);
        flow = new BarChartFlow(data);
        flow.createFlow(data);
        Assert.assertEquals(2, flow.getRecordSize());

    }

    @Test
    public void testUpdateFlow() throws Exception {
        String dataString = "{ ID: \"roba\", name: \"hoho\", records: [{ norrisRecordID : \"flusso1201505081\", value: [\"1\", 25]}, {norrisRecordID : \"flusso1201505082\", value: [\"2\", 15]}]}";
        data = new JSONObject(dataString);
        flow = new BarChartFlow(data);
        String a = "{ name: \"ciao\", color: \"blue\"}";
        JSONObject data2 = new JSONObject(a);
        flow.createFlow(data);
        flow.updateFlow(data2);
        Assert.assertEquals(flow.getFlowName(), "ciao");
        Assert.assertEquals(flow.getFlowColour(), "blue");

    }

    @Test
    public void testDeleteRecordList() throws Exception {
        flow.addRecord(new JSONObject("{ norrisRecordID : \"flusso1201505081\", value: [\"1\", 25]}"));
        flow.deleteRecordList();
        Assert.assertTrue("", flow.getRecordSize()==0);
    }

    @Test
    public void testAddRecord() throws Exception {
        if(flow.getRecordSize()!=0)
            flow.deleteRecordList();
        flow.addRecord(new JSONObject("{ norrisRecordID : \"flusso1201505081\", value: [\"1\", 25]}"));
        Assert.assertEquals(1, flow.getRecordSize());
    }

    @Test
    public void testAddRecords() throws Exception {
        if(flow.getRecordSize()!=0)
            flow.deleteRecordList();
        flow.addRecords(new JSONArray("[{ norrisRecordID : \"flusso1201505081\", value: [\"1\", 25]},{norrisRecordID : \"flusso1201505080\", value: [\"2\", 20]}]"), false);
        Assert.assertEquals(2, flow.getRecordSize());
    }

    @Test
    public void testUpdateRecord() throws Exception {
        if(flow.getRecordSize()!=0)
            flow.deleteRecordList();
        flow.addRecord(new JSONObject("{ norrisRecordID : \"flusso1201505081\", value: [\"1\", 25]}"));
        flow.updateRecord(new JSONObject("{ norrisRecordID : \"flusso1201505081\", value: [\"2\", 20]}"));
        Assert.assertEquals(flow.getRecordId(0), "flusso1201505081");
        Assert.assertEquals(flow.getRecordIndex(0), "2");
        Assert.assertEquals(flow.getRecordValue(0), 20.0, 0);
        flow.updateRecord(new JSONObject("{ norrisRecordID : \"a\", value: [\"2\", 20]}"));

    }

    @Test
    public void testDeleteRecord() throws Exception {
        if(flow.getRecordSize()!=0)
            flow.deleteRecordList();
        flow.addRecord(new JSONObject("{ norrisRecordID : \"flusso1201505081\", value: [\"1\", 25]}"));
        flow.deleteRecord(new JSONObject("{ norrisRecordID : \"flusso1201505081\"}"));
        Assert.assertEquals(0, flow.getRecordSize());
    }

}