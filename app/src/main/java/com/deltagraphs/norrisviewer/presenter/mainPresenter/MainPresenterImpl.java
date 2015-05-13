package com.deltagraphs.norrisviewer.presenter.mainPresenter;

import com.deltagraphs.norrisviewer.presenter.mainPresenter.MainPresenter;
import com.deltagraphs.norrisviewer.view.MainActivity;
import com.deltagraphs.norrisviewer.view.MainView;
import com.deltagraphs.norrisviewer.presenter.SocketManager;

/**
 * Created by davide on 12/05/15.
 */
public class MainPresenterImpl implements MainPresenter {

    private SocketManager mainSocket;
    private MainView mainView = new MainActivity();

}
