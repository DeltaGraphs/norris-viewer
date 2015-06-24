package deltagraphs.norrisviewer.model.pageModel;

import junit.framework.TestCase;

import org.json.JSONObject;
import org.junit.Test;

import java.util.Observable;
import java.util.Observer;

/*
 * Name : { Nome del file }.java
 * Module : deltagraphs.norrisviewer.model.pageModel
 * Location : norrisviewer { Percorso in cui \'e presente il file }
 *
 * History :

 * Version Date Programmer Description
 * ===============================================================
 * 
 * 0.1.0 2015-X-X Enrico Savoca Coding of all methods and attibutes
 *
 * 0.0.1 2015-X-X Enrico Savoca Creation of the file
 *
 * ===============================================================
 *
 */public class PageModelImplTest extends TestCase {
    Observer y = new Observer() {
        @Override
        public void update(Observable observable, Object data) {

        }
    };

    PageModelImpl x;

    @Test
    public void testSetPageModel() throws Exception {
        x = new PageModelImpl(y);
        JSONObject json = new JSONObject("{\"name\":\"norris\",\"data\":[{\"properties\":{\"ID\":\"page1\",\"name\":\"Pagina 1\",\"description\":\"Questa è una bella pagina\",\"socketURL\":\"http://norris-nrti-dev.herokuapp.com/page1\"},\"data\":[{\"ID\":\"map1\",\"title\":\"APS\",\"type\":\"MapChart\",\"socketURL\":\"http://norris-nrti-dev.herokuapp.com/page1/map1\"},{\"ID\":\"line1\",\"title\":\"LINEE\",\"type\":\"LineChart\",\"socketURL\":\"http://norris-nrti-dev.herokuapp.com/page1/line1\"},{\"ID\":\"bar1\",\"title\":\"BARRE\",\"type\":\"BarChart\",\"socketURL\":\"http://norris-nrti-dev.herokuapp.com/page1/bar1\"},{\"ID\":\"table1\",\"title\":\"Tabella\",\"type\":\"Table\",\"socketURL\":\"http://norris-nrti-dev.herokuapp.com/page1/table1\"}]},{\"properties\":{\"ID\":\"page2\",\"name\":\"Pagina 2\",\"description\":\"Questa è una bella pagina 2\",\"socketURL\":\"http://norris-nrti-dev.herokuapp.com/page2\"},\"data\":[{\"ID\":\"bar1\",\"title\":\"Empty Bar Chart\",\"type\":\"BarChart\",\"socketURL\":\"http://norris-nrti-dev.herokuapp.com/page2/bar1\"}]}]}");
        assertEquals(x.isConfigured(), false);
        x.setPageModel(json, "configPageList");
        assertEquals(x.isConfigured(), true);
        assertEquals(x.getName(), "norris");
        assertEquals(x.getPageListSize(), 2);
        assertEquals(x.getPage(0).getId(), "page1");
        assertEquals(x.getPage(0).getName(), "Pagina 1");
        assertEquals(x.getPage(1).getDescription(),"Questa è una bella pagina 2");
        assertEquals(x.getItemListSize(0), 4);
        assertEquals(x.getItemList(0).get(0).getId(), "map1");
        assertEquals(x.getItemList(0).get(0).getName(), "APS");
        assertEquals(x.getItemList(0).get(0).getType(), "MapChart");
        assertEquals(x.getItemList(0).get(0).getUrl(), "http://norris-nrti-dev.herokuapp.com/page1/map1");
    }

    @Test
    public void testAddPage() throws Exception {
        x = new PageModelImpl(y);
        x.addPage("id", "name", "description");
        assertEquals(x.getPage(0).getId(), "id");
        assertEquals(x.getPage(0).getName(), "name");
        assertEquals(x.getPage(0).getDescription(), "description");
    }

    @Test
    public void testRemoveObservers() throws Exception {
        x = new PageModelImpl(y);
        x.removeObservers();
        assertEquals(x.countObservers(), 0);
    }
}