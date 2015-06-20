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
 * 0.1.1 2015-05-31 Davide Trivellato Fix and update method update(Observable observable, Object data)
 *
 * 0.1.0 2015-05-30 Davide Trivellato Coding of all methods and attributes
 *
 * 0.0.1 2015-05-28 Davide Trivellato Creation of the file
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
                lineChartInstance.getAxisX().getMaxIndex(),
                lineChartInstance.getAxisX().getMinIndex(),
                lineChartInstance.getAxisX().getTicks()
        );
        graphView.setAxis('y',
                lineChartInstance.getAxisY().getName(),
                lineChartInstance.getAxisY().getMaxIndex(),
                lineChartInstance.getAxisY().getMinIndex(),
                lineChartInstance.getAxisY().getTicks()
        );
        //graphView.setViewFinder(lineChartInstance.getViewFinder());
        //graphView.setBackground(lineChartInstance.getBackground());
        graphView.setGrid(lineChartInstance.getHorizontalGrid(), lineChartInstance.getVerticalGrid());
        //graphView.setLegendOnPoint(lineChartInstance.getLegendOnPoint());
    }

    public void destroy() {
        stopSocket();
    }

}
