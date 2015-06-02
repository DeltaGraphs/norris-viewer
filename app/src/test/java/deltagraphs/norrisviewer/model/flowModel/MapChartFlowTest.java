package deltagraphs.norrisviewer.model.flowModel;

import junit.framework.TestCase;

import org.json.JSONArray;
import org.json.JSONException;
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
 * 0.1.0 2015-05-X Enrico Savoca Codifica di tutti gli attributi e i metodi
 *
 * 0.0.1 2015-05-X Enrico Savoca Creazione file
 *
 * ===============================================================
 *
 */public class MapChartFlowTest extends TestCase {

    private MapChartFlow flow;
    JSONArray array;
    JSONObject obj;
    JSONArray rec;
    JSONObject data;

    @org.junit.Before
    public void initialize() throws JSONException {
        try {
            array = new JSONArray().put(0, 45.399394989014).put(1, 11.877456665039);
            obj = new JSONObject().put("norrisRecordID", "flow114331692096017");
            obj.put("markerID", 837);
            obj.put("value", array);
            rec = new JSONArray().put(0, obj);
            data = new JSONObject().put("records", rec);

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }


    @org.junit.Test
    public void testCreateFlow() throws JSONException {
        initialize();

        flow = new MapChartFlow(data);
        flow.createFlow(data);
        Boolean x = false;
        System.out.println("ciaooo " + data.toString());
        if (data.has("records"))
            x = true;
        assertTrue("", x);
        assertEquals(1, flow.getRecordSize());
    }

    public void testUpdateFlow() throws Exception {

    }

    public void testDeleteRecordList() throws Exception {

    }

    public void testAddRecord() throws Exception {

    }

    public void testAddRecords() throws Exception {

    }

    public void testUpdateRecord() throws Exception {

    }

    public void testSearchRecordIndex() throws Exception {

    }

    public void testDeleteRecord() throws Exception {

    }
}