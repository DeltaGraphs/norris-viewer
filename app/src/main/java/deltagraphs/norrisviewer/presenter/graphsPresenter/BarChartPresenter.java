package deltagraphs.norrisviewer.presenter.graphsPresenter;

/*
 * Name : BarChartPresenter.java
 * Module : deltagraphs.norrisviewer.presenter.graphsPresenter
 * Location : norrisviewer\presenter\graphsPresenter
 *
 * History :

 * Version Date Programmer Description
 * ===============================================================
 *
 * 0.1.0 2015-05-23 Davide Trivellato Coding of all methods
 *
 * 0.0.1 2015-05-23 Davide Trivellato Creation of the file
 *
 * ===============================================================
 *
 */

public interface BarChartPresenter {
    //when called, the socket connection is stopped
    void stopConnection();

    //when called, the socket connection is started
    void startConnection();

}
