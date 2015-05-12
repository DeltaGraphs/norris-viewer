package com.deltagraphs.norrisviewer;

import android.app.Activity;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;

import com.github.nkzawa.emitter.Emitter;
import com.github.nkzawa.socketio.client.Socket;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by davide on 12/05/15.
 */
public class MainActivity extends ActionBarActivity {
    private SocketManager socketManager;

    {
        socketManager.startListening("ciao", this);
    }
}
