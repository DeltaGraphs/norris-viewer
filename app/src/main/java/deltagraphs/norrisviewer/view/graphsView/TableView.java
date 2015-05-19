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

public interface TableView {
    public void setAppearance(String appearance);
    public void setHeaders(ArrayList<String> headers);
    public void setAddRowOn(String addRowOn);
    public void setMaxItemsDisplayedPerPage(int maxItemsPerPage);
    public void setSortable(Boolean Sortable);
}
