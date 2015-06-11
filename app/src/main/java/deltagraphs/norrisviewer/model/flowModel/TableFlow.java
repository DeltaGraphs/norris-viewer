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
 * 0.3.2 2015-05-14 Enrico Savoca Add and Update method addRecords(JSONArray data, boolean insertOnTop);
 *
 * 0.3.1 2015-05-14 Enrico Savoca Fix some JSON-reading bugs
 *
 * 0.3.0 2015-05-14 Enrico Savoca Add classes TableRecord and Value
 *
 * 0.2.0 2015-05-14 Enrico Savoca Reorganize the whole class
 *
 * 0.1.0 2015-05-14 Enrico Savoca Coding of all methods and attibutes
 *
 * 0.0.1 2015-05-14 Enrico Savoca Creation of the file
 *
 * ===============================================================
 *
 */


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.LinkedList;

public class TableFlow extends FlowModel {
    private int maxItems;
    private LinkedList<TableRecord> records = new LinkedList<TableRecord>();

    public int getMaxItems() {
        return maxItems;
    } //per page

    public int getRecordSize() {
        return records.size();
    }

    public String getRecordId(int index) {
        return records.get(index).recordId;
    }

    public String getCellTextColour(int index, int columnIndex) {
        return records.get(index).values.get(columnIndex).textColour;
    }

    public String getCellBackgroundColour(int index, int columnIndex) {
        return records.get(index).values.get(columnIndex).background;
    }

    public String getCellData(int index, int columnIndex) {
        return records.get(index).values.get(columnIndex).data;
    }


    public TableFlow(JSONObject data) {
        try {
            flowId = data.getString("ID");
            flowName = data.getString("name");
            maxItems = data.getInt("maxItemsPage");
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    class TableRecord {
        private String recordId;
        private LinkedList<Value> values = new LinkedList<Value>();

        public TableRecord(String id, JSONArray valueList, JSONArray appearance) {
            recordId = id;
            try {
                int listLength = valueList.length();
                for (int i = 0; i < listLength; i++) {
                    String value = valueList.getString(i);
                    String bg = appearance.getJSONObject(i).getString("bg");
                    String text = appearance.getJSONObject(i).getString("text");
                    values.add(new Value(value, bg, text));
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        class Value {
            private String data;
            private String background;
            private String textColour;

            Value(String data, String bg, String tC) {
                this.data = data;
                background = bg;
                textColour = tC;
            }
        }
    }

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

    @Override
    public void updateFlow(JSONObject data) {
        try {
            flowName = data.getString("name");
            maxItems = data.getInt("maxItemsPage");
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteRecordList() {
        records = null;
    }

    @Override
    public void addRecord(JSONObject data) {
        String id = null;
        try {
            id = data.getString("norrisRecordID");
            JSONArray jsonValues = data.getJSONArray("value");
            JSONArray appearance = data.getJSONArray("appearance");
            records.add(new TableRecord(id, jsonValues, appearance));
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public void addFirstRecord(JSONObject data) {
        String id = null;
        try {
            id = data.getString("norrisRecordID");
            JSONArray jsonValues = data.getJSONArray("value");
            JSONArray appearance = data.getJSONArray("appearance");
            records.addFirst(new TableRecord(id, jsonValues, appearance));
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

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

    @Override
    public void updateRecord(JSONObject data) {
        try {
            String recordId = data.getString("norrisRecordID");
            int recordIndex = searchRecordIndex(recordId);
            JSONArray valueList = data.getJSONArray("values");
            JSONArray appearance = data.getJSONArray("appearance");
            int listLength = valueList.length();
            for (int i = 0; i < listLength; i++) {
                records.get(recordIndex).values.get(i).data = valueList.getString(i);
                records.get(recordIndex).values.get(i).background = appearance.getJSONObject(i).getString("bg");
                records.get(recordIndex).values.get(i).textColour = appearance.getJSONObject(i).getString("text");
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }


    // it searches the record index in the list of records
    protected int searchRecordIndex(String id) {
        int index = 0;
        while (index < records.size()) {
            if (records.get(index).recordId.equals(id))
                return index;
            index++;
        }
        return -1;
    }

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
