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
 * 0.1.1 2015-06-12 Enrico Savoca Add some default value for sortOrder, sortColumn and borderWidth
 *
 * 0.1.0 2015-06-09 Enrico Savoca Coding of all methods and attributes
 *
 * 0.0.1 2015-06-07 Enrico Savoca Creation of the file
 *
 * ===============================================================
 *
 */

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Observer;

import deltagraphs.norrisviewer.model.flowModel.*;

public class TableImpl extends Graph implements Table {

    private String addRowOn;  // top or bottom
    private String sortOrder = null; //ascendent or descendent
    private String sortColumn = null; // sorted by column "sortColumn"

    private ArrayList<String> headers = new ArrayList<String>();

    public String getAddRowOn() {
        return addRowOn;
    }

    public String sortByCol() {
        return sortColumn;
    }

    public String getSortOrder() {
        return sortOrder;
    }

    public int getNumberOfColumns() {
        return headers.size();
    }

    //column parameters
    public String getHeaderValue(int index) {
        return headers.get(index);
    }

    public TableImpl(Observer chartPresenter) {
        addObserver(chartPresenter);
    }

    public void setParameters(JSONObject data) {
        try {
            title = data.getString("title");
            addRowOn = data.getString("addRowOn");
            if (data.has("sort") && (!(data.isNull("sort")))) {
                sortOrder = data.getJSONObject("sort").getJSONArray("ordering").getString(0);
                sortColumn = data.getJSONObject("sort").getJSONArray("column").getString(0);
            }

            JSONArray jsonColumns = data.getJSONArray("headers");
            int jsonColumnsSize = jsonColumns.length();
            for (int i = 0; i < jsonColumnsSize; i++) {
                headers.add(jsonColumns.getString(i));
            }

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

            if (data.has("addRowOn")) {
                addRowOn = data.getString("addRowOn");
                Log.d("", addRowOn);
            }
            if ((data.has("sort")) && (data.getJSONObject("sort").has("ordering")))
                sortOrder = data.getJSONObject("sort").getJSONArray("ordering").getString(0);

            if ((data.has("sort")) && (data.getJSONObject("sort").has("column")))
                if (!(data.isNull("sort")))
                    sortColumn = data.getJSONObject("sort").getJSONArray("column").getString(0);
                else sortColumn = null;

            if (data.has("headers")) {
                headers = new ArrayList<String>();
                JSONArray jsonColumns = data.getJSONArray("headers");
                int jsonColumnsSize = jsonColumns.length();
                for (int i = 0; i < jsonColumnsSize; i++) {
                    headers.add(jsonColumns.getString(i));
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void addFlow(JSONObject flow) {
        flowList.add(new TableFlow(flow));
    }
}
