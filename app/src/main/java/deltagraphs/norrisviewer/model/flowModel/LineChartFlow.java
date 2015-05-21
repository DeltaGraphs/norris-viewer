package deltagraphs.norrisviewer.model.flowModel;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import deltagraphs.norrisviewer.model.flowModel.*;

/*
 * Name : LineChartFlow.java
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

public class LineChartFlow extends FlowModel{

    private String flowColour;
    private String marker;
    private String interpolation;
    private String subAreaColour;
    private int maxItems;
    private ArrayList<Record> records;
    public ArrayList<Record> getRecords() { return records; }

    public void setFlowColour(String colour){ flowColour = colour;}

    class Record{
        private String recordId;
        private int xValue; // x value
        private int yValue; // y value

        public Record(String id, int xValue, int yValue){
            this.recordId = id;
            this.xValue = xValue;
            this.yValue = yValue;
        }

        public void setIndex(int xValue) { this.xValue = xValue; }
        public void setValue(int yValue) { this.yValue = yValue; }

        public int getYValue() { return xValue; }
        public int getXValue() { return yValue; }
    }

    public LineChartFlow(String id, String name, String color) {
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
            int xValue = jsonValues.getInt(0);
            int yValue = jsonValues.getInt(1);
            records.add(new Record(id, xValue, yValue));
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
            records.get(recordIndex).xValue = jsonValues.getInt(0);
            records.get(recordIndex).yValue = jsonValues.getInt(1);
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
