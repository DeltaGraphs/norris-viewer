package deltagraphs.norrisviewer.model.flowModel;

import android.util.Log;

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

public class LineChartFlow extends FlowModel {

    private String flowColour;
    private String marker;
    private String interpolation;
    private String subAreaColour;
    private int maxItems;
    private ArrayList<LineChartRecord> records = new ArrayList<LineChartRecord>();

    public String getFlowColour() {
        return flowColour;
    }

    public String getMarker() {
        return marker;
    }

    public String getInterpolation() {
        return interpolation;
    }

    public String getSubAreaColour() {
        return subAreaColour;
    }

    public int getMaxItems() {
        return maxItems;
    }

    public int getRecordSize() {
        return records.size();
    }

    public String getRecordId(int index) {
        return records.get(index).recordId;
    }

    public float getRecordValueX(int index) {
        return records.get(index).xValue;
    }

    public float getRecordValueY(int index) {
        return records.get(index).yValue;
    }

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
            if (data.has("marker"))
                this.marker = data.getString("marker");
            else
                this.marker = "none";
            if (data.has("interpolation"))
                this.interpolation = data.getString("interpolation");
            else
                this.interpolation = "standard";
            if (data.has("area"))
                this.subAreaColour = data.getString("area");
            else
                this.subAreaColour = "none";
            if (data.has("maxItems"))
                this.maxItems = data.getInt("maxItems");
            else
                this.maxItems = 0;
            if (data.has("records")) {
                JSONArray recordList = data.getJSONArray("records");
                addRecords(recordList, false);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    class LineChartRecord {
        private String recordId;
        private float xValue; // x value
        private float yValue; // y value

        public LineChartRecord(String id, float xValue, float yValue) {
            this.recordId = id;
            this.xValue = xValue;
            this.yValue = yValue;
        }
    }

    @Override
    public void createFlow(JSONObject data) {
        Log.d("flow", "createFlow");
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
            Log.d("flow", "updateFlow");
            flowName = data.getString("name");
            flowColour = data.getString("flowColor");
            marker = data.getString("marker");
            interpolation = data.getString("interpolation");
            subAreaColour = data.getString("area");
            maxItems = data.getInt("maxItems");
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
        try {
            Log.d("flow", "addRecord");
            String id = data.getString("norrisRecordID");
            JSONArray jsonValues = data.getJSONArray("value");
            float xValue = (float) jsonValues.getDouble(0);
            float yValue = (float) jsonValues.getDouble(1);
            records.add(new LineChartRecord(id, xValue, yValue));
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void addRecords(JSONArray jsonRecords, boolean insertOnTop) {
        try {
            Log.d("flow", "addRecords");
            int recordLength = jsonRecords.length();
            for (int i = 0; i < recordLength; i++) {
                JSONObject record = jsonRecords.getJSONObject(i);
                addRecord(record);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updateRecord(JSONObject data) {
        try {
            Log.d("flow", "updateRecord");
            String recordId = data.getString("norrisRecordID");
            int recordIndex = searchRecordIndex(recordId);
            JSONArray jsonValues = data.getJSONArray("value");
            records.get(recordIndex).xValue = jsonValues.getInt(0);
            records.get(recordIndex).yValue = jsonValues.getInt(1);
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
            Log.d("flow", "deleteRecord");
            String recordId = data.getString("norrisRecordID");
            int recordIndex = searchRecordIndex(recordId);
            records.remove(recordIndex);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

}
