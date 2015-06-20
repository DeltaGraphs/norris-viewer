package deltagraphs.norrisviewer.model.flowModel;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/*
 * Name : LineChartFlow.java
 * Module : norrisviewer.model.flowModel
 * Location : norrisviewer\model\flowModel
 *
 * History :

 * Version Date Programmer Description
 * ===============================================================
 *
 * 0.2.1 2015-06-12 Enrico Savoca Add and Update method addRecords(JSONArray data, boolean insertOnTop);
 *
 * 0.2.0 2015-06-01 Enrico Savoca Remove useless methods
 *
 * 0.1.1 2015-05-31 Enrico Savoca Increment method LineChartFlow(JSONObject data) to manage null JSON Objects
 *
 * 0.1.0 2015-05-31 Enrico Savoca Coding of all methods and attibutes
 *
 * 0.0.1 2015-05-30 Enrico Savoca Creation of the file
 *
 * ===============================================================
 *
 */

public class LineChartFlow extends FlowModel {

    private String flowColour;
    private ArrayList<LineChartRecord> records = new ArrayList<LineChartRecord>(); /* a list that contains all the records of the flow */

    //it returns the colour of the flow
    public String getFlowColour() { return flowColour; }

    //it returns the record list length
    public int getRecordSize() {
        return records.size();
    }

    //it returns the id of the record at the position 'index' in the list
    public String getRecordId(int index) {
        return records.get(index).recordId;
    }

    //it returns the X value of the record at the position 'index' in the list
    public float getRecordValueX(int index) {
        return records.get(index).xValue;
    }

    //it returns the Y value of the record at the position 'index' in the list
    public float getRecordValueY(int index) {
        return records.get(index).yValue;
    }

    // Constructor of the flow.
    // It is called when a new flow is added to the flow list of a chart.
    // Flow parameters are initialized with jsonobject data content.
    // Jsonobject data must contain a value for each parameter.
    public LineChartFlow(JSONObject data) {
        try {
            this.flowId = data.getString("ID");
            if (data.has("name"))
                this.flowName = data.getString("name");
            else
                this.flowName = "";
            if (data.has("flowColor"))
                this.flowColour = data.getString("flowColor");
            else
                this.flowColour = "random";
            if (data.has("records")) {
                JSONArray recordList = data.getJSONArray("records");
                addRecords(recordList, false);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    // This inner class contains all the attributes that are related to a single record
    class LineChartRecord {
        private String recordId;
        private float xValue; /* x value */
        private float yValue; /* y value */

        // Record constructor.
        // It's used when a new record is added to a record list.
        // The new record is initialized with the passed parameters.
        public LineChartRecord(String id, float xValue, float yValue) {
            this.recordId = id;
            this.xValue = xValue;
            this.yValue = yValue;
        }
    }

    // The following method create a new flow.
    // The record list is created and is initialized with the jsonobject data.
    // The Jsonobject data must contain a record list.
    @Override
    public void createFlow(JSONObject data) {
        try {
            JSONArray recordList = data.getJSONArray("records");
            addRecords(recordList, false);
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    // The following method updates all the flow attributes.
    // The Jsonobject data must contain a value for each of them.
    @Override
    public void updateFlow(JSONObject data) {
        try {
            flowName = data.getString("name");
            flowColour = data.getString("flowColor");
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    // Used to delete the whole flow list. Params of the flow will remain.
    @Override
    public void deleteRecordList() {
        records = null;
    }

    // The following method insert a record in the flow.
    // The record is build with the JsonObject's informations.
    @Override
    public void addRecord(JSONObject data) {
        try {
            String id = data.getString("norrisRecordID");
            JSONArray jsonValues = data.getJSONArray("value");
            float xValue = (float) jsonValues.getDouble(0);
            float yValue = (float) jsonValues.getDouble(1);
            records.add(new LineChartRecord(id, xValue, yValue));
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    // Used to insert some records in the flow.
    // The JsonObject data must contain the a JsonArray with the records that must be inserted.
    // This method will use the method 'addRecord(record)' for each record adding.
    // The boolean variable 'insertOnTop' is not currently used, but it allows future extensions.
    @Override
    public void addRecords(JSONArray jsonRecords, boolean insertOnTop) {
        try {
            int recordLength = jsonRecords.length();
            for (int i = 0; i < recordLength; i++) {
                JSONObject record = jsonRecords.getJSONObject(i);
                addRecord(record);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    // Used to update a record of the flow.
    // The JsonObject data must contain the ID of the record that will be updated.
    // The method will search for the record Index in the flowList, using its ID.
    // After that, all the parameters of the record will be updated.
    @Override
    public void updateRecord(JSONObject data) {
        try {
            String recordId = data.getString("norrisRecordID");
            int recordIndex = searchRecordIndex(recordId);
            JSONArray jsonValues = data.getJSONArray("value");
            records.get(recordIndex).xValue = jsonValues.getInt(0);
            records.get(recordIndex).yValue = jsonValues.getInt(1);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }


    // It searches the record index in the list of records.
    // A record's id must be provided.
    protected int searchRecordIndex(String id) {
        int index = 0;
        while (index < records.size()) {
            if (records.get(index).recordId.equals(id))
                return index;
            index++;
        }
        return -1;
    }

    // Used to delete a record from the flow.
    // The JsonObject data must contain the ID of the record that will be deleted.
    // The method will search for the record Index in the flowList, using its ID.
    @Override
    public void deleteRecord(JSONObject data) {
        try {
            String recordId = data.getString("norrisRecordID");
            int recordIndex = searchRecordIndex(recordId);
            records.remove(recordIndex);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

}
