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

public interface Table {
    public String getAppearance();
    public ArrayList<String> getHeaders();
    public String getAddRowOn();
    public int getMaxItemsDisplayedPerPage();
    public Boolean getSortable();

}
