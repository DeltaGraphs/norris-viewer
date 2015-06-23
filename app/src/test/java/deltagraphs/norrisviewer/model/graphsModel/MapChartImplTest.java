package deltagraphs.norrisviewer.model.graphsModel;/*
 * Name : {Nome del file}.java
 * Module : deltagraphs.norrisviewer.model.graphsModel
 * Location : norrisviewer { Percorso in cui \'e presente il file }
 *
 * History :

 * Version      Date        Programmer         Description
 * ===============================================================
 * 
 * 0.1.0 2015-05-22 Davide Trivellato Codifica di tutti gli attributi e metodi
 *
 * 0.0.1 2015-05-22 Davide Trivellato Creazione file
 *
 * ===============================================================
 *
 */

import junit.framework.TestCase;

import org.json.JSONObject;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Observable;
import java.util.Observer;

public class MapChartImplTest extends TestCase {
    JSONObject data;
    Observer x;
    MapChartImpl mapChart;

    @Before
    public void setUp() throws Exception {
        super.setUp();
        x = new Observer() {
            @Override
            public void update(Observable observable, Object data) {

            }
        };
        mapChart = new MapChartImpl(x);
    }

    @Test
    public void testSetParameters() throws Exception {
        String dataString="{\"ID\":\"map1\",\"title\":\"APS\",\"type\":\"MapChart\",\"height\":600,\"width\":1000,\"enableLegend\":true,\"legendOnPoint\":false,\"latitude\":45.4113311,\"longitude\":11.8876318,\"mapType\":\"terrain\",\"mapWidth\":2000," +
                "\"mapHeight\":2000,\"legend\":{\"position\":\"W\",\"fontColor\":\"#00AA00\",\"backgroundColor\":\"#FFAAFF\"},\"flows\":[{\"ID\":\"flow1\",\"name\":\"linea 22\",\"filters\":null,\"longitudeKey\":\"2\",\"latitudeKey\":\"1\",\"objectKey\":\"0\"," +
                "\"longitudeFormat\":\"coordinates\",\"latitudeFormat\":\"coordinates\",\"marker\":{\"type\":\"shape\",\"shape\":\"bus\",\"color\":\"#FFC4F6\"},\"trace\":{\"type\":\"poly\",\"coordinates\":[[45.42946,11.9409],[45.42941,11.94081]," +
                "[45.42955,11.94065]],\"strokeColor\":\"#DC271C\",\"fillColor\":\"#3a6d99\"},\"trailLength\":3,\"maxItemsSaved\":500}]},\"data\":[{\"ID\":\"flow1\",\"records\":[{\"norrisRecordID\":\"flow1_1434961647052_2\",\"markerID\":805," +
                "\"value\":[45.414463043213,11.875088691711]},{\"norrisRecordID\":\"flow1_1434961647053_8\",\"markerID\":837,\"value\":[45.421276092529,11.882029533386]},{\"norrisRecordID\":\"flow1_1434961647052_1\",\"markerID\":875,\"value\":[45.426425933838,11.908160209656]}," +
                "{\"norrisRecordID\":\"flow1_1434961647053_7\",\"markerID\":880,\"value\":[45.426040649414,11.906901359558]},{\"norrisRecordID\":\"flow1_1434961647052_5\",\"markerID\":814,\"value\":[45.420963287354,11.880981445312]},{\"norrisRecordID\":\"flow1_1434961647052_6\"," +
                "\"markerID\":845,\"value\":[45.395496368408,11.872955322266]},{\"norrisRecordID\":\"flow1_1434961647052_4\",\"markerID\":835,\"value\":[45.397514343262,11.87842464447]},{\"norrisRecordID\":\"flow1_1434961647052_3\",\"markerID\":867,\"value\":[45.368686676025,11.830775260925]}]}]}";
        data = new JSONObject(dataString);
        mapChart.setParameters(data);
        Assert.assertEquals(mapChart.getMapHeight(), 2000, 0);
        Assert.assertEquals(mapChart.getMapWidth(), 2000, 0);
        Assert.assertEquals(mapChart.getMapType(), "terrain");
        Assert.assertEquals(mapChart.getLegendOnPoint(), false);
        Assert.assertEquals(mapChart.getLatitude(), 45.4113311, 0.0000001);
        Assert.assertEquals(mapChart.getLongitude(), 11.8876318, 0.000001);
        Assert.assertEquals(mapChart.getFlowList().get(0).getFlowId(), "flow1");
        Assert.assertEquals(mapChart.getFlowList().get(0).getFlowName(), "linea 22");
    }

    @Test
    public void testCreateFlow() throws Exception {
        String dataString="{\"ID\":\"flow1\",\"name\":\"linea22\",\"filters\":null,\"longitudeKey\":\"2\",\"latitudeKey\":\"1\",\"objectKey\":\"0\",\"longitudeFormat\":\"coordinates\",\"latitudeFormat\":\"coordinates\",\"marker\":{\"type\":\"shape\",\"shape\":\"bus\",\"color\":\"#FFC4F6\"}}";
        data = new JSONObject(dataString);
        mapChart.addFlow(data);
    }

    @Test
    public void testUpdateParameters() throws Exception {
        String dataString="{\"ID\":\"map1\",\"title\":\"APS\",\"type\":\"MapChart\",\"height\":600,\"width\":1000,\"enableLegend\":true,\"legendOnPoint\":false,\"latitude\":45.4113311,\"longitude\":11.8876318,\"mapType\":\"terrain\",\"mapWidth\":2000," +
                "\"mapHeight\":2000,\"legend\":{\"position\":\"W\",\"fontColor\":\"#00AA00\",\"backgroundColor\":\"#FFAAFF\"},\"flows\":[{\"ID\":\"flow1\",\"name\":\"linea 22\",\"filters\":null,\"longitudeKey\":\"2\",\"latitudeKey\":\"1\",\"objectKey\":\"0\"," +
                "\"longitudeFormat\":\"coordinates\",\"latitudeFormat\":\"coordinates\",\"marker\":{\"type\":\"shape\",\"shape\":\"bus\",\"color\":\"#FFC4F6\"},\"trace\":{\"type\":\"poly\",\"coordinates\":[[45.42946,11.9409],[45.42941,11.94081]," +
                "[45.42955,11.94065]],\"strokeColor\":\"#DC271C\",\"fillColor\":\"#3a6d99\"},\"trailLength\":3,\"maxItemsSaved\":500}]},\"data\":[{\"ID\":\"flow1\",\"records\":[{\"norrisRecordID\":\"flow1_1434961647052_2\",\"markerID\":805," +
                "\"value\":[45.414463043213,11.875088691711]},{\"norrisRecordID\":\"flow1_1434961647053_8\",\"markerID\":837,\"value\":[45.421276092529,11.882029533386]},{\"norrisRecordID\":\"flow1_1434961647052_1\",\"markerID\":875,\"value\":[45.426425933838,11.908160209656]}," +
                "{\"norrisRecordID\":\"flow1_1434961647053_7\",\"markerID\":880,\"value\":[45.426040649414,11.906901359558]},{\"norrisRecordID\":\"flow1_1434961647052_5\",\"markerID\":814,\"value\":[45.420963287354,11.880981445312]},{\"norrisRecordID\":\"flow1_1434961647052_6\"," +
                "\"markerID\":845,\"value\":[45.395496368408,11.872955322266]},{\"norrisRecordID\":\"flow1_1434961647052_4\",\"markerID\":835,\"value\":[45.397514343262,11.87842464447]},{\"norrisRecordID\":\"flow1_1434961647052_3\",\"markerID\":867,\"value\":[45.368686676025,11.830775260925]}]}]}";
        data = new JSONObject(dataString);
        mapChart.setParameters(data);
        dataString="{\"ID\":\"map1\",\"title\":\"APS\",\"type\":\"MapChart\",\"height\":600,\"width\":1000,\"enableLegend\":true,\"legendOnPoint\":true,\"latitude\":49.4113311,\"longitude\":15.8876315,\"mapType\":\"terrain\",\"mapWidth\":2000," +
                "\"mapHeight\":2000,\"legend\":{\"position\":\"W\",\"fontColor\":\"#00AA00\",\"backgroundColor\":\"#FFAAFF\"},\"flows\":[{\"ID\":\"flow3\",\"name\":\"linea 33\",\"filters\":null,\"longitudeKey\":\"2\",\"latitudeKey\":\"1\",\"objectKey\":\"0\"," +
                "\"longitudeFormat\":\"coordinates\",\"latitudeFormat\":\"coordinates\",\"marker\":{\"type\":\"shape\",\"shape\":\"bus\",\"color\":\"#FFC4F6\"},\"trace\":{\"type\":\"poly\",\"coordinates\":[[45.42946,11.9409],[45.42941,11.94081]," +
                "[45.42955,11.94065]],\"strokeColor\":\"#DC271C\",\"fillColor\":\"#3a6d99\"},\"trailLength\":3,\"maxItemsSaved\":500}]},\"data\":[{\"ID\":\"flow1\",\"records\":[{\"norrisRecordID\":\"flow1_1434961647052_2\",\"markerID\":805," +
                "\"value\":[45.414463043213,11.875088691711]},{\"norrisRecordID\":\"flow1_1434961647053_8\",\"markerID\":837,\"value\":[45.421276092529,11.882029533386]},{\"norrisRecordID\":\"flow1_1434961647052_1\",\"markerID\":875,\"value\":[45.426425933838,11.908160209656]}," +
                "{\"norrisRecordID\":\"flow1_1434961647053_7\",\"markerID\":880,\"value\":[45.426040649414,11.906901359558]},{\"norrisRecordID\":\"flow1_1434961647052_5\",\"markerID\":814,\"value\":[45.420963287354,11.880981445312]},{\"norrisRecordID\":\"flow1_1434961647052_6\"," +
                "\"markerID\":845,\"value\":[45.395496368408,11.872955322266]},{\"norrisRecordID\":\"flow1_1434961647052_4\",\"markerID\":835,\"value\":[45.397514343262,11.87842464447]},{\"norrisRecordID\":\"flow1_1434961647052_3\",\"markerID\":867,\"value\":[45.368686676025,11.830775260925]}]}]}";
        data = new JSONObject(dataString);
        mapChart.updateParameters(data);
        Assert.assertEquals(mapChart.getMapHeight(), 2000, 0);
        Assert.assertEquals(mapChart.getMapWidth(), 2000, 0);
        Assert.assertEquals(mapChart.getMapType(), "terrain");
        Assert.assertEquals(mapChart.getLegendOnPoint(), true);
        Assert.assertEquals(mapChart.getLatitude(), 49.4113311, 0.0000001);
        Assert.assertEquals(mapChart.getLongitude(), 15.8876315, 0.0000001);
    }
}
