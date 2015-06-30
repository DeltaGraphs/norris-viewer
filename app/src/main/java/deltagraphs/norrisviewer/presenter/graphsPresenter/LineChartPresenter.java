package deltagraphs.norrisviewer.presenter.graphsPresenter;

/*
 * Name : LineChartPresenter.java
 * Module : deltagraphs.norrisviewer.presenter.graphsPresenter
 * Location : norrisviewer\presenter\graphsPresenter
 *
 * History :

 * Version Date Programmer Description
 * ===============================================================
 *
 * 0.1.0 2015-05-28 Davide Trivellato Coding of all methods
 *
 * 0.0.1 2015-05-28 Davide Trivellato Creation of the file
 *
 * ===============================================================
 *
 */

public interface LineChartPresenter {
    //when called, the socket connection is stopped
    void stopConnection();

    //when called, the socket connection is started
    void startConnection();

    void startListening();

    void stopListening();

    //when called, the socket and its connection are destroyed
    public void destroyConnection();
}
