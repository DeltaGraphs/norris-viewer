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
    private CharSequence title;

    public GraphPresenter(String url, CharSequence title){
        this.title = title;
        this.graphSocket = new SocketManager();
        graphSocket.setSocketUrl(url);
        graphSocket.startListening("graphConfig", new Activity());
    }

    public CharSequence getTitle(){
        return title;
    }

    public abstract void setUpViews();

}
