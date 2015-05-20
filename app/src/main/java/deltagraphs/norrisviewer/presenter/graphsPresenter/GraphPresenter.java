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

    public GraphPresenter(String url) {
        this.graphSocket = new SocketManager();
        graphSocket.setSocketUrl(url);
    }

    public void startSocket( Activity graphActivity, Object graphModel){
        graphSocket.startConnection();
        graphSocket.startListening("graphConfig", graphActivity, graphModel);
        graphSocket.startListening("updateGraphProp", graphActivity, graphModel);
        graphSocket.startListening("insertFlow", graphActivity, graphModel);
        graphSocket.startListening("deleteFlow", graphActivity, graphModel);
        graphSocket.startListening("updateFlowProp", graphActivity, graphModel);
        graphSocket.startListening("updateFlowData", graphActivity, graphModel);
    }

    public void stopSocket(){}

    public abstract void setUpViews();

}
