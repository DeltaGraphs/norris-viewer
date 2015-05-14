package com.example.deltagraphs.norrisviewer.model.pageModel;/*
 * Name : { Nome del file }.java
 * Module : com.example.deltagraphs.norrisviewer.model.pageModel
 * Location : norrisviewer { Percorso in cui \'e presente il file }
 *
 * History :

 * Version Date Programmer Description
 * ===============================================================
 * 
 * 0.0.1 2015-05-X davide Creazione file
 *
 * ===============================================================
 *
 */

import org.junit.Test;

import java.util.ArrayList;

import static junit.framework.TestCase.assertEquals;

public class PageModelImplTest {

    private PageModelImpl mPageModelImpl;

    @Test
    public void addPagesToList() {
        mPageModelImpl = new PageModelImpl();
        mPageModelImpl.addPage("Page1","Pagina Uno","questa è la pagina uno");
        ArrayList<PageModelImpl.Page> list = mPageModelImpl.getList();
        int dim = list.size();
        assertEquals("[New page inserted,]", 1, dim);
    }
}
