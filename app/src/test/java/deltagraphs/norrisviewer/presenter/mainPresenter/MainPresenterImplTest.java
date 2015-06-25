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
import org.mockito.Mock;

import java.util.Observable;

import deltagraphs.norrisviewer.model.pageModel.PageModel;
import deltagraphs.norrisviewer.presenter.SocketManager;
import deltagraphs.norrisviewer.view.mainView.MainActivity;
import deltagraphs.norrisviewer.view.mainView.MainView;

class SocketMock extends SocketManager {
    Boolean connected;

    SocketMock(){
        connected=new Boolean(false);
    }

    SocketMock(String url){
        super(url);
        connected=new Boolean(false);
    }

    @Override
    public Boolean isNull() {
        return (connected == null);
    }

    @Override
    public Boolean isConnected() {
        return connected;
    }

    @Override
    public void startConnection(){
        connected = true;
    }

    @Override
    public void stopConnection(){
        connected = false;
    }

    @Override
    public void destroyConnection(){
        connected = false;
    }
}

class MockMainActivity extends MainActivity{

    boolean update = false;

    @Override
    public void updatePagesList(PageModel pageModel){
        update = true;
    }

    public boolean getUpdate(){
        return update;
    }
}

public class MainPresenterImplTest extends TestCase {

    MainPresenterImpl mainPresenter;


    @Before
    public void setUp() throws Exception {
        super.setUp();
        mainPresenter = new MainPresenterImpl(new MainActivity());
        mainPresenter.mainSocket = new SocketMock();
        mainPresenter.mainView = new MockMainActivity();
    }

    @Test
    public void testSetUpSocket() throws Exception {
        mainPresenter.setUpSocket("http://norris-nrti-dev.herokuapp.com/norris");
    }

    @Test
    public void testStartConnection() throws Exception {
        mainPresenter.startConnection();
        Assert.assertTrue(mainPresenter.isConnected());
    }

    @Test
    public void testStopConnection() throws Exception {
        mainPresenter.startConnection();
        mainPresenter.stopConnection();
        Assert.assertFalse(mainPresenter.isConnected());
    }

    @Test
    public void testDestroyConnection() throws Exception {
        mainPresenter.startConnection();
        mainPresenter.destroyConnection();
        Assert.assertFalse(mainPresenter.isConnected());
    }

    @Test
    public void testReinitializePageList() throws Exception{
        mainPresenter.reinitializePageList();

    }

    @Test
    public void testIsSocketNull() throws Exception{
        Assert.assertFalse(mainPresenter.isSocketNull());
    }

    @Test
    public void testUpdate() throws Exception{
        mainPresenter.update((Observable) mainPresenter.pageModel, new Object());
        Assert.assertTrue(((MockMainActivity)mainPresenter.mainView).getUpdate());
    }

/*
    @Test
    public void testShowDialog() throws Exception {
        AlertDialog d = mainPresenter.showDialog(new MainActivity());
        Assert.assertEquals(d.isShowing(), true);
    }
*/
}
