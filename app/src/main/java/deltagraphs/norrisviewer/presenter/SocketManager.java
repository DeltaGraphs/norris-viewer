package deltagraphs.norrisviewer.presenter;

import java.net.URISyntaxException;

import android.app.Activity;

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
    private String url = "";


    //Constructor
    public SocketManager() {
        try {
            mSocket = IO.socket("");
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
    }

    //Constructor
    public SocketManager(String url) {
        setSocketUrl(url);
    }

    //setter method
    public void setSocketUrl(String url) {
        this.url = url;
        try {
            mSocket = IO.socket(url);
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
    }

    //getter method
    public String getSocketUrl() {
        return url;
    }

    //start the connection with socket
    public void startConnection() {
        mSocket.connect();

    }

    public Boolean isNull() {
        return (mSocket == null);
    }

    public Boolean isConnected() {
        return mSocket.connected();
    }

    //stop the connection with the socket
    public void stopConnection() {
        mSocket.disconnect();
    }

    //close the connection with the socket
    public void destroyConnection() {
        mSocket.close();
    }

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
                        } else if (activity instanceof BarChartView)
                            ((Graph) model).setGraph(obj, signal);
                        else if (activity instanceof LineChartView)
                            ((Graph) model).setGraph(obj, signal);
                        else if (activity instanceof MapChartView) {
                            ((Graph) model).setGraph(obj, signal);
                        } else if (activity instanceof TableView)
                            ((Graph) model).setGraph(obj, signal);
                    }
                });
            }
        });
    }
}
