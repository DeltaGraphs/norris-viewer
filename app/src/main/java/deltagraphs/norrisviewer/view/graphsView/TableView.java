package deltagraphs.norrisviewer.view.graphsView;

/*
 * Name : TableChartView.java
 * Module : com.example.deltagraphs.norrisviewer.view.graphsView
 * Location : norrisviewer\view\graphsView
 *
 * History :

 * Version Date Programmer Description
 * ===============================================================
 * 
 * 0.0.1 2015-05-16 Davide Trivellato Creazione file
 *
 * ===============================================================
 *
 */

import java.util.ArrayList;

import deltagraphs.norrisviewer.model.flowModel.FlowModel;

public interface TableView {
    public void setAppearance(String appearance);

    public void setMaxItemsDisplayedPerPage(int maxItemsPerPage);

    public void setSortable(Boolean Sortable);

    public void setSortByCol(String sortingColumn);

    public void setSortOrder(String sortOrder);

    public void setBorderWidth(int borderWidth);

    public void setBorderColour(String borderColour);

    //column parameters
    public void setHeader(int index, String value, String textColour, String bgColour);

    public void setRowEven(int index, String textColour, String bgColour);

    public void setRowOdd(int index, String textColour, String bgColour);

    public void setHeaders(String[] headers);

    public void setData(ArrayList<FlowModel> flowList, int numOfColumns, String signal);
}
