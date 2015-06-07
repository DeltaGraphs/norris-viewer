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
 * 0.1.0 2015-05-15 Davide Trivellato Codifica di tutti gli attributi e i metodi
 *
 * 0.0.1 2015-05-15 Davide Trivellato Creazione file
 *
 * ===============================================================
 *
 */

public interface MapChartPresenter {
    void stopConnection();

    void destroyConnection();

    void startConnection();
}
