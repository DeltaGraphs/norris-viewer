package deltagraphs.norrisviewer.model.flowModel;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/*
 * Name : BarChartFlow.java
 * Module : norrisviewer.model.flowModel
 * Location : norrisviewer\model\flowModel
 *
 * History :

 * Version Date Programmer Description
 * ===============================================================
 *
 * 0.1.2 2015-06-12 Enrico Savoca Add and Update method addRecords(JSONArray data, boolean insertOnTop);
 *
 * 0.1.1 2015-05-27 Enrico Savoca Add a default value for the attribute "flowColour"
 *
 * 0.1.0 2015-05-25 Enrico Savoca Coding of all methods and attibutes
 *
 * 0.0.1 2015-05-25 Enrico Savoca Creation of the file
 *
 * ===============================================================
 *
 */

public class BarChartFlow extends FlowModel {

    private String flowColour = "random"; /* 'random' is the default colour */
    private ArrayList<BarChartRecord> records = new ArrayList<BarChartRecord>(); /* a list that contains all the records of the flow */

    //it returns the colour of the flow
    public String getFlowColour() {
        return flowColour;
    }

    //it returns the record length
    public int getRecordSize() {
        if (records == null)
            return 0;
        return records.size();
    }

    //it returns the id of the record at the position 'index' in the list
    public String getRecordId(int index) {
        return records.get(index).recordId;
    }

    //it returns the bar index of the record at the position 'index' in the list
    public String getRecordIndex(int index) {
        return records.get(index).index;
    }

    //it returns the value of the record at the position 'index' in the list
    public float getRecordValue(int index) {
        return records.get(index).value;
    }

    // Constructor of the flow.
    // It is called when a new flow is added to the flow list of a chart.
    // Flow parameters are initialized with jsonobject data content.
    // Jsonobject data must contain a value for each parameter.
    public BarChartFlow(JSONObject data) {
        records = new ArrayList<BarChartRecord>();
        try {
            flowId = data.getString("ID");
            flowName = data.getString("name");
            if (data.has("flowColor"))
                flowColour = data.getString("flowColor");
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    // This inner class contains all the attributes that are related to a single record
    class BarChartRecord {
        private String recordId;
        private String index; /* ID of bar */
        private float value; /* value for that bar */

        // Record constructor.
        // It's used when a new record is added to a record list.
        // The new record is initialized with the passed parameters.
        public BarChartRecord(String id, String index, float value) {
            this.recordId = id;
            this.index = index;
            this.value = value;
        }
    }

    // The following method create a new flow.
    // The record list is created and is initialized with the jsonobject data.
    // The Jsonobject data must contain a record list.
    @Override
    public void createFlow(JSONObject data) {
        records = new ArrayList<BarChartRecord>();
        JSONArray recordList = null;
        try {
            recordList = data.getJSONArray("records");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        addRecords(recordList, false);
    }

    // The following method updates all the flow attributes.
    // The Jsonobject data must contain a value for each of them.
    @Override
    public void updateFlow(JSONObject data) {
        try {
            flowName = data.getString("name");
            flowColour = data.getString("color");
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    // Used to delete the whole flow list. Params of the flow will remain.
    @Override
    public void deleteRecordList() {
        records = null;
    }

    // The following method inserts a record in the flow.
    // The record is build with the JsonObject's informations.
    @Override
    public void addRecord(JSONObject data) {
        try {
            if (records == null) {
                records = new ArrayList<BarChartRecord>();
            }
            String id = data.getString("norrisRecordID");
            JSONArray jsonValues = data.getJSONArray("value");
            String index = jsonValues.getString(0);
            float value = (float) jsonValues.getDouble(1);
            records.add(new BarChartRecord(id, index, value));
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
            if(recordIndex!=-1){
                JSONArray jsonValues = data.getJSONArray("value");
                records.get(recordIndex).index = jsonValues.getString(0);
                records.get(recordIndex).value = jsonValues.getInt(1);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }


    // It searches the record index in the list of records.
    // A record's id must be provided.
    private int searchRecordIndex(String id) {
        int index = 0;
        while (index < records.size()) {
            if (records.get(index).recordId.equals(id))
                return index;
            index++;
        }
        return -1;
    }


    // Used to delete a record in the flow.
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
