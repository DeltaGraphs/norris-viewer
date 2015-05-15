package com.example.deltagraphs.norrisviewer.model.pageModel;

import junit.framework.TestCase;

import java.util.ArrayList;

public class PageModelImplTest extends TestCase {

    private PageModelImpl mPageModelImpl;
/*
    @org.junit.Test
    public void testAddPage() throws Exception {
        System.out.println("Testing: testAddPage()");
        System.out.println("Done!");
    }

    @org.junit.Test
    public void testGetName() throws Exception {
        System.out.println("Testing: testGetName()");
        System.out.println("Done!");
    }
*/
    @org.junit.Test
    public void testAddPagesToList() throws Exception {
        System.out.println("Testing: AddPagesToList()");
        mPageModelImpl = new PageModelImpl();
        mPageModelImpl.addPage("Page1","Pagina Uno","questa è la pagina uno");
        ArrayList<PageModelImpl.Page> list = mPageModelImpl.getList();
        int dim = list.size();
        System.out.println("Done!");
        assertEquals("[New page inserted,]", 2, dim);

    }
}


