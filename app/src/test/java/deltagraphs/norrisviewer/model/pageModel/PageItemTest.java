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
 */public class PageItemTest extends TestCase {
    PageItem x;

    @org.junit.Before
    public void setUp() throws Exception {
        super.setUp();
        x= new PageItem("","","","");
    }

    @org.junit.Test
    public void testSetId() throws Exception {
        String id= "id";
        x.setId(id);
        assertEquals(id, x.getId());
    }

    @org.junit.Test
    public void testSetName() throws Exception {
        String name= "name";
        x.setName(name);
        assertEquals(name, x.getName());
    }

    @org.junit.Test
    public void testSetType() throws Exception {
        String type= "type";
        x.setType(type);
        assertEquals(type, x.getType());
    }
    @org.junit.Test
    public void testSetUrl() throws Exception {
        String url= "url";
        x.setUrl(url);
        assertEquals(url, x.getUrl());
    }
}