package deltagraphs.norrisviewer.model.graphsModel;

import org.json.JSONObject;

import java.util.Observable;

/*
 * Name : MapChartImpl.java
 * Module : deltagraphs.norrisviewer.model.graphsModel
 * Location : norrisviewer\model\graphsModel
 *
 * History :

 * Version Date Programmer Description
 * ===============================================================
 * 
 * 0.1.0 2015-05-17 Enrico Savoca Codifica di tutti gli attributi e i metodi
 *
 * 0.0.1 2015-05-17 Enrico Savoca Creazione file
 *
 * ===============================================================
 *
 */

public class MapChartImpl extends Graph implements MapChart {

    private float latitude;
    private float longitude;
    private int scale;
    private String mapType;
    private Boolean zoom;
    private Boolean legendOnPoint;


    public void setMapChartImpl(JSONObject obj, String signal){JSONParser(obj, signal);}

    public float getLatitude() { return latitude; }
    public float getLongitude() { return longitude; }
    public int getScale() { return scale; }
    public String getMapType() { return mapType; }
    public Boolean getZoom() { return zoom; }
    public Boolean getLegendOnPoint(){ return legendOnPoint; }


    @Override
    public void addFlow(JSONObject data) {

    }

    @Override
    public void updateFlow(JSONObject data) {

    }

    @Override
    public void setData(JSONObject data) {

    }

    @Override
    public void updateData(JSONObject data) {

    }

    @Override
    public void setParameters(JSONObject data) {

    }

    @Override
    public void updateParameters(JSONObject data) {

    }


}
