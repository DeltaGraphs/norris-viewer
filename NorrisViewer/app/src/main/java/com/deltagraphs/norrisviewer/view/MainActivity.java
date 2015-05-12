package com.deltagraphs.norrisviewer.view;

import android.support.v7.app.ActionBarActivity;

import com.deltagraphs.norrisviewer.presenter.SocketManager;

/**
 * Created by davide on 12/05/15.
 */
public class MainActivity extends ActionBarActivity implements MainView {
    private SocketManager socketManager;

    {
        socketManager.startListening("ciao", this);
    }
}
