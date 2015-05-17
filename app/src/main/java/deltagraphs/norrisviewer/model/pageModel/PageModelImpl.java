package deltagraphs.norrisviewer.model.pageModel;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.Observable;
import java.util.ArrayList;

/*
 * Name : PageModelImpl.java
 * Module : norrisviewer::model::pageModel
 * Location : norrisviewer\model\pageModel
 *
 * History :

 * Version Date Programmer Description
 * ===============================================================
 * 0.1.0 2015-05-12 Enrico Savoca Codifica di tutti gli attributi e i metodi
 *
 * 0.0.1 2015-05-12 Enrico Savoca Creazione file
 *
 * ===============================================================
 *
 */

public class PageModelImpl extends Observable implements PageModel{
    class Page{
        class PageItem{
            private String itemId;
            private String itemName;
            private String itemType;
            private String itemURL;

        // constructor of PageItem
            PageItem(String iI, String iN, String iT, String URL ){
                itemId = iI;
                itemName = iN;
                itemType = iT;
                itemURL = URL;
            }

            public void setId(String id){ this.itemId = id; }
            public void setName(String name){ this.itemName = name; }
            public void setType(String type){ this.itemType = type; }
            public void setUrl(String url){ this.itemURL = url; }

            public String getId(){ return itemId; }
            public String getName(){ return itemName; }
            public String getType(){ return itemType; }
            public String getUrl(){ return itemURL; }
        }

        private String id;
        private String name;
        private String description;
        private ArrayList<PageItem> pageItemList = new ArrayList<PageItem>();

    // constructor of Page
        Page(String id, String name, String description){
            this.id = id;
            this.name = name;
            this.description = description;
        }

        public void addItem(String id, String name, String type, String URL){
            pageItemList.add(new PageItem(id, name, type, URL));
        }

        public void setName(String name){ this.name = name; }
        public void setDescription(String description){ this.description = description; }

        public ArrayList<PageItem> getPageItemList(){ return pageItemList; }
        public String getId(){ return id;}
        public String getName(){ return name;}
        public String getDescription(){ return description; }
    }

    private String name;
    private ArrayList<Page> pageList = new ArrayList<Page>();

    public PageModelImpl(){}

    public PageModelImpl(JSONObject data){
        // insert roba XD
    }

    //this method inserts a page
    public void addPage(String id, String name, String description){
        pageList.add(new Page(id, name, description));
    }

    private void JSONParser(JSONObject data, String signal){
        try{
           //choise of procedure based on arriving signals

            switch(signal) {
                case "configPageList": {

                    /* this is the case of the first initialization.
                       It uses JSON to create firstly PageModel's attributes, then the Pages List.
                       After this, it creates and populates each page */

                    name = data.getString("name");
                    JSONArray pagesArray = data.getJSONArray("data");
                    int pageArrayDim = pagesArray.length();
                    for (int i = 0; i < pageArrayDim; i++) {
                        JSONObject page = pagesArray.getJSONObject(i);
                        String pageId = page.getString("ID");
                        String pageName = page.getString("name");
                        String pageDescription = page.getString("description");

                        addPage(pageId, pageName, pageDescription);

                        JSONArray itemsArray = page.getJSONArray("graphs");
                        int itemArrayDim = itemsArray.length();
                        for (int j = 0; j < itemArrayDim; j++) {
                            JSONObject item = itemsArray.getJSONObject(j);
                            String itemId = item.getString("ID");
                            String itemName = item.getString("title");
                            String itemType = item.getString("type");
                            String itemURL = item.getString("URLSocket");

                            pageList.get(pageList.size() - 1).addItem(itemId, itemName, itemType, itemURL);
                        }
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

                    JSONArray itemsArray = data.getJSONArray("graphs");
                    int itemArrayDim = itemsArray.length();
                    for (int j = 0; j < itemArrayDim; j++) {
                        JSONObject item = itemsArray.getJSONObject(j);
                        String itemId = item.getString("ID");
                        String itemName = item.getString("title");
                        String itemType = item.getString("type");
                        String itemURL = item.getString("URLSocket");

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
                        String itemId = data.getString("ID");
                        String itemName = data.getString("title");
                        String itemType = data.getString("type");
                        String itemURL = data.getString("URLSocket");

                        //then it insert a graph in the list of the found page, with an index equal to the JSON's one
                        pageList.get(pageIndex).addItem(itemId, itemName, itemType, itemURL);
                    }
                    break;
                }

                case("updateGraph"):{

                    // on the arrival of this signal, a Graph,
                    // identified by an id, is updated.

                    //first of all it searches a page in the list, with an index equal to the JSON's one

                    int pageIndex = -1;
                    for(int i=0; i < pageList.size(); i++){
                        if(pageList.get(i).getId().equals(data.getString("ID"))) pageIndex = i;
                    }

                    //then it searches a graph in the list of the found page, with an index equal to the JSON's one

                    if(pageIndex != -1) {
                        int graphIndex = -1;
                        for (int j = 0; j < pageList.size(); j++) {
                            if (pageList.get(pageIndex).getPageItemList().get(j).getId().equals(data.getString("ID")))
                                graphIndex = j;
                        }

                        // found indexes are used to update the item

                        if (graphIndex != -1) {
                            pageList.get(pageIndex).getPageItemList().get(graphIndex).setId(data.getString("ID"));
                            pageList.get(pageIndex).getPageItemList().get(graphIndex).setName(data.getString("title"));
                            pageList.get(pageIndex).getPageItemList().get(graphIndex).setType(data.getString("type"));
                            pageList.get(pageIndex).getPageItemList().get(graphIndex).setUrl(data.getString("URLSocket"));
                        }//else exception
                    }
                    break;
                }
            }
        }catch(JSONException e){}
    }

    public String getName(){ return name; }
    public ArrayList<Page> getList(){ return pageList; }

}
