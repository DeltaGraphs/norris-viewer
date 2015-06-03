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
import android.util.Log;

import java.util.Observable;
import java.util.Observer;

import deltagraphs.norrisviewer.presenter.SocketManager;

public abstract class GraphPresenter implements Observer {
    private SocketManager graphSocket;
    protected Boolean firstConnection = true;

    public GraphPresenter(String url) {
        this.graphSocket = new SocketManager(url);
    }

    public void startSocket( Activity graphActivity, Object graphModel){
        if(firstConnection) {
            graphSocket.startListening("configGraph", graphActivity, graphModel, this);
        }
        else {
            graphSocket.startListening("configGraph", graphActivity, graphModel, this);
            graphSocket.startListening("updateGraphProp", graphActivity, graphModel,  this);
            graphSocket.startListening("insertFlow", graphActivity, graphModel,this );
            graphSocket.startListening("deleteFlow", graphActivity, graphModel, this);
            graphSocket.startListening("updateFlowProp", graphActivity, graphModel, this);
            graphSocket.startListening("updateFlowData", graphActivity, graphModel, this);
        }
        graphSocket.startConnection();
    }

    public void stopSocket(){}

    protected abstract void setGraphParameters();

    @Override
    public void update(Observable observable,Object data){}
}
