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

public class MapChartPresenterImpl extends GraphPresenter implements MapChartPresenter{

    private MapChartView graphView;
    private MapChart mapChartInstance;

    public MapChartPresenterImpl(MapChartView view, String url) {
        super(url);
        graphView = view;
        mapChartInstance = (MapChart) new MapChartImpl();
        startSocket((MapChartActivity) view, mapChartInstance);
        //lineChartInstance = new LineChartImpl(jsonData);
        //this.setUpViews();
    }

    private void startNewConnections(){ startSocket((MapChartActivity) graphView, mapChartInstance);}

    @Override
    protected void setGraphParameters(){
        graphView.cameraPosition(mapChartInstance.getLatitude(),
                mapChartInstance.getLongitude()
        );
        graphView.setZoom(mapChartInstance.getMapWidth(), mapChartInstance.getMapHeight());
        graphView.setLegendOnPoint(mapChartInstance.getLegendOnPoint());
    }

    @Override
    public void update(Observable observable, Object data) {
        if (observable instanceof MapChartImpl) {
            String signal = (String) data;
            if((signal == "configGraph") || (signal=="updateGraphProp"))
                setGraphParameters();
            graphView.setData(mapChartInstance.getFlowList(), signal);

            firstConnection = false;
            startNewConnections();
        }
    }


}

