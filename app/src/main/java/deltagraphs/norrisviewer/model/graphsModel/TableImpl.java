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
 * 0.1.0 2015-05-17 Enrico Savoca Codifica di tutti gli attributi e i metodi
 *
 * 0.0.1 2015-05-17 Enrico Savoca Creazione file
 *
 * ===============================================================
 *
 */

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import deltagraphs.norrisviewer.model.flowModel.*;

public class TableImpl extends Graph implements Table {

    private String addRowOn;  // top or bottom
    private int maxItems; //displayed per page
    private Boolean sortable;
    private String sortOrder; //ascendent or descendent
    private String sortColumn; // sorted by column "sortColumn"
    private int borderWidth = 1;
    private String borderColour;

    private ArrayList<Column> tableColumns;

    class Column{
        private String headerValue;
        //text and background colour of header row
        private String headerTextColour;
        private String headerBGColour;
        //text and background colour of even row
        private String rowEvenTextColour;
        private String rowEvenBGColour;
        //text and background colour of odd row
        private String rowOddTextColour;
        private String rowOddBGColour;

        Column(String value, JSONObject data){
           try {
               headerValue = value;
               headerTextColour = data.getJSONObject("headers").getString("textColor");
               headerBGColour = data.getJSONObject("headers").getString("backgroundColor");
               rowEvenTextColour = data.getJSONObject("rowEven").getString("textColor");
               rowEvenBGColour = data.getJSONObject("rowEven").getString("backgroundColor");
               rowOddTextColour = data.getJSONObject("oddEven").getString("textColor");
               rowOddBGColour = data.getJSONObject("oddEven").getString("backgroundColor");
           }catch (JSONException e){}
        }
    }

    public String getAddRowOn() { return addRowOn; }
    public int getMaxItems() { return maxItems; }
    public Boolean getSortable() { return sortable; }
    public String sortByCol() { return sortColumn; }
    public String getSortOrder() { return sortOrder; }
    public int getBorderWidth() { return borderWidth; }
    public String getBorderColour() { return borderColour; }
    public ArrayList<FlowModel> getFlowList(){ return super.getFlowList(); }
    public int getNumberOfColumns(){ return tableColumns.size(); }

    //column parameters
    public String getHeaderValue(int index) { return tableColumns.get(index).headerValue; }
    public String getHeaderTextColour(int index) { return tableColumns.get(index).headerTextColour; }
    public String getHeaderBGColour(int index) { return tableColumns.get(index).headerBGColour; }
    public String getRowEvenTC(int index) { return tableColumns.get(index).rowEvenTextColour; }
    public String getRowEvenBGColour(int index) { return tableColumns.get(index).rowEvenBGColour; }
    public String getRowOddTC(int index) { return tableColumns.get(index).rowOddTextColour; }
    public String getRowOddBGColour(int index) { return tableColumns.get(index).rowOddBGColour; }


    public void setParameters(JSONObject data) {
        try {
            title = data.getString("title");
            sortable = data.getBoolean("sortable");
            maxItems = data.getInt("maxItemsPage");
            addRowOn = data.getString("addRowOn");
            sortOrder = data.getJSONObject("sort").getString("ordering");
            sortColumn = data.getJSONObject("sort").getString("column");
            borderColour = data.getJSONObject("appearance").getJSONObject("border").getString("color");
            borderWidth = data.getJSONObject("appearance").getJSONObject("border").getInt("width");

            JSONArray jsonColumns = data.getJSONArray("headers");
            int jsonColumnsSize = jsonColumns.length();
            for(int i = 0; i< jsonColumnsSize; i++){
                tableColumns.add(new Column(jsonColumns.getString(i), data.getJSONObject("appearance")));
            }

            //changes to flow params
            JSONArray jsonFlows = data.getJSONArray("flows");
            int flowLenght = jsonFlows.length();
            for(int i=0; i< flowLenght; i++){
                JSONObject flow = jsonFlows.getJSONObject(i);
                addFlow(flow);
            }
        }catch (JSONException e){}
    }

    @Override
    public void updateParameters(JSONObject data) {
        try {
            if (data.has("title"))
                title = data.getString("title");

            if(data.has("sortable"))
                sortable = data.getBoolean("sortable");

            if(data.has("maxItemsPage"))
                maxItems = data.getInt("maxItemsPage");

            if(data.has("addRowOn"))
                addRowOn = data.getString("addRowOn");

            if((data.has("sort"))&&(data.getJSONObject("sort").has("ordering")))
                sortOrder = data.getJSONObject("sort").getString("ordering");

            if((data.has("sort"))&&(data.getJSONObject("sort").has("column")))
                sortColumn = data.getJSONObject("sort").getString("column");

            if((data.has("appearance"))&&(data.getJSONObject("appearance").has("border"))&&
                    (data.getJSONObject("appearance").getJSONObject("border").has("color")))
                borderColour = data.getJSONObject("appearance").getJSONObject("border").getString("color");

            if((data.has("appearance"))&&(data.getJSONObject("appearance").has("border"))&&
                    (data.getJSONObject("appearance").getJSONObject("border").has("width")))
                borderWidth = data.getJSONObject("appearance").getJSONObject("border").getInt("width");

            if(data.has("headers")){
                JSONArray jsonColumns = data.getJSONArray("headers");
                int jsonColumnsSize = jsonColumns.length();
                for(int i = 0; i< jsonColumnsSize; i++) {
                    tableColumns.get(i).headerValue = jsonColumns.getString(i);
                }
            }

            if((data.has("appearance"))&&(data.getJSONObject("appearance").has("rowEven"))&&
                    (data.getJSONObject("appearance").getJSONObject("rowEven").has("textColor"))){
                JSONArray jsonColumns = data.getJSONObject("appearance").getJSONObject("rowEven").getJSONArray("textColor");
                int jsonColumnsSize = jsonColumns.length();
                for(int i = 0; i< jsonColumnsSize; i++) {
                    tableColumns.get(i).rowEvenTextColour = jsonColumns.getString(i);
                }
            }

            if((data.has("appearance"))&&(data.getJSONObject("appearance").has("rowEven"))&&
                    (data.getJSONObject("appearance").getJSONObject("rowEven").has("backgroundColor"))){
                JSONArray jsonColumns = data.getJSONObject("appearance").getJSONObject("rowEven").getJSONArray("backgroundColor");
                int jsonColumnsSize = jsonColumns.length();
                for(int i = 0; i< jsonColumnsSize; i++) {
                    tableColumns.get(i).rowEvenBGColour = jsonColumns.getString(i);
                }
            }

            if((data.has("appearance"))&&(data.getJSONObject("appearance").has("rowOdd"))&&
                    (data.getJSONObject("appearance").getJSONObject("rowOdd").has("textColor"))){
                JSONArray jsonColumns = data.getJSONObject("appearance").getJSONObject("rowOdd").getJSONArray("textColor");
                int jsonColumnsSize = jsonColumns.length();
                for(int i = 0; i< jsonColumnsSize; i++) {
                    tableColumns.get(i).rowOddTextColour = jsonColumns.getString(i);
                }
            }

            if((data.has("appearance"))&&(data.getJSONObject("appearance").has("rowEven"))&&
                    (data.getJSONObject("appearance").getJSONObject("rowOdd").has("backgroundColor"))){
                JSONArray jsonColumns = data.getJSONObject("appearance").getJSONObject("rowOdd").getJSONArray("backgroundColor");
                int jsonColumnsSize = jsonColumns.length();
                for(int i = 0; i< jsonColumnsSize; i++) {
                    tableColumns.get(i).rowOddBGColour = jsonColumns.getString(i);
                }
            }

            if((data.has("appearance"))&&(data.getJSONObject("appearance").has("headers"))&&
                    (data.getJSONObject("appearance").getJSONObject("headers").has("textColor"))){
                JSONArray jsonColumns = data.getJSONObject("appearance").getJSONObject("headers").getJSONArray("textColor");
                int jsonColumnsSize = jsonColumns.length();
                for(int i = 0; i< jsonColumnsSize; i++) {
                    tableColumns.get(i).headerTextColour = jsonColumns.getString(i);
                }
            }

            if((data.has("appearance"))&&(data.getJSONObject("appearance").has("headers"))&&
                    (data.getJSONObject("appearance").getJSONObject("headers").has("backgroundColor"))){
                JSONArray jsonColumns = data.getJSONObject("appearance").getJSONObject("headers").getJSONArray("backgroundColor");
                int jsonColumnsSize = jsonColumns.length();
                for(int i = 0; i< jsonColumnsSize; i++) {
                    tableColumns.get(i).headerBGColour = jsonColumns.getString(i);
                }
            }

        }catch(Exception e){}
    }

    @Override
    public void addFlow(JSONObject flow){
        flowList.add(new TableFlow(flow));
    }
}
