package deltagraphs.norrisviewer.model.graphsModel;

import android.util.Log;

import junit.framework.TestCase;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Observable;
import java.util.Observer;


/*
 * Name : GraphTest.java
 * Module : deltagraphs.norrisviewer.model.graphsModel
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
 */public class GraphTest extends TestCase {
/*
    public JSONObject json;
    public String command = ("{\"properties\":{\"ID\":\"map1\",\"title\":\"APS\",\"type\":\"MapChart\",\"height\":600,\"width\":1000,\"enableLegend\":true,\"legend\":{\"position\":\"NE\",\"fontColor\":\"#000000\",\"backgroundColor\":\"#FFFFFF\"},\"latitude\":45.42533493042,\"longitude\":45.42533493042,\"mapType\":\"roadmap\",\"mapWidth\":2000,\"mapHeight\":2000,\"legendOnPoint\":true,\"flows\":[{\"ID\":\"flow1\",\"name\":\"linea 22\",\"filters\":null,\"longitudeKey\":\"2\",\"latitudeKey\":\"1\",\"objectKey\":\"0\",\"longitudeFormat\":\"coordinates\",\"latitudeFormat\":\"coordinates\",\"marker\":{\"type\":\"shape\",\"shape\":\"circle\",\"icon\":\"null\",\"text\":\"null\",\"color\":\"#000\"},\"trace\":{\"type\":\"none\",\"coordinates\":[]},\"trailLength\":3,\"maxItemsSaved\":500}]},\"data\":[{\"ID\":\"flow1\",\"records\":[{\"norrisRecordID\":\"flow114331513389434\",\"markerID\":814,\"value\":[45.420356750488,11.879591941833]},{\"norrisRecordID\":\"flow114331513389410\",\"markerID\":875,\"value\":[45.425243377686,11.900855064392]},{\"norrisRecordID\":\"flow114331513389432\",\"markerID\":867,\"value\":[45.368156433105,11.830556869507]},{\"norrisRecordID\":\"flow114331513389437\",\"markerID\":837,\"value\":[45.424633026123,11.885098457336]},{\"norrisRecordID\":\"flow114331513389436\",\"markerID\":880,\"value\":[45.427024841309,11.909913063049]},{\"norrisRecordID\":\"flow114331513389431\",\"markerID\":805,\"value\":[45.416053771973,11.876558303833]},{\"norrisRecordID\":\"flow114331513389435\",\"markerID\":845,\"value\":[45.397495269775,11.874231338501]},{\"norrisRecordID\":\"flow114331513389433\",\"markerID\":835,\"value\":[45.397827148438,11.87478351593]}]}]}");
    //MapChartImpl mapChartMock= new MapChartImpl();

    public GraphTest() {
        try {

            json = new JSONObject(command);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    /*class Mock extends Graph{
        @Override
        public void setParameters(JSONObject data) {}
        @Override
        public void updateParameters(JSONObject data) {}

        @Override
        public void addFlow(JSONObject flow) {
            flowList.add(new MapChartFlow(flow));
        }
    }

    class MockObserver implements Observer {

        @Override
        public void update(Observable observable, Object data) {

        }
    }


    public void testGetFlowList() throws Exception {

    }

    @org.junit.After
    public void testSetGraph() throws Exception {
        /*mapChartMock = new MapChartImpl();
        MockObserver obs = new MockObserver();
        mapChartMock.setGraph(json, "configGraph", obs);
        Log.d(mapChartMock.getTitle(),"");
        assertEquals("APS", mapChartMock.getTitle());
        assertEquals("MapChart", mapChartMock.getMapType());
        assertEquals(45.42533493042, mapChartMock.getLatitude());
        assertEquals(45.42533493042, mapChartMock.getLongitude());
        assertEquals(true, mapChartMock.getLegendOnPoint().booleanValue());
        assertEquals(2000, mapChartMock.getMapWidth());
        assertEquals(2000, mapChartMock.getMapHeight());
    }
*/
    public void testAddFlow() throws Exception {
        assertTrue("", true);
    }
/*
    public void testUpdateFlow() throws Exception {

    }

    public void testSetRecords() throws Exception {

    }

    public void testDeleteFlow() throws Exception {

    }
    */
}