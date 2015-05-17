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

public class TableImpl {

    private String appearance;
    private ArrayList<String> headers;
    private String addRowOn;
    private int maxItemsDisplayedPerPage;
    private Boolean sortable;
    private JSONObject sort;

    TableImpl(JSONObject data){
        // TO DO
    }

    public String getAppearance() { return appearance; }
    public ArrayList<String> getHeaders() { return headers; }
    public String getAddRowOn() { return addRowOn; }
    public int getMaxItemsDisplayedPerPage() { return maxItemsDisplayedPerPage; }
    public Boolean getSortable() { return sortable; }
    public JSONObject getSort() { return sort; }
}
