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
    public float getLatitude();
    public float getLongitude();
    public float getMapWidth();
    public float getMapHeight();
    public Boolean getLegendOnPoint();
    public String getMapType();
    public ArrayList<FlowModel> getFlowList();
}
