package deltagraphs.norrisviewer.model.graphsModel;

/*
 * Name : MapChart.java
 * Module : deltagraphs.norrisviewer.model.graphsModel
 * Location : norrisviewer\model\graphsModel
 *
 * History :

 * Version Date Programmer Description
 * ===============================================================
 *
 * 0.1.0 2015-06-01 Enrico Savoca Coding of all methods
 *
 * 0.0.1 2015-06-01 Enrico Savoca Creation of the file
 *
 * ===============================================================
 *
 */

import java.util.ArrayList;

import deltagraphs.norrisviewer.model.flowModel.FlowModel;

public interface MapChart {
    //it returns the latitude of the center of the mapChart
    public float getLatitude();

    //it returns the longitude of the center of the mapChart
    public float getLongitude();

    //it returns the width of the center of the mapChart
    public float getMapWidth();

    //it returns the height of the center of the mapChart
    public float getMapHeight();

    //it returns true if the legend on point exists
    public Boolean getLegendOnPoint();

    //it returns the type of map.
    public String getMapType();

    //it returns the flow list of the chart
    public ArrayList<FlowModel> getFlowList();
}
