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

    //it returns the title of the chart
    public String getTitle() {
        return title;
    }

    //it returns the flow list of the chart
    public ArrayList<FlowModel> getFlowList() {
        return flowList;
    }

    // this method is used when a new json object arrives from the socket
    // Used to initialize, set and update parameters and flows of a chart.
    public void setGraph(JSONObject obj, String signal) {
        JSONParser(obj, signal);
        setChanged();
        notifyObservers(signal);

    }

    // this method, defined by the concrete classes that extend graph, is used to set their parameters
    // using a json Object
    public abstract void setParameters(JSONObject data);

    // this method, defined by the concrete classes that extend graph, is used to update their parameters
    // using a json Object
    public abstract void updateParameters(JSONObject data);

    // this method, defined by the concrete classes that extend graph, is used to add a flow to them
    // using a json Object
    public abstract void addFlow(JSONObject flow);

    // this method is used to update a flow of a graph, using a json Object.
    // it will search the flow that must be updated using its ID.
    public void updateFlow(JSONObject data) {
        try {
            String flowId = data.getString("ID");
            int index = searchFlowIndex(flowId);
            flowList.get(index).updateFlow(data);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    // this method is used to delete a flow of a graph.
    // it will search the flow that must be deleted using its ID.
    public void deleteFlow(String flowID) {
        int index = searchFlowIndex(flowID);
        if (index != -1) {
            flowList.remove(index);
        }
    }

    // it searches the flow index in the list of flows
    // it uses a value of ID for a flow, to get an index in the data structure that contains flows.
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

    //this method is called by "setGraph". Its aim is to receive a json object
    // and a signal and do different choices considering their content.
    // Choices will depend on the signal content. For each case a different action could be done on the graph:
    // initializing of properties, updating of properties and flows can be added, updated or removed
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

    //This method is called by "jsonParser". It is used to update records of a flow.
    // the ID of a flow is provided. When a flow with that ID exists in the graph. It will be updated.
    // records in that flow can be inserted, deleted or updated.
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
