package deltagraphs.norrisviewer.view.graphsView;

/*
 * Name : MapChartView.java
 * Module : deltagraphs.norrisviewer.view.graphsView
 * Location : norrisviewer\view\graphsView
 *
 * History :

 * Version Date Programmer Description
 * ===============================================================
 * 
 * 0.1.0 2015-05-19 Enrico Savoca Codifica di tutti gli attributi e i metodi
 *
 * 0.0.1 2015-05-19 Enrico Savoca Creazione file
 *
 * ===============================================================
 *
 */

import java.util.ArrayList;

import deltagraphs.norrisviewer.model.flowModel.FlowModel;

public interface MapChartView {
    public void setParams(float latitude, float longitude);
    public void setMapType(String type);
    public void setLegendOnPoint(Boolean legend);
    public void setZoom(float height, float width);
    public void setData(ArrayList<FlowModel> flowList);
}
