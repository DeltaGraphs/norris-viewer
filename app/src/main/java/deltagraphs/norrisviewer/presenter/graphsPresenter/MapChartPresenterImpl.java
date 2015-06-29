package deltagraphs.norrisviewer.presenter.graphsPresenter;

/*
 * Name : MapChartPresenterImpl.java
 * Module : deltagraphs.norrisviewer.presenter.graphsPresenter
 * Location : norrisviewer\presenter\graphsPresenter
 *
 * History :

 * Version Date Programmer Description
 * ===============================================================
 *
 * 0.1.1 2015-06-05 Davide Trivellato Update method update(Observable observable, Object data)
 *
 * 0.1.0 2015-06-04 Davide Trivellato Coding of all methods and attributes
 *
 * 0.0.1 2015-06-03 Davide Trivellato Creation of the file
 *
 * ===============================================================
 *
 */

import android.util.Log;

import java.util.Observable;

import deltagraphs.norrisviewer.model.graphsModel.*;
import deltagraphs.norrisviewer.view.graphsView.*;

public class MapChartPresenterImpl extends GraphPresenter implements MapChartPresenter {

    protected MapChartView graphView;
    protected MapChart mapChartInstance;

    /* constructor of MapChartPresenterImpl. It requires an activity and an url to be instatiated properly.
    The object will be associated to the activity and a new connection will be established with the
    given url. Furthermore, an instance of mapchart model will be created. The object will observe the
    changes on it, according to the observer design pattern. */
    public MapChartPresenterImpl(MapChartView view, String url) {
        super(url);
        graphView = view;
        mapChartInstance = new MapChartImpl(this);
        startSocket((MapChartActivity) view, mapChartInstance);
    }

    /* this method is always called by the method "update".
    Its objective is to set graph parameters, extracting the informations from the model. */
    private void setGraphParameters() {
        graphView.cameraPosition(mapChartInstance.getLatitude(),
                mapChartInstance.getLongitude()
        );
        graphView.setZoom(mapChartInstance.getMapWidth(), mapChartInstance.getMapHeight());
        graphView.setLegendOnPoint(mapChartInstance.getLegendOnPoint());
        graphView.setMapType(mapChartInstance.getMapType());
    }

    /* This object is an observer of the map chart model. When there are some changes on it, a signal is sent
    to this object and the following method is called. Its aim is to set or update informations that are
    shown on the activity. Data is always updated on it. In some cases, when a signal arrives with the value
    "configGraph" or "updateGraphProp", there is also an update of the graph parameters, on the activity.
    All of these informations are extracted from the model.*/
    @Override
    public void update(Observable observable, Object data) {
        if (observable instanceof MapChartImpl) {
            String signal = (String) data;
            if ((signal == "configGraph") || (signal == "updateGraphProp"))
                setGraphParameters();
            graphView.setData(mapChartInstance.getFlowList(), signal);
        }
        Log.d("", "update map");
    }

    //when called, the socket connection is stopped
    public void stopConnection() {
        stopSocket();
    }

    //when called, the socket connection is started
    public void startConnection() {
        startSocket((MapChartActivity) graphView, mapChartInstance);
    }

    //when called, the socket and its connection are destroyed
    public void destroyConnection() {
        destroySocket();
    }
}

