package deltagraphs.norrisviewer.model.graphsModel;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Observer;

import deltagraphs.norrisviewer.model.flowModel.MapChartFlow;

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

    private float mapWidth;
    private float mapHeight;
    private float latitude;
    private float longitude;
    private Boolean legendOnPoint;
    private String mapType;

    public float getLatitude() {
        return latitude;
    }

    public float getLongitude() {
        return longitude;
    }

    public float getMapWidth() {
        return mapWidth;
    }

    public float getMapHeight() {
        return mapHeight;
    }

    public Boolean getLegendOnPoint() {
        return legendOnPoint;
    }

    public String getMapType() {
        return mapType;
    }

    public MapChartImpl(Observer chartPresenter) {
        addObserver(chartPresenter);
    }

    public void setParameters(JSONObject data) {
        try {
            title = data.getString("title");
            mapWidth = (float) data.getDouble("mapWidth");
            mapHeight = (float) data.getDouble("mapHeight");
            latitude = (float) data.getDouble("latitude");
            longitude = (float) data.getDouble("longitude");
            legendOnPoint = data.getBoolean("legendOnPoint");
            mapType = data.getString("mapType");
            //changes to flow params
            JSONArray jsonFlows = data.getJSONArray("flows");
            int flowLenght = jsonFlows.length();
            for (int i = 0; i < flowLenght; i++) {
                JSONObject flow = jsonFlows.getJSONObject(i);
                addFlow(flow);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updateParameters(JSONObject data) {
        try {
            if (data.has("title"))
                title = data.getString("title");
            if (data.has("mapWidth"))
                mapWidth = (float) data.getDouble("mapWidth");
            if (data.has("mapHeight"))
                mapHeight = (float) data.getDouble("mapHeight");
            if (data.has("latitude"))
                latitude = (float) data.getDouble("latitude");
            if (data.has("longitude"))
                longitude = (float) data.getDouble("longitude");
            if (data.has("legendOnPoint"))
                legendOnPoint = data.getBoolean("legendOnPoint");
            if (data.has("mapType"))
                mapType = data.getString("mapType");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void addFlow(JSONObject flow) {
        flowList.add(new MapChartFlow(flow));
    }
}
