package deltagraphs.norrisviewer.model.graphsModel;

/*
 * Name : LineChart.java
 * Module : deltagraphs.norrisviewer.model.graphsModel
 * Location : norrisviewer\model\graphsModel
 *
 * History :

 * Version Date Programmer Description
 * ===============================================================
 *
 * 0.1.0 2015-05-27 Enrico Savoca Coding of all methods
 *
 * 0.0.1 2015-05-27 Enrico Savoca Creation of the file
 *
 * ===============================================================
 *
 */

import java.util.ArrayList;

import deltagraphs.norrisviewer.model.flowModel.FlowModel;

public interface LineChart {
    public AxisModel getAxisX();
    public AxisModel getAxisY();
    public String getBackground();
    public Boolean getViewFinder();
    public Boolean getHorizontalGrid();
    public Boolean getVerticalGrid();
    public Boolean getLegendOnPoint();
    public ArrayList<FlowModel> getFlowList();
}
