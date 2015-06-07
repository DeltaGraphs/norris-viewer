package deltagraphs.norrisviewer.model.graphsModel;

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
 * 0.1.0 2015-05-15 Enrico Savoca Codifica di tutti gli attributi e i metodi
 *
 * 0.0.1 2015-05-15 Enrico Savoca Creazione file
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

    public float getMaxIndex() {
        return maxIndex;
    }

    public float getMinIndex() {
        return minIndex;
    }

    public int getScale() {
        return scale;
    }

    public int getTicks() {
        return ticks;
    }

}

