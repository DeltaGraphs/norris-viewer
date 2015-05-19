package deltagraphs.norrisviewer.model.flowModel;

/*
 * Name : FlowModel.java
 * Module : norrisviewer.model.flowModel
 * Location : norrisviewer\model\flowModel
 *
 * History :

 * Version Date Programmer Description
 * ===============================================================
 *
 * 0.1.0 2015-05-14 Enrico Savoca Codifica degli attributi e dei parametri
 *
 * 0.0.1 2015-05-13 Enrico Savoca Creazione file
 *
 * ===============================================================
 *
 */

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Objects;

public class FlowModel {

    private String flowId;
    private String flowName;
    private String flowColour = "#FFFFFF"; //default
    private ArrayList<Record> values = new ArrayList<Record>();


    public FlowModel(JSONObject obj){
        try {
            flowId = obj.getString("ID");
            flowName = obj.getString("name");
            flowColour = obj.getString("color");
        } catch (JSONException e) {}

    }

    public void addRecord(JSONObject data){
        values.add(new BarChartRecord(data));
    }

    public void createRecord(JSONObject data){}
    public void updateRecord(JSONObject data){}
    public void deleteRecord(JSONObject data){}

    public String getFlowName() { return flowName; }
    public ArrayList<Record> getValues() { return values; }
    public String getFlowId() { return flowId; }

    public interface Record{}

}

