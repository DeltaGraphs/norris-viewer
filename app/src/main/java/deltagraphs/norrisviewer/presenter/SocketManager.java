package deltagraphs.norrisviewer.presenter;

import java.net.URISyntaxException;
import java.util.Observer;

import android.app.Activity;
import android.util.Log;

import com.github.nkzawa.emitter.Emitter;
import com.github.nkzawa.socketio.client.IO;
import com.github.nkzawa.socketio.client.Socket;

import org.json.JSONException;
import org.json.JSONObject;

import deltagraphs.norrisviewer.model.graphsModel.*;
import deltagraphs.norrisviewer.model.graphsModel.Graph;
import deltagraphs.norrisviewer.model.pageModel.PageModel;
import deltagraphs.norrisviewer.model.pageModel.PageModelImpl;
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
 * 0.1.0 2015-05-12 Davide Trivellato Codifica di tutti gli attributi e i metodi
 *
 * 0.0.1 2015-05-12 Davide Trivellato Creazione file
 *
 * ===============================================================
 *
 */

//This class provides an instrument for manage Socket connection and the URL I/O

public class SocketManager{

    private Socket mSocket;
    private String url;

    //Constructor
    public SocketManager(){}

    //Constructor
    public SocketManager(String url){
        setSocketUrl(url);
    }

    //setter method
    public void setSocketUrl(String url){
        this.url = url;
        try {
            mSocket = IO.socket(url);
        } catch (URISyntaxException e) {}
    }

    //getter method
    public String getSocketUrl(){
        return url;
    }

    //start the connection with socket
    public void startConnection(){
        mSocket.connect();
    }

    //stop the connection with the socket
    public void stopConnection(){
        mSocket.disconnect();
    }

    //close the connection with the socket
    public void closeSocket(){
        mSocket.close();
    }

    public void startListening(final String signal, final Activity activity, final Object model){
        Log.d("ciao", "ciao");
        mSocket.on(signal, new Emitter.Listener() {
            @Override
            public void call(final Object... args) {
                activity.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Log.d("ciao", "arrrg");
                        JSONObject obj = (JSONObject) args[0];
                        //try {
                            if(activity instanceof MainView) {
                                Log.d("", "mainview");
                                ((PageModel) model).setPageModel(obj, signal);
                            }
                            else
                                if(activity instanceof BarChartView)
                                    ((Graph)model).setGraph(obj, signal);
                                    else
                                    if(activity instanceof LineChartView)
                                        ((Graph)model).setGraph(obj, signal);
                                        else
                                        if(activity instanceof MapChartView) {
                                            ((Graph) model).setGraph(obj, signal);
                                        }
                                        else
                                            if(activity instanceof TableView)
                                                ((Graph)model).setGraph(obj, signal);


                            //float x = (float) obj.getDouble("item");
                            //dataModel.setMyData((float) obj.getDouble("item"));
                        //} catch (JSONException e) {
                        //    return;
                        //}
                    }
                });
            }
        });
    }
}
