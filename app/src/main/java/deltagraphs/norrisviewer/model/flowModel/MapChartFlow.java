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

    private Marker markerProperties;
    private int maxItems;
    private TraceModel trace;
    private ArrayList<MapChartRecord> records;
    public ArrayList<MapChartRecord> getRecords() { return records; }

    public int getMaxItems() { return maxItems; }
    public TraceModel getTrace() { return trace; }

    public int getRecordSize(){return records.size(); }
    public String getRecordId(int index){ return records.get(index).recordId; }
    public String getRecordMarkerId(int index){ return records.get(index).markerId; }
    public float getRecordLatitude(int index){ return records.get(index).latitude; }
    public float getRecordLongitude(int index){ return records.get(index).longitude; }

    public String getMarkerType(){ return markerProperties.type; };
    public String getMarkerColour(){ return markerProperties.colour; }
    public String getMarkerProperty(String type){
        switch (type){
            case("shape"):{
                return markerProperties.shape;
            }
            case("icon"):{
                return markerProperties.icon;
            }
            case("text"):{
                return markerProperties.text;
            }
            default:{
                return "default";
            }
        }
    }

    private String shape = null;
    private String icon = null;
    private String text = null;
    private String colour;

    class MapChartRecord{
        private String recordId;
        private String markerId;
        private float latitude;
        private float longitude;

        public MapChartRecord(String rId, String mId, float lat, float longit){
            this.recordId = rId;
            this.markerId = mId;
            latitude = lat;
            longitude = longit;
        }
    }

    class Marker{
        private String type;
        private String shape = null;
        private String icon = null;
        private String text = null;
        private String colour;

        public Marker(JSONObject data){
            try {
                type = data.getString("type");
                shape = data.getString("shape");
                icon = data.getString("icon");
                text = data.getString("text");
                colour = data.getString("color");
            } catch (JSONException e) {}
        }
    }

    class TraceModel{
        private String type;
        private String stokeColour;  // colour of the polyline
        private String fillColour;  // colour of the area subtended by the polyline
        private ArrayList<Float> latitudeCoords = new ArrayList<Float>();
        private ArrayList<Float> longitudeCoords = new ArrayList<Float>();
    }

    @Override
    public void createFlow(JSONObject data) {
        records = new ArrayList<MapChartRecord>();
        try {
            JSONObject recordList = data.getJSONObject("records");
            addRecords(recordList);
        } catch (JSONException e) {}
    }

    @Override
    public void updateFlow(JSONObject data) {
        try {
            flowName = data.getString("name");
            maxItems = data.getInt("maxItemsPage");
            markerProperties.type = data.getJSONObject("marker").getString("type");
            markerProperties.shape = data.getJSONObject("marker").getString("shape");
            markerProperties.icon = data.getJSONObject("marker").getString("icon");
            markerProperties.text = data.getJSONObject("marker").getString("text");
            markerProperties.colour = data.getJSONObject("marker").getString("color");
            trace.type = data.getJSONObject("trace").getString("type");
            trace.stokeColour = data.getJSONObject("trace").getString("stokeColor");
            trace.fillColour = data.getJSONObject("trace").getString("fillColor");
            JSONArray jsonCoordinates = data.getJSONArray("coordinates");
            for(int i=0; i< jsonCoordinates.length(); i++) {
                trace.latitudeCoords.add((float) jsonCoordinates.getJSONArray(i).getDouble(0));
                trace.longitudeCoords.add((float) jsonCoordinates.getJSONArray(i).getDouble(1));
            }
        } catch (JSONException e) {}
    }

    @Override
    public void deleteRecordList() {
        records = null;
    }

    @Override
    public void addRecord(JSONObject data) {
        try {
            String recordId = data.getString("norrisRecordID");
            String markerId = data.getString("markerID");
            float latitude = (float) data.getJSONArray("value").getDouble(0);
            float longitude = (float) data.getJSONArray("value").getDouble(1);
            records.add(new MapChartRecord(recordId, markerId, latitude, longitude));
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
            records.get(recordIndex).markerId = data.getString("markerID");
            records.get(recordIndex).latitude = (float) data.getJSONArray("value").getDouble(0);
            records.get(recordIndex).longitude = (float) data.getJSONArray("value").getDouble(1);
        } catch (JSONException e) {}
    }


    // it searches the record index in the list of records
    protected int searchRecordIndex(String id){
        int index = 0;
        Boolean found = false;
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

