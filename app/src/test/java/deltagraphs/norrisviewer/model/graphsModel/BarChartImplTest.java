package deltagraphs.norrisviewer.model.graphsModel;

import junit.framework.TestCase;

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
 */public class BarChartImplTest extends TestCase {

    Observer x;
    BarChartImpl barChart;

    public void setUp() throws Exception {
        super.setUp();
        barChart = new BarChartImpl(x);
    }

    public void testSetParameters() throws Exception {
        String data="{\"properties\":{\"ID\":\"bar1\",\"title\":\"BARRE\",\"type\":\"BarChart\",\"height\":600,\"width\":1000,\"enableLegend\":true,\"legendOnPoint\":true,\"backgroundColor\":\"#FFFFFF\",\"grid\":true,\"groupingControl\":false,\"barOrientation\":\"V\",\"legend\":{\"position\":\"NE\",\"fontColor\":\"#000000\",\"backgroundColor\":\"#FFFFFF\"},\"xAxis\":{\"name\":\"tempo\",\"color\":\"#000000\",\"maxIndex\":7,\"minIndex\":0,\"ticks\":10,\"scale\":\"logarithmic\"},\"yAxis\":{\"name\":\"pressione\",\"color\":\"#000000\",\"maxIndex\":null,\"minIndex\":null,\"ticks\":10,\"scale\":\"linear\"},\"headers\":[\"h1\",\"h2\",\"h3\",\"h4\",\"h5\"],\"flows\":[{\"ID\":\"flow1\",\"name\":\"grafico tempo-pressione\",\"filters\":null,\"indexKey\":\"tempo\",\"valueKey\":\"pressione\",\"indexFormat\":null,\"valueFormat\":null,\"flowColor\":\"#33AAFF\"},{\"ID\":\"flow2\",\"name\":\"grafico tempo-umidita\",\"filters\":null,\"indexKey\":\"tempo\",\"valueKey\":\"umidita\",\"indexFormat\":null,\"valueFormat\":null,\"flowColor\":\"#22CC55\"}]},\"data\":[{\"ID\":\"flow1\",\"records\":[{\"norrisRecordID\":\"flow1_1434881604073_1\",\"value\":[1,3]},{\"norrisRecordID\":\"flow1_1434881604073_2\",\"value\":[2,2]},{\"norrisRecordID\":\"flow1_1434881604073_3\",\"value\":[3,3]},{\"norrisRecordID\":\"flow1_1434881604073_4\",\"value\":[4,5]},{\"norrisRecordID\":\"flow1_1434881604073_5\",\"value\":[5,7]}]},{\"ID\":\"flow2\",\"records\":[{\"norrisRecordID\":\"flow2_1434881604073_1\",\"value\":[1,7]},{\"norrisRecordID\":\"flow2_1434881604073_2\",\"value\":[2,4]},{\"norrisRecordID\":\"flow2_1434881604073_3\",\"value\":[3,5]},{\"norrisRecordID\":\"flow2_1434881604073_4\",\"value\":[4,2]},{\"norrisRecordID\":\"flow2_1434881604073_5\",\"value\":[5,4]}]}]}";


    }

    public void testUpdateParameters() throws Exception {

    }

    public void testAddFlow() throws Exception {

    }
}