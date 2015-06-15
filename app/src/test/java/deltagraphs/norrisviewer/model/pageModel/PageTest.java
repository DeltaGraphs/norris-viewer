package deltagraphs.norrisviewer.model.pageModel;

import junit.framework.TestCase;

/*
 * Name : { Nome del file }.java
 * Module : deltagraphs.norrisviewer.model.pageModel
 * Location : norrisviewer { Percorso in cui \'e presente il file }
 *
 * History :

 * Version Date Programmer Description
 * ===============================================================
 * 
 * 0.1.0 2015-05-X Enrico Savoca Codifica di tutti gli attributi e i metodi
 *
 * 0.0.1 2015-05-X Enrico Savoca Creazione file
 *
 * ===============================================================
 *
 */public class PageTest extends TestCase {
    Page x;

    public void setUp() throws Exception {
        super.setUp();
        x = new Page("", "", "");
    }

    public void testAddItem() throws Exception {
        String a = "a", b = "b", c = "c", d = "d";
        PageItem y = new PageItem(a, b, c, d);
        x.addItem(a, b, c, d);
        if (x.getItemListSize() == 1)
            if (x.getPageItemList().get(0).getName() == y.getName() &&
                    x.getPageItemList().get(0).getId() == y.getId() &&
                    x.getPageItemList().get(0).getType() == y.getType() &&
                    x.getPageItemList().get(0).getUrl() == y.getUrl())
                assertTrue("", true);
    }

    public void testSetId() throws Exception {
        String id= "id";
        x.setId(id);
        assertEquals(x.getId(), id);
    }

    public void testSetName() throws Exception {
        String name= "name";
        x.setName(name);
        assertEquals(x.getName(), name);
    }

    public void testSetDescription() throws Exception {
        String description= "description";
        x.setDescription(description);
        assertEquals(x.getDescription(), description);
    }
}