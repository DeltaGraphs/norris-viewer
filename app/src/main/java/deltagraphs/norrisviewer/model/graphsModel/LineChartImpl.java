package deltagraphs.norrisviewer.model.graphsModel;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Observer;

import deltagraphs.norrisviewer.model.flowModel.LineChartFlow;


/*
 * Name : LineChartImpl.java
 * Module : deltagraphs.norrisviewer.model.graphsModel
 * Location : norrisviewer\model\graphsModel
 *
 * History :

 * Version Date Programmer Description
 * ===============================================================
 *
 * 0.1.1 2015-05-30 Enrico Savoca Change to updateParameters(JSONObject data). Json is read better than before.
 *
 * 0.1.0 2015-05-30 Enrico Savoca Coding of all methods and attributes
 *
 * 0.0.1 2015-05-29 Enrico Savoca Creation of the file
 *
 * ===============================================================
 *
 */

public class LineChartImpl extends Graph implements LineChart {

    private AxisModel axisX;
    private AxisModel axisY;
    private String background;
    private Boolean viewFinder;
    private Boolean horizontalGrid;
    private Boolean verticalGrid;

    public AxisModel getAxisX() {
        return axisX;
    }

    public AxisModel getAxisY() {
        return axisY;
    }

    public String getBackground() {
        return background;
    }

    public Boolean getViewFinder() {
        return viewFinder;
    }

    public Boolean getHorizontalGrid() {
        return horizontalGrid;
    }

    public Boolean getVerticalGrid() { return verticalGrid; }

    public LineChartImpl(Observer chartPresenter) {
        addObserver(chartPresenter);
    }

    public void setParameters(JSONObject data) {
        try {
            title = data.getString("title");
            background = data.getString("backgroundColor");
            viewFinder = data.getBoolean("viewFinder");
            horizontalGrid = data.getBoolean("horizontalGrid");
            verticalGrid = data.getBoolean("verticalGrid");

            //changes to axises
            JSONObject xAxis = data.getJSONObject("xAxis");
            axisX = new AxisModel(xAxis);
            JSONObject yAxis = data.getJSONObject("yAxis");
            axisY = new AxisModel(yAxis);

            //changes to flow params
            JSONArray jsonFlows = data.getJSONArray("flows");
            int flowLenght = jsonFlows.length();
            for (int i = 0; i < flowLenght; i++) {
                JSONObject flow = jsonFlows.getJSONObject(i);
                addFlow(flow);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updateParameters(JSONObject data) {
        try {
            if (data.has("title"))
                title = data.getString("title");
            if (data.has("backgroundColor"))
                background = data.getString("backgroundColor");
            if (data.has("viewFinder"))
                viewFinder = data.getBoolean("viewFinder");
            if (data.has("horizontalGrid"))
                horizontalGrid = data.getBoolean("horizontalGrid");
            if (data.has("verticalGrid"))
                verticalGrid = data.getBoolean("verticalGrid");
            //changes to axises
            if (data.has("xAxis")) {
                JSONObject xAxis = data.getJSONObject("xAxis");
                axisX = new AxisModel(xAxis);
            }
            if (data.has("yAxis")) {
                JSONObject yAxis = data.getJSONObject("yAxis");
                axisY = new AxisModel(yAxis);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void addFlow(JSONObject flow) {
        flowList.add(new LineChartFlow(flow));
    }

}
