package deltagraphs.norrisviewer.view.mainView;

/*
 * Name : MainView.java
 * Module : norrisviewer::view::mainView
 * Location : norrisviewer\view\mainView
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


import android.support.v4.app.FragmentManager;
import android.support.v4.widget.DrawerLayout;

public interface MainView{
    PageNavigationFragment getFragment(int id);
    DrawerLayout findDrawer(int id);
    FragmentManager getSupportManager();
}
