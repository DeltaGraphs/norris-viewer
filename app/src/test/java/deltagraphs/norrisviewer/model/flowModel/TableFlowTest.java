package deltagraphs.norrisviewer.model.flowModel;

import junit.framework.Assert;
import junit.framework.TestCase;

import org.json.JSONArray;
import org.json.JSONObject;

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
 */public class TableFlowTest extends TestCase {
    JSONObject data;
    TableFlow flow;

    public void setUp() throws Exception {
        super.setUp();
        String dataString = "{\"properties\":{\"ID\":\"table1\",\"title\":\"Tabella Last Sync: 24/6/2015 @ 14:23:19\",\"type\":\"Table\",\"height\":600,\"width\":1000,\"enableLegend\":false,\"legend\":{\"position\":\"NE\",\"fontColor\":\"#000000\",\"backgroundColor\":\"#FFFFFF\"},\"sortable\":true,\"sort\":{\"column\":[\"IDMezzo\",\"WGS84Fi\"],\"ordering\":[\"DESC\",\"ASC\"]},\"maxItemsPage\":20,\"headers\":[\"IDMezzo\",\"WGS84Fi\",\"WGS84La\"],\"appearance\":{\"border\":{\"color\":\"#00AA00\",\"width\":1},\"rowEven\":{\"textColor\":[\"#1F3D99\",\"#000000\"],\"backgroundColor\":[\"#99E2F2\",\"#F2E899\"]},\"rowOdd\":{\"textColor\":[\"#000000\",\"#1F3D99\"],\"backgroundColor\":[\"#99F2DF\",\"#D9F299\"]},\"headers\":{\"textColor\":[\"#FFFFFF\",\"#FFFFFF\"],\"backgroundColor\":[\"#2FBA38\",\"#2F3ABA\"]}},\"addRowOn\":\"top\",\"flows\":[{\"ID\":\"flow1\",\"name\":\"autobus\",\"filters\":null,\"columnKeys\":[\"0\",\"1\",\"2\"],\"columnFormats\":null,\"maxItems\":50,\"maxItemsSaved\":500}]},\"data\":[{\"ID\":\"flow1\", \"name\":\"name\", \"records\":[{\"norrisRecordID\":\"flow1_1435155722552_1\",\"value\":[\"837\",\"45.39281463623\",\"11.871248245239\"]},{\"norrisRecordID\":\"flow1_1435155727555_2\",\"value\":[\"875\",\"45.426074981689\",\"11.907616615295\"]},{\"norrisRecordID\":\"flow1_1435155732561_3\",\"value\":[\"805\",\"45.386032104492\",\"11.865413665771\"]},{\"norrisRecordID\":\"flow1_1435155737564_4\",\"value\":[\"837\",\"45.397495269775\",\"11.874231338501\"],\"appearance\":[{\"bg\":\"#FFAAFF\",\"text\":\"#FFAAAA\"},{\"bg\":\"#FFFFFF\",\"text\":\"#000000\"},{\"bg\":\"#FFAAFF\",\"text\":\"#FFAACC\"}]},{\"norrisRecordID\":\"flow1_1435155742566_5\",\"value\":[\"837\",\"45.397689819336\",\"11.874346733093\"]},{\"norrisRecordID\":\"flow1_1435155747570_6\",\"value\":[\"880\",\"45.412399291992\",\"11.878684997559\"],\"appearance\":[{\"bg\":\"#FFAAFF\",\"text\":\"#FFAAAA\"},{\"bg\":\"#FFAAFF\",\"text\":\"#FFABBA\"},{\"bg\":\"#FFAAFF\",\"text\":\"#FFAACC\"}]},{\"norrisRecordID\":\"flow1_1435155752571_7\",\"value\":[\"875\",\"45.431159973145\",\"11.914177894592\"],\"appearance\":[{\"bg\":\"#FFAAFF\",\"text\":\"#FFAAAA\"},{\"bg\":\"#FFAAFF\",\"text\":\"#FFABBA\"},{\"bg\":\"#FFAAFF\",\"text\":\"#FFAACC\"}]},{\"norrisRecordID\":\"flow1_1435155757573_8\",\"value\":[\"805\",\"45.387706756592\",\"11.868689537048\"]},{\"norrisRecordID\":\"flow1_1435155762575_9\",\"value\":[\"814\",\"45.43616104126\",\"11.917216300964\"]},{\"norrisRecordID\":\"flow1_1435155767587_10\",\"value\":[\"814\",\"45.43616104126\",\"11.917216300964\"]},{\"norrisRecordID\":\"flow1_1435155772591_11\",\"value\":[\"875\",\"45.43480682373\",\"11.916501998901\"]},{\"norrisRecordID\":\"flow1_1435155777594_12\",\"value\":[\"837\",\"45.399394989014\",\"11.877456665039\"],\"appearance\":[{\"bg\":\"#FFAAFF\",\"text\":\"#FFAAAA\"},{\"bg\":\"#FFAAFF\",\"text\":\"#FFABBA\"},{\"bg\":\"#FFAAFF\",\"text\":\"#FFAACC\"}]},{\"norrisRecordID\":\"flow1_1435155782597_13\",\"value\":[\"835\",\"45.420070648193\",\"11.878535270691\"],\"appearance\":[{\"bg\":\"#FFFFFF\",\"text\":\"#F299AC\"},{\"bg\":\"#F299AC\",\"text\":\"#FFFFFF\"},{\"bg\":\"#FFAAFF\",\"text\":\"#FFAACC\"}]},{\"norrisRecordID\":\"flow1_1435155787599_14\",\"value\":[\"867\",\"45.389148712158\",\"11.869828224182\"]},{\"norrisRecordID\":\"flow1_1435155792604_15\",\"value\":[\"814\",\"45.434585571289\",\"11.913011550903\"]},{\"norrisRecordID\":\"flow1_1435155797605_16\",\"value\":[\"867\",\"45.386985778809\",\"11.86462688446\"],\"appearance\":[{\"bg\":\"#FFAAFF\",\"text\":\"#FFAAAA\"},{\"bg\":\"#FFAAFF\",\"text\":\"#FFABBA\"},{\"bg\":\"#FFAAFF\",\"text\":\"#FFAACC\"}]},{\"norrisRecordID\":\"flow1_1435155802607_17\",\"value\":[\"835\",\"45.413475036621\",\"11.87677192688\"]},{\"norrisRecordID\":\"flow1_1435155807616_18\",\"value\":[\"814\",\"45.425636291504\",\"11.903347969055\"]}]}]}";
        data = new JSONObject(dataString);
        flow = new TableFlow(data.getJSONArray("data").getJSONObject(0));
    }

    public void testCreateFlow() throws Exception {
        String dataString = "{\"properties\":{\"ID\":\"table1\",\"title\":\"Tabella Last Sync: 24/6/2015 @ 14:23:19\",\"type\":\"Table\",\"height\":600,\"width\":1000,\"enableLegend\":false,\"legend\":{\"position\":\"NE\",\"fontColor\":\"#000000\",\"backgroundColor\":\"#FFFFFF\"},\"sortable\":true,\"sort\":{\"column\":[\"IDMezzo\",\"WGS84Fi\"],\"ordering\":[\"DESC\",\"ASC\"]},\"maxItemsPage\":20,\"headers\":[\"IDMezzo\",\"WGS84Fi\",\"WGS84La\"],\"appearance\":{\"border\":{\"color\":\"#00AA00\",\"width\":1},\"rowEven\":{\"textColor\":[\"#1F3D99\",\"#000000\"],\"backgroundColor\":[\"#99E2F2\",\"#F2E899\"]},\"rowOdd\":{\"textColor\":[\"#000000\",\"#1F3D99\"],\"backgroundColor\":[\"#99F2DF\",\"#D9F299\"]},\"headers\":{\"textColor\":[\"#FFFFFF\",\"#FFFFFF\"],\"backgroundColor\":[\"#2FBA38\",\"#2F3ABA\"]}},\"addRowOn\":\"top\",\"flows\":[{\"ID\":\"flow1\",\"name\":\"autobus\",\"filters\":null,\"columnKeys\":[\"0\",\"1\",\"2\"],\"columnFormats\":null,\"maxItems\":50,\"maxItemsSaved\":500}]},\"data\":[{\"ID\":\"flow1\",\"records\":[{\"norrisRecordID\":\"flow1_1435155722552_1\",\"value\":[\"837\",\"45.39281463623\",\"11.871248245239\"]},{\"norrisRecordID\":\"flow1_1435155727555_2\",\"value\":[\"875\",\"45.426074981689\",\"11.907616615295\"]},{\"norrisRecordID\":\"flow1_1435155732561_3\",\"value\":[\"805\",\"45.386032104492\",\"11.865413665771\"]},{\"norrisRecordID\":\"flow1_1435155737564_4\",\"value\":[\"837\",\"45.397495269775\",\"11.874231338501\"],\"appearance\":[{\"bg\":\"#FFAAFF\",\"text\":\"#FFAAAA\"},{\"bg\":\"#FFFFFF\",\"text\":\"#000000\"},{\"bg\":\"#FFAAFF\",\"text\":\"#FFAACC\"}]},{\"norrisRecordID\":\"flow1_1435155742566_5\",\"value\":[\"837\",\"45.397689819336\",\"11.874346733093\"]},{\"norrisRecordID\":\"flow1_1435155747570_6\",\"value\":[\"880\",\"45.412399291992\",\"11.878684997559\"],\"appearance\":[{\"bg\":\"#FFAAFF\",\"text\":\"#FFAAAA\"},{\"bg\":\"#FFAAFF\",\"text\":\"#FFABBA\"},{\"bg\":\"#FFAAFF\",\"text\":\"#FFAACC\"}]},{\"norrisRecordID\":\"flow1_1435155752571_7\",\"value\":[\"875\",\"45.431159973145\",\"11.914177894592\"],\"appearance\":[{\"bg\":\"#FFAAFF\",\"text\":\"#FFAAAA\"},{\"bg\":\"#FFAAFF\",\"text\":\"#FFABBA\"},{\"bg\":\"#FFAAFF\",\"text\":\"#FFAACC\"}]},{\"norrisRecordID\":\"flow1_1435155757573_8\",\"value\":[\"805\",\"45.387706756592\",\"11.868689537048\"]},{\"norrisRecordID\":\"flow1_1435155762575_9\",\"value\":[\"814\",\"45.43616104126\",\"11.917216300964\"]},{\"norrisRecordID\":\"flow1_1435155767587_10\",\"value\":[\"814\",\"45.43616104126\",\"11.917216300964\"]},{\"norrisRecordID\":\"flow1_1435155772591_11\",\"value\":[\"875\",\"45.43480682373\",\"11.916501998901\"]},{\"norrisRecordID\":\"flow1_1435155777594_12\",\"value\":[\"837\",\"45.399394989014\",\"11.877456665039\"],\"appearance\":[{\"bg\":\"#FFAAFF\",\"text\":\"#FFAAAA\"},{\"bg\":\"#FFAAFF\",\"text\":\"#FFABBA\"},{\"bg\":\"#FFAAFF\",\"text\":\"#FFAACC\"}]},{\"norrisRecordID\":\"flow1_1435155782597_13\",\"value\":[\"835\",\"45.420070648193\",\"11.878535270691\"],\"appearance\":[{\"bg\":\"#FFFFFF\",\"text\":\"#F299AC\"},{\"bg\":\"#F299AC\",\"text\":\"#FFFFFF\"},{\"bg\":\"#FFAAFF\",\"text\":\"#FFAACC\"}]},{\"norrisRecordID\":\"flow1_1435155787599_14\",\"value\":[\"867\",\"45.389148712158\",\"11.869828224182\"]},{\"norrisRecordID\":\"flow1_1435155792604_15\",\"value\":[\"814\",\"45.434585571289\",\"11.913011550903\"]},{\"norrisRecordID\":\"flow1_1435155797605_16\",\"value\":[\"867\",\"45.386985778809\",\"11.86462688446\"],\"appearance\":[{\"bg\":\"#FFAAFF\",\"text\":\"#FFAAAA\"},{\"bg\":\"#FFAAFF\",\"text\":\"#FFABBA\"},{\"bg\":\"#FFAAFF\",\"text\":\"#FFAACC\"}]},{\"norrisRecordID\":\"flow1_1435155802607_17\",\"value\":[\"835\",\"45.413475036621\",\"11.87677192688\"]},{\"norrisRecordID\":\"flow1_1435155807616_18\",\"value\":[\"814\",\"45.425636291504\",\"11.903347969055\"]}]}]}";
        data = new JSONObject(dataString);
        flow.createFlow(data.getJSONArray("data").getJSONObject(0));
        Assert.assertEquals(18, flow.getRecordSize());
    }

    public void testUpdateFlow() throws Exception {
        String dataString = "{\"properties\":{\"ID\":\"table1\",\"title\":\"Tabella Last Sync: 24/6/2015 @ 14:23:19\",\"type\":\"Table\",\"height\":600,\"width\":1000,\"enableLegend\":false,\"legend\":{\"position\":\"NE\",\"fontColor\":\"#000000\",\"backgroundColor\":\"#FFFFFF\"},\"sortable\":true,\"sort\":{\"column\":[\"IDMezzo\",\"WGS84Fi\"],\"ordering\":[\"DESC\",\"ASC\"]},\"maxItemsPage\":20,\"headers\":[\"IDMezzo\",\"WGS84Fi\",\"WGS84La\"],\"appearance\":{\"border\":{\"color\":\"#00AA00\",\"width\":1},\"rowEven\":{\"textColor\":[\"#1F3D99\",\"#000000\"],\"backgroundColor\":[\"#99E2F2\",\"#F2E899\"]},\"rowOdd\":{\"textColor\":[\"#000000\",\"#1F3D99\"],\"backgroundColor\":[\"#99F2DF\",\"#D9F299\"]},\"headers\":{\"textColor\":[\"#FFFFFF\",\"#FFFFFF\"],\"backgroundColor\":[\"#2FBA38\",\"#2F3ABA\"]}},\"addRowOn\":\"top\",\"flows\":[{\"ID\":\"flow1\",\"name\":\"autobus\",\"filters\":null,\"columnKeys\":[\"0\",\"1\",\"2\"],\"columnFormats\":null,\"maxItems\":50,\"maxItemsSaved\":500}]},\"data\":[{\"ID\":\"flow1\", \"name\":\"name1\",\"records\":[{\"norrisRecordID\":\"flow1_1435155722551\",\"value\":[\"836\",\"45.39281463623\",\"11.871248245239\"]},{\"norrisRecordID\":\"flow1_1435155727555_2\",\"value\":[\"875\",\"45.426074981689\",\"11.907616615295\"]},{\"norrisRecordID\":\"flow1_1435155732561_3\",\"value\":[\"805\",\"45.386032104492\",\"11.865413665771\"]},{\"norrisRecordID\":\"flow1_1435155737564_4\",\"value\":[\"837\",\"45.397495269775\",\"11.874231338501\"],\"appearance\":[{\"bg\":\"#FFAAFF\",\"text\":\"#FFAAAA\"},{\"bg\":\"#FFFFFF\",\"text\":\"#000000\"},{\"bg\":\"#FFAAFF\",\"text\":\"#FFAACC\"}]},{\"norrisRecordID\":\"flow1_1435155742566_5\",\"value\":[\"837\",\"45.397689819336\",\"11.874346733093\"]},{\"norrisRecordID\":\"flow1_1435155747570_6\",\"value\":[\"880\",\"45.412399291992\",\"11.878684997559\"],\"appearance\":[{\"bg\":\"#FFAAFF\",\"text\":\"#FFAAAA\"},{\"bg\":\"#FFAAFF\",\"text\":\"#FFABBA\"},{\"bg\":\"#FFAAFF\",\"text\":\"#FFAACC\"}]},{\"norrisRecordID\":\"flow1_1435155752571_7\",\"value\":[\"875\",\"45.431159973145\",\"11.914177894592\"],\"appearance\":[{\"bg\":\"#FFAAFF\",\"text\":\"#FFAAAA\"},{\"bg\":\"#FFAAFF\",\"text\":\"#FFABBA\"},{\"bg\":\"#FFAAFF\",\"text\":\"#FFAACC\"}]},{\"norrisRecordID\":\"flow1_1435155757573_8\",\"value\":[\"805\",\"45.387706756592\",\"11.868689537048\"]},{\"norrisRecordID\":\"flow1_1435155762575_9\",\"value\":[\"814\",\"45.43616104126\",\"11.917216300964\"]},{\"norrisRecordID\":\"flow1_1435155767587_10\",\"value\":[\"814\",\"45.43616104126\",\"11.917216300964\"]},{\"norrisRecordID\":\"flow1_1435155772591_11\",\"value\":[\"875\",\"45.43480682373\",\"11.916501998901\"]},{\"norrisRecordID\":\"flow1_1435155777594_12\",\"value\":[\"837\",\"45.399394989014\",\"11.877456665039\"],\"appearance\":[{\"bg\":\"#FFAAFF\",\"text\":\"#FFAAAA\"},{\"bg\":\"#FFAAFF\",\"text\":\"#FFABBA\"},{\"bg\":\"#FFAAFF\",\"text\":\"#FFAACC\"}]},{\"norrisRecordID\":\"flow1_1435155782597_13\",\"value\":[\"835\",\"45.420070648193\",\"11.878535270691\"],\"appearance\":[{\"bg\":\"#FFFFFF\",\"text\":\"#F299AC\"},{\"bg\":\"#F299AC\",\"text\":\"#FFFFFF\"},{\"bg\":\"#FFAAFF\",\"text\":\"#FFAACC\"}]},{\"norrisRecordID\":\"flow1_1435155787599_14\",\"value\":[\"867\",\"45.389148712158\",\"11.869828224182\"]},{\"norrisRecordID\":\"flow1_1435155792604_15\",\"value\":[\"814\",\"45.434585571289\",\"11.913011550903\"]},{\"norrisRecordID\":\"flow1_1435155797605_16\",\"value\":[\"867\",\"45.386985778809\",\"11.86462688446\"],\"appearance\":[{\"bg\":\"#FFAAFF\",\"text\":\"#FFAAAA\"},{\"bg\":\"#FFAAFF\",\"text\":\"#FFABBA\"},{\"bg\":\"#FFAAFF\",\"text\":\"#FFAACC\"}]},{\"norrisRecordID\":\"flow1_1435155802607_17\",\"value\":[\"835\",\"45.413475036621\",\"11.87677192688\"]},{\"norrisRecordID\":\"flow1_1435155807616_18\",\"value\":[\"814\",\"45.425636291504\",\"11.903347969055\"]}]}]}";
        data = new JSONObject(dataString);
        flow.updateFlow(data.getJSONArray("data").getJSONObject(0));
        Assert.assertEquals(flow.getFlowName(), "name1");
        Assert.assertEquals(flow.getRecordSize(), 0);
    }

    public void testDeleteRecordList() throws Exception {
        String dataString = "{\"norrisRecordID\":\"flow1_1435155722551\",\"value\":[\"836\",\"45.39281463623\",\"11.871248245239\"]}";
        JSONObject data = new JSONObject(dataString);
        flow.addRecord(data);
        dataString = "{\"norrisRecordID\":\"flow1_2\",\"value\":[\"835\",\"45.3923\",\"11.871248229\"]}";
        data = new JSONObject(dataString);
        flow.addFirstRecord(data);
        assertEquals(2, flow.getRecordSize());
        flow.deleteRecordList();
        assertEquals(0, flow.getRecordSize());
    }

    public void testAddRecord() throws Exception {
        String dataString = "{\"norrisRecordID\":\"flow1_1435155722551\",\"value\":[\"836\",\"45.39281463623\",\"11.871248245239\"]}";
        JSONObject data = new JSONObject(dataString);
        flow.addRecord(data);
        assertEquals(1, flow.getRecordSize());
        assertEquals(flow.getCellData(0, 0), "836");
        assertEquals(flow.getRecordId(0), "flow1_1435155722551");
    }

    public void testAddFirstRecord() throws Exception {
        String dataString = "{\"norrisRecordID\":\"flow1_1435155722551\",\"value\":[\"836\",\"45.39281463623\",\"11.871248245239\"]}";
        JSONObject data = new JSONObject(dataString);
        flow.addRecord(data);
        assertEquals(1, flow.getRecordSize());
        dataString = "{\"norrisRecordID\":\"flow1_2\",\"value\":[\"835\",\"45.3923\",\"11.871248229\"]}";
        data = new JSONObject(dataString);
        flow.addFirstRecord(data);
        assertEquals(2, flow.getRecordSize());
        assertEquals(flow.getCellData(0, 0), "835");
        assertEquals(flow.getRecordId(0), "flow1_2");


    }

    public void testAddRecords() throws Exception {
        String dataString = "[{\"norrisRecordID\":\"flow1_1435155722551\",\"value\":[\"836\",\"45.39281463623\",\"11.871248245239\"]},{\"norrisRecordID\":\"flow1_1435155727555_2\",\"value\":[\"875\",\"45.426074981689\",\"11.907616615295\"]}]";
        JSONArray data = new JSONArray(dataString);
        flow.addRecords(data, false);
        assertEquals(flow.getRecordSize(), 2);
        dataString = "[{\"norrisRecordID\":\"flow1_551\",\"value\":[\"836\",\"45.39281463623\",\"11.871248245239\"]},{\"norrisRecordID\":\"flow1_1435727555_2\",\"value\":[\"875\",\"45.426074981689\",\"11.907616615295\"]}]";
        data = new JSONArray(dataString);
        flow.addRecords(data, true);
        assertEquals(flow.getRecordSize(), 4);
    }

    public void testUpdateRecord() throws Exception {
        String dataString = "{\"norrisRecordID\":\"flow1_1435155722551\",\"value\":[\"836\",\"45.39281463623\",\"11.871248245239\"]}";
        JSONObject data = new JSONObject(dataString);
        flow.addRecord(data);
        assertEquals(1, flow.getRecordSize());
        dataString = "{\"norrisRecordID\":\"flow1_1435155722551\",\"value\":[\"84\",\"44\",\"11.9\"]}";
        data = new JSONObject(dataString);
        flow.updateRecord(data);
        assertEquals(1, flow.getRecordSize());
        assertEquals(flow.getCellData(0, 0), "84");
        assertEquals(flow.getRecordId(0), "flow1_1435155722551");
        dataString = "{\"norrisRecordID\":\"flow1_1111\",\"value\":[\"84\",\"44\",\"11.9\"]}";
        data = new JSONObject(dataString);
        flow.updateRecord(data);
    }

    public void testDeleteRecord() throws Exception {
        String dataString = "{\"norrisRecordID\":\"flow1_1435155722551\",\"value\":[\"836\",\"45.39281463623\",\"11.871248245239\"]}";
        JSONObject data = new JSONObject(dataString);
        flow.addRecord(data);
        assertEquals(1, flow.getRecordSize());
        flow.deleteRecord(data);
        assertEquals(0, flow.getRecordSize());
    }
}