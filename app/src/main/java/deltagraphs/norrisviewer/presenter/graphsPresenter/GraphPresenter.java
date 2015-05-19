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
 * 0.1.0 2015-05-17 Enrico Savoca Codifica di tutti gli attributi e i metodi
 *
 * 0.0.1 2015-05-17 Enrico Savoca Creazione file
 *
 * ===============================================================
 *
 */

import android.app.Activity;

import deltagraphs.norrisviewer.presenter.SocketManager;

public abstract class GraphPresenter {
    private SocketManager graphSocket;

    public GraphPresenter(String url, Activity graphActivity){
        this.graphSocket = new SocketManager();
        graphSocket.setSocketUrl(url);
        graphSocket.startConnection();
        graphSocket.startListening("graphConfig", graphActivity);
        graphSocket.startListening("updateGraphProp", graphActivity);
        graphSocket.startListening("insertFlow", graphActivity);
        graphSocket.startListening("deleteFlow", graphActivity);
        graphSocket.startListening("updateFlowProp", graphActivity);
        graphSocket.startListening("updateFlowData", graphActivity);
    }

    public abstract void setUpViews();

}
