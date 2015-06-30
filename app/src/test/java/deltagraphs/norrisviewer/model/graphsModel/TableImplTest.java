package deltagraphs.norrisviewer.model.graphsModel;

import junit.framework.TestCase;

import org.json.JSONObject;
import org.junit.Assert;

import java.util.Observable;
import java.util.Observer;

/*
 * Name : { Nome del file }.java
 * Module : deltagraphs.norrisviewer.model.graphsModel
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
 */public class TableImplTest extends TestCase {

    JSONObject json;
    TableImpl table;
    Observer x;

    public void setUp() throws Exception {
        super.setUp();
        x = new Observer() {
            @Override
            public void update(Observable observable, Object data) {

            }
        };

        table = new TableImpl(x);
    }

    public void testSetParameters() throws Exception {
        json = new JSONObject("{\"ID\":\"table1\",\"title\":\"Tabella\",\"type\":\"Table\",\"height\":600,\"width\":1000,\"enableLegend\":false,\"legend\":{\"position\":\"NE\",\"fontColor\":\"#000000\",\"backgroundColor\":\"#FFFFFF\"},\"sortable\":true,\"sort\":{\"column\":[\"IDMezzo\",\"WGS84Fi\"],\"ordering\":[\"DESC\",\"ASC\"]},\"maxItemsPage\":20,\"headers\":[\"IDMezzo\",\"WGS84Fi\",\"WGS84La\"],\"appearance\":{\"horizontalGrid\":{\"color\":\"#00AA00\",\"width\":1}, \"verticalGrid\":{\"color\":\"#00AA00\",\"width\":1},\"rowEven\":{\"textColor\":[\"#1F3D99\",\"#000000\"],\"backgroundColor\":[\"#99E2F2\",\"#F2E899\"]},\"rowOdd\":{\"textColor\":[\"#000000\",\"#1F3D99\"],\"backgroundColor\":[\"#99F2DF\",\"#D9F299\"]},\"headers\":{\"textColor\":[\"#FFFFFF\",\"#FFFFFF\"],\"backgroundColor\":[\"#2FBA38\",\"#2F3ABA\"]}},\"addRowOn\":\"bottom\",\"flows\":[{\"ID\":\"flow1\",\"name\":\"autobus\",\"filters\":null,\"columnKeys\":[\"0\",\"1\",\"2\"],\"columnFormats\":null,\"maxItems\":50,\"maxItemsSaved\":500}],\"data\":[{\"ID\":\"flow1\",\"records\":[{\"norrisRecordID\":\"flow1_1435222498294_1\",\"value\":[837,45.39281463623,11.871248245239]},{\"norrisRecordID\":\"flow1_1435222503296_2\",\"value\":[875,45.426074981689,11.907616615295]},{\"norrisRecordID\":\"flow1_1435222508297_3\",\"value\":[805,45.386032104492,11.865413665771]},{\"norrisRecordID\":\"flow1_1435222513301_4\",\"value\":[837,45.397495269775,11.874231338501],\"appearance\":[{\"bg\":\"#FFAAFF\",\"text\":\"#FFAAAA\"},{\"bg\":\"#FFFFFF\",\"text\":\"#000000\"},{\"bg\":\"#FFAAFF\",\"text\":\"#FFAACC\"}]},{\"norrisRecordID\":\"flow1_1435222518305_5\",\"value\":[837,45.397689819336,11.874346733093]},{\"norrisRecordID\":\"flow1_1435222523306_6\",\"value\":[880,45.412399291992,11.878684997559],\"appearance\":[{\"bg\":\"#FFAAFF\",\"text\":\"#FFAAAA\"},{\"bg\":\"#FFAAFF\",\"text\":\"#FFABBA\"},{\"bg\":\"#FFAAFF\",\"text\":\"#FFAACC\"}]},{\"norrisRecordID\":\"flow1_1435222528313_7\",\"value\":[875,45.431159973145,11.914177894592],\"appearance\":[{\"bg\":\"#FFAAFF\",\"text\":\"#FFAAAA\"},{\"bg\":\"#FFAAFF\",\"text\":\"#FFABBA\"},{\"bg\":\"#FFAAFF\",\"text\":\"#FFAACC\"}]}]}]}");
        table.setParameters(json);
        Assert.assertEquals(table.getTitle(), "Tabella");
        Assert.assertEquals(table.getAddRowOn(), "bottom");
        Assert.assertEquals(table.getNumberOfColumns(), 3);
        Assert.assertEquals(table.getHeaderValue(0) , "IDMezzo");
        Assert.assertEquals(table.sortByCol(), "IDMezzo");
        Assert.assertEquals(table.getSortOrder(), "DESC");
        Assert.assertEquals(table.getFlowList().get(0).getFlowId(), "flow1");
    }

    public void testUpdateParameters() throws Exception {
        json = new JSONObject("{\"ID\":\"table1\",\"title\":\"Tabella\",\"type\":\"Table\",\"height\":600,\"width\":1000,\"enableLegend\":false,\"legend\":{\"position\":\"NE\",\"fontColor\":\"#000000\",\"backgroundColor\":\"#FFFFFF\"},\"sortable\":true,\"sort\":{\"column\":[\"IDMezzo\",\"WGS84Fi\"],\"ordering\":[\"DESC\",\"ASC\"]},\"maxItemsPage\":20,\"headers\":[\"IDMezzo\",\"WGS84Fi\",\"WGS84La\"],\"appearance\":{\"horizontalGrid\":{\"color\":\"#00AA00\",\"width\":1}, \"verticalGrid\":{\"color\":\"#00AA00\",\"width\":1},\"rowEven\":{\"textColor\":[\"#1F3D99\",\"#000000\"],\"backgroundColor\":[\"#99E2F2\",\"#F2E899\"]},\"rowOdd\":{\"textColor\":[\"#000000\",\"#1F3D99\"],\"backgroundColor\":[\"#99F2DF\",\"#D9F299\"]},\"headers\":{\"textColor\":[\"#FFFFFF\",\"#FFFFFF\"],\"backgroundColor\":[\"#2FBA38\",\"#2F3ABA\"]}},\"addRowOn\":\"bottom\",\"flows\":[{\"ID\":\"flow1\",\"name\":\"autobus\",\"filters\":null,\"columnKeys\":[\"0\",\"1\",\"2\"],\"columnFormats\":null,\"maxItems\":50,\"maxItemsSaved\":500}],\"data\":[{\"ID\":\"flow1\",\"records\":[{\"norrisRecordID\":\"flow1_1435222498294_1\",\"value\":[837,45.39281463623,11.871248245239]},{\"norrisRecordID\":\"flow1_1435222503296_2\",\"value\":[875,45.426074981689,11.907616615295]},{\"norrisRecordID\":\"flow1_1435222508297_3\",\"value\":[805,45.386032104492,11.865413665771]},{\"norrisRecordID\":\"flow1_1435222513301_4\",\"value\":[837,45.397495269775,11.874231338501],\"appearance\":[{\"bg\":\"#FFAAFF\",\"text\":\"#FFAAAA\"},{\"bg\":\"#FFFFFF\",\"text\":\"#000000\"},{\"bg\":\"#FFAAFF\",\"text\":\"#FFAACC\"}]},{\"norrisRecordID\":\"flow1_1435222518305_5\",\"value\":[837,45.397689819336,11.874346733093]},{\"norrisRecordID\":\"flow1_1435222523306_6\",\"value\":[880,45.412399291992,11.878684997559],\"appearance\":[{\"bg\":\"#FFAAFF\",\"text\":\"#FFAAAA\"},{\"bg\":\"#FFAAFF\",\"text\":\"#FFABBA\"},{\"bg\":\"#FFAAFF\",\"text\":\"#FFAACC\"}]},{\"norrisRecordID\":\"flow1_1435222528313_7\",\"value\":[875,45.431159973145,11.914177894592],\"appearance\":[{\"bg\":\"#FFAAFF\",\"text\":\"#FFAAAA\"},{\"bg\":\"#FFAAFF\",\"text\":\"#FFABBA\"},{\"bg\":\"#FFAAFF\",\"text\":\"#FFAACC\"}]}]}]}");
        table.setParameters(json);
        json = new JSONObject("{\"ID\":\"table1\",\"title\":\"Tab\",\"type\":\"Table\",\"height\":600,\"width\":1000,\"enableLegend\":false,\"legend\":{\"position\":\"NE\",\"fontColor\":\"#000000\",\"backgroundColor\":\"#FFFFFF\"},\"sortable\":true,\"sort\":{\"column\":[\"ciaoo\",\"WGS84Fi\"],\"ordering\":[\"ASC\",\"ASC\"]},\"maxItemsPage\":20,\"headers\":[\"IDMezzo\",\"WGS84Fi\",\"WGS84La\", \"ciaoo\"],\"appearance\":{\"border\":{\"color\":\"#00AA00\",\"width\":1},\"rowEven\":{\"textColor\":[\"#1F3D99\",\"#000000\"],\"backgroundColor\":[\"#99E2F2\",\"#F2E899\"]},\"rowOdd\":{\"textColor\":[\"#000000\",\"#1F3D99\"],\"backgroundColor\":[\"#99F2DF\",\"#D9F299\"]},\"headers\":{\"textColor\":[\"#FFFFFF\",\"#FFFFFF\"],\"backgroundColor\":[\"#2FBA38\",\"#2F3ABA\"]}},\"addRowOn\":\"top\",\"flows\":[{\"ID\":\"flow1\",\"name\":\"autobus\",\"filters\":null,\"columnKeys\":[\"0\",\"1\",\"2\"],\"columnFormats\":null,\"maxItems\":50,\"maxItemsSaved\":500}],\"data\":[{\"ID\":\"flow1\",\"records\":[{\"norrisRecordID\":\"flow1_1435222498294_1\",\"value\":[837,45.39281463623,11.871248245239]},{\"norrisRecordID\":\"flow1_1435222503296_2\",\"value\":[875,45.426074981689,11.907616615295]},{\"norrisRecordID\":\"flow1_1435222508297_3\",\"value\":[805,45.386032104492,11.865413665771]},{\"norrisRecordID\":\"flow1_1435222513301_4\",\"value\":[837,45.397495269775,11.874231338501],\"appearance\":[{\"bg\":\"#FFAAFF\",\"text\":\"#FFAAAA\"},{\"bg\":\"#FFFFFF\",\"text\":\"#000000\"},{\"bg\":\"#FFAAFF\",\"text\":\"#FFAACC\"}]},{\"norrisRecordID\":\"flow1_1435222518305_5\",\"value\":[837,45.397689819336,11.874346733093]},{\"norrisRecordID\":\"flow1_1435222523306_6\",\"value\":[880,45.412399291992,11.878684997559],\"appearance\":[{\"bg\":\"#FFAAFF\",\"text\":\"#FFAAAA\"},{\"bg\":\"#FFAAFF\",\"text\":\"#FFABBA\"},{\"bg\":\"#FFAAFF\",\"text\":\"#FFAACC\"}]},{\"norrisRecordID\":\"flow1_1435222528313_7\",\"value\":[875,45.431159973145,11.914177894592],\"appearance\":[{\"bg\":\"#FFAAFF\",\"text\":\"#FFAAAA\"},{\"bg\":\"#FFAAFF\",\"text\":\"#FFABBA\"},{\"bg\":\"#FFAAFF\",\"text\":\"#FFAACC\"}]}]}]}");
        table.updateParameters(json);
        Assert.assertEquals(table.getTitle(), "Tab");
        Assert.assertEquals(table.getAddRowOn(), "top");
        Assert.assertEquals(table.getNumberOfColumns(), 4);
        Assert.assertEquals(table.getHeaderValue(3) , "ciaoo");
        Assert.assertEquals(table.sortByCol(), "ciaoo");
        Assert.assertEquals(table.getSortOrder(), "ASC");
        Assert.assertEquals(table.getFlowList().get(0).getFlowId(), "flow1");
    }

    public void testAddFlow() throws Exception {
        Assert.assertEquals(table.getFlowList().size(), 0);
        String dataString="{\"ID\":\"flow1\",\"name\":\"newRecord\"}}";
        json = new JSONObject(dataString);
        table.addFlow(json);
        Assert.assertEquals(table.getFlowList().size(), 1);
    }
}