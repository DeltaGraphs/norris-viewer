package deltagraphs.norrisviewer.model.pageModel;

/*
 * Name : Page.java
 * Module : deltagraphs.norrisviewer.model.pageModel
 * Location : norrisviewer\model\pageModel
 *
 * History :

 * Version Date Programmer Description
 * ===============================================================
 *
 * 0.1.1 2015-05-20 Enrico Savoca Add methods getItemListSize() and getPageItemList()
 *
 * 0.1.0 2015-05-19 Enrico Savoca Coding of all methods and attributes
 *
 * 0.0.1 2015-05-19 Enrico Savoca Creation of the file
 *
 * ===============================================================
 *
 */

import java.util.ArrayList;

public class Page {
    private String id;
    private String name;
    private String description;
    private ArrayList<PageItem> pageItemList = new ArrayList<PageItem>();

    // constructor of Page
    Page(String id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
    }

    // this method is used to add to a page a page item
    public void addItem(String id, String name, String type, String URL) {
        pageItemList.add(new PageItem(id, name, type, URL));
    }

    // the following methods are used to set specific attributes
    // of a page like id, name and description

    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    // the following methods return information about a single page.
    // Returned informations are id, name, description, item list and its size.

    public ArrayList<PageItem> getPageItemList() {
        return pageItemList;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public int getItemListSize() {
        return pageItemList.size();
    }
}
