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
 * 0.2.1 2015-06-12 Enrico Savoca Add and Update method addRecords(JSONArray data, boolean insertOnTop);
 *
 * 0.2.0 2015-06-04 Enrico Savoca Add classes TraceModel and Marker
 *
 * 0.1.0 2015-06-03 Enrico Savoca Coding of all methods and attibutes
 *
 * 0.0.1 2015-06-03 Enrico Savoca Creation of the file
 *
 * ===============================================================
 *
 */

import com.google.android.gms.maps.model.LatLng;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;


public class MapChartFlow extends FlowModel {

    private Marker markerProperties;
    private TraceModel trace;
    private ArrayList<MapChartRecord> records = new ArrayList<MapChartRecord>(); /* a list that contains all the records of the flow */

    //it returns the record list length
    public int getRecordSize() {
        return records.size();
    }

    //it returns the id of the record at the position 'index' in the list
    public String getRecordId(int index) {
        return records.get(index).recordId;
    }

    //it returns the marker id of the record at the position 'index' in the list
    public String getRecordMarkerId(int index) {
        return records.get(index).markerId;
    }

    //it returns the latitude value of the record at the position 'index' in the list
    public float getRecordLatitude(int index) {
        return records.get(index).latitude;
    }

    //it returns the longitude value of the record at the position 'index' in the list
    public float getRecordLongitude(int index) {
        return records.get(index).longitude;
    }

    // it returns true if the trace model has been updated after being drawed in the map chart (view)
    // When used, the value of 'traceUpdated' always becomes false at the end.
    public Boolean isTraceUpdated() {
        if (trace.traceUpdated == true) {
            trace.traceUpdated = false;
            return true;
        }
        return false;
    }

    //it returns the type of trace: area or polyline
    public String getTraceType() {
        return trace.type;
    }

    //it returns the colour of the trace
    public String getTraceStrokeColour() {
        return trace.strokeColour;
    } // colour of the polyline

    //it returns the colour of the area within the trace
    public String getTraceFillColour() {
        return trace.fillColour;
    } // colour of the area subtended by the polyline

    //it returns the coordinates of a trace
    public ArrayList<LatLng> getTraceCoords() {
        return trace.coords;
    }

    //it returns the marker type value
    public String getMarkerType() {
        return markerProperties.type;
    }

    //it returns the marker colour
    public String getMarkerColour() {
        return markerProperties.colour;
    }

    //it returns marker type.
    // Actually, it can assume three values: shape, text, default.
    public String getMarkerProperty(String type) {
        switch (type) {
            case ("shape"): {
                return markerProperties.shape;
            }
            case ("text"): {
                return markerProperties.text;
            }
            default: {
                return "default";
            }
        }
    }

    // Constructor of the flow.
    // It is called when a new flow is added to the flow list of a chart.
    // Flow parameters are initialized with jsonobject data content.
    // Jsonobject data must contain a value for each parameter.
    public MapChartFlow(JSONObject data) {
        try {
            flowId = data.getString("ID");
            flowName = data.getString("name");
            if (data.has("marker"))
                markerProperties = new Marker(data.getJSONObject("marker"));
            if (data.has("trace"))
                trace = new TraceModel(data.getJSONObject("trace"));
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    // This inner class contains all the attributes that are related to a single record
    class MapChartRecord {
        private String recordId;
        private String markerId;
        private float latitude;
        private float longitude;

        // Record constructor.
        // It's used when a new record is added to a record list.
        // The new record is initialized with the passed parameters.
        public MapChartRecord(String rId, String mId, float lat, float longit) {
            recordId = rId;
            markerId = mId;
            latitude = lat;
            longitude = longit;
        }
    }

    // This inner class contains all the attributes of a marker
    class Marker {
        String type = "default";
        String shape = null;
        String text = null;
        String colour = "";

        // Marker constructor.
        // It's used when a new type of marker is created.
        // The new marker type is initialized with the passed parameters.
        public Marker(JSONObject data) {
            try {
                if (data.has("type"))
                    type = data.getString("type");
                if (data.has("shape"))
                    shape = data.getString("shape");
                if (data.has("text"))
                    text = data.getString("text");
                if (data.has("color")&&(!(data.isNull("color"))))
                    colour = data.getString("color");
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }

    // This inner class contains all the attributes of a trace
    class TraceModel {
        String type;
        String strokeColour = "default";  /* colour of the polyline */
        String fillColour = "default";  /* colour of the area subtended by the polyline */
        Boolean traceUpdated = false;
        ArrayList<LatLng> coords = new ArrayList<LatLng>();

        // Trace constructor.
        // It's used when a new trace is created.
        // The new trace is initialized with the passed parameters.
        public TraceModel(JSONObject data) {
            traceUpdated = true;
            try {
                type = data.getString("type");
                if (data.has("strokeColor"))
                    strokeColour = data.getString("strokeColor");
                if (data.has("fillColor"))
                    fillColour = data.getString("fillColor");
                JSONArray line = data.getJSONArray("coordinates");
                for (int i = 0; i < line.length(); i++) {
                    JSONArray coordinates = line.getJSONArray(i);
                    coords.add(new LatLng((float) coordinates.getDouble(0), (float) coordinates.getDouble(1)));
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }

    // The following method create a new flow.
    // The record list is created and is initialized with the jsonobject data.
    // The Jsonobject data must contain a record list.
    @Override
    public void createFlow(JSONObject data) {

        records = new ArrayList<MapChartRecord>();
        try {
            JSONArray recordList = data.getJSONArray("records");
            addRecords(recordList, false);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    // The following method updates all the flow attributes.
    // The Jsonobject data must contain a value for each of them.
    @Override
    public void updateFlow(JSONObject data) {
        try {
            flowName = data.getString("name");
            markerProperties.type = data.getJSONObject("marker").getString("type");
            markerProperties.shape = data.getJSONObject("marker").getString("shape");
            markerProperties.text = data.getJSONObject("marker").getString("text");
            markerProperties.colour = data.getJSONObject("marker").getString("color");
            if (data.has("trace"))
                trace.traceUpdated = true;
            trace.type = data.getJSONObject("trace").getString("type");
            trace.strokeColour = data.getJSONObject("trace").getString("strokeColor");
            trace.fillColour = data.getJSONObject("trace").getString("fillColor");
            JSONArray jsonCoordinates = data.getJSONArray("coordinates");
            for (int i = 0; i < jsonCoordinates.length(); i++) {
                trace.coords.add(new LatLng((float) jsonCoordinates.getJSONArray(i).getDouble(0),
                        (float) jsonCoordinates.getJSONArray(i).getDouble(1)));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    // Used to delete the whole flow list. Params of the flow will remain.
    @Override
    public void deleteRecordList() {
        records = null;
    }

    // The following method insert a record in the flow.
    // The record is build with the JsonObject's informations.
    @Override
    public void addRecord(JSONObject data) {
        try {
            String recordId = data.getString("norrisRecordID");
            String markerId = data.getString("markerID");
            float latitude = (float) data.getJSONArray("value").getDouble(0);
            float longitude = (float) data.getJSONArray("value").getDouble(1);
            records.add(new MapChartRecord(recordId, markerId, latitude, longitude));
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    // Used to insert some records in the flow.
    // The JsonObject data must contain the a JsonArray with the records that must be inserted.
    // This method will use the method 'addRecord(record)' for each record adding.
    // The boolean variable 'insertOnTop' is not currently used, but it allows future extensions.
    @Override
    public void addRecords(JSONArray jsonRecords, boolean insertOnTop) {
        try {
            int recordLength = jsonRecords.length();
            for (int i = 0; i < recordLength; i++) {
                JSONObject record = jsonRecords.getJSONObject(i);
                addRecord(record);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    // Used to update a record of the flow.
    // The JsonObject data must contain the ID of the record that will be updated.
    // The method will search for the record Index in the flowList, using its ID.
    // After that, all the parameters of the record will be updated.
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


    // It searches the record index in the list of records.
    // A record's id must be provided.
    protected int searchRecordIndex(String id) {
        int index = 0;
        while (index < records.size()) {
            if (records.get(index).recordId.equals(id))
                return index;
            index++;
        }
        return -1;
    }

    // Used to delete a record from the flow.
    // The JsonObject data must contain the ID of the record that will be deleted.
    // The method will search for the record Index in the flowList, using its ID.
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

