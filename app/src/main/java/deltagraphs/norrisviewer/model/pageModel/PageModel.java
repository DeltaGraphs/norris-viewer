package deltagraphs.norrisviewer.model.pageModel;

/*
 * Name : PageModel.java
 * Module : norrisviewer::model::pageModel
 * Location : norrisviewer\model\pageModel
 *
 * History :

 * Version Date Programmer Description
 * ===============================================================
 *
 * 0.0.1 2015-05-13 Enrico Savoca Creazione file
 *
 * ===============================================================
 *
 */

import java.util.ArrayList;

public interface PageModel {
    public ArrayList<Page> getPageList();
    public int getPageListSize();
    public int getItemListSize(int page);
    public ArrayList<PageItem> getItemList(int page);
}
