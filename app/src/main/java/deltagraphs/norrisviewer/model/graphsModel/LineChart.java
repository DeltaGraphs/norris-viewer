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
 * 0.0.1 2015-05-17 Enrico Savoca Creazione file
 *
 * ===============================================================
 *
 */

import java.util.ArrayList;

public interface LineChart {
    public AxisModel getAxisX();
    public AxisModel getAxisY();
    public String getBackground();
    public Boolean getViewFinder();
    public Boolean getHorizontalGrid();
    public Boolean getVerticalGrid();
    public Boolean getLegendOnPoint();
}
