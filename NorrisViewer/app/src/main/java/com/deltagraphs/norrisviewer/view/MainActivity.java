package com.deltagraphs.norrisviewer.view;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;

import com.deltagraphs.norrisviewer.presenter.SocketManager;
import com.deltagraphs.norrisviewer.presenter.mainPresenter.MainPresenter;

/*
 * Name : MainActivity.java
 * Module : norrisviewer::view::mainView
 * Location : /com.deltagraphs.norrisviewer/view/mainView
 *
 * History :

 * Version Date Programmer Description
 * ===============================================================
 *
 * 0.1.0 2015-05-13  Davide Trivellato Codifica di tutti gli attributi e i metodi
 *
 * 0.0.1 2015-05-13  Davide Trivellato Creazione del file
 *
 * ===============================================================
 *
 */
public class MainActivity extends ActionBarActivity implements MainView {

    private String title;
    private MainPresenter presenter;

    @Override
    public void onCreate(Bundle savedInstanceState){

    }

    @Override
    public void onStart(){

    }

    @Override
    public void onRestart(){

    }

    @Override
    public void onPause(){

    }

    @Override
    public void onResume(){

    }

    @Override
    public void onStop(){

    }

    @Override
    public void onDestroy(){

    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void showDialog(){

    }

    public void showGraphs(){

    }

    public void onGraphSelected(){

    }

    public void onSettingsSelected(){

    }

    @Override
    public void onBackPressed(){

    }
}
