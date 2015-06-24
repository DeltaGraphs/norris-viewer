package deltagraphs.norrisviewer.model.flowModel;

import junit.framework.TestCase;

import org.json.JSONObject;
import org.junit.Assert;

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
 */public class LineChartFlowTest extends TestCase {
    JSONObject data;
    LineChartFlow flow;

    public void setUp() throws Exception {
        super.setUp();
        String dataString = "{\"data\":[{\"ID\":\"flow1\",\"name\":\"name\", \"records\":[{\"norrisRecordID\":\"flow1_1435142614086_1\",\"value\":[1,10]}]}]}";
        data = new JSONObject(dataString);
        flow = new LineChartFlow(data.getJSONArray("data").getJSONObject(0));
    }

    public void testCreateFlow() throws Exception {
        String dataString = "{\"data\":[{\"ID\":\"flow1\",\"name\":\"name\", \"records\":[{\"norrisRecordID\":\"flow1_14314086_1\",\"value\":[1,10]}]}]}";
        data = new JSONObject(dataString);
        flow.createFlow(data.getJSONArray("data").getJSONObject(0));
        Assert.assertEquals(2, flow.getRecordSize());
    }

    public void testUpdateFlow() throws Exception {
        String dataString = "{\"ID\":\"flow1\", name:\"ora\", flowColor:\"giallo\", \"records\":[{\"norrisRecordID\":\"flow1_1435142614086_1\",\"value\":[2,20]}]}";
        data = new JSONObject(dataString);
        flow.updateFlow(data);
        Assert.assertEquals(1, flow.getRecordSize());
        Assert.assertEquals(flow.getFlowColour(),"giallo");

        Assert.assertEquals(flow.getRecordSize(), 1);
        Assert.assertEquals(flow.getFlowId(), "flow1");
        Assert.assertEquals(flow.getFlowName(), "ora");
    }

    public void testDeleteRecordList() throws Exception {
        flow.deleteRecordList();
        Assert.assertEquals(0, flow.getRecordSize());
    }

    public void testAddRecord() throws Exception {
        String dataString = "{\"norrisRecordID\":\"flow1_1435142614086_1\",\"value\":[2,20]}";
        data = new JSONObject(dataString);
        flow.addRecord(data);
        Assert.assertEquals(2, flow.getRecordSize());
    }

    public void testAddRecords() throws Exception {
        String dataString = "{\"ID\":\"flow1\",\"records\":[{\"norrisRecordID\":\"flow1_1435142614086_1\",\"value\":[2,20]}, {\"norrisRecordID\":\"flow1_1435142614086_2\",\"value\":[3,23]}]}";
        data = new JSONObject(dataString);
        flow.addRecords(data.getJSONArray("records"), false);
        Assert.assertEquals(3, flow.getRecordSize());
    }

    public void testUpdateRecord() throws Exception {
        String dataString = "{\"ID\":\"flow1\",\"records\":[{\"norrisRecordID\":\"flow1_1435142614086_1\",\"value\":[2,20]}, {\"norrisRecordID\":\"flow1_143\",\"value\":[3,23]}]}";
        data = new JSONObject(dataString);
        flow.updateRecord(data.getJSONArray("records").getJSONObject(0));
        Assert.assertEquals(flow.getRecordId(0), "flow1_1435142614086_1");
        Assert.assertEquals(flow.getRecordValueX(0), 2, 0);
        Assert.assertEquals(flow.getRecordValueY(0), 20, 0);
        flow.updateRecord(data.getJSONArray("records").getJSONObject(1));

    }

    public void testDeleteRecord() throws Exception {
        String dataString = "{\"norrisRecordID\":\"flow1_1435142614086_1\",\"value\":[2,20]}";
        data = new JSONObject(dataString);
        flow.deleteRecord(data);
        Assert.assertEquals(flow.getRecordSize(), 0);
    }
}