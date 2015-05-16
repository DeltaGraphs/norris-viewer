package deltagraphs.norrisviewer.model.flowModel;

/*
 * Name : FlowModel.java
 * Module : norrisviewer::model::flowModel
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

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Objects;

public abstract class FlowModel {

    private String flowId;
    private String flowName;
    private ArrayList<Record> values = new ArrayList<Record>();

    public void addRecord(JSONObject data){
        // TO DO!!!
    }

    public abstract void createRecord(JSONObject data);
    public abstract void updateRecord(JSONObject data);
    public abstract void deleteRecord(JSONObject data);

    public String getFlowName() { return flowName; }
    public ArrayList<Record> getValues() { return values; }
    public String getFlowId() { return flowId; }

    public interface Record{}

}

