package deltagraphs.norrisviewer.presenter.mainPresenter;/*
 * Name : {Nome del file}.java
 * Module : deltagraphs.norrisviewer.presenter.mainPresenter
 * Location : norrisviewer { Percorso in cui \'e presente il file }
 *
 * History :

 * Version      Date        Programmer         Description
 * ===============================================================
 * 
 * 0.1.0 2015-05-23 Davide Trivellato Codifica di tutti gli attributi e metodi
 *
 * 0.0.1 2015-05-23 Davide Trivellato Creazione file
 *
 * ===============================================================
 *
 */

import android.app.AlertDialog;

import junit.framework.Assert;
import junit.framework.TestCase;

import org.junit.Before;
import org.junit.Test;

import deltagraphs.norrisviewer.model.pageModel.PageModel;
import deltagraphs.norrisviewer.view.mainView.MainActivity;
import deltagraphs.norrisviewer.view.mainView.MainView;

public class MainPresenterImplTest extends TestCase {

    MainPresenterImpl mainPresenter;

    @Before
    public void setUp() throws Exception {
        super.setUp();
        mainPresenter = new MainPresenterImpl(new MainActivity());
    }

    @Test
    public void testSetUpSocket() throws Exception {
        mainPresenter.setUpSocket("http://norris-nrti-dev.herokuapp.com/norris");
    }

    @Test
    public void testShowDialog() throws Exception {
        AlertDialog d = mainPresenter.showDialog(new MainActivity());
        Assert.assertEquals(d.isShowing(), true);
    }

}
