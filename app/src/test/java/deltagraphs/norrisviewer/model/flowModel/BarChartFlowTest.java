package deltagraphs.norrisviewer.model.flowModel;

import android.util.Log;

import junit.framework.TestCase;

import org.json.*;
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
 */
public class BarChartFlowTest {

    /*
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
    */

    JSONArray array;
    JSONObject obj;
    JSONArray rec;
    JSONObject data;
    BarChartFlow flow;


    @Test
    public void testGetRecordSize() throws Exception {

    }

    @Test
    public void testCreateFlow() throws Exception {
        //try {
            /*array= new JSONArray().put(0, 45.399394989014).put(1, 11.877456665039);
            obj = new JSONObject().put("norrisRecordID","flow114331692096017");
            obj.put("markerID", 837);
            obj.put("value", array);
            rec = new JSONArray().put(0, obj);
            data = new JSONObject().put("records", rec);
*/
            //String dataString= "{\"ID\" : \"flusso1\", \"records\" : [{ \"norrisRecordID\" : \"flusso1201505081\", \"value\": [1, 25]}, {\"norrisRecordID\" : \"flusso1201505082\",\"value\": [2, 15]}]}";
            //System.out.println(dataString);
            try {
               data = new JSONObject("{\"records\": []}");
                     System.out.println( new JSONObject().put("JSON", "Hello, World!").toString());
            data.put("ID", "ciao");
                String x= data.getString("ID");
                if(data.has("ID"))
                System.out.println(data.toString());
            else
                System.out.println("porco******");
            }catch (JSONException e){e.printStackTrace();}
            if(data.has("records"))
                System.out.println(data.toString());
            else
                System.out.println("porco*****");
            flow = new BarChartFlow(data);
            flow.createFlow(data);
//            assertEquals(2, flow.getRecordSize());
        //} catch (JSONException e) {
       //     e.printStackTrace();
       // }
    }

    @Test
    public void testUpdateFlow() throws Exception {

    }

    @Test
    public void testDeleteRecordList() throws Exception {

    }

    @Test
    public void testAddRecord() throws Exception {

    }

    @Test
    public void testAddRecords() throws Exception {

    }

    @Test
    public void testUpdateRecord() throws Exception {

    }

    @Test
    public void testSearchRecordIndex() throws Exception {

    }

    @Test
    public void testDeleteRecord() throws Exception {

    }
}