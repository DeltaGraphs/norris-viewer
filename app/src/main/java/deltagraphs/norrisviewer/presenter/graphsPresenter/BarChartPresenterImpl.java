package deltagraphs.norrisviewer.presenter.graphsPresenter;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

import deltagraphs.norrisviewer.model.graphsModel.*;
import deltagraphs.norrisviewer.view.graphsView.BarChartActivity;
import deltagraphs.norrisviewer.view.graphsView.BarChartView;
import deltagraphs.norrisviewer.view.graphsView.HorizontalBarChartActivity;
import deltagraphs.norrisviewer.view.graphsView.MapChartActivity;
import lecho.lib.hellocharts.model.Axis;

/*
 * Name : BarChartPresenterImpl.java
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

public class BarChartPresenterImpl extends GraphPresenter implements BarChartPresenter{

        private BarChartView graphView;
        private BarChart barChartInstance;


    public BarChartPresenterImpl(BarChartView view,String url){
        super(url);
        graphView=view;
        barChartInstance = (BarChart) new BarChartImpl(this);
        startSocket((HorizontalBarChartActivity) view, barChartInstance);
    //lineChartInstance = new LineChartImpl(jsonData);
        //this.setUpViews();
    }


    /*
    @Override
    public void setUpViews(){
        previewLineChartView=(lecho.lib.hellocharts.view.PreviewLineChartView)findViewById(R.id.chart);
        previewLineChartView.setLineChartData(dataModel.getData());
        previewLineChartView.setLineChartData(previewData);
        // Disable zoom/scroll for previewed chart, visible chart ranges depends on preview chart viewport so
        // zoom/scroll is unnecessary.
        previewLineChartView.setZoomEnabled(false);
        previewLineChartView.setScrollEnabled(false);
        }*/

    @Override
    public void update(Observable observable,Object data) {
        if (observable instanceof BarChartImpl) {
        // in quanto potremmo avere piu modelli dati
        // verifichiamo su quale modello e' avvenuto un cambiamento dei dati
        // prima di effettuare il cast
            String signal = (String) data;
            if((signal == "configGraph") || (signal=="updateGraphProp"))
                setGraphParameters();
            graphView.setData(barChartInstance.getFlowList(), signal);
            firstConnection = false;
        }
    }

    @Override
    protected void setGraphParameters() {
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
        graphView.setHeaders(barChartInstance.getHeaders());
        graphView.setBackground(barChartInstance.getBackground());
        graphView.setSortable(barChartInstance.getSortable());
        graphView.setGrid(barChartInstance.getGrid());
        graphView.setLegendOnPoint(barChartInstance.getLegendOnPoint());
    }

    public void viewPointLegend(){

    }

}