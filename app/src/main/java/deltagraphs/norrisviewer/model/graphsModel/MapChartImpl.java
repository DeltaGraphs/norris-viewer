package deltagraphs.norrisviewer.model.graphsModel;

import org.json.JSONObject;

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

public class MapChartImpl {

    private float latitude;
    private float longitude;
    private int scale;
    private String mapType;
    private Boolean zoom;

    MapChartImpl(JSONObject data){
        // TO DO !!!
    }

    public float getLatitude() { return latitude; }
    public float getLongitude() { return longitude; }
    public int getScale() { return scale; }
    public String getMapType() { return mapType; }
    public Boolean getZoom() { return zoom; }
}
