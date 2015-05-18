package deltagraphs.norrisviewer.presenter.graphsPresenter;

import java.net.Socket;
import java.util.Observable;
import java.util.Observer;

import deltagraphs.norrisviewer.presenter.SocketManager;
import deltagraphs.norrisviewer.view.graphsView.*;
import deltagraphs.norrisviewer.model.graphsModel.*;
import deltagraphs.norrisviewer.view.graphsView.LineChartView;
import lecho.lib.hellocharts.model.LineChartData;
import lecho.lib.hellocharts.view.*;

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

    LineChartView graphView;
    LineChart lineChartInstance;
   // PreviewLineChartView previewLineChartView;
   // LineChartData lineChartData=new LineChartData();


    public LineChartPresenterImpl(LineChartView view, String url) {
        super(url);
        graphView = view;
        //lineChartInstance = new LineChartImpl(jsonData);
        this.setUpViews();
    }

    LineChartPresenterImpl(){super("url");}


    @Override
    public void setUpViews() {
       /* previewLineChartView=(lecho.lib.hellocharts.view.PreviewLineChartView)findViewById(R.id.chart);
        previewLineChartView.setLineChartData(dataModel.getData());
        previewLineChartView.setLineChartData(previewData);
        // Disable zoom/scroll for previewed chart, visible chart ranges depends on preview chart viewport so
        // zoom/scroll is unnecessary.
        previewLineChartView.setZoomEnabled(false);
        previewLineChartView.setScrollEnabled(false);*/
    }

    @Override
    public void update(Observable observable, Object data) {
       /* if(observable instanceof LineChartModel){
            // in quanto potremmo avere piu modelli dati
            // verifichiamo su quale modello è avvenuto un cambiamento dei dati
            // prima di effettuare il cast
            MyDataModel m=(MyDataModel)observable;
            myChart.setLineChartData(m.getData());*/
    }

    public void viewPointLegend(){

    }
}
