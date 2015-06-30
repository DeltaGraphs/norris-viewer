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
 * 1.0.0 2015-06-02 Matteo Furlan Approve
 *
 * 0.2.0 2015-06-01 Enrico Savoca Verify
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

    //when called, the socket begins listening to some events
    void startListening();

    //when called, the socket stop listening to some events
    void stopListening();

    //when called, the socket and its connection are destroyed
    void destroyConnection();
}
