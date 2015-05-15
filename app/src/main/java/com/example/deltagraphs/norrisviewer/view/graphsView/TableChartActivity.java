package com.example.deltagraphs.norrisviewer.view.graphsView;/*
 * Name : TableChartActivity.java
 * Module : com.example.deltagraphs.norrisviewer.view.graphsView
 * Location : norrisviewer\view\graphsView
 *
 * History :

 * Version Date Programmer Description
 * ===============================================================
 * 
 * 0.0.1 2015-05-15 davide Creazione file
 *
 * ===============================================================
 *
 */

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;

import com.example.deltagraphs.norrisviewer.presenter.graphsPresenter.TablePresenter;
import com.example.deltagraphs.norrisviewer.presenter.graphsPresenter.TablePresenterImpl;

public class TableChartActivity extends ActionBarActivity implements TableChartView{
    private String title;
    private TablePresenter tablePresenter;

    @Override
    public void onCreate(Bundle savedInstanceState){
        tablePresenter = new TablePresenterImpl();
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

    public void setInitialState(){

    }

    public void onLegendButtonClicked(){

    }
}
