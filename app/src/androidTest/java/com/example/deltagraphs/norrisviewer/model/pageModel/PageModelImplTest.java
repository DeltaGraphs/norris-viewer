package com.example.deltagraphs.norrisviewer.model.pageModel;

import junit.framework.TestCase;

import java.util.ArrayList;

import static junit.framework.Assert.assertEquals;
import static org.junit.Assert.*;

public class PageModelImplTest extends TestCase {

    private PageModelImpl mPageModelImpl;

    @org.junit.Test
    public void testAddPage() throws Exception {

    }

    @org.junit.Test
    public void testGetName() throws Exception {

    }

    @org.junit.Test
    public void AddPagesToList() throws Exception {
        mPageModelImpl = new PageModelImpl();
        mPageModelImpl.addPage("Page1","Pagina Uno","questa è la pagina uno");
        ArrayList<PageModelImpl.Page> list = mPageModelImpl.getList();
        int dim = list.size();
        assertEquals("[New page inserted,]", 1, dim);
    }
}


