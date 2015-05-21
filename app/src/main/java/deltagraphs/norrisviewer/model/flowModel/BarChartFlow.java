package deltagraphs.norrisviewer.model.flowModel;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/*
 * Name : BarChartRecord.java
 * Module : norrisviewer.model.flowModel
 * Location : norrisviewer\model\flowModel
 *
 * History :

 * Version Date Programmer Description
 * ===============================================================
 *
 * 0.1.0 2015-05-14 Enrico Savoca Codifica degli attributi e dei parametri
 *
 * 0.0.1 2015-05-14 Enrico Savoca Creazione file
 *
 * ===============================================================
 *
 */

public class BarChartFlow extends FlowModel{

    private String flowColour = "#FFFFFF"; //default
    private ArrayList<Record> records;
    public ArrayList<Record> getRecords() { return records; }

    public void setFlowColour(String colour){ flowColour = colour;}

    class Record{
        private String recordId;
        private int index; // ID of bar
        private int value; // value for that bar

        public Record(String id, int index, int value){
            this.recordId = id;
            this.index = index;
            this.value = value;
        }

        public void setIndex(int index) { this.index = index; }
        public void setValue(int value) { this.value = value; }

        public int getIndex() { return index; }
        public int getValue() { return value; }
    }

    public BarChartFlow(String id, String name, String color) {
        this.flowId = id;
        this.flowName = name;
        this.flowColour = color;
    }

    @Override
    public void createFlow(JSONObject data) {
        records = new ArrayList<Record>();
        JSONObject recordList = null;
        try {
            recordList = data.getJSONObject("records");
        } catch (JSONException e) {}
        addRecords(recordList);
    }

    @Override
    public void updateFlow(JSONObject data) {
        try {
            flowName = data.getString("name");

        flowColour = data.getString("color");
        } catch (JSONException e) {}
    }

    @Override
    public void deleteFlow() {
        records = null;
    }

    @Override
    public void addRecord(JSONObject data) {
        String id = null;
        try {
            id = data.getString("norrisRecordID");
            JSONArray jsonValues = data.getJSONArray("value");
            int index = jsonValues.getInt(0);
            int value = jsonValues.getInt(1);
            records.add(new Record(id, index, value));
        } catch (JSONException e) {}
    }

    @Override
    public void addRecords(JSONObject recordArray){
        try {
            JSONArray jsonRecords = recordArray.getJSONArray("records");
            int recordLength = jsonRecords.length();
            for (int i = 0; i < recordLength; i++) {
                JSONObject record = jsonRecords.getJSONObject(i);
                addRecord(record);
            }
       }catch (JSONException e) {}
    }

    @Override
    public void updateRecord(JSONObject data) {
        try {
            String recordId = data.getString("norrisRecordID");
            int recordIndex = searchRecordIndex(recordId);
            JSONArray jsonValues = data.getJSONArray("value");
            records.get(recordIndex).index = jsonValues.getInt(0);
            records.get(recordIndex).value = jsonValues.getInt(1);
        } catch (JSONException e) {}
    }


    // it searches the record index in the list of records
    protected int searchRecordIndex(String id){
        int index = -1;
        while ((index < records.size()) && (records.get(index).recordId.equals(id))) {
            index++;
        }
        return index;
    }

    @Override
    public void deleteRecord(JSONObject data) {
        try {
            String recordId = data.getString("norrisRecordID");
            int recordIndex = searchRecordIndex(recordId);
            records.remove(recordIndex);
        } catch (JSONException e) {}
    }
}
