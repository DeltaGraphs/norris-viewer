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
 * 0.3.2 2015-06-12 Enrico Savoca Update method addRecords(JSONArray data, boolean insertOnTop)
 *
 * 0.3.1 2015-06-11 Enrico Savoca fix some bugs of  initial configuration, added attribute "configured"
 *
 * 0.3.0 2015-05-29 Enrico Savoca Apply other several changes to JSONParser(JSONObject obj, String signal)
 *
 * 0.2.0 2015-05-25 Enrico Savoca Apply several changes to JSONParser(JSONObject obj, String signal)
 *
 * 0.1.1 2015-05-23 Enrico Savoca Change the method addFlow to abstract
 *
 * 0.1.0 2015-05-22 Enrico Savoca Coding of all methods and attributes
 *
 * 0.0.1 2015-05-22 Enrico Savoca Creation of the file
 *
 * ===============================================================
 *
 */

import deltagraphs.norrisviewer.model.flowModel.*;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


import java.util.*;

public abstract class Graph extends Observable {

    protected String title;
    protected ArrayList<FlowModel> flowList = new ArrayList<FlowModel>();
    private boolean configured = false;

    public boolean isConfigured(){ return configured; }

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
        }
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
                    if (!configured) {
                        JSONObject properties = obj.getJSONObject("properties");
                        setParameters(properties);
                        if(obj.has("data")) {
                            JSONArray data = obj.getJSONArray("data");
                            for (int i = 0; i < data.length(); i++) {
                                int flowIndex = searchFlowIndex(data.getJSONObject(i).getString("ID"));
                                if (flowIndex != -1) {
                                    flowList.get(flowIndex).createFlow(data.getJSONObject(i));
                                }
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
                    int flowIndex = searchFlowIndex(obj.getJSONObject("properties").getString("ID"));
                    if (flowIndex != -1) {
                        flowList.get(flowIndex).createFlow(obj);
                    }
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
                        if ((this instanceof Table) && ((TableImpl) this).getAddRowOn().equals("top"))
                            insertOnTop = true;
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
