package deltagraphs.norrisviewer.view.graphsView;

/*
 * Name : BarChartActivity.java
 * Module : norrisviewer::view::graphsView
 * Location : norrisviewer\view\graphsView
 *
 * History :

 * Version Date Programmer Description
 * ===============================================================
 * 
 * 0.1.0 2015-05-23 Davide Trivellato Coding of methods
 *
 * 0.0.1 2015-05-23 Davide Trivellato Creation of the file
 *
 * ===============================================================
 *
 */

import java.util.ArrayList;

import deltagraphs.norrisviewer.model.flowModel.FlowModel;

public interface BarChartView {
    void setBarOrientation(String orientation);
    void setData(ArrayList<FlowModel> flowList, String signal, ArrayList<String> headers);
}
