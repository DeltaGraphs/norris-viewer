package deltagraphs.norrisviewer.model.graphsModel;

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

    AxisModel(JSONObject params){
        // TO DO !!!
    }

    public String getName() { return name; }
    public String getAppearance() { return appearance; }
    public float getMaxIndex() { return maxIndex; }
    public float getMinIndex() { return minIndex; }
    public int getScale() { return scale; }
    public int getTicks() { return ticks; }

}

