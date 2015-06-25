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
    //it returns the position where new records will be inserted in table flows
    public String getAddRowOn();

    //if not null, it returns the name of a header of the table. In this case
    //the table will be ordered by values contained in its column.
    public String sortByCol();

    //if sortbyCol isn't null, it will return the type of ordering: ascendent or descendent.
    public String getSortOrder();

    //it returns the number of columns of the table
    public int getNumberOfColumns();

    //it returns the value of a header for a given index. The index represents the number of the column
    public String getHeaderValue(int index);

    //it returns the flow list of the chart
    public ArrayList<FlowModel> getFlowList();
}
