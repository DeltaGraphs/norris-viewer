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
 * 1.0.0 2015-06-02 Matteo Furlan Approve
 *
 * 0.2.0 2015-06-01 Davide Trivellato Verify
 *
 * 0.1.1 2015-05-20 Enrico Savoca Update constructor
 *
 * 0.1.0 2015-05-19 Enrico Savoca Coding of all methods and attributes
 *
 * 0.0.1 2015-05-19 Enrico Savoca Creation of the file
 *
 * ===============================================================
 *
 */

public class PageItem {
    private String itemId;
    private String itemName;
    private String itemType;
    private String itemURL;

    // constructor of PageItem
    PageItem(String iI, String iN, String iT, String URL) {
        itemId = iI;
        itemName = iN;
        itemType = iT;
        itemURL = URL;
    }

    // the following methods are used to set specific attributes
    // of a page like id, name, type and url.

    public void setId(String id) {
        this.itemId = id;
    }

    public void setName(String name) {
        this.itemName = name;
    }

    public void setType(String type) {
        this.itemType = type;
    }

    public void setUrl(String url) {
        this.itemURL = url;
    }

    // the following methods return information about a single page.
    // Returned informations are id, name, type and url.

    public String getId() {
        return itemId;
    }

    public String getName() {
        return itemName;
    }

    public String getType() {
        return itemType;
    }

    public String getUrl() {
        return itemURL;
    }
}
