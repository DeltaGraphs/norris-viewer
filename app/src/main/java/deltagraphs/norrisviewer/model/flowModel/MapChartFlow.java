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
    private ArrayList<Record> records;
    public ArrayList<Record> getRecords() { return records; }



    class Record{
        private int index; // ID of bar
        private int value; // value for that bar

        public Record(int index, int value){
        }

        public void setIndex(int index) { this.index = index; }
        public void setValue(int value) { this.value = value; }

        public int getIndex() { return index; }
        public int getValue() { return value; }

    }

    public MapChartFlow(String id, String name, String color) {

    }

    @Override
    public void addRecord(JSONObject record){
        try {
            JSONArray jsonValues = record.getJSONArray("value");
            int index = jsonValues.getInt(0);
            int value = jsonValues.getInt(1);
            records.add(new Record(index, value));
        }catch (JSONException e) {}
    }

}
