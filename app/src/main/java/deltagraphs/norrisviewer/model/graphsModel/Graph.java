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

import android.util.Log;

import deltagraphs.norrisviewer.model.flowModel.*;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


import java.util.*;

public abstract class Graph extends Observable {

    protected String title;
    protected ArrayList<FlowModel> flowList = new ArrayList<FlowModel>();
    private boolean configured = false;

    public String getTitle() {
        return title;
    }

    public ArrayList<FlowModel> getFlowList() {
        return flowList;
    }

    public void setGraph(JSONObject obj, String signal) {
        JSONParser(obj, signal);
        setChanged();
        notifyObservers(signal);

    }

    public abstract void setParameters(JSONObject data);

    public abstract void updateParameters(JSONObject data);

    public abstract void addFlow(JSONObject flow);


    public void updateFlow(JSONObject data) {
        try {
            String flowId = data.getString("ID");
            int index = searchFlowIndex(flowId);
            flowList.get(index).updateFlow(data);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public void deleteFlow(String flowID) {
        int index = searchFlowIndex(flowID);
        if (index != -1) {
            flowList.remove(index);
        }//else eccezione
    }

    // it searches the flow index in the list of flows
    private int searchFlowIndex(String flowId) {
        int index = 0;
        while (index < flowList.size()) {
            if (flowList.get(index).getFlowId().equals(flowId)) {
                return index;
            }
            index++;
        }
        return -1;
    }

    private void JSONParser(JSONObject obj, String signal) {
        try {
            switch (signal) {
                case "configGraph": {
                    if (configured != true) {
                        JSONObject properties = obj.getJSONObject("properties");
                        setParameters(properties);
                        JSONArray data = obj.getJSONArray("data");
                        for (int i = 0; i < data.length(); i++) {
                            int flowIndex = searchFlowIndex(data.getJSONObject(i).getString("ID"));
                            if (flowIndex != -1) {
                                flowList.get(flowIndex).createFlow(data.getJSONObject(i));
                            }
                        }
                        configured = true;
                    }
                    break;
                }
                case "updateGraphProp": {
                    updateParameters(obj);
                    break;
                }
                case "insertFlow": {
                    if (obj.has("properties")) {
                        JSONObject jsonFlowParam = obj.getJSONObject("properties");
                        addFlow(jsonFlowParam);
                    }
                    Log.d("insert", "insertFlow");
                    int flowIndex = searchFlowIndex(obj.getJSONObject("properties").getString("ID"));
                    if (flowIndex != -1) {
                        flowList.get(flowIndex).createFlow(obj);
                    }
                    break;
                }
                case "deleteFlow": {
                    Log.d("delete", "deleteFlow");
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
                    Log.d("graph", "dentro updateFlowData");
                    updateRecords(obj);
                    break;
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private void updateRecords(JSONObject data) {
        try {
            String action = data.getString("action");
            switch (action) {
                case "insertRecords": {
                    String id = data.getString("ID");
                    int flowIndex = searchFlowIndex(id);
                    if (flowIndex != -1) {
                        boolean insertOnTop = false; // only for the table
                        if ((this instanceof TableImpl) && (((TableImpl) this).getAddRowOn() == "top"))
                            insertOnTop=true;
                        flowList.get(flowIndex).addRecords(data.getJSONArray("records"), insertOnTop);
                    }
                    break;
                }
                case "deleteRecord": {
                    String id = data.getString("ID");
                    int flowIndex = searchFlowIndex(id);
                    if (flowIndex != -1)
                        flowList.get(flowIndex).deleteRecord(data);
                    break;
                }
                case "updateRecord": {
                    String id = data.getString("ID");
                    int flowIndex = searchFlowIndex(id);
                    if (flowIndex != -1)
                        flowList.get(flowIndex).updateRecord(data);
                    break;
                }
                case "replaceData": {
                    String id = data.getString("ID");
                    int flowIndex = searchFlowIndex(id);
                    if (flowIndex != -1) {
                        flowList.get(flowIndex).deleteRecordList();
                        flowList.get(flowIndex).createFlow(data);
                    }
                    break;
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
