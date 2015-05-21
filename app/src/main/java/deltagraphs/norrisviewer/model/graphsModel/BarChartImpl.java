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

public class BarChartImpl extends Graph implements BarChart{

    private AxisModel axisX;
    private AxisModel axisY;
    private ArrayList<String> headers;
    private String barOrientation;
    private String background;
    private Boolean sortable;
    private Boolean grid;
    private Boolean legendOnPoint;

    public AxisModel getAxisX() { return axisX; }
    public AxisModel getAxisY() { return axisY; }
    public ArrayList<String> getHeaders() { return headers; }
    public String getBackground() { return background; }
    public String getBarOrientation() { return barOrientation; }
    public Boolean getSortable() { return sortable; }
    public Boolean getGrid() { return grid; }
    public Boolean getLegendOnPoint(){ return legendOnPoint; }

    public void setBarChartImpl(JSONObject obj, String signal){JSONParser(obj, signal);}

    @Override
    public void setParameters(JSONObject data) {
       try {
           title = data.getString("title");
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
               JSONObject flow = jsonFlows.getJSONObject(i);
               addFlow(flow);
           }
       }catch (JSONException e){}
    }

    @Override
    public void updateParameters(JSONObject data) {
        try {
            if (data.has("title"))
                title = data.getString("title");
            if(data.has("backgroundColor"))
                background = data.getString("backgroundColor");
            if(data.has("barOrientation"))
                barOrientation = data.getString("barOrientation");
            if(data.has("sortable"))
                sortable = data.getBoolean("sortable");
            if(data.has("grid"))
                grid = data.getBoolean("grid");
            if(data.has("legendOnPoint"))
                legendOnPoint = data.getBoolean("legendOnPoint");
            if(data.has("headers")){
                JSONArray jsonHeaders = data.getJSONArray("headers");
                int length = jsonHeaders.length();
                if (length > 0) {
                    for (int i = 0; i < length; i++) {
                       headers.add(jsonHeaders.getString(i));
                    }
                }
            }
            //changes to axises
            if(data.has("xAxis")) {
                JSONObject xAxis = data.getJSONObject("xAxis");
                axisX = new AxisModel(xAxis);
            }
            if(data.has("yAxis")) {
                JSONObject yAxis = data.getJSONObject("yAxis");
                axisY = new AxisModel(yAxis);
            }
        }catch(Exception e){}
    }

    @Override
    public void addFlow(JSONObject data) {
        try {
            String flowId = data.getString("ID");
            String name = data.getString("name");
            String color = data.getString("color");
            flowList.add(new BarChartFlow(flowId, name, color));
        }catch(JSONException e){}
    }

    @Override
    public void updateFlow(JSONObject data) {
        try {
            String flowId = data.getString("ID");
            int index = searchFlowIndex(flowId);
            flowList.get(index).updateFlow(data);

        } catch (JSONException e) {}
    }

    @Override
    public void setRecords(JSONObject record) {
        try {
            String flowID = record.getString("ID");
            int index = searchFlowIndex(flowID);
            if (index != -1) {
               flowList.get(index).addRecords(record);
            }
        } catch (JSONException e) {}
    }

}
