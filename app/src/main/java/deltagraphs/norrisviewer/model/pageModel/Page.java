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
 * 0.1.0 2015-05-12 Enrico Savoca Codifica di tutti gli attributi e i metodi
 *
 * 0.0.1 2015-05-12 Enrico Savoca Creazione file
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

    public void addItem(String id, String name, String type, String URL) {
        pageItemList.add(new PageItem(id, name, type, URL));
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

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
