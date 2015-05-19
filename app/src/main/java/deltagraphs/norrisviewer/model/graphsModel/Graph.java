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
    protected ArrayList<FlowModel> flowList;
    private String title;

    public void addFlow(JSONObject flow){
        flowList.add(new FlowModel(flow));
    }

    public void addRecords(JSONObject record){
        String flowID = null;
        try {
            flowID = record.getString("ID");
        } catch (JSONException e) {}

        int index = -1;
        while ((index < flowList.size()) && (flowList.get(index).getFlowId().equals(flowID))){
            index ++;
        }
        if(index != -1) {


            // TO DO !!!
            //ALERUTO!!! METODO TOSTO!!! Cambiare nome, id, filtri maybe, non so se anche i valori


        }//else eccezione

    }

    public void deleteFlow(String flowID){
        int index = -1;
        while ((index < flowList.size()) && (flowList.get(index).getFlowId().equals(flowID))){
            index ++;
        }
        if(index != -1) {
            flowList.remove(index);
        }//else eccezione
    }

    public abstract void setData(JSONObject data);
    public abstract void setParameters(JSONObject data);



    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }
}
