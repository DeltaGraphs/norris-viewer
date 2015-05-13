package com.example.deltagraphs.norrisviewer.presenter.mainPresenter;


    import com.example.deltagraphs.norrisviewer.view.mainView.MainActivity;
    import com.example.deltagraphs.norrisviewer.view.mainView.MainView;
    import com.example.deltagraphs.norrisviewer.presenter.SocketManager;

/**
 * Created by davide on 12/05/15.
 */

public class MainPresenterImpl implements MainPresenter {

    private SocketManager mainSocket;
    private MainView mainView = new MainActivity();

}
