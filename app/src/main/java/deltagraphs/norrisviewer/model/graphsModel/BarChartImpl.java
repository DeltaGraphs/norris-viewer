package deltagraphs.norrisviewer.model.graphsModel;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/*
 * Name : BarChartImpl.java
 * Module : deltagraphs.norrisviewer.model.graphsModel
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
 */

public class BarChartImpl extends Graph{

    private AxisModel axisX;
    private AxisModel axisY;
    private ArrayList<String> headers;
    private String barOrientation;
    private String background;
    private Boolean sortable;
    private Boolean grid;
    private Boolean legendOnPoint;

    public BarChartImpl(JSONObject data){
        // TO DO
    }


    @Override
    public void setParameters(JSONObject data) {
       try {
           background = data.getString("backgroundColor");
           barOrientation = data.getString("barOrientation");
           sortable = data.getBoolean("sortable");
           grid = data.getBoolean("grid");
           JSONArray jsonHeaders = data.getJSONArray("headers");
           int length = jsonHeaders.length();
           if (length > 0) {
               for (int i = 0; i < length; i++) {
                   headers.add(jsonHeaders.getString(i));
               }
           }
           JSONObject xAxis = data.getJSONObject("xAxis");
           axisX = new AxisModel(xAxis);
           JSONObject yAxis = data.getJSONObject("yAxis");
           axisY = new AxisModel(yAxis);
       }catch (JSONException e){}
    }


    @Override
    public void setData(JSONObject data) {}


    public AxisModel getAxisX() { return axisX; }
    public AxisModel getAxisY() { return axisY; }
    public ArrayList<String> getHeaders() { return headers; }
    public String getBackground() { return background; }
    public String getBarOrientation() { return barOrientation; }
    public Boolean getSortable() { return sortable; }
    public Boolean getGrid() { return grid; }
    public Boolean getLegendOnPoint(){ return legendOnPoint; }

    private void JSONParser(JSONObject data, String signal){
        try{
            switch (signal){
                case "configGraph":{
                    JSONObject properties = data.getJSONObject("properties");
                    setParameters(properties);

                    break;
                }
                case "updateGraphProp":{



                    break;
                }
            }


        }catch(JSONException e){
            return;
        }
    }

}
