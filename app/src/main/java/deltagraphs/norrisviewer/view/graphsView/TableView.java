package deltagraphs.norrisviewer.view.graphsView;

/*
 * Name : TableView.java
 * Module : norrisviewer::view::graphsView
 * Location : norrisviewer\view\graphsView
 *
 * History :

 * Version Date Programmer Description
 * ===============================================================
 * 
 * 0.1.0 2015-06-09 Enrico Savoca coding of methods
 *
 * 0.0.1 2015-06-09 Enrico Savoca creation of the file
 *
 * ===============================================================
 *
 */

import java.util.ArrayList;

import deltagraphs.norrisviewer.model.flowModel.FlowModel;

public interface TableView {
    // set the table to be sorted by a defined column
    public void setSortByCol(String sortingColumn);

    // set if the table must be sorted in ascendent or descendent order
    public void setSortOrder(String sortOrder);

    // set the headers of the table
    public void setHeaders(String[] headers);

    //set the data in the table. It is extracted from the table model
    public void setData(ArrayList<FlowModel> flowList, int numOfColumns);
}
