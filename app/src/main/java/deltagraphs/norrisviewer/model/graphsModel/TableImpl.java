package deltagraphs.norrisviewer.model.graphsModel;

/*
 * Name : TableImpl.java
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
 * 0.1.1 2015-06-12 Enrico Savoca Add some default value for sortOrder, sortColumn and borderWidth
 *
 * 0.1.0 2015-06-09 Enrico Savoca Coding of all methods and attributes
 *
 * 0.0.1 2015-06-07 Enrico Savoca Creation of the file
 *
 * ===============================================================
 *
 */


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Observer;

import deltagraphs.norrisviewer.model.flowModel.*;

public class TableImpl extends Graph implements Table {

    private String addRowOn;  // top or bottom
    private Boolean horizontalBorder = false; // horizontal border
    private Boolean verticalBorder = false; // vertical border


    private ArrayList<String> headers = new ArrayList<String>();

    //it returns the position where new records will be inserted in table flows
    public String getAddRowOn() {
        return addRowOn;
    }

    // returns true if the table has an horizontal border
    public Boolean getHorizontalBorder() {
        return horizontalBorder;
    }

    // returns true if the table has a vertical border
    public Boolean getVerticalBorder() { return verticalBorder; }

    //it returns the number of columns of the table
    public int getNumberOfColumns() {
        return headers.size();
    }

    //it returns the value of a header for a given index. The index represents the number of the column
    public String getHeaderValue(int index) {
        return headers.get(index);
    }

    // constructor of TableImpl. It requires an observer. It will receives update from TableImpl
    public TableImpl(Observer chartPresenter) {
        addObserver(chartPresenter);
    }

    // The following method is called when a new update arrives from the socket.
    // The arriving Json Object contains the informations to set the attributes of the chart
    // It's used to set all the parameters of the chart and to create its flows.
    public void setParameters(JSONObject data) {
        try {
            title = data.getString("title");

            addRowOn = data.getString("addRowOn");
            JSONArray jsonColumns = data.getJSONArray("headers");
            int jsonColumnsSize = jsonColumns.length();
            for (int i = 0; i < jsonColumnsSize; i++) {
                headers.add(jsonColumns.getString(i));
            }

            if (data.getJSONObject("appearance").getJSONObject("horizontalGrid").getInt("width") > 0)
                horizontalBorder = true;
            if (data.getJSONObject("appearance").getJSONObject("verticalGrid").getInt("width") > 0)
                verticalBorder = true;

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

            if (data.has("addRowOn")) {
                addRowOn = data.getString("addRowOn");
            }

            if (data.has("headers")) {
                headers = new ArrayList<String>();
                JSONArray jsonColumns = data.getJSONArray("headers");
                int jsonColumnsSize = jsonColumns.length();
                for (int i = 0; i < jsonColumnsSize; i++) {
                    headers.add(jsonColumns.getString(i));
                }
            }

            if (data.has("appearance")){
                if (data.getJSONObject("appearance").has("horizontalGrid"))
                    if (data.getJSONObject("appearance").getJSONObject("horizontalGrid").getInt("width") > 0)
                        horizontalBorder = true;
                    else horizontalBorder = false;
                if (data.getJSONObject("appearance").has("verticalGrid"))
                    if (data.getJSONObject("appearance").getJSONObject("verticalGrid").getInt("width") > 0)
                        verticalBorder = true;
                    else verticalBorder = false;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // The following method is called when a new update arrives from the socket.
    // The arriving Json Object contains the informations to add a flow to the chart.
    @Override
    public void addFlow(JSONObject flow) {
        flowList.add(new TableFlow(flow));
    }
}
