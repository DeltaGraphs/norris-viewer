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
 * 0.2.0 2015-05-15 Enrico Savoca Update startSocket(Activity graphActivity) to  startSocket(Activity graphActivity, Object graphModel)
 *
 * 0.1.1 2015-05-15 Enrico Savoca Fix method startSocket(Activity graphActivity)
 *
 * 0.1.0 2015-05-15 Enrico Savoca Coding of all methods and attributes
 *
 * 0.0.1 2015-05-15 Enrico Savoca Creation of the file
 *
 * ===============================================================
 *
 */

import android.app.Activity;

import java.util.Observable;
import java.util.Observer;

import deltagraphs.norrisviewer.presenter.SocketManager;

public abstract class GraphPresenter implements Observer {
    private SocketManager graphSocket;

    public GraphPresenter(String url) {
        this.graphSocket = new SocketManager(url);
    }

    public void startSocket(Activity graphActivity, Object graphModel) {
        graphSocket.startConnection();
        graphSocket.startListening("configGraph", graphActivity, graphModel);
        graphSocket.startListening("updateGraphProp", graphActivity, graphModel);
        graphSocket.startListening("insertFlow", graphActivity, graphModel);
        graphSocket.startListening("deleteFlow", graphActivity, graphModel);
        graphSocket.startListening("updateFlowProp", graphActivity, graphModel);
        graphSocket.startListening("updateFlowData", graphActivity, graphModel);
    }

    public void stopSocket() {
        graphSocket.stopConnection();
    }

    public void destroySocket() {
        graphSocket.destroyConnection();
    }

    @Override
    public void update(Observable observable, Object data) {
    }
}
