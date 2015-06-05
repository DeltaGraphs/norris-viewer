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

import android.util.Log;

import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MapChartFlow extends FlowModel {

    private Marker markerProperties;
    private int maxItems;
    private TraceModel trace;
    private ArrayList<MapChartRecord> records = new ArrayList<MapChartRecord>();

    public int getMaxItems() { return maxItems; }

    public int getRecordSize(){return records.size(); }
    public String getRecordId(int index){ return records.get(index).recordId; }
    public String getRecordMarkerId(int index){ return records.get(index).markerId; }
    public float getRecordLatitude(int index){ return records.get(index).latitude; }
    public float getRecordLongitude(int index){ return records.get(index).longitude; }

    public String getTraceType(){return trace.type;}
    public String getTraceStrokeColour(){return trace.strokeColour;} // colour of the polyline
    public String getTraceFillColour(){return trace.fillColour;} // colour of the area subtended by the polyline
    public ArrayList<LatLng> getTraceCoords(){ return trace.coords; }

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



    public MapChartFlow(JSONObject data) {
        try {
            flowId = data.getString("ID");
            flowName = data.getString("name");
            maxItems = data.getInt("maxItemsSaved");
            if(data.has("marker"))
                markerProperties = new Marker(data.getJSONObject("marker"));
            if(data.has("trace"))
                trace = new TraceModel(data.getJSONObject("trace"));
        }catch(JSONException e){
            e.printStackTrace();
        }
    }


    class MapChartRecord{
        private String recordId;
        private String markerId;
        private float latitude;
        private float longitude;

        public MapChartRecord(String rId, String mId, float lat, float longit){
            recordId = rId;
            markerId = mId;
            latitude = lat;
            longitude = longit;
        }
    }

    class Marker{
        String type = "default";
        String shape = null;
        String icon = null;
        String text = null;
        String colour = null;

        public Marker(JSONObject data){
            try {
                if(data.has("type"))
                    type = data.getString("type");
                if(data.has("shape"))
                    shape = data.getString("shape");
                if(data.has("icon"))
                    icon = data.getString("icon");
                if(data.has("text"))
                    text = data.getString("text");
                if(data.has("colour"))
                    colour = data.getString("color");
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }

    class TraceModel{
        String type;
        String strokeColour="default";  // colour of the polyline
        String fillColour="default";  // colour of the area subtended by the polyline
        ArrayList<LatLng> coords = new ArrayList<LatLng>();;

        public TraceModel(JSONObject data) {
            try {
                type = data.getString("type");
                if(data.has("strokeColor"))
                    strokeColour = data.getString("strokeColor");
                if(data.has("fillColor"))
                    fillColour = data.getString("fillColor");
                JSONArray line = data.getJSONArray("coordinates");
                for(int i=0; i<line.length(); i++){
                    JSONArray coordinates = line.getJSONArray(i);
                    coords.add(new LatLng((float) coordinates.getDouble(0),(float)coordinates.getDouble(1)));
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void createFlow(JSONObject data) {

        records = new ArrayList<MapChartRecord>();
        try {
            JSONArray recordList = data.getJSONArray("records");
            addRecords(recordList);
        } catch (JSONException e) {
            e.printStackTrace();
        }
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
            trace.strokeColour = data.getJSONObject("trace").getString("strokeColor");
            trace.fillColour = data.getJSONObject("trace").getString("fillColor");
            JSONArray jsonCoordinates = data.getJSONArray("coordinates");
            for(int i=0; i< jsonCoordinates.length(); i++) {
                trace.coords.add(new LatLng((float) jsonCoordinates.getJSONArray(i).getDouble(0),
                        (float)jsonCoordinates.getJSONArray(i).getDouble(1)));
            }
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
            String recordId = data.getString("norrisRecordID");
            String markerId = data.getString("markerID");
            float latitude = (float) data.getJSONArray("value").getDouble(0);
            float longitude = (float) data.getJSONArray("value").getDouble(1);
            Log.d("addRecordJSONObjectdata", recordId+" "+markerId+" "+latitude+" "+longitude);
            records.add(new MapChartRecord(recordId, markerId, latitude, longitude));
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void addRecords(JSONArray jsonRecords){
        try {
            int recordLength = jsonRecords.length();
            for (int i = 0; i < recordLength; i++) {
                JSONObject record = jsonRecords.getJSONObject(i);
                Log.d("addRecords"+i, record.toString());
                addRecord(record);
            }
        }catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updateRecord(JSONObject data) {
        try {
            String recordId = data.getString("norrisRecordID");
            int recordIndex = searchRecordIndex(recordId);
            records.get(recordIndex).markerId = data.getString("markerID");
            records.get(recordIndex).latitude = (float) data.getJSONArray("value").getDouble(0);
            records.get(recordIndex).longitude = (float) data.getJSONArray("value").getDouble(1);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }


    // it searches the record index in the list of records
    protected int searchRecordIndex(String id){
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
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}

