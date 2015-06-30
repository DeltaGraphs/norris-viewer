package deltagraphs.norrisviewer.presenter;

import java.net.URISyntaxException;

import android.app.Activity;
import android.util.Log;

import com.github.nkzawa.emitter.Emitter;
import com.github.nkzawa.socketio.client.IO;
import com.github.nkzawa.socketio.client.Socket;

import org.json.JSONObject;

import deltagraphs.norrisviewer.model.graphsModel.*;
import deltagraphs.norrisviewer.model.pageModel.PageModel;
import deltagraphs.norrisviewer.view.graphsView.*;
import deltagraphs.norrisviewer.view.mainView.MainView;

/*
 * Name : SocketManager.java
 * Module : norrisviewer::presenter
 * Location : norrisviewer\presenter
 *
 * History :
 *
 * Version Date Programmer Description
 * ===============================================================
 *
 * 0.1.1 2015-05-21 Davide Trivellato update method startListening(final String signal) to startListening(final String signal, final Object model)
 *
 * 0.1.0 2015-05-19 Davide Trivellato Coding of all methods and attributes
 *
 * 0.0.1 2015-05-19 Davide Trivellato Creation of the file
 *
 * ===============================================================
 *
 */

//This class provides an instrument for manage Socket connection and the URL I/O

public class SocketManager {

    private Socket mSocket;
    private String url = "http://5.231.33.217:3000/norris";

    //Constructor of SocketManager. A new socket is created without an url.
    public SocketManager() {
        try {
            mSocket = IO.socket("");
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
    }

    //Constructor of SocketManager. A new socket is created with a given url.
    public SocketManager(String url) {
        setSocketUrl(url);
    }

    //The following method uses the given url to set up a connection.
    public void setSocketUrl(String url) {
        this.url = url;
        try {
            mSocket = IO.socket(url);
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
    }

    //The following method returns the socket url
    public String getSocketUrl() {
        return url;
    }

    //when called, the socket connection is started
    public void startConnection() {
        mSocket.connect();
    }

    // it returns true if the socket hasn't been instatiated.
    public Boolean isNull() {
        return (mSocket == null);
    }

    // it returns true if the socket is connected.
    public Boolean isConnected() {
        if (mSocket == null)
            return false;
        return mSocket.connected();
    }

    //when called, the socket connection is stopped
    public void stopConnection() {
        mSocket.disconnect();
    }

    //when called, the socket and its connection are destroyed
    public void destroyConnection() {
        mSocket.close();
    }


    public void stopListening(final String signal) {
        mSocket.off(signal);
    }

    // the following method creates a thread that will be put on a listening mode for defined events.
    // The socket is connected to an url that sends events. When an event arrives, the method decides
    // where to send the json that has arrived.
    public void startListening(final String signal, final Activity activity, final Object model) {
        mSocket.on(signal, new Emitter.Listener() {
            @Override
            public void call(final Object... args) {
                activity.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        JSONObject obj = (JSONObject) args[0];
                        //try {
                        if (activity instanceof MainView) {
                            ((PageModel) model).setPageModel(obj, signal);
                            Log.d("", "arrivo page");
                        } else if (activity instanceof BarChartView) {
                            ((Graph) model).setGraph(obj, signal);
                            Log.d("update", "arrivo bar");
                        } else if (activity instanceof LineChartView) {
                            ((Graph) model).setGraph(obj, signal);
                            Log.d("update", "arrivo line");
                        } else if (activity instanceof MapChartView) {
                            Log.d("update", "arrivo map");
                            ((Graph) model).setGraph(obj, signal);
                        } else if (activity instanceof TableView) {
                            ((Graph) model).setGraph(obj, signal);
                            Log.d("update", "arrivo table");
                        } else Log.d("LOL", "fail");
                    }
                });
            }
        });
    }
}
