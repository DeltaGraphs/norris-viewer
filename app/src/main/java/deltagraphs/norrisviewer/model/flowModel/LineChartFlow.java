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
    private ArrayList<LineChartRecord> records;

    public String getFlowColour(){ return flowColour; }
    public String getMarker(){ return marker; }
    public String getInterpolation() { return interpolation; }
    public String getSubAreaColour(){ return subAreaColour; }
    public int maxItems() { return maxItems; }

    public int getRecordSize(){return records.size(); }
    public String getRecordId(int index){ return records.get(index).recordId; }
    public float getRecordValueX(int index){ return records.get(index).xValue; }
    public float getRecordValueY(int index){ return records.get(index).yValue; }

    public LineChartFlow(JSONObject data) {
        try {
            this.flowId = data.getString("ID");
            this.flowName = data.getString("name");
            this.flowColour = data.getString("color");
            this.marker = data.getString("marker");
            this.interpolation = data.getString("interpolation");
            this.subAreaColour = data.getString("area");
            this.maxItems = data.getInt("maxItems");
        } catch (JSONException e) {}
    }

    class LineChartRecord{
        private String recordId;
        private float xValue; // x value
        private float yValue; // y value

        public LineChartRecord(String id, float xValue, float yValue){
            this.recordId = id;
            this.xValue = xValue;
            this.yValue = yValue;
        }
    }

    @Override
    public void createFlow(JSONObject data) {
        records = new ArrayList<LineChartRecord>();
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
            marker = data.getString("marker");
            interpolation = data.getString("interpolation");
            subAreaColour = data.getString("area");
            maxItems = data.getInt("maxItems");
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
            float xValue = (float) jsonValues.getDouble(0);
            float yValue = (float) jsonValues.getDouble(1);
            records.add(new LineChartRecord(id, xValue, yValue));
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
