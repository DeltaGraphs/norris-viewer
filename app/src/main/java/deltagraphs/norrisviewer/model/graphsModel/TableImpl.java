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
    private ArrayList<Column> columns = new ArrayList<Column>(); // properties for each column
    private ArrayList<String> headers = new ArrayList<String>(); // values of headers

    //it returns the position where new records will be inserted in table flows
    public String getAddRowOn() {
        return addRowOn;
    }

    // returns true if the table has an horizontal border
    public Boolean getHorizontalBorder() {
        return horizontalBorder;
    }

    // returns true if the table has a vertical border
    public Boolean getVerticalBorder() {
        return verticalBorder;
    }

    //it returns the number of columns of the table
    public int getNumberOfColumns() {
        return headers.size();
    }

    //it returns the value of a header for a given index. The index represents the number of the column
    public String getHeaderValue(int index) {
        return headers.get(index);
    }

    @Override
    public String getRowEvenTC(int index) {
        return columns.get(index).rowEvenTextColour;
    }

    @Override
    public String getRowEvenBGColour(int index) {
        return columns.get(index).rowEvenBGColour;
    }

    @Override
    public String getRowOddTC(int index) {
        return columns.get(index).rowOddTextColour;
    }

    @Override
    public String getRowOddBGColour(int index) {
        return columns.get(index).rowOddBGColour;
    }

    // constructor of TableImpl. It requires an observer. It will receives update from TableImpl
    public TableImpl(Observer chartPresenter) {
        addObserver(chartPresenter);
    }


    class Column {
        //text and background colour of even row
        private String rowEvenTextColour = "#000000";
        private String rowEvenBGColour = "#FFFFFF";
        //text and background colour of odd row
        private String rowOddTextColour = "#000000";
        private String rowOddBGColour = "#FFFFFF";

        Column(JSONObject data, int index) {
            try {
                if (index < data.getJSONObject("rowEven").getJSONArray("textColor").length())
                    rowEvenTextColour = data.getJSONObject("rowEven").getJSONArray("textColor").getString(index);
                if (index < data.getJSONObject("rowEven").getJSONArray("backgroundColor").length())
                    rowEvenBGColour = data.getJSONObject("rowEven").getJSONArray("backgroundColor").getString(index);
                if (index < data.getJSONObject("rowOdd").getJSONArray("textColor").length())
                    rowOddTextColour = data.getJSONObject("rowOdd").getJSONArray("textColor").getString(index);
                if (index < data.getJSONObject("rowOdd").getJSONArray("backgroundColor").length())
                    rowOddBGColour = data.getJSONObject("rowOdd").getJSONArray("backgroundColor").getString(index);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }


    // The following method is called when a new update arrives from the socket.
    // The arriving Json Object contains the informations to set the attributes of the chart
    // It's used to set all the parameters of the chart and to create its flows.
    public void setParameters(JSONObject data) {
        try {
            title = data.getString("title");

            addRowOn = data.getString("addRowOn");
            JSONObject appearance = data.getJSONObject("appearance");
            // for each column a value of header and of colours are taken from the json
            JSONArray jsonColumns = data.getJSONArray("headers");
            int jsonColumnsSize = jsonColumns.length();
            for (int i = 0; i < jsonColumnsSize; i++) {
                headers.add(jsonColumns.getString(i));
                columns.add(new Column(appearance, i));
            }

            if (appearance.getJSONObject("horizontalGrid").getInt("width") > 0)
                horizontalBorder = true;
            else
                horizontalBorder = false;
            if (appearance.getJSONObject("verticalGrid").getInt("width") > 0)
                verticalBorder = true;
            else
                verticalBorder = false;
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

            if (data.has("appearance")) {
                JSONObject appearance = data.getJSONObject("appearance");
                if (appearance.has("horizontalGrid"))
                    if (appearance.getJSONObject("horizontalGrid").getInt("width") > 0)
                        horizontalBorder = true;
                    else horizontalBorder = false;
                if (appearance.has("verticalGrid"))
                    if (appearance.getJSONObject("verticalGrid").getInt("width") > 0)
                        verticalBorder = true;
                    else verticalBorder = false;

                for (int i = 0; i < columns.size(); i++) {
                    if (i < appearance.getJSONObject("rowEven").getJSONArray("textColor").length())
                        columns.get(i).rowEvenTextColour = appearance.getJSONObject("rowEven").getJSONArray("textColor").getString(i);
                    if (i < appearance.getJSONObject("rowEven").getJSONArray("backgroundColor").length())
                        columns.get(i).rowEvenBGColour = appearance.getJSONObject("rowEven").getJSONArray("backgroundColor").getString(i);
                    if (i < appearance.getJSONObject("rowOdd").getJSONArray("textColor").length())
                        columns.get(i).rowOddTextColour = appearance.getJSONObject("rowOdd").getJSONArray("textColor").getString(i);
                    if (i < appearance.getJSONObject("rowOdd").getJSONArray("backgroundColor").length())
                        columns.get(i).rowOddBGColour = appearance.getJSONObject("rowOdd").getJSONArray("backgroundColor").getString(i);
                }
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
