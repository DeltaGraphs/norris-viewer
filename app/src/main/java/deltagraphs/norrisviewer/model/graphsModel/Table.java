package deltagraphs.norrisviewer.model.graphsModel;

/*
 * Name : Table.java
 * Module : deltagraphs.norrisviewer.model.graphsModel
 * Location : norrisviewer\model\graphsModel
 *
 * History :

 * Version Date Programmer Description
 * ===============================================================
 *
 * 0.0.1 2015-05-17 Enrico Savoca Creazione file
 *
 * ===============================================================
 *
 */

import java.util.ArrayList;

import deltagraphs.norrisviewer.model.flowModel.FlowModel;

public interface Table {
    public String getAddRowOn();
    public int getMaxItems();
    public Boolean getSortable();
    public String sortByCol() ;
    public String getSortOrder();
    public ArrayList<FlowModel> getFlowList();
    public int getBorderWidth();
    public String getBorderColour();
    public int getNumberOfColumns();

    //column parameters
    public String getHeaderValue(int index);
    public String getHeaderTextColour(int index);
    public String getHeaderBGColour(int index);
    public String getRowEvenTC(int index);
    public String getRowEvenBGColour(int index);
    public String getRowOddTC(int index);
    public String getRowOddBGColour(int index);

}
