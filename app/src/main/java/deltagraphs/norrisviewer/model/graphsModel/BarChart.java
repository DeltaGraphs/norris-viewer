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
 * 0.1.1 2015-05-18 Enrico Savoca Remove method getSortable(), not used
 *
 * 0.1.0 2015-05-17 Enrico Savoca Coding of all methods
 *
 * 0.0.1 2015-05-17 Enrico Savoca Creation of the file
 *
 * ===============================================================
 *
 */

public interface BarChart {
    public AxisModel getAxisX();

    public AxisModel getAxisY();

    public ArrayList<String> getHeaders();

    public String getBackground();

    public String getBarOrientation();

    public Boolean getGrid();

    public Boolean getLegendOnPoint();

    public ArrayList<FlowModel> getFlowList();
}
