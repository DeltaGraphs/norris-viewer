package deltagraphs.norrisviewer.model.graphsModel;

import org.json.JSONObject;

import java.util.ArrayList;

/*
 * Name : BarChartImpl.java
 * Module : com.example.deltagraphs.norrisviewer.model.graphsModel
 * Location : norrisviewer\model\graphsModel
 *
 * History :

 * Version Date Programmer Description
 * ===============================================================
 *
 * 0.1.0 2015-05-15 Enrico Savoca Codifica di tutti gli attributi e i metodi
 *
 * 0.0.1 2015-05-15 Enrico Savoca Creazione file
 *
 * ===============================================================
 *
 */public class BarChartImpl extends Graph{

    private AxisModel axisX;
    private AxisModel axisY;
    private ArrayList<String> headers;
    private String barOrientation;
    private String background;
    private Boolean sortable;
    private String type;
    private Boolean grid;

    public BarChartImpl(JSONObject data){
        // TO DO
    }

    @Override
    public void setData(JSONObject data) {

    }

    @Override
    public void setParameters(JSONObject data) {}

    public AxisModel getAxisX() { return axisX; }
    public AxisModel getAxisY() { return axisY; }
    public ArrayList<String> getHeaders() { return headers; }
    public String getBackground() { return background; }
    public String getBarOrientation() { return barOrientation; }
    public Boolean getSortable() { return sortable; }
    public String getType() { return type; }
    public Boolean getGrid() { return grid; }

}
