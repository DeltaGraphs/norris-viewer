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
 * 0.1.0 2015-05-17 Enrico Savoca Codifica di tutti gli attributi e i metodi
 *
 * 0.0.1 2015-05-17 Enrico Savoca Creazione file
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
    public Boolean getSortable();
    public Boolean getGrid();
    public Boolean getLegendOnPoint();
    public ArrayList<FlowModel> getFlowList();
}
