package deltagraphs.norrisviewer.model.pageModel;

import junit.framework.TestCase;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

public class PageModelImplTest extends TestCase {

    private PageModelImpl mPageModelImpl = new PageModelImpl();
    private String name = "Page1";
    private Page page;

    @org.junit.Before
    public void testAddPage() throws Exception {
        System.out.println("Testing: addPage()");
        mPageModelImpl.addPage("id", name, "questa è la pagina uno");
        ArrayList<Page> list = mPageModelImpl.getPageList();
        int dim = list.size();
        assertEquals("[New page inserted,]", 1, dim);
        System.out.println("Done!");
    }

    @org.junit.Before
    public void testGetPageList() throws Exception {
        System.out.println("Testing: getPageList()");
        ArrayList<Page> list = mPageModelImpl.getPageList();
        assertEquals("", list, mPageModelImpl.getPageList());
        System.out.println("Done!");
    }

   /* @org.junit.After
    public void testGetName() throws Exception {
        System.out.println("Testing: testGetName()");
        assertTrue("", name.equals(mPageModelImpl.getName()));
        System.out.println("Done!");
    }
*/
    @org.junit.After
    public void testGetPageListSize() throws Exception{
        System.out.println("Testing: testGetPageListSize()");
        assertEquals("", mPageModelImpl.getPageList().size(), mPageModelImpl.getPageListSize());
        System.out.println("Done!");
    }
/*
    @org.junit.After
    public void testGetPage() throws Exception{
        page = mPageModelImpl.getPage(0);
        assertEquals("", page, mPageModelImpl.getPageList().get(0));
    }

    @org.junit.After
    public void testGetItemListSize() throws Exception{
        page.addItem("a", "b", "c", "d");
        System.out.println("Testing: testGetItemListSize()");
        assertEquals("", mPageModelImpl.getPageList().get(0).getPageItemList().size(),  mPageModelImpl.getItemListSize(0));
        System.out.println("Done!");
    }

    @org.junit.Test
    public void testParser() throws Exception {
        JSONObject obj = new JSONObject();
        obj.put("name", "Dashboard APS");
        obj.put("data", "");
    }
    */
}


