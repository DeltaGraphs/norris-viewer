package deltagraphs.norrisviewer.model.graphsModel;

import java.util.ArrayList;

import deltagraphs.norrisviewer.model.flowModel.FlowModel;


/*
 * Name : BarChart.java
 * Module : deltagraphs.norrisviewer.model.graphsModel
 * Location : norrisviewer\model\graphsModel
 *
 * History :

 * Version Date Programmer Description
 * ===============================================================
 *
 * 0.1.0 2015-05-22 Enrico Savoca Coding of all methods
 *
 * 0.0.1 2015-05-22 Enrico Savoca Creation of the file
 *
 * ===============================================================
 *
 */


public interface BarChart {
    //it returns the properties of the x axis
    public AxisModel getAxisX();

    //it returns the properties of the y axis
    public AxisModel getAxisY();

    //it returns the list of values of the headers of the bar chart
    public ArrayList<String> getHeaders();

    //it returns the orientation of bars in the chart
    public String getBarOrientation();

    //it returns the flow list of the chart
    public ArrayList<FlowModel> getFlowList();
}
