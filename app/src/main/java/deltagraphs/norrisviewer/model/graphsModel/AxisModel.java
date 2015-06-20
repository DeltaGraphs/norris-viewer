package deltagraphs.norrisviewer.model.graphsModel;

import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

/*
 * Name : AxisModel.java
 * Module : deltagraphs.norrisviewer.model.graphsModel
 * Location : norrisviewer\model\graphsModel
 *
 * History :

 * Version Date Programmer Description
 * ===============================================================
 *
 * 0.1.2 2015-05-23 Davide Trivellato Fix method AxisModel(JSONObject params)
 *
 * 0.1.1 2015-05-23 Davide Trivellato Change the type of minIndex from int to float
 *
 * 0.1.0 2015-05-22 Davide Trivellato Coding of all methods and attibutes
 *
 * 0.0.1 2015-05-22 Davide Trivellato Creation of the file
 *
 * ===============================================================
 *
 */

public class AxisModel {
    private String name;
    private float maxIndex;
    private float minIndex;
    private int ticks;

    // Axis constructor.
    // It's used when a new axis must be built.
    // The Jsonobject 'params' must be contain the attributes, in order to initialize the class.
    AxisModel(JSONObject params) {
        try {
            if (params.has("name"))
                name = params.getString("name");
            if (params.has("maxIndex"))
                maxIndex = (float) params.getDouble("maxIndex");
            if (params.has("minIndex"))
                minIndex = (float) params.getDouble("minIndex");
            if (params.has("ticks"))
                ticks = params.getInt("ticks");
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    //it returns the name of an axis.
    public String getName() { return name; }

    //it returns the maximum index on an axis.
    public Float getMaxIndex() {
        return maxIndex;
    }

    //it returns the minimum index on an axis.
    public Float getMinIndex() { return minIndex; }

    //it returns the number of ticks on an axis.
    public int getTicks() {
        return ticks;
    }

}

