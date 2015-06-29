package deltagraphs.norrisviewer.presenter.graphsPresenter;

/*
 * Name : GraphPresenter.java
 * Module : deltagraphs.norrisviewer.presenter.graphsPresenter
 * Location : norrisviewer\presenter\graphsPresenter
 *
 * History :

 * Version Date Programmer Description
 * ===============================================================
 *
 * 0.2.0 2015-05-24 Enrico Savoca Update startSocket(Activity graphActivity) to  startSocket(Activity graphActivity, Object graphModel)
 *
 * 0.1.1 2015-05-23 Enrico Savoca Fix method startSocket(Activity graphActivity)
 *
 * 0.1.0 2015-05-23 Enrico Savoca Coding of all methods and attributes
 *
 * 0.0.1 2015-05-23 Enrico Savoca Creation of the file
 *
 * ===============================================================
 *
 */

import android.app.Activity;

import java.util.Observable;
import java.util.Observer;

import deltagraphs.norrisviewer.presenter.SocketManager;

public abstract class GraphPresenter implements Observer {
    protected SocketManager graphSocket;

    // constructor of the abstract class GraphPresenter. It requires an url to be instatiated properly.
    // A new connection will be established with the given url.
    public GraphPresenter(String url) {
        this.graphSocket = new SocketManager(url);
    }

    /* This method is called when a new socket is created and a new connection must be established.
    The socket is put to listen for some events that will arrive when the connection is started.
    For each type of event a different path of instructions will be executed, in order to set,
    update or delete some informations. */
    public void startSocket(Activity graphActivity, Object graphModel) {
        graphSocket.startConnection();
        //startListening(graphActivity, graphModel);
    }

    public void stopListening(){
        graphSocket.stopListening("configGraph");
        graphSocket.stopListening("updateGraphProp");
        graphSocket.stopListening("insertFlow");
        graphSocket.stopListening("deleteFlow");
        graphSocket.stopListening("updateFlowProp");
        graphSocket.stopListening("updateFlowData");
    }

    public void startListening(Activity graphActivity, Object graphModel){
        graphSocket.startListening("configGraph", graphActivity, graphModel);
        graphSocket.startListening("updateGraphProp", graphActivity, graphModel);
        graphSocket.startListening("insertFlow", graphActivity, graphModel);
        graphSocket.startListening("deleteFlow", graphActivity, graphModel);
        graphSocket.startListening("updateFlowProp", graphActivity, graphModel);
        graphSocket.startListening("updateFlowData", graphActivity, graphModel);
    }

    //when called, the socket connection is stopped
    public void stopSocket() {
        graphSocket.stopConnection();
    }

    // it returns the socket of the class that extends GraphPresenter
    public SocketManager getGraphSocket(){
        return graphSocket;
    }

/*
    @Override
    public void update(Observable observable, Object data) {
    }
    */
}
