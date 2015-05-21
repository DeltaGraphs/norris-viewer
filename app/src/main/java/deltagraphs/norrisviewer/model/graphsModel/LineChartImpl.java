package deltagraphs.norrisviewer.model.graphsModel;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import deltagraphs.norrisviewer.model.flowModel.LineChartFlow;
import lecho.lib.hellocharts.model.Axis;

/*
 * Name : LineChartImpl.java
 * Module : deltagraphs.norrisviewer.model.graphsModel
 * Location : norrisviewer\model\graphsModel
 *
 * History :

 * Version Date Programmer Description
 * ===============================================================
 *
 * 0.1.0 2015-05-17 Enrico Savoca Codifica di tutti gli attributi e i metodi
 *
 * 0.0.1 2015-05-17 Enrico Savoca Creazione file
 *
 * ===============================================================
 *
 */

public class LineChartImpl extends Graph  implements LineChart{

    private AxisModel axisX;
    private AxisModel axisY;
    private String background;
    private Boolean viewFinder;
    private Boolean horizontalGrid;
    private Boolean verticalGrid;
    private Boolean legendOnPoint;


    public void setLineChartImpl(JSONObject obj, String signal){JSONParser(obj, signal);}
    public void setViewFinder(Boolean choice) { this.viewFinder = choice; }

    public AxisModel getAxisX() { return axisX; }
    public AxisModel getAxisY() { return axisY; }
    public String getBackground() { return background; }
    public Boolean getViewFinder() { return viewFinder; }
    public Boolean getHorizontalGrid() { return horizontalGrid; }
    public Boolean getVerticalGrid() { return verticalGrid; }
    public Boolean getLegendOnPoint(){ return legendOnPoint; }

    public void setParameters(JSONObject data) {
        try {
            title = data.getString("title");
            background = data.getString("backgroundColor");
            legendOnPoint = data.getBoolean("legendOnPoint");
            viewFinder = data.getBoolean("viewFinder");
            horizontalGrid = data.getBoolean("horizontalGrid");
            verticalGrid = data.getBoolean("verticalGrid");

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
                flowList.add(new LineChartFlow(flowId, name, color));
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
            if(data.has("viewFinder"))
                viewFinder = data.getBoolean("viewFinder");
            if(data.has("horizontalGrid"))
                horizontalGrid = data.getBoolean("horizontalGrid");
            if(data.has("verticalGrid"))
                verticalGrid = data.getBoolean("verticalGrid");
            if(data.has("legendOnPoint"))
                legendOnPoint = data.getBoolean("legendOnPoint");
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
                ((LineChartFlow)flowList.get(index)).setFlowColour(colour);
            }//else eccezione
        } catch (JSONException e) {}
    }

    // it searches the flow index in the list of flows
    private int searchFlowIndex(String flowId){
        int index = -1;
        while ((index < flowList.size()) && (flowList.get(index).getFlowId().equals(flowId))) {
            index++;
        }
        return index;
    }

    @Override
    public void setData(JSONObject record) {
        String flowID = null;
        try {
            flowID = record.getString("ID");
        } catch (JSONException e) {
        }

        int index = searchFlowIndex(flowID);

        if (index != -1) {
            flowList.get(index).addRecords(record);
        }
    }

    @Override
    public void updateData(JSONObject data) {
        try {
            String action = data.getString("action");
            switch (action){
                case "insertRecord": {
                    String id = data.getString("ID");
                    int flowIndex = searchFlowIndex(id);
                    flowList.get(flowIndex).addRecord(data);
                    break;
                }
                case "deleteRecord": {
                    String id = data.getString("ID");
                    int flowIndex = searchFlowIndex(id);
                    flowList.get(flowIndex).deleteRecord(data);
                    break;
                }
                case "updateRecord": {
                    String id = data.getString("ID");
                    int flowIndex = searchFlowIndex(id);
                    flowList.get(flowIndex).updateRecord(data);
                    break;
                }
                case "filtersChanged": {
                    String id = data.getString("ID");
                    int flowIndex = searchFlowIndex(id);
                    flowList.get(flowIndex).deleteFlow();
                    flowList.get(flowIndex).createFlow(data);
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
                    flowList.add(new LineChartFlow(flowId, name, color));
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
                            flowList.add(new LineChartFlow(flowId, name, color));
                        }
                    }
                    break;
                }
                case "updateFlowData": {
                    updateData(obj);
                    break;
                }
            }
        }catch(JSONException e){
            return;
        }
    }
}

