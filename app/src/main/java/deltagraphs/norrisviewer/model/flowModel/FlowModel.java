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

import org.json.JSONArray;
import org.json.JSONObject;

public abstract class FlowModel {

    protected String flowId;
    protected String flowName;

    public abstract void createFlow(JSONObject data);

    public abstract void updateFlow(JSONObject data);

    public abstract void deleteRecordList();

    public abstract void addRecord(JSONObject data);

    public abstract void addRecords(JSONArray data);

    public abstract void updateRecord(JSONObject data);

    public abstract void deleteRecord(JSONObject data);

    public String getFlowName() {
        return flowName;
    }

    public String getFlowId() {
        return flowId;
    }

}

