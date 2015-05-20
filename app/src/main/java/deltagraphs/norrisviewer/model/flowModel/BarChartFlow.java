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

    public String getFlowColour(){ return flowColour;}
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
    public void addRecord(JSONObject record){
        try {
            String id = record.getString("norrisRecordID");
            JSONArray jsonValues = record.getJSONArray("value");
            int index = jsonValues.getInt(0);
            int value = jsonValues.getInt(1);
            records.add(new Record(id, index, value));
       }catch (JSONException e) {}
    }

    @Override
    public void updateRecord(JSONObject data) {

    }

    @Override
    public void deleteRecord(JSONObject data) {

    }
}
