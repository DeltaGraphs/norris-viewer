package deltagraphs.norrisviewer.model.graphsModel;

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
 * 1.0.0 2015-06-22 Matteo Furlan Approve
 *
 * 0.2.0 2015-06-21 Davide Trivellato Verify
 *
 * 0.1.0 2015-06-03 Enrico Savoca Coding of all methods and attributes
 *
 * 0.0.1 2015-06-01 Enrico Savoca Creation of the file
 *
 * ===============================================================
 *
 */

public class MapChartImpl extends Graph implements MapChart {

    private double mapWidth;
    private double mapHeight;
    private double latitude;
    private double longitude;
    private Boolean legendOnPoint = true;
    private String mapType;

    //it returns the latitude of the center of the mapChart
    public double getLatitude() {
        return latitude;
    }

    //it returns the longitude of the center of the mapChart
    public double getLongitude() {
        return longitude;
    }

    //it returns the width of the center of the mapChart
    public double getMapWidth() {
        return mapWidth;
    }

    //it returns the height of the center of the mapChart
    public double getMapHeight() {
        return mapHeight;
    }

    //it returns true if the legend on point exists
    public Boolean getLegendOnPoint() {
        return legendOnPoint;
    }

    //it returns the type of map.
    public String getMapType() {
        return mapType;
    }

    // constructor of MapChartImpl. It requires an observer. It will receives update from MapChartImpl
    public MapChartImpl(Observer chartPresenter) {
        addObserver(chartPresenter);
    }

    // The following method is called when a new update arrives from the socket.
    // The arriving Json Object contains the informations to set the attributes of the chart
    // It's used to set all the parameters of the chart and to create its flows.
    public void setParameters(JSONObject data) {
        try {
            title = data.getString("title");
            mapWidth = data.getDouble("mapWidth");
            mapHeight = data.getDouble("mapHeight");
            latitude = data.getDouble("latitude");
            longitude = data.getDouble("longitude");
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

    // The following method is called when a new update arrives from the socket.
    // The arriving Json Object contains the informations to update the attributes of the chart
    // It's only used to update all the parameters of the chart. If an attribute isn't contained in
    // the json object, it won't be updated.
    @Override
    public void updateParameters(JSONObject data) {
        try {
            if (data.has("title"))
                title = data.getString("title");
            if (data.has("mapWidth"))
                mapWidth = data.getDouble("mapWidth");
            if (data.has("mapHeight"))
                mapHeight = data.getDouble("mapHeight");
            if (data.has("latitude"))
                latitude = data.getDouble("latitude");
            if (data.has("longitude"))
                longitude = data.getDouble("longitude");
            if (data.has("legendOnPoint"))
                legendOnPoint = data.getBoolean("legendOnPoint");
            if (data.has("mapType"))
                mapType = data.getString("mapType");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // The following method is called when a new update arrives from the socket.
    // The arriving Json Object contains the informations to add a flow to the chart.
    @Override
    public void addFlow(JSONObject flow) {
        flowList.add(new MapChartFlow(flow));
    }
}
