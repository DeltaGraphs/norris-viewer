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
 * 1.0.0 2015-06-20 Matteo Furlan Approve
 *
 * 0.2.0 2015-06-15 Davide Trivellato Verify
 *
 * 0.1.1 2015-06-12 Enrico Savoca Add and Update method addRecords(JSONArray data, boolean insertOnTop);
 *
 * 0.1.0 2015-05-22 Enrico Savoca Coding of all methods and attributes
 *
 * 0.0.1 2015-05-22 Enrico Savoca Creation of the file
 *
 * ===============================================================
 *
 */

import org.json.JSONArray;
import org.json.JSONObject;

public abstract class FlowModel {

    protected String flowId;    /*The id of the flow */
    protected String flowName;  /*The id of the flow */

    // The following method create a new flow, initializing the record list
    public abstract void createFlow(JSONObject data);

    // The following method updates all the flow attributes.
    public abstract void updateFlow(JSONObject data);

    // Used to delete the whole flow list. Params of the flow will remain.
    public abstract void deleteRecordList();

    // The following method insert a record in the flow.
    public abstract void addRecord(JSONObject data);

    // Used to insert some records in the flow.
    public abstract void addRecords(JSONArray data, boolean insertOnTop);

    // Used to update a record of the flow.
    public abstract void updateRecord(JSONObject data);

    // Used to delete a record in the flow.
    public abstract void deleteRecord(JSONObject data);

    //it returns the value of flowName
    public String getFlowName() {
        return flowName;
    }

    //it returns the value of flowId
    public String getFlowId() {
        return flowId;
    }

}

