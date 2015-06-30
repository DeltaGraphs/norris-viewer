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
 * 1.0.0 2015-06-27 Matteo Furlan Approve
 *
 * 0.2.0 2015-06-26 Enrico Savoca Verify
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
    // used to update the page list on the Main Activity
    void updatePagesList(PageModel pageModel);

    // used to set the title on the Main Activity
    void setTitle(String title);
}
