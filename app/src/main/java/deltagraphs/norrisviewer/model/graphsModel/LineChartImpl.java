package deltagraphs.norrisviewer.model.graphsModel;

import org.json.JSONObject;

import lecho.lib.hellocharts.model.Axis;

/*
 * Name : LineChartImpl.java
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

public class LineChartImpl extends Graph  implements LineChart{

    private AxisModel xAxis;
    private AxisModel yAxis;
    private String background;
    private Boolean viewFinder;
    private Boolean horizontalGrid;
    private Boolean verticalGrid;
    private Boolean legendOnPoint;


    public void setLineChartImpl(JSONObject obj, String signal){JSONParser(obj, signal);}

    public void setViewFinder(Boolean choice) { this.viewFinder = choice; }

    public AxisModel getAxisX() { return xAxis; }
    public AxisModel getAxisY() { return yAxis; }
    public String getBackground() { return background; }
    public Boolean getViewFinder() { return viewFinder; }
    public Boolean getHorizontalGrid() { return horizontalGrid; }
    public Boolean getVerticalGrid() { return verticalGrid; }
    public Boolean getLegendOnPoint(){ return legendOnPoint; }

    @Override
    public void setData(JSONObject data) {

    }

    @Override
    public void updateData(JSONObject data) {

    }

    @Override
    public void setParameters(JSONObject data) {

    }

    @Override
    public void updateParameters(JSONObject data) {

    }

    @Override
    public void updateFlowProp(JSONObject data) {

    }

    private void JSONParser(JSONObject data, String signal){

    }
}
