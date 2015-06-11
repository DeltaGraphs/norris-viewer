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
 * 0.1.0 2015-05-19 Davide Trivellato Coding of methods
 *
 * 0.0.1 2015-05-19 Davide Trivellato Creation of the file
 *
 * ===============================================================
 *
 */


import deltagraphs.norrisviewer.model.pageModel.PageModel;

public interface MainView {
    void updatePagesList(PageModel pageModel);
    void setTitle(String title);
}
