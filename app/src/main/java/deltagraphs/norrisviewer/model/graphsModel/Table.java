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
 * 1.0.0 2015-06-20 Matteo Furlan Approve
 *
 * 0.2.0 2015-06-19 Davide Trivellato Verify
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

    //it returns the number of columns of the table
    public int getNumberOfColumns();

    //it returns the flow list of the chart
    public ArrayList<FlowModel> getFlowList();

    // returns true if the table has an horizontal border
    public Boolean getHorizontalBorder();

    // returns true if the table has a vertical border
    public Boolean getVerticalBorder();

    //it returns the value of a header for a given index. The index represents the number of the column
    public String getHeaderValue(int index);

    public String getRowEvenTC(int index);

    public String getRowEvenBGColour(int index);

    public String getRowOddTC(int index);

    public String getRowOddBGColour(int index);
}
