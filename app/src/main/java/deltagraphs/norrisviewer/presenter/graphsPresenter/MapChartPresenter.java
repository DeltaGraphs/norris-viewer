package deltagraphs.norrisviewer.presenter.graphsPresenter;

/*
 * Name : MapChartPresenter.java
 * Module : deltagraphs.norrisviewer.presenter.graphsPresenter
 * Location : norrisviewer\presenter\graphsPresenter
 *
 * History :

 * Version Date Programmer Description
 * ===============================================================
 *
 * 0.1.0 2015-06-03 Davide Trivellato Coding of all methods
 *
 * 0.0.1 2015-06-03 Davide Trivellato Creation of the file
 *
 * ===============================================================
 *
 */

public interface MapChartPresenter {
    //when called, the socket connection is stopped
    void stopConnection();

    //when called, the socket connection is started
    void startConnection();

    void startListening();

    void stopListening();

    //when called, the socket and its connection are destroyed
    public void destroyConnection();
}
