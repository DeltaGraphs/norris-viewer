package deltagraphs.norrisviewer.presenter.graphsPresenter;

import java.util.Observable;

import deltagraphs.norrisviewer.model.graphsModel.*;
import deltagraphs.norrisviewer.view.graphsView.LineChartActivity;
import deltagraphs.norrisviewer.view.graphsView.LineChartView;

/*
 * Name : LineChartPresenterImpl.java
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

public class LineChartPresenterImpl extends GraphPresenter implements LineChartPresenter {

    private LineChartView graphView;
    private LineChart lineChartInstance;

    public LineChartPresenterImpl(LineChartView view, String url) {
        super(url);
        graphView = view;
        lineChartInstance = new LineChartImpl(this);
        startSocket((LineChartActivity) view, lineChartInstance);
    }

    private void startNewConnections() {
        startSocket((LineChartActivity) graphView, lineChartInstance);
    }

    @Override
    public void update(Observable observable, Object data) {
        if (observable instanceof LineChartImpl) {
            String signal = (String) data;
            if ((signal == "configGraph") || (signal == "updateGraphProp"))
                setGraphParameters();

            graphView.setData(lineChartInstance.getFlowList(), signal);
        }
    }

    private void setGraphParameters() {
        graphView.setAxis('x',
                lineChartInstance.getAxisX().getName(),
                lineChartInstance.getAxisX().getAppearance(),
                lineChartInstance.getAxisX().getMaxIndex(),
                lineChartInstance.getAxisX().getMinIndex(),
                lineChartInstance.getAxisX().getTicks(),
                lineChartInstance.getAxisX().getScale()
        );
        graphView.setAxis('y',
                lineChartInstance.getAxisY().getName(),
                lineChartInstance.getAxisY().getAppearance(),
                lineChartInstance.getAxisY().getMaxIndex(),
                lineChartInstance.getAxisY().getMinIndex(),
                lineChartInstance.getAxisY().getTicks(),
                lineChartInstance.getAxisY().getScale()
        );
        graphView.setViewFinder(lineChartInstance.getViewFinder());
        graphView.setBackground(lineChartInstance.getBackground());
        graphView.setGrid(lineChartInstance.getHorizontalGrid(), lineChartInstance.getVerticalGrid());
        graphView.setLegendOnPoint(lineChartInstance.getLegendOnPoint());
    }


    public void viewPointLegend() {

    }

    public void destroy() {
        stopSocket();
    }

}
