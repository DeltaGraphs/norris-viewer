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
    public void setAxis(char axisXorY, String name, String appearance, float maxIndex, float minIndex, int ticks, int scale);

    public void setViewFinder(Boolean withViewFinder);

    public void setBackground(String bg);

    public void setGrid(Boolean horizontal, Boolean vertical);

    public void setLegendOnPoint(Boolean legend);

    public void setData(ArrayList<FlowModel> flowList, String signal);
}
