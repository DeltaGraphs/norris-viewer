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
 * 0.0.1 2015-05-15 Davide Trivellato Creazione file
 *
 * ===============================================================
 *
 */

import android.util.Log;

import java.util.Observable;
import java.util.Observer;

import deltagraphs.norrisviewer.model.flowModel.MapChartFlow;
import deltagraphs.norrisviewer.model.graphsModel.*;
import deltagraphs.norrisviewer.view.graphsView.*;

public class MapChartPresenterImpl extends GraphPresenter implements MapChartPresenter, Observer {

    private MapChartView graphView;
    private MapChart mapChartInstance;

    public MapChartPresenterImpl(MapChartView view, String url) {
        super(url);
        graphView = view;
        mapChartInstance = (MapChart) new MapChartImpl();
        startSocket((MapChartActivity) view, mapChartInstance);
        //lineChartInstance = new LineChartImpl(jsonData);
        this.setUpViews();
    }

    @Override
    public void setUpViews() {

    }

    @Override
    public void update(Observable observable, Object data) {
        if (observable instanceof MapChartImpl) {
            graphView.setParams(mapChartInstance.getLatitude(),
                    mapChartInstance.getLongitude()
            );
            graphView.setZoom(mapChartInstance.getMapHeight(), mapChartInstance.getMapWidth());
            graphView.setLegendOnPoint(mapChartInstance.getLegendOnPoint());
            graphView.setData(mapChartInstance.getFlowList());
            System.out.print("fine update");
            firstConnection = false;
            startNewConnections();
        }
    }

    private void startNewConnections(){ startSocket((MapChartActivity) graphView, mapChartInstance);}
}

