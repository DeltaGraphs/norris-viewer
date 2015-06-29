package deltagraphs.norrisviewer.view.graphsView;

/*
 * Name : LineChartView.java
 * Module : norrisviewer::view::graphsView
 * Location : norrisviewer\view\graphsView
 *
 * History :

 * Version Date Programmer Description
 * ===============================================================
 * 
 * 0.1.0 2015-05-31 Enrico Savoca coding of methods
 *
 * 0.0.1 2015-05-31 Enrico Savoca creation of the file
 *
 * ===============================================================
 *
 */

import java.util.ArrayList;

import deltagraphs.norrisviewer.model.flowModel.FlowModel;

public interface LineChartView {
    //This method configures a single axis for the line chart
    void setAxis(char axisXorY, String name, int ticks);

    //This method sets a grid if the option is enabled by the two variables
    void setGrid(Boolean horizontal, Boolean vertical);

    //It's the main method. It update the view by getting data from the model and set the values on the graph
    void setData(ArrayList<FlowModel> flowList, String signal);

    //This method
    void setViewFinder(Boolean isVisible);
}
