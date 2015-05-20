package deltagraphs.norrisviewer.model.graphsModel;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import deltagraphs.norrisviewer.model.flowModel.BarChartFlow;

/*
 * Name : BarChartImpl.java
 * Module : deltagraphs.norrisviewer.model.graphsModel
 * Location : norrisviewer\model\graphsModel
 *
 * History :

 * Version Date Programmer Description
 * ===============================================================
 *
 * 0.1.0 2015-05-15 Enrico Savoca Codifica di tutti gli attributi e i metodi
 *
 * 0.0.1 2015-05-15 Enrico Savoca Creazione file
 *
 * ===============================================================
 *
 */

public class BarChartImpl extends Graph{

    private AxisModel axisX;
    private AxisModel axisY;
    private ArrayList<String> headers;
    private String barOrientation;
    private String background;
    private Boolean sortable;
    private Boolean grid;
    private Boolean legendOnPoint;

    public BarChartImpl(){}

    public void setBarChartImpl(JSONObject obj, String signal){JSONParser(obj, signal);}

    @Override
    public void setParameters(JSONObject data) {
       try {
           setTitle(data.getString("title"));
           background = data.getString("backgroundColor");
           barOrientation = data.getString("barOrientation");
           sortable = data.getBoolean("sortable");
           grid = data.getBoolean("grid");
           legendOnPoint = data.getBoolean("legendOnPoint");
           JSONArray jsonHeaders = data.getJSONArray("headers");
           int length = jsonHeaders.length();
           if (length > 0) {
               for (int i = 0; i < length; i++) {
                   headers.add(jsonHeaders.getString(i));
               }
           }
           //changes to axises
           JSONObject xAxis = data.getJSONObject("xAxis");
           axisX = new AxisModel(xAxis);
           JSONObject yAxis = data.getJSONObject("yAxis");
           axisY = new AxisModel(yAxis);
           //changes to flow params
           JSONArray jsonFlows = data.getJSONArray("flows");
           int flowLenght = jsonFlows.length();
           for(int i=0; i< flowLenght; i++){
               String flowId = jsonFlows.getJSONObject(i).getString("ID");
               String name = jsonFlows.getJSONObject(i).getString("name");
               String color = jsonFlows.getJSONObject(i).getString("color");
               addFlow(new BarChartFlow(flowId, name, color));
           }


       }catch (JSONException e){}
    }


    @Override
    public void setData(JSONObject record) {
        String flowID = null;
        try {
            flowID = record.getString("ID");
        } catch (JSONException e) {
        }

        int index = -1;
        while ((index < flowList.size()) && (flowList.get(index).getFlowId().equals(flowID))) {
            index++;
        }
        if (index != -1) {
            try {
                JSONArray jsonRecords = record.getJSONArray("records");
                int recordLength = jsonRecords.length();
                for (int i = 0; i < recordLength; i++) {
                    flowList.get(index).addRecord(jsonRecords.getJSONObject(i));
                }
            } catch (JSONException e) {
            }
        }
    }


    public AxisModel getAxisX() { return axisX; }
    public AxisModel getAxisY() { return axisY; }
    public ArrayList<String> getHeaders() { return headers; }
    public String getBackground() { return background; }
    public String getBarOrientation() { return barOrientation; }
    public Boolean getSortable() { return sortable; }
    public Boolean getGrid() { return grid; }
    public Boolean getLegendOnPoint(){ return legendOnPoint; }

    private void JSONParser(JSONObject obj, String signal){
        try{
            switch (signal){
                case "configGraph":{
                    JSONObject properties = obj.getJSONObject("properties");
                    setParameters(properties);
                    JSONObject data = obj.getJSONObject("data");
                    setData(data);
                    break;
                }
                case "updateGraphProp":{



                    break;
                }
            }


        }catch(JSONException e){
            return;
        }
    }

}
