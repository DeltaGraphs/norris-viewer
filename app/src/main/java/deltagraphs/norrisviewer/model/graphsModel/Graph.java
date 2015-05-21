package deltagraphs.norrisviewer.model.graphsModel;

/*
 * Name : Graph.java
 * Module : norrisviewer.model.graphsModel
 * Location : norrisviewer\model\graphsModel
 *
 * History :

 * Version Date Programmer Description
 * ===============================================================
 *
 * 0.1.0 2015-05-14 Enrico Savoca Codifica di tutti gli attributi e i metodi
 *
 * 0.0.1 2015-05-13 Enrico Savoca Creazione file
 *
 * ===============================================================
 *
 */

import java.util.Observable;
import deltagraphs.norrisviewer.model.flowModel.*;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.*;

public abstract class Graph extends Observable {
    protected ArrayList<FlowModel> flowList = new ArrayList<FlowModel>();
    protected String title;

    public String getTitle() { return title; }

    public abstract void setRecords(JSONObject data);
    public abstract void setParameters(JSONObject data);
    public abstract void updateParameters(JSONObject data);
    public abstract void addFlow(JSONObject data);
    public abstract void updateFlow(JSONObject data);

    public void deleteFlow(String flowID){
        int index = searchFlowIndex(flowID);
        if(index != -1) {
            flowList.remove(index);
        }//else eccezione
    }

    // it searches the flow index in the list of flows
    protected int searchFlowIndex(String flowId){
        int index = -1;
        while ((index < flowList.size()) && (flowList.get(index).getFlowId().equals(flowId))) {
            index++;
        }
        return index;
    }

    protected void JSONParser(JSONObject obj, String signal){
        try{
            switch (signal) {
                case "configGraph": {
                    JSONObject properties = obj.getJSONObject("properties");
                    setParameters(properties);
                    JSONObject data = obj.getJSONObject("data");
                    setRecords(data);
                    break;
                }
                case "updateGraphProp": {
                    updateParameters(obj);
                    break;
                }
                case "insertFlow": {
                    JSONObject jsonFlowParam = obj.getJSONObject("properties");
                    addFlow(jsonFlowParam);
                    setRecords(obj.getJSONObject("data"));
                    break;
                }
                case "deleteFlow": {
                    String id = obj.getString("ID");
                    deleteFlow(id);
                    break;
                }
                case "updateFlowProp": {
                    //changes to flow params
                    updateFlow(obj);
                    break;
                }
                case "updateFlowData": {
                    updateRecords(obj);
                    break;
                }
            }
        }catch(JSONException e){
            return;
        }
    }

    protected void updateRecords(JSONObject data) {
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
                    flowList.get(flowIndex).deleteRecordList();
                    flowList.get(flowIndex).createFlow(data);
                    break;
                }
            }
        } catch (JSONException e) {}
    }
}
