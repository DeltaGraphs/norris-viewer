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
    private String appearance;
    private float maxIndex;
    private float minIndex;
    private int ticks;
    private int scale;

    AxisModel(JSONObject params) {
        try {
            if (params.has("name"))
                name = params.getString("name");
            if (params.has("color"))
                appearance = params.getString("color");
            if (params.has("maxIndex"))
                maxIndex = (float) params.getDouble("maxIndex");
            if (params.has("minIndex"))
                minIndex = (float) params.getDouble("minIndex");
            if (params.has("ticks"))
                ticks = params.getInt("ticks");
            if (params.has("scale"))
                scale = params.getInt("scale");
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public String getName() {
        return name;
    }

    public String getAppearance() {
        return appearance;
    }

    public Float getMaxIndex() {
        return maxIndex;
    }

    public Float getMinIndex() {
        return minIndex;
    }

    public int getScale() {
        return scale;
    }

    public int getTicks() {
        return ticks;
    }

}

