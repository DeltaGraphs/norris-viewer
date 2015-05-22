package deltagraphs.norrisviewer.model.flowModel;

/*
 * Name : TableFlow.java
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

import java.lang.reflect.Array;
import java.util.ArrayList;

public class TableFlow extends FlowModel {
    private int maxItems;

    private ArrayList<TableRecord> records;
    public ArrayList<TableRecord> getRecords() { return records; }


    public TableFlow(JSONObject data) {

    }

    class TableRecord{
        private String recordId;
        private ArrayList<Value> values = new ArrayList<Value>();

        public TableRecord(String id, ArrayList<Value> valuesList){
            recordId = id;
            values = valuesList;
        }

        class Value{
            String data;
            String background;
            String textColour;

            Value(String data, String bg, String tC){
                this.data = data;
                background = bg;
                textColour = tC;
            }
        }

    }


    @Override
    public void createFlow(JSONObject data) {

    }

    @Override
    public void updateFlow(JSONObject data) {

    }

    @Override
    public void deleteRecordList() {

    }

    @Override
    public void addRecord(JSONObject record){
        try {
            JSONArray jsonValues = record.getJSONArray("value");

            int index = jsonValues.getInt(0);
            int value = jsonValues.getInt(1);
            records.add(new TableRecord( ));
        }catch (JSONException e) {}
    }

    @Override
    public void addRecords(JSONObject data) {

    }

    @Override
    public void updateRecord(JSONObject data) {

    }

    @Override
    public void deleteRecord(JSONObject data) {

    }
}