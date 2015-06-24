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
 * 0.1.0 2015-06-09 Enrico Savoca Coding of all methods
 *
 * 0.0.1 2015-06-09 Enrico Savoca Creation of the file
 *
 * ===============================================================
 *
 */

import java.util.ArrayList;

import deltagraphs.norrisviewer.model.flowModel.FlowModel;

public interface Table {
    public String getAddRowOn();

    public String sortByCol();

    public String getSortOrder();

    public ArrayList<FlowModel> getFlowList();

    public int getNumberOfColumns();

    //column parameters
    public String getHeaderValue(int index);
}
