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
 * 0.1.0 2015-05-12 Enrico Savoca Codifica di tutti gli attributi e i metodi
 *
 * 0.0.1 2015-05-12 Enrico Savoca Creazione file
 *
 * ===============================================================
 *
 */

import org.json.JSONObject;

import java.util.ArrayList;

public interface PageModel {
    public String getName();
    public void setPageModel(JSONObject data, String signal);
    public ArrayList<Page> getPageList();
    public int getPageListSize();
    public int getItemListSize(int page);
    public ArrayList<PageItem> getItemList(int page);
    public Page getPage(int index);
    public void removeObservers();
}
