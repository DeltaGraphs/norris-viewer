package deltagraphs.norrisviewer.model.pageModel;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Observable;
import java.util.ArrayList;
import java.util.Observer;

/*
 * Name : PageModelImpl.java
 * Module : norrisviewer::model::pageModel
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
 * 0.2.3 2015-05-23 Enrico Savoca Add variable "configured" and some related methods to avoid configuration problems
 *
 * 0.2.2 2015-05-22 Enrico Savoca Changes to constructor
 *
 * 0.2.1 2015-05-21 Enrico Savoca Fix some reading errors in JSONParser(JSONObject data, String signal)
 *
 * 0.2.0 2015-05-21 Enrico Savoca Several changes to JSONParser(JSONObject data, String signal)
 *
 * 0.1.0 2015-05-19 Enrico Savoca Coding of all methods and attributes
 *
 * 0.0.1 2015-05-19 Enrico Savoca Creation of the file
 *
 * ===============================================================
 *
 */

public class PageModelImpl extends Observable implements PageModel {


    private String name;
    private ArrayList<Page> pageList = new ArrayList<Page>();
    private boolean configured = false;

    // constructor of pageModelImpl. It requires an observer. It will receives update from the PagemodelImpl
    public PageModelImpl(Observer presenter) {
        addObserver(presenter);
    }

    //it returns true if the pageModel has been already defined
    public boolean isConfigured(){return configured; }

    //this method let set attributes that are related to a page, using a json object.
    public void setPageModel(JSONObject data, String signal) {
        JSONParser(data, signal);
        setChanged();
        notifyObservers();
    }

    //this method inserts a page
    public void addPage(String id, String name, String description) {
        pageList.add(new Page(id, name, description));
    }

    private void JSONParser(JSONObject data, String signal) {
        try {
            //choise of procedure based on arriving signals

            switch (signal) {
                case "configPageList": {
                    if (configured != true) {
                    /* this is the case of the first initialization.
                       It uses JSON to create firstly PageModel's attributes, then the Pages List.
                       After this, it creates and populates each page */

                        name = data.getString("name");
                        JSONArray pagesArray = data.getJSONArray("data");
                        for (int i = 0; i < pagesArray.length(); i++) {
                            JSONObject page = pagesArray.getJSONObject(i);
                            JSONObject properties = page.getJSONObject("properties");
                            String pageId = properties.getString("ID");
                            String pageName = properties.getString("name");
                            String pageDescription = properties.getString("description");

                            addPage(pageId, pageName, pageDescription);

                            JSONArray itemsArray = page.getJSONArray("data");
                            for (int j = 0; j < itemsArray.length(); j++) {
                                JSONObject item = itemsArray.getJSONObject(j);
                                String itemId = item.getString("ID");
                                String itemName = item.getString("title");
                                String itemType = item.getString("type");
                                String itemURL = item.getString("socketURL");

                                pageList.get(pageList.size() - 1).addItem(itemId, itemName, itemType, itemURL);
                            }
                        }
                        configured = true;
                    }
                    break;
                }
                case ("insertPage"): {
                    // on the arrival of this signal, a new Page,
                    // with every parameters, is added to the pages list of PageModel.

                    String pageId = data.getString("ID");
                    String pageName = data.getString("name");
                    String pageDescription = data.getString("description");

                    addPage(pageId, pageName, pageDescription);

                    JSONArray itemsArray = data.getJSONArray("data");
                    int itemArrayDim = itemsArray.length();
                    for (int j = 0; j < itemArrayDim; j++) {
                        JSONObject item = itemsArray.getJSONObject(j);
                        String itemId = item.getString("ID");
                        String itemName = item.getString("title");
                        String itemType = item.getString("type");
                        String itemURL = item.getString("socketURL");

                        pageList.get(pageList.size() - 1).addItem(itemId, itemName, itemType, itemURL);
                    }
                    break;
                }
                case ("updatePage"): {
                    // on the arrival of this signal, a page,
                    // identified by an id, is updated.

                    //first of all it searches a page in the list, with an index equal to the JSON's one

                    int pageIndex = -1;
                    for (int i = 0; i < pageList.size(); i++) {
                        if (pageList.get(i).getId().equals(data.getString("ID"))) pageIndex = i;
                    }
                    if (pageIndex != -1) {
                        pageList.get(pageIndex).setName(data.getString("name"));
                        pageList.get(pageIndex).setDescription(data.getString("description"));
                    }//else non trovato, eccezione??

                    break;
                }
                case ("insertGraph"): {
                    // on the arrival of this signal, a new Graph,
                    // with every parameters, is added to the item list of the declared Page.

                    //first of all it searches a page in the list, with an index equal to the JSON's one

                    int pageIndex = -1;
                    for (int i = 0; i < pageList.size(); i++) {
                        if (pageList.get(i).getId().equals(data.getString("ID"))) pageIndex = i;
                    }
                    if (pageIndex != -1) {
                        data= data.getJSONObject("data");
                        String itemId = data.getString("ID");
                        String itemName = data.getString("title");
                        String itemType = data.getString("type");
                        String itemURL = data.getString("socketURL");

                        //then it insert a graph in the list of the found page, with an index equal to the JSON's one
                        pageList.get(pageIndex).addItem(itemId, itemName, itemType, itemURL);
                    }
                    break;
                }

                case ("updateGraph"): {
                    // on the arrival of this signal, a Graph,
                    // identified by an id, is updated.

                    //first of all it searches a page in the list, with an index equal to the JSON's one

                    int pageIndex = -1;
                    for (int i = 0; i < pageList.size(); i++) {
                        if (pageList.get(i).getId().equals(data.getString("ID"))) pageIndex = i;
                    }

                    //then it searches a graph in the list of the found page, with an index equal to the JSON's one

                    data = data.getJSONObject("data");
                    if (pageIndex != -1) {
                        int graphIndex = -1;
                        for (int j = 0; j < pageList.get(pageIndex).getItemListSize(); j++) {
                            if (pageList.get(pageIndex).getPageItemList().get(j).getId().equals(data.getString("ID")))
                                graphIndex = j;
                        }

                        // found indexes are used to update the item

                        if (graphIndex != -1) {
                            pageList.get(pageIndex).getPageItemList().get(graphIndex).setId(data.getString("ID"));
                            if(data.has("title"))
                                pageList.get(pageIndex).getPageItemList().get(graphIndex).setName(data.getString("title"));
                            if(data.has("type"))
                                pageList.get(pageIndex).getPageItemList().get(graphIndex).setType(data.getString("type"));
                            if(data.has("socketURL"))
                                pageList.get(pageIndex).getPageItemList().get(graphIndex).setUrl(data.getString("socketURL"));
                        }//else exception
                    }
                    break;
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    //it returns the name of a page
    public String getName() {
        return name;
    }

    //this method returns the list of pages
    public ArrayList<Page> getPageList() {
        return pageList;
    }

    //this method returns the size of the pages list
    public int getPageListSize() {
        return pageList.size();
    }

    //this method returns the size of a page, the number of charts on it.
    public int getItemListSize(int page) {
        return pageList.get(page).getItemListSize();
    }

    //this method returns the list of item in a page
    public ArrayList<PageItem> getItemList(int page) {
        return pageList.get(page).getPageItemList();
    }

    //this method returns a page for a given index
    public Page getPage(int index) {
        return this.getPageList().get(index);
    }

    //it removes all the observers attached to a pageModel implementation.
    @Override
    public void removeObservers() {
        deleteObservers();
    }

}
