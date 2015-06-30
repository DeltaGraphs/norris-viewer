package deltagraphs.norrisviewer.model.graphsModel;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Observer;

import deltagraphs.norrisviewer.model.flowModel.BarChartFlow;

/*
 * Name : BarChartImpl.java
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
 * 0.1.1 2015-05-26 Enrico Savoca Several changes to Constructor
 *
 * 0.1.0 2015-05-25 Enrico Savoca Coding of all methods and attributes
 *
 * 0.0.1 2015-05-23 Enrico Savoca Creation of the file
 *
 * ===============================================================
 *
 */

public class BarChartImpl extends Graph implements BarChart {

    private AxisModel axisX;
    private AxisModel axisY;
    private ArrayList<String> headers = new ArrayList<String>();
    private String barOrientation = "V";

    //it returns the properties of the x axis
    public AxisModel getAxisX() {
        return axisX;
    }

    //it returns the properties of the y axis
    public AxisModel getAxisY() {
        return axisY;
    }

    //it returns the list of values of the headers of the bar chart
    public ArrayList<String> getHeaders() {
        return headers;
    }

    //it returns the orientation of bars in the chart
    public String getBarOrientation() {
        return barOrientation;
    }

    // constructor of BarChartImpl. It requires an observer. It will receives update from BarChartImpl
    public BarChartImpl(Observer chartPresenter) {
        addObserver(chartPresenter);
    }


    // The following method is called when a new update arrives from the socket.
    // The arriving Json Object contains the informations to set the attributes of the chart
    // It's used to set all the parameters of the chart and to create its flows.
    @Override
    public void setParameters(JSONObject data) {
        try {
            title = data.getString("title");
            barOrientation = data.getString("barOrientation");
            JSONArray jsonHeaders = data.getJSONArray("headers");
            int length = jsonHeaders.length();
            if (length > 0) {
                for (int i = 0; i < length; i++) {
                    headers.add(jsonHeaders.getString(i));
                }
            }
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
            if (data.has("barOrientation"))
                barOrientation = data.getString("barOrientation");
            if (data.has("headers")) {
                headers=new ArrayList<String>();
                JSONArray jsonHeaders = data.getJSONArray("headers");
                int length = jsonHeaders.length();
                if (length > 0) {
                    for (int i = 0; i < length; i++) {
                        headers.add(jsonHeaders.getString(i));
                    }
                }
            }
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
        flowList.add(new BarChartFlow(flow));
    }


}
