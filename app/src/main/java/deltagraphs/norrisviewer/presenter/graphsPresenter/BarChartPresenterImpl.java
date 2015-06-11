package deltagraphs.norrisviewer.presenter.graphsPresenter;

import java.util.Observable;

import deltagraphs.norrisviewer.model.graphsModel.*;
import deltagraphs.norrisviewer.view.graphsView.BarChartActivity;
import deltagraphs.norrisviewer.view.graphsView.BarChartView;

/*
 * Name : BarChartPresenterImpl.java
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

public class BarChartPresenterImpl extends GraphPresenter implements BarChartPresenter {

    private BarChartView graphView;
    private BarChart barChartInstance;


    public BarChartPresenterImpl(BarChartView view, String url) {
        super(url);
        graphView = view;
        barChartInstance = new BarChartImpl(this);
        startSocket((BarChartActivity) view, barChartInstance);
    }

    @Override
    public void update(Observable observable, Object data) {
        if (observable instanceof BarChartImpl) {
            String signal = (String) data;
            if ((signal == "configGraph") || (signal == "updateGraphProp"))
                setGraphParameters();
            graphView.setData(barChartInstance.getFlowList(), signal, barChartInstance.getHeaders());
        }
    }

    private void setGraphParameters() {
        graphView.setBarOrientation(barChartInstance.getBarOrientation());
        graphView.setAxis('x',
                barChartInstance.getAxisX().getName(),
                barChartInstance.getAxisX().getAppearance(),
                barChartInstance.getAxisX().getMaxIndex(),
                barChartInstance.getAxisX().getMinIndex(),
                barChartInstance.getAxisX().getTicks(),
                barChartInstance.getAxisX().getScale()
        );
        graphView.setAxis('y',
                barChartInstance.getAxisY().getName(),
                barChartInstance.getAxisY().getAppearance(),
                barChartInstance.getAxisY().getMaxIndex(),
                barChartInstance.getAxisY().getMinIndex(),
                barChartInstance.getAxisY().getTicks(),
                barChartInstance.getAxisY().getScale()
        );
    }

    public void viewPointLegend() {

    }

    public void stopConnection() {
        stopSocket();
    }

    public void startConnection() {
        startSocket((BarChartActivity) graphView, barChartInstance);
    }

    public void destroyConnection() {
        destroySocket();
    }
}