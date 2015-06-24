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
    void setAxis(char axisXorY, String name, int ticks);

    void setGrid(Boolean horizontal, Boolean vertical);

    void setData(ArrayList<FlowModel> flowList, String signal);
}
