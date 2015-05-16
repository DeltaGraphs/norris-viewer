package deltagraphs.norrisviewer.view.mainView;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;

import deltagraphs.norrisviewer.presenter.SocketManager;
import deltagraphs.norrisviewer.presenter.mainPresenter.MainPresenter;
import deltagraphs.norrisviewer.presenter.mainPresenter.MainPresenterImpl;
import deltagraphs.norrisviewer.view.mainView.MainView;

/*
 * Name : MainActivity.java
 * Module : norrisviewer::view::mainView
 * Location : norrisviewer\view\mainView
 *
 * History :

 * Version Date Programmer Description
 * ===============================================================
 *
 * 0.1.0 2015-05-13 Davide Trivellato Codifica di tutti gli attributi e i metodi
 *
 * 0.0.1 2015-05-13 Davide Trivellato Creazione file
 *
 * ===============================================================
 *
 */

public class MainActivity extends ActionBarActivity implements MainView {

    private String title;
    private MainPresenter presenter;

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        presenter = new MainPresenterImpl(this);
        showDialog();
    }

    @Override
    public void onStart(){
        super.onStart();
    }

    @Override
    public void onRestart(){
        super.onRestart();
    }

    @Override
    public void onPause(){
        super.onPause();
    }

    @Override
    public void onResume(){
        super.onResume();
    }

    @Override
    public void onStop(){
        super.onStop();
    }

    @Override
    public void onDestroy(){
        super.onDestroy();
    }

    @Override
    public void onBackPressed(){
        super.onBackPressed();
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void showDialog(){
        presenter.showDialog(this);
    }

    public void showGraphs(){

    }

    public void onGraphSelected(){

    }

    public void onSettingsSelected(){

    }

}
