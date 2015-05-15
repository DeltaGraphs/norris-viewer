package com.example.deltagraphs.norrisviewer.view.graphsView;

/*
 * Name : BarChartActivity.java
 * Module : norrisviewer::view::graphsView
 * Location : norrisviewer\view\graphsView
 *
 * History :

 * Version Date Programmer Description
 * ===============================================================
 *
 * 0.0.1 2015-05-13 Enrico Savoca Creazione file
 *
 * ===============================================================
 *
 */

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;

import com.example.deltagraphs.norrisviewer.presenter.graphsPresenter.BarChartPresenter;
import com.example.deltagraphs.norrisviewer.presenter.graphsPresenter.BarChartPresenterImpl;


public class BarChartActivity extends ActionBarActivity implements BarChartView{

    private BarChartPresenter barChartPresenter;

    @Override
    public void onCreate(Bundle savedInstanceState){
        barChartPresenter = new BarChartPresenterImpl();
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

    @Override
    public void onBackPressed(){

    }

    public void setInitialState(){

    }

    public void onValueSelected(){

    }
}
