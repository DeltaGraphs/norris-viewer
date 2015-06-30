package deltagraphs.norrisviewer.model.flowModel;

/*
 * Name : TableFlow.java
 * Module : deltagraphs.norrisviewer.model.flowModel
 * Location : norrisviewer\model\flowModel
 *
 * History :

 * Version Date Programmer Description
 * ===============================================================
 *
 * 1.0.0 2015-06-20 Matteo Furlano Approve
 *
 * 0.4.0 2015-06-15 Davide Trivellato Verify
 *
 * 0.3.2 2015-06-12 Enrico Savoca Add and Update method addRecords(JSONArray data, boolean insertOnTop);
 *
 * 0.3.1 2015-06-11 Enrico Savoca Fix some JSON-reading bugs
 *
 * 0.3.0 2015-06-09 Enrico Savoca Add classes TableRecord and Value
 *
 * 0.2.0 2015-06-09 Enrico Savoca Reorganize the whole class
 *
 * 0.1.0 2015-06-08 Enrico Savoca Coding of all methods and attibutes
 *
 * 0.0.1 2015-06-08 Enrico Savoca Creation of the file
 *
 * ===============================================================
 *
 */




import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.LinkedList;

public class TableFlow extends FlowModel {
    private LinkedList<TableRecord> records = new LinkedList<TableRecord>(); /* a list that contains all the records of the flow */

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

    //it returns the value of the record at the position 'index' in the list
    public String getCellData(int index, int columnIndex) {
        return records.get(index).values.get(columnIndex).data;
    }

    //it returns the colour of the background of the record at the position 'index' in the list
    public String getCellBGColour(int index, int columnIndex) {
        return records.get(index).values.get(columnIndex).background;
    }

    //it returns the colour of the text of the record at the position 'index' in the list
    public String getCellTColour(int index, int columnIndex) {
        return records.get(index).values.get(columnIndex).textColour;
    }

    // Constructor of the flow.
    // It is called when a new flow is added to the flow list of a chart.
    // Flow parameters are initialized with jsonobject data content.
    // Jsonobject data must contain a value for each parameter.
    public TableFlow(JSONObject data) {
        try {
            flowId = data.getString("ID");
            flowName = data.getString("name");
            //maxItems = data.getInt("maxItemsPage");
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    // This inner class contains all the attributes that are related to a single record
    class TableRecord {
        private String recordId;
        private LinkedList<Value> values = new LinkedList<Value>();

        // Record constructor.
        // It's used when a new record is added to a record list.
        // The new record is initialized with the passed parameters.
        public TableRecord(String id, JSONArray valueList, JSONArray appearance) {
            recordId = id;
            try {
                int listLength = valueList.length();
                for (int i = 0; i < listLength; i++) {
                    String value = valueList.getString(i);
                    String bg = "#FFFFFF";
                    String text = "#000000";
                    if (appearance != null) {
                        bg = appearance.getJSONObject(i).getString("bg");
                        text = appearance.getJSONObject(i).getString("text");
                    }
                    values.add(new Value(value, bg, text));
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        /* The following class contains properties of a cell: data, background and text colour */
        class Value {
            private String data;
            private String background = "#FFFFFF";
            private String textColour = "#000000";

            // Value constructor.
            // It's used when a new Value is added to the record list
            // The new marker type is initialized with the passed parameters.
            Value(String data, String bg, String tC) {
                this.data = data;
                background = bg;
                textColour = tC;
            }
        }
    }

    // The following method create a new flow.
    // The record list is created and is initialized with the jsonobject data.
    // The Json object data must contain a record list.
    @Override
    public void createFlow(JSONObject data) {
        records = new LinkedList<TableRecord>();
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
            //maxItems = data.getInt("maxItemsPage");
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
            JSONArray appearance = null;
            if (data.has("appearance"))
                appearance = data.getJSONArray("appearance");
            records.add(new TableRecord(id, jsonValues, appearance));
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    // The following method insert a record in the flow.
    // The record is build with the JsonObject's informations.
    // Differently from the method 'addRecord(JSONObject data)', the new record will be added on Top.
    public void addFirstRecord(JSONObject data) {
        try {
            String id = data.getString("norrisRecordID");
            JSONArray jsonValues = data.getJSONArray("value");
            JSONArray appearance = null;
            if (data.has("appearance"))
                appearance = data.getJSONArray("appearance");
            records.addFirst(new TableRecord(id, jsonValues, appearance));
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    // Used to insert some records in the flow.
    // The JsonObject data must contain the a JsonArray with the records that must be inserted.
    // This method will use the method 'addRecord(record)' for each record adding.
    // If the boolean variable 'insertOnTop' is set on 'true', the new records will be added on the top of the list.
    @Override
    public void addRecords(JSONArray jsonRecords, boolean insertOnTop) {
        try {
            int recordLength = jsonRecords.length();
            if (insertOnTop) {
                for (int i = 0; i < recordLength; i++) {
                    JSONObject record = jsonRecords.getJSONObject(i);
                    addFirstRecord(record);
                }
            } else {
                for (int i = 0; i < recordLength; i++) {
                    JSONObject record = jsonRecords.getJSONObject(i);
                    addRecord(record);
                }
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
            if (recordIndex != -1) {
                JSONArray valueList = data.getJSONArray("value");
                JSONArray appearance = null;
                if (data.has("appearance"))
                    appearance = data.getJSONArray("appearance");
                int listLength = valueList.length();
                for (int i = 0; i < listLength; i++) {
                    records.get(recordIndex).values.get(i).data = valueList.getString(i);
                    if (appearance != null) {
                        records.get(recordIndex).values.get(i).background = appearance.getJSONObject(i).getString("bg");
                        records.get(recordIndex).values.get(i).textColour = appearance.getJSONObject(i).getString("text");
                    }
                }
            }
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
