package deltagraphs.norrisviewer.model.flowModel;

import junit.framework.TestCase;

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

    private BarChartFlow flow;
    JSONObject data;

    @org.junit.Before
    public void initialize() throws JSONException {
        data=new JSONObject("");
        flow = new BarChartFlow(data);
    }

    @org.junit.Test
    public void testCreateFlow() throws JSONException {
        flow.createFlow(new JSONObject("{\"records\":[{\"norrisRecordID\":\"flow114331692096017\",\"markerID\":837,\"value\":[45.399394989014,11.877456665039]}]}"));
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