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
 * 1.0.0 2015-06-22 Matteo Furlan Approve
 *
 * 0.2.0 2015-06-21 Davide Trivellato Verify
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
    private Boolean viewFinder;
    private Boolean horizontalGrid = new Boolean(true);
    private Boolean verticalGrid = new Boolean(true);

    //it returns the properties of the x axis
    public AxisModel getAxisX() {
        return axisX;
    }

    //it returns the properties of the y axis
    public AxisModel getAxisY() {
        return axisY;
    }

    //it returns true if the viewFinder is set
    public Boolean getViewFinder() {
        return viewFinder;
    }

    //it returns true if the horizontal grid must exists on the graph
    public Boolean getHorizontalGrid() {
        return horizontalGrid;
    }

    //it returns true if the vertical grid must exists on the graph
    public Boolean getVerticalGrid() { return verticalGrid; }

    // constructor of LineChartImpl. It requires an observer. It will receives update from LineChartImpl
    public LineChartImpl(Observer chartPresenter) {
        addObserver(chartPresenter);
    }

    // The following method is called when a new update arrives from the socket.
    // The arriving Json Object contains the informations to set the attributes of the chart
    // It's used to set all the parameters of the chart and to create its flows.
    public void setParameters(JSONObject data) {
        try {
            title = data.getString("title");
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

    // The following method is called when a new update arrives from the socket.
    // The arriving Json Object contains the informations to update the attributes of the chart
    // It's only used to update all the parameters of the chart. If an attribute isn't contained in
    // the json object, it won't be updated.
    @Override
    public void updateParameters(JSONObject data) {
        try {
            if (data.has("title"))
                title = data.getString("title");
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

    // The following method is called when a new update arrives from the socket.
    // The arriving Json Object contains the informations to add a flow to the chart.
    @Override
    public void addFlow(JSONObject flow) {
        flowList.add(new LineChartFlow(flow));
    }

}
