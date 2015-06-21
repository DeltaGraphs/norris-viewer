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

import java.util.Observer;

public class LineChartImplTest extends TestCase {

    JSONObject data;
    Observer x;
    LineChartImpl lineChart;

    public void setUp() throws Exception {
        super.setUp();
        lineChart = new LineChartImpl(x);
    }

    @Before
    public void testSetParameters() throws Exception {
        String dataString="{\"properties\":{\"ID\":\"line1\",\"title\":\"LINEE\",\"type\":\"LineChart\",\"height\":600,\"width\":1000,\"enableLegend\":true,\"legendOnPoint\":true,\"backgroundColor\":\"#FFFFFF\",\"viewFinder\":true,\"horizontalGrid\":true,\"verticalGrid\":true," +
                "\"legend\":{\"position\":\"NE\",\"fontColor\":\"#000000\",\"backgroundColor\":\"#FFFFFF\"},\"xAxis\":{\"name\":\"tempo\",\"color\":\"#000000\",\"maxIndex\":null,\"minIndex\":null,\"ticks\":10,\"scale\":\"linear\"},\"yAxis\":{\"name\":\"temperatura\"," +
                "\"color\":\"#000000\",\"maxIndex\":null,\"minIndex\":null,\"ticks\":10,\"scale\":\"linear\"},\"flows\":[]},\"data\":[]}";
        data = new JSONObject(dataString);
        lineChart.setParameters(data);
        Assert.assertEquals(lineChart.getViewFinder(), true);
        Assert.assertEquals(lineChart.getHorizontalGrid(), true);
        Assert.assertEquals(lineChart.getVerticalGrid(), true);
        Assert.assertEquals(lineChart.getHorizontalGrid(), true);
        Assert.assertEquals(lineChart.getBackground(), "#FFFFFF");
    }

    public void testUpdateParameters() throws Exception {

    }

    public void testAddFlow() throws Exception {

    }
}
