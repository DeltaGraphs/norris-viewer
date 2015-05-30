package deltagraphs.norrisviewer.presenter.graphsPresenter;

import java.util.Observable;
import java.util.Observer;

import deltagraphs.norrisviewer.model.graphsModel.*;
import deltagraphs.norrisviewer.view.graphsView.LineChartActivity;
import deltagraphs.norrisviewer.view.graphsView.LineChartView;
import deltagraphs.norrisviewer.view.graphsView.MapChartActivity;

/*
 * Name : LineChartPresenterImpl.java
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

public class LineChartPresenterImpl extends GraphPresenter implements LineChartPresenter, Observer{

    private LineChartView graphView;
    private LineChart lineChartInstance;

    public LineChartPresenterImpl(LineChartView view, String url) {
        super(url);
        graphView = view;
        lineChartInstance = (LineChart) new LineChartImpl();
        startSocket((LineChartActivity) view, lineChartInstance);
        //lineChartInstance = new LineChartImpl(jsonData);
        //this.setUpViews();
    }

         /*  @Override
    public void setUpViews() {
 previewLineChartView=(lecho.lib.hellocharts.view.PreviewLineChartView)findViewById(R.id.chart);
        previewLineChartView.setLineChartData(dataModel.getData());
        previewLineChartView.setLineChartData(previewData);
        // Disable zoom/scroll for previewed chart, visible chart ranges depends on preview chart viewport so
        // zoom/scroll is unnecessary.
        previewLineChartView.setZoomEnabled(false);
        previewLineChartView.setScrollEnabled(false);
    }*/

    private void startNewConnections(){ startSocket((MapChartActivity) graphView, lineChartInstance);}

    @Override
    public void update(Observable observable, Object data) {
        if (observable instanceof LineChartImpl) {
            // in quanto potremmo avere piu modelli dati
            // verifichiamo su quale modello é avvenuto un cambiamento dei dati
            // prima di effettuare il cast
            String signal = (String) data;
            if((signal == "configGraph") || (signal=="updateGraphProp"))
                setGraphParameters();
            graphView.setData(lineChartInstance.getFlowList(), signal);
            firstConnection = false;
            startNewConnections();
        }
    }

    @Override
    protected void setGraphParameters() {
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


    public void viewPointLegend(){

    }

}
