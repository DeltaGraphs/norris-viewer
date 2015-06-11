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
 * 0.1.1 2015-05-20 Davide Trivellato Fix and update method update(Observable observable, Object data)
 *
 * 0.1.0 2015-05-20 Davide Trivellato Coding of all methods and attributes
 *
 * 0.0.1 2015-05-20 Davide Trivellato Creation of the file
 *
 * ===============================================================
 *
 */

import java.util.Observable;

import deltagraphs.norrisviewer.model.graphsModel.*;
import deltagraphs.norrisviewer.view.graphsView.*;

public class MapChartPresenterImpl extends GraphPresenter implements MapChartPresenter {

    private MapChartView graphView;
    private MapChart mapChartInstance;

    public MapChartPresenterImpl(MapChartView view, String url) {
        super(url);
        graphView = view;
        mapChartInstance = new MapChartImpl(this);
        startSocket((MapChartActivity) view, mapChartInstance);
    }

    public void startConnection() {
        startSocket((MapChartActivity) graphView, mapChartInstance);
    }

    private void setGraphParameters() {
        graphView.cameraPosition(mapChartInstance.getLatitude(),
                mapChartInstance.getLongitude()
        );
        graphView.setZoom(mapChartInstance.getMapWidth(), mapChartInstance.getMapHeight());
        graphView.setLegendOnPoint(mapChartInstance.getLegendOnPoint());
        graphView.setMapType(mapChartInstance.getMapType());
    }

    @Override
    public void update(Observable observable, Object data) {
        if (observable instanceof MapChartImpl) {
            String signal = (String) data;
            if ((signal == "configGraph") || (signal == "updateGraphProp"))
                setGraphParameters();
            graphView.setData(mapChartInstance.getFlowList(), signal);
        }
    }

    public void stopConnection() {
        stopSocket();
    }

    public void destroyConnection() {
        destroySocket();
    }
}

