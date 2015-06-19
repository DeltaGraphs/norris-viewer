package deltagraphs.norrisviewer.model.flowModel;

import junit.framework.TestCase;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Assert;
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
/*
    @org.junit.Before
    public void setUp() throws Exception {
        super.setUp();
        data = new JSONObject("{records: [{norrisRecordID : \"flusso1201505081\", value: [1, 25]}]");
        flow = new BarChartFlow(data);

    data = new JSONObject().put("records",
                                        new JSONArray().
    put(0, new JSONObject().
    put("markerID", 837).
    put("norrisRecordID", "flow114331692096017").
    put("value", new JSONArray().
    put(0, 45.399394989014).
    put(1, 11.877456665039)
    )
            )
            );

/*
    @Test
    public void testGetRecordSize() throws Exception {

    }
*//*
    @Test
    public void testCreateFlow() throws Exception {

            String dataString = "{ [{ \"norrisRecordID\" : \"flusso1201505081\", \"value\": [1, 25]}, {\"norrisRecordID\" : \"flusso1201505082\",\"value\": [2, 15]}]}";
            data = new JSONObject().put("records", dataString);
        System.out.println(dataString);
            flow = new BarChartFlow(data);
            flow.createFlow(data);
            Assert.assertEquals(1, flow.getRecordSize());

    }

    public void testUpdateFlow() throws Exception {

    }

    public void testDeleteRecordList() throws Exception {

    }

    public void testAddRecord() throws Exception {

    }
*/
    @Test
    public void testAddRecords() throws Exception {
        Assert.assertTrue("", true);
    }
/*
    public void testUpdateRecord() throws Exception {

    }

    public void testDeleteRecord() throws Exception {

    }
    */
}