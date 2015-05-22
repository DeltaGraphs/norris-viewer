package deltagraphs.norrisviewer.model.flowModel;



/*
 * Name : MapChartFlow.java
 * Module : deltagraphs.norrisviewer.model.flowModel
 * Location : norrisviewer\model\flowModel
 *
 * History :

 * Version Date Programmer Description
 * ===============================================================
 *
 * 0.1.0 2015-05-14 Enrico Savoca Codifica degli attributi e dei parametri
 *
 * 0.0.1 2015-05-14 Enrico Creazione file
 *
 * ===============================================================
 *
 */

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MapChartFlow extends FlowModel {

    private String longitudeKey;
    private String latitudeKey;
    private String longitudeFormat;
    private String latitudeFormat;
    private String marker;
    private int maxItems;
    private String trace;
    private ArrayList<MapChartRecord> records;
    public ArrayList<MapChartRecord> getRecords() { return records; }



    class MapChartRecord{
        private String recordId;
        private int value; // value for that bar

        public MapChartRecord(int index, int value){
        }
    }

    @Override
    public void createFlow(JSONObject data) {
        records = new ArrayList<MapChartRecord>();
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
            //flowColour = data.getString("color");
            marker = data.getString("marker");
            //interpolation = data.getString("interpolation");
            //subAreaColour = data.getString("area");
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
            int xValue = jsonValues.getInt(0);
            int yValue = jsonValues.getInt(1);
            //records.add(new LineChartRecord(id, xValue, yValue));
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
            //records.get(recordIndex).xValue = jsonValues.getInt(0);
            //records.get(recordIndex).yValue = jsonValues.getInt(1);
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
