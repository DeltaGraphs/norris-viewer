package com.example.deltagraphs.norrisviewer.view.mainView;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;

import com.example.deltagraphs.norrisviewer.presenter.SocketManager;
import com.example.deltagraphs.norrisviewer.presenter.mainPresenter.MainPresenter;
import com.example.deltagraphs.norrisviewer.view.mainView.MainView;

/**
 * Created by davide on 12/05/15.
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
