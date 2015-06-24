package deltagraphs.norrisviewer.model.graphsModel;/*
 * Name : {Nome del file}.java
 * Module : deltagraphs.norrisviewer.model.graphsModel
 * Location : norrisviewer { Percorso in cui \'e presente il file }
 *
 * History :

 * Version      Date        Programmer         Description
 * ===============================================================
 * 
 * 0.1.0 2015-05-21 Davide Trivellato Codifica di tutti gli attributi e metodi
 *
 * 0.0.1 2015-05-21 Davide Trivellato Creazione file
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

public class LineChartImplTest extends TestCase {

    JSONObject data;
    Observer x;
    LineChartImpl lineChart;

    @Before
    public void setUp() throws Exception {
        super.setUp();
        x = new Observer() {
            @Override
            public void update(Observable observable, Object data) {

            }
        };
        lineChart = new LineChartImpl(x);
    }

    @Test
    public void testSetParameters() throws Exception {
        String dataString="{\"ID\":\"line1\",\"title\":\"LINEE\",\"type\":\"LineChart\",\"height\":600,\"width\":1000,\"enableLegend\":true,\"legendOnPoint\":true,\"backgroundColor\":\"#FFFFFF\",\"viewFinder\":true,\"horizontalGrid\":true,\"verticalGrid\":true," +
                "\"legend\":{\"position\":\"NE\",\"fontColor\":\"#000000\",\"backgroundColor\":\"#FFFFFF\"},\"xAxis\":{\"name\":\"tempo\",\"color\":\"#000000\",\"maxIndex\":null,\"minIndex\":null,\"ticks\":10,\"scale\":\"linear\"},\"yAxis\":{\"name\":\"temperatura\"," +
                "\"color\":\"#000000\",\"maxIndex\":null,\"minIndex\":null,\"ticks\":10,\"scale\":\"linear\"},\"flows\":[]},\"data\":[]}";
        data = new JSONObject(dataString);
        lineChart.setParameters(data);
        Assert.assertEquals(lineChart.getViewFinder(), true);
        Assert.assertEquals(lineChart.getVerticalGrid(), true);
        Assert.assertEquals(lineChart.getHorizontalGrid(), true);
        Assert.assertEquals(lineChart.getBackground(), "#FFFFFF");
        Assert.assertEquals(lineChart.getAxisX().getName(), "tempo");
        Assert.assertEquals(lineChart.getAxisX().getTicks(), 10);
        Assert.assertEquals(lineChart.getAxisY().getName(), "temperatura");
        Assert.assertEquals(lineChart.getAxisY().getTicks(), 10);

    }

    @Test
    public void testUpdateParameters() throws Exception {
        String dataString="{\"ID\":\"line1\",\"title\":\"LINEE\",\"type\":\"LineChart\",\"height\":600,\"width\":1000,\"enableLegend\":true,\"legendOnPoint\":true,\"backgroundColor\":\"#FFFFFF\",\"viewFinder\":true,\"horizontalGrid\":true,\"verticalGrid\":true," +
                "\"legend\":{\"position\":\"NE\",\"fontColor\":\"#000000\",\"backgroundColor\":\"#FFFFFF\"},\"xAxis\":{\"name\":\"tempo\",\"color\":\"#000000\",\"maxIndex\":null,\"minIndex\":null,\"ticks\":10,\"scale\":\"linear\"},\"yAxis\":{\"name\":\"temperatura\"," +
                "\"color\":\"#000000\",\"maxIndex\":null,\"minIndex\":null,\"ticks\":10,\"scale\":\"linear\"},\"flows\":[]},\"data\":[]}";
        data = new JSONObject(dataString);
        lineChart.setParameters(data);
        dataString="{\"ID\":\"line1\",\"title\":\"LINEE\",\"type\":\"LineChart\",\"height\":600,\"width\":1000,\"enableLegend\":true,\"legendOnPoint\":true,\"backgroundColor\":\"#FFCCFF\",\"viewFinder\":false,\"horizontalGrid\":false,\"verticalGrid\":true," +
                "\"legend\":{\"position\":\"NE\",\"fontColor\":\"#000000\",\"backgroundColor\":\"#FFFFFF\"},\"xAxis\":{\"name\":\"tempo\",\"color\":\"#000000\",\"maxIndex\":7,\"minIndex\":2,\"ticks\":10,\"scale\":\"linear\"},\"yAxis\":{\"name\":\"temperatura\"," +
                "\"color\":\"#000000\",\"maxIndex\":5,\"minIndex\":3,\"ticks\":20,\"scale\":\"linear\"},\"flows\":[]},\"data\":[]}";
        data = new JSONObject(dataString);
        lineChart.updateParameters(data);
        Assert.assertEquals(lineChart.getViewFinder(), false);
        Assert.assertEquals(lineChart.getVerticalGrid(), true);
        Assert.assertEquals(lineChart.getHorizontalGrid(), false);
        Assert.assertEquals(lineChart.getBackground(), "#FFCCFF");
        Assert.assertEquals(lineChart.getAxisX().getName(), "tempo");
        Assert.assertEquals(lineChart.getAxisX().getTicks(), 10);
        Assert.assertEquals(lineChart.getAxisY().getName(), "temperatura");
        Assert.assertEquals(lineChart.getAxisY().getTicks(), 20);
    }

    @Test
    public void testAddFlow() throws Exception {
        String dataString = "{\"ID\":\"flow1\",\"name\":\"grafico tempo-temperatura\",\"marker\":\"triangle\"," +
                "\"flowColor\":\"#B9D3EE\",\"area\":false,\"maxItems\":50},\"records\":[]}";
        data = new JSONObject(dataString);
        lineChart.addFlow(data);
        Assert.assertEquals(lineChart.getFlowList().size(), 1);
    }

    @Test
    public void testUpdateFlow() throws Exception {
        String dataString = "{\"ID\":\"flow1\",\"name\":\"grafico tempo-temperatura\",\"marker\":\"triangle\"," +
                "\"flowColor\":\"#B9D3EE\",\"area\":false,\"maxItems\":50},\"records\":[]}";
        data = new JSONObject(dataString);
        lineChart.addFlow(data);
        dataString = "{\"ID\":\"flow1\",\"name\":\"grafico spazio-tempo\",\"marker\":\"circle\"," +
                "\"flowColor\":\"#B99999\",\"area\":false,\"maxItems\":60},\"records\":[]}";
        data = new JSONObject(dataString);
        lineChart.updateFlow(data);
        Assert.assertEquals(lineChart.getFlowList().get(0).getFlowName(), "grafico spazio-tempo");
    }
}
