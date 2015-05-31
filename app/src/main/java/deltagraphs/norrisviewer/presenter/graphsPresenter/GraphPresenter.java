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

import deltagraphs.norrisviewer.presenter.SocketManager;

public abstract class GraphPresenter {
    private SocketManager graphSocket;
    protected Boolean firstConnection = true;

    public GraphPresenter(String url) {
        this.graphSocket = new SocketManager(url);
    }

    public void startSocket( Activity graphActivity, Object graphModel){
        if(firstConnection) {
            graphSocket.startListening("configGraph", graphActivity, graphModel, (MapChartPresenterImpl) this);
        }
        else {
            graphSocket.startListening("updateGraphProp", graphActivity, graphModel, (MapChartPresenterImpl) this);
            graphSocket.startListening("insertFlow", graphActivity, graphModel,(MapChartPresenterImpl) this );
            graphSocket.startListening("deleteFlow", graphActivity, graphModel, (MapChartPresenterImpl) this);
            graphSocket.startListening("updateFlowProp", graphActivity, graphModel, (MapChartPresenterImpl) this);
            graphSocket.startListening("updateFlowData", graphActivity, graphModel, (MapChartPresenterImpl) this);
        }
        graphSocket.startConnection();
    }

    public void stopSocket(){}

   protected abstract void setGraphParameters();

}
