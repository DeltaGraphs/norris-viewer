package deltagraphs.norrisviewer.presenter.mainPresenter;

/*
 * Name : MainPresenter.java
 * Module : norrisviewer::presenter::mainPresenter
 * Location : norrisviewer\presenter\mainPresenter
 *
 * History :

 * Version Date Programmer Description
 * ===============================================================
 *
 * 0.1.1 2015-05-20 Enrico Savoca Added methods isConnected() and boolean isSocketNull()
 *
 * 0.1.0 2015-05-20 Enrico Savoca Coding of all methods
 *
 * 0.0.1 2015-05-20 Enrico Savoca Creation of the file
 *
 * ===============================================================
 *
 */

import android.content.Context;

public interface MainPresenter {
    void showDialog(Context context);
    void stopConnection();
    void destroyConnection();
    void startConnection();
    boolean isConnected();
    boolean isSocketNull();
}
