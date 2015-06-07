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
 * 0.1.0 2015-05-14 Enrico Savoca Codifica degli attributi e dei parametri
 *
 * 0.0.1 2015-05-14 Enrico Savoca Creazione file
 *
 * ===============================================================
 *
 */

public class BarChartFlow extends FlowModel{

    private String flowColour = "random"; //default
    private ArrayList<BarChartRecord> records= new ArrayList<BarChartRecord>();

    public String getFlowColour(){ return flowColour; }
    public int getRecordSize(){return records.size(); }
    public String getRecordId(int index){ return records.get(index).recordId; }
    public String getRecordIndex(int index) { return records.get(index).index; }
    public float getRecordValue(int index) { return records.get(index).value; }

    public BarChartFlow(JSONObject data) {
        records=new ArrayList<BarChartRecord>();
        try {
            flowId = data.getString("ID");
            flowName = data.getString("name");
            if(data.has("flowColor"))
                flowColour = data.getString("flowColor");
        }catch(JSONException e)
            {
                e.printStackTrace();
            }
    }

    class BarChartRecord{
        private String recordId;
        private String index; // ID of bar
        private float value; // value for that bar

        public BarChartRecord(String id, String index, float value){
            this.recordId = id;
            this.index = index;
            this.value = value;
        }
    }


    @Override
    public void createFlow(JSONObject data) {
        records = new ArrayList<BarChartRecord>();
        JSONArray recordList = null;
        try {
            recordList = data.getJSONArray("records");
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
    public void deleteRecordList() {
        records = null;
    }

    @Override
    public void addRecord(JSONObject data) {
        String id = null;
        try {
            id = data.getString("norrisRecordID");
            JSONArray jsonValues = data.getJSONArray("value");
            String index = jsonValues.getString(0);
            float value = (float) jsonValues.getDouble(1);
            records.add(new BarChartRecord(id, index, value));
        } catch (JSONException e) {}
    }

    @Override
    public void addRecords(JSONArray jsonRecords){
        try {
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
            records.get(recordIndex).index = jsonValues.getString(0);
            records.get(recordIndex).value = jsonValues.getInt(1);
        } catch (JSONException e) {}
    }


    // it searches the record index in the list of records
    private int searchRecordIndex(String id){
        int index = 0;
        while (index < records.size()){
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
        } catch (JSONException e) {}
    }
}
