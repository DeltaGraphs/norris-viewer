package deltagraphs.norrisviewer.view.mainView;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;

import deltagraphs.norrisviewer.R;

import java.util.ArrayList;
import java.util.List;

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
    public static List<String> pagesList;

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

    public PageNavigationFragment getFragment(int id){
        return (PageNavigationFragment) getSupportFragmentManager().findFragmentById(R.id.navigation_drawer);
    }

    public DrawerLayout findDrawer(int id){
        return (DrawerLayout) findViewById(R.id.drawer_layout);
    }

    public FragmentManager getSupportManager(){
        return getSupportFragmentManager();
    }

}
