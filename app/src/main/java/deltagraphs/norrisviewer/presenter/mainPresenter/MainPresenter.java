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
 * 0.0.1 2015-05-14 Enrico Savoca Creazione file
 *
 * ===============================================================
 *
 */

import android.content.Context;

public interface MainPresenter {
    public void showDialog(Context context);
    public String[] getPages();
}
