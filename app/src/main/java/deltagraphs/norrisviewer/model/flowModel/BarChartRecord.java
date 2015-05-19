package deltagraphs.norrisviewer.model.flowModel;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

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

public class BarChartRecord implements FlowModel.Record{

    private int index; // ID of bar
    private int value; // value for that bar


    public BarChartRecord(JSONObject record){
        try {
        JSONArray jsonValues = record.getJSONArray("value");
        index = jsonValues.getInt(0);
        value = jsonValues.getInt(1);
        } catch (JSONException e) {}


    }


}
