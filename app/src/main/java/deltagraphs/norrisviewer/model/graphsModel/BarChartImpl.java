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
               String flowId = jsonFlows.getJSONObject(i).getString("ID");
               String name = jsonFlows.getJSONObject(i).getString("name");
               String color = jsonFlows.getJSONObject(i).getString("color");
               flowList.add(new BarChartFlow(flowId, name, color));
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
    public void updateFlowProp(JSONObject data) {
        int index = -1;
        try {
            String flowID = data.getString("ID");

        while ((index < flowList.size()) && (flowList.get(index).getFlowId().equals(flowID))){
            index ++;
        }
        if(index != -1) {
            String name = data.getString("name");
            String colour = data.getString("color");
            flowList.remove(index);
            flowList.get(index).setFlowName(name);
            ((BarChartFlow)flowList.get(index)).setFlowColour(colour);
        }//else eccezione
        } catch (JSONException e) {}
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

    @Override
    public void updateData(JSONObject data) {
        try {
            String action = data.getString("action");
            switch (action){
                case "insertRecord": {
                    break;
                }
                case "deleteRecord": {
                    break;
                }
                case "updateRecord": {
                    break;
                }
                case "filtersChanged": {
                    break;
                }
            }
        } catch (JSONException e) {}

    }



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
                case "updateGraphProp": {
                    updateParameters(obj);
                    break;
                }
                case "insertFlow": {
                    JSONObject jsonFlowParam = obj.getJSONObject("flows");
                    String flowId = jsonFlowParam.getString("ID");
                    String name = jsonFlowParam.getString("name");
                    String color = jsonFlowParam.getString("color");
                    flowList.add(new BarChartFlow(flowId, name, color));
                    setData(obj.getJSONObject("data"));
                    break;
                }
                case "deleteFlow": {
                    String id = obj.getString("ID");
                    deleteFlow(id);
                    break;
                }
                case "updateFlowProp": {
                    //changes to flow params
                    if (obj.has("flows")) {
                        JSONArray jsonFlows = obj.getJSONArray("flows");
                        int flowLenght = jsonFlows.length();
                        for (int i = 0; i < flowLenght; i++) {
                            String flowId = jsonFlows.getJSONObject(i).getString("ID");
                            String name = jsonFlows.getJSONObject(i).getString("name");
                            String color = jsonFlows.getJSONObject(i).getString("color");
                            flowList.add(new BarChartFlow(flowId, name, color));
                        }
                    }
                break;
                }
                case "updateFlowData": {
                    updateData(obj);
                }
            }


        }catch(JSONException e){
            return;
        }
    }

}
