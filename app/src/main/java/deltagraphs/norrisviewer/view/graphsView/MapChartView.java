package deltagraphs.norrisviewer.view.graphsView;

/*
 * Name : MapChartView.java
 * Module : norrisviewer::view::graphsView
 * Location : norrisviewer\view\graphsView
 *
 * History :

 * Version Date Programmer Description
 * ===============================================================
 * 
 * 0.1.0 2015-06-04 Enrico Savoca coding of methods
 *
 * 0.0.1 2015-06-04 Enrico Savoca creation of the file
 *
 * ===============================================================
 *
 */

import java.util.ArrayList;

import deltagraphs.norrisviewer.model.flowModel.FlowModel;

public interface MapChartView {
    // This method sets the position of the camera on the Google map.
    public void cameraPosition(float latitude, float longitude);

    // This method set the type of the map.
    public void setMapType(String type);

    // This method enable or disable the legend on point.
    public void setLegendOnPoint(Boolean legend);

    // The followiong method set the zoom on the map and move the camere to the center position of the map zoomed
    public void setZoom(float width, float height);

    //This is the main method of the class. The method set the data on the view when an update has arrived.
    public void setData(ArrayList<FlowModel> flowList, String signal);
}
