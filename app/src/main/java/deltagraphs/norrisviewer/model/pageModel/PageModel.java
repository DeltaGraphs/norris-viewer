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
 * 0.1.0 2015-05-19 Enrico Savoca Coding of all methods
 *
 * 0.0.1 2015-05-19 Enrico Savoca Creation of the file
 *
 * ===============================================================
 *
 */

import org.json.JSONObject;

import java.util.ArrayList;

public interface PageModel {

    //it returns the name of a page
    public String getName();

    //this method let set attributes that are related to a page, using a json object.
    public void setPageModel(JSONObject data, String signal);

    //this method returns the list of pages
    public ArrayList<Page> getPageList();

    //this method returns the size of the pages list
    public int getPageListSize();

    //this method returns the size of a page, the number of charts on it.
    public int getItemListSize(int page);

    //this method returns the list of item in a page
    public ArrayList<PageItem> getItemList(int page);

    //this method returns a page for a given index
    public Page getPage(int index);

    //it removes all the observers attached to a pageModel implementation.
    public void removeObservers();
}
