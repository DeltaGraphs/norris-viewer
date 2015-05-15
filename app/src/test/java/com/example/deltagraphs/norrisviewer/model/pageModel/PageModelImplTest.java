package com.example.deltagraphs.norrisviewer.model.pageModel;

import junit.framework.TestCase;

import java.util.ArrayList;

import static junit.framework.Assert.assertEquals;
import static org.junit.Assert.*;

public class PageModelImplTest extends TestCase {

    private PageModelImpl mPageModelImpl;

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

    @org.junit.Test
    public void AddPagesToList() throws Exception {
        System.out.println("Testing: AddPagesToList()");
        mPageModelImpl = new PageModelImpl();
        mPageModelImpl.addPage("Page1","Pagina Uno","questa è la pagina uno");
        ArrayList<PageModelImpl.Page> list = mPageModelImpl.getList();
        int dim = list.size();
        System.out.println("Done!");
        assertEquals("[New page inserted,]", 1, dim);

    }
}


