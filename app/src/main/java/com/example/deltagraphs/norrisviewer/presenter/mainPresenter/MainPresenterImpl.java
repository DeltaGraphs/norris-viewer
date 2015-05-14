package com.example.deltagraphs.norrisviewer.presenter.mainPresenter;


    import com.example.deltagraphs.norrisviewer.view.mainView.MainActivity;
    import com.example.deltagraphs.norrisviewer.view.mainView.MainView;
    import com.example.deltagraphs.norrisviewer.presenter.SocketManager;

/*
 * Name : MainPresenterImpl.java
 * Module : norrisviewer::presenter::mainPresenter
 * Location : norrisviewer\presenter\mainPresenter
 *
 * History :

 * Version Date Programmer Description
 * ===============================================================
 *
 * 0.0.1 2015-05-13 Davide Trivellato Creazione file
 *
 * ===============================================================
 *
 */

public class MainPresenterImpl implements MainPresenter {

    private SocketManager mainSocket;
    private MainView mainView = new MainActivity();

}
