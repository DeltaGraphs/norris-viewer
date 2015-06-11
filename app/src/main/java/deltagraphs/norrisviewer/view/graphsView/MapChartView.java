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
 * 0.1.0 2015-05-14 Enrico Savoca coding of methods
 *
 * 0.0.1 2015-05-14 Enrico Savoca creation of the file
 *
 * ===============================================================
 *
 */

import java.util.ArrayList;

import deltagraphs.norrisviewer.model.flowModel.FlowModel;

public interface MapChartView {
    public void cameraPosition(float latitude, float longitude);

    public void setMapType(String type);

    public void setLegendOnPoint(Boolean legend);

    public void setZoom(float width, float height);

    public void setData(ArrayList<FlowModel> flowList, String signal);
}
