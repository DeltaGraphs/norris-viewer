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
    //it returns the properties of the x axis
    public AxisModel getAxisX();

    //it returns the properties of the y axis
    public AxisModel getAxisY();

    //it returns true if the viewFinder is set
    public Boolean getViewFinder();

    //it returns true if the horizontal grid must exists on the graph
    public Boolean getHorizontalGrid();

    //it returns true if the vertical grid must exists on the graph
    public Boolean getVerticalGrid();

    //it returns the flow list of the chart
    public ArrayList<FlowModel> getFlowList();
}
