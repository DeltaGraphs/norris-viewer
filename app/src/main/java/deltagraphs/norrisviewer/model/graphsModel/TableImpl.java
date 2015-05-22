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

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Observable;

public class TableImpl extends Graph implements Table {

    private String addRowOn;  // top or bottom
    private int maxItems; //displayed per page
    private Boolean sortable;
    private String sortOrder; //ascendent or descendent
    private String sortColumn; // sorted by column "sortColumn"
    private String borderColour;
    private int borderWidth = 1;

    private ArrayList<Column> tableColumns;

    class Column{
        private String headerValue;
        //text and background colour of odd row
        private String headerTextColour;
        private String headerBGColour;
        //text and background colour of even row
        private String rowEvenTextColour;
        private String rowEvenBGColour;
        //text and background colour of odd row
        private String rowOddTextColour;
        private String rowOddBGColour;

        Column(String header, JSONObject appearance){
            //TODO
        }
    }

    public String getAddRowOn() { return addRowOn; }
    public int getMaxItems() { return maxItems; }
    public Boolean getSortable() { return sortable; }
    public String sortByCol() { return sortColumn; }
    public String getSortOrder() { return sortOrder; }
    public int getBorderWidth() { return borderWidth; }
    public String getBorderColour() { return borderColour; }

    //column parameters
    public String getHeaderValue(int index) { return tableColumns.get(index).headerValue; }
    public String getHeaderTextColour(int index) { return tableColumns.get(index).headerTextColour; }
    public String getHeaderBGColour(int index) { return tableColumns.get(index).headerBGColour; }
    public String getRowEvenTC(int index) { return tableColumns.get(index).rowEvenTextColour; }
    public String getRowEvenBGColour(int index) { return tableColumns.get(index).rowEvenBGColour; }
    public String getRowOddTC(int index) { return tableColumns.get(index).rowOddTextColour; }
    public String getRowOddBGColour(int index) { return tableColumns.get(index).rowOddBGColour; }

    @Override
    public void addFlow(JSONObject data) {

    }

    @Override
    public void updateFlow(JSONObject data) {

    }

    @Override
    public void setRecords(JSONObject data) {

    }

    @Override
    public void setParameters(JSONObject data) {

    }

    @Override
    public void updateParameters(JSONObject data) {

    }
}
