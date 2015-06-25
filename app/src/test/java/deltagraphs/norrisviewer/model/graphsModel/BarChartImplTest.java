package deltagraphs.norrisviewer.model.graphsModel;

import junit.framework.TestCase;

import org.json.JSONObject;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

import deltagraphs.norrisviewer.model.flowModel.BarChartFlow;
import deltagraphs.norrisviewer.model.flowModel.FlowModel;

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
 */

public class BarChartImplTest extends TestCase {

    JSONObject data;
    Observer x=new Observer() {
        @Override
        public void update(Observable observable, Object data) {

        }
    };
    BarChartImpl barChart;

    @Before
    public void setUp() throws Exception {
        super.setUp();
        barChart = new BarChartImpl(x);
    }

    @Test
    public void testSetParameters() throws Exception {
        String dataString ="{ID:\"bar1\",title:\"BARRE\",type:\"BarChart\",height:600,width:1000,enableLegend:true,legendOnPoint:true,backgroundColor:\"#FFFFFF\",grid:true,groupingControl:false,barOrientation:\"V\",legend:{position:\"NE\",fontColor:\"#000000\",backgroundColor:\"#FFFFFF\"},xAxis:{name:\"tempo\",color:\"#000000\",maxIndex:7,minIndex:0,ticks:10,scale:\"logarithmic\"},yAxis:{name:\"pressione\",color:\"#000000\",maxIndex:null,minIndex:null,ticks:10,scale:\"linear\"},headers:[\"h1\",\"h2\",\"h3\",\"h4\",\"h5\"],flows:[{ID:\"flow1\",name:\"grafico tempo-pressione\",filters:null,indexKey:\"tempo\",valueKey:\"pressione\",indexFormat:null,\"valueFormat\":null,flowColor:\"#33AAFF\"},{ID:\"flow2\",name:\"grafico tempo-umidita\",filters:null,indexKey:\"tempo\",valueKey:\"umidita\",indexFormat:null,valueFormat:null,flowColor:\"#22CC55\"}]},data:[{ID:\"flow1\",records:[{norrisRecordID:\"flow1_1434881604073_1\",value:[1,3]},{norrisRecordID:\"flow1_1434881604073_2\",value:[2,2]},{norrisRecordID:\"flow1_1434881604073_3\",value:[3,3]},{norrisRecordID:\"flow1_1434881604073_4\",value:[4,5]},{norrisRecordID:\"flow1_1434881604073_5\",value:[5,7]}]},{ID:\"flow2\",records:[{norrisRecordID:\"flow2_1434881604073_1\",value:[1,7]},{norrisRecordID:\"flow2_1434881604073_2\",value:[2,4]},{\"norrisRecordID\":flow2_1434881604073_3,value:[3,5]},{norrisRecordID:\"flow2_1434881604073_4\",value:[4,2]},{norrisRecordID:\"flow2_1434881604073_5\",value:[5,4]}]}]}";
        data = new JSONObject(dataString);
        barChart.setParameters(data);
        Assert.assertEquals(barChart.getTitle(), "BARRE");
        Assert.assertEquals(barChart.getAxisX().getName(), "tempo");
        Assert.assertEquals(barChart.getAxisX().getTicks(), 10);
        Assert.assertEquals(barChart.getAxisY().getName(), "pressione");
        Assert.assertEquals(barChart.getAxisY().getTicks(), 10);
        Assert.assertEquals(barChart.getBarOrientation(), "V");
        Assert.assertEquals(barChart.getHeaders().size(), 5);
        Assert.assertEquals(barChart.getFlowList().size(), 2);


    }

    @Test
    public void testUpdateParameters() throws Exception {
        String dataString ="{ID:\"bar1\",title:\"BARRE\",type:\"BarChart\",height:600,width:1000,enableLegend:true,legendOnPoint:true,backgroundColor:\"#FFFFFF\",grid:true,groupingControl:false,barOrientation:\"V\",legend:{position:\"NE\",fontColor:\"#000000\",backgroundColor:\"#FFFFFF\"},xAxis:{name:\"tempo\",color:\"#000000\",maxIndex:7,minIndex:0,ticks:10,scale:\"logarithmic\"},yAxis:{name:\"pressione\",color:\"#000000\",maxIndex:null,minIndex:null,ticks:10,scale:\"linear\"},headers:[\"h1\",\"h2\",\"h3\",\"h4\",\"h5\"],flows:[{ID:\"flow1\",name:\"grafico tempo-pressione\",filters:null,indexKey:\"tempo\",valueKey:\"pressione\",indexFormat:null,\"valueFormat\":null,flowColor:\"#33AAFF\"},{ID:\"flow2\",name:\"grafico tempo-umidita\",filters:null,indexKey:\"tempo\",valueKey:\"umidita\",indexFormat:null,valueFormat:null,flowColor:\"#22CC55\"}]},data:[{ID:\"flow1\",records:[{norrisRecordID:\"flow1_1434881604073_1\",value:[1,3]},{norrisRecordID:\"flow1_1434881604073_2\",value:[2,2]},{norrisRecordID:\"flow1_1434881604073_3\",value:[3,3]},{norrisRecordID:\"flow1_1434881604073_4\",value:[4,5]},{norrisRecordID:\"flow1_1434881604073_5\",value:[5,7]}]},{ID:\"flow2\",records:[{norrisRecordID:\"flow2_1434881604073_1\",value:[1,7]},{norrisRecordID:\"flow2_1434881604073_2\",value:[2,4]},{\"norrisRecordID\":flow2_1434881604073_3,value:[3,5]},{norrisRecordID:\"flow2_1434881604073_4\",value:[4,2]},{norrisRecordID:\"flow2_1434881604073_5\",value:[5,4]}]}]}";
        data = new JSONObject(dataString);
        barChart.setParameters(data);
        dataString ="{ID:\"bar1\",title:\"BARRE\",type:\"BarChart\",height:600,width:1000,enableLegend:true,legendOnPoint:true,backgroundColor:\"#FFFFFE\",grid:true,groupingControl:false,barOrientation:\"H\",legend:{position:\"NE\",fontColor:\"#000000\",backgroundColor:\"#FFFFFF\"},xAxis:{name:\"temp\",color:\"#000000\",maxIndex:5,minIndex:1,ticks:9,scale:\"logarithmic\"},yAxis:{name:\"pression\",color:\"#000000\",maxIndex:0,minIndex:0,ticks:9,scale:\"linear\"},headers:[\"h1\",\"h2\",\"h3\",\"h4\",\"h5\", \"h6\"],flows:[{ID:\"flow1\",name:\"grafico tempo-pressione\",filters:null,indexKey:\"tempo\",valueKey:\"pressione\",indexFormat:null,\"valueFormat\":null,flowColor:\"#33AAFF\"},{ID:\"flow2\",name:\"grafico tempo-umidita\",filters:null,indexKey:\"tempo\",valueKey:\"umidita\",indexFormat:null,valueFormat:null,flowColor:\"#22CC55\"}]},data:[{ID:\"flow1\",records:[{norrisRecordID:\"flow1_1434881604073_1\",value:[1,3]},{norrisRecordID:\"flow1_1434881604073_2\",value:[2,2]},{norrisRecordID:\"flow1_1434881604073_3\",value:[3,3]},{norrisRecordID:\"flow1_1434881604073_4\",value:[4,5]},{norrisRecordID:\"flow1_1434881604073_5\",value:[5,7]}]},{ID:\"flow2\",records:[{norrisRecordID:\"flow2_1434881604073_1\",value:[1,7]},{norrisRecordID:\"flow2_1434881604073_2\",value:[2,4]},{\"norrisRecordID\":flow2_1434881604073_3,value:[3,5]},{norrisRecordID:\"flow2_1434881604073_4\",value:[4,2]},{norrisRecordID:\"flow2_1434881604073_5\",value:[5,4]}]}]}";
        data = new JSONObject(dataString);
        barChart.updateParameters(data);
        Assert.assertEquals(barChart.getAxisX().getName(), "temp");
        Assert.assertEquals(barChart.getAxisX().getTicks(), 9);
        Assert.assertEquals(barChart.getAxisY().getName(), "pression");
        Assert.assertEquals(barChart.getAxisY().getTicks(), 9);
        Assert.assertEquals(barChart.getBarOrientation(), "H");
        Assert.assertEquals(barChart.getHeaders().size(), 6);
        Assert.assertEquals(barChart.getFlowList().size(), 2);
    }

    @Test
    public void testAddFlow() throws Exception {
        String dataString = "{ ID: \"ciao\", name: \"hoho\", records: [{ norrisRecordID : \"flusso1201505081\", value: [\"1\", 25]}, {norrisRecordID : \"flusso1201505082\", value: [\"2\", 15]}]}";
        data = new JSONObject(dataString);
        barChart.addFlow(data);
        Assert.assertEquals(barChart.getFlowList().size(), 1);
    }

    @Test
    public void testUpdateFlow() throws Exception {
        String dataString = "{ ID: \"ciao\", name: \"gigi\", records: [{ norrisRecordID : \"flusso1201505081\", value: [\"1\", 25]}, {norrisRecordID : \"flusso1201505082\", value: [\"2\", 15]}]}";
        data = new JSONObject(dataString);
        barChart.addFlow(data);
        dataString = "{ ID: \"ciao\", name: \"hoho\", color: \" grigio\", records: [{ norrisRecordID : \"flusso1201505081\", value: [\"1\", 25]}, {norrisRecordID : \"flusso1201505082\", value: [\"2\", 15]}]}";
        data = new JSONObject(dataString);
        barChart.updateFlow(data);
        Assert.assertEquals(barChart.getFlowList().get(0).getFlowId(), "ciao");
        Assert.assertEquals(barChart.getFlowList().get(0).getFlowName(), "hoho");
    }
}