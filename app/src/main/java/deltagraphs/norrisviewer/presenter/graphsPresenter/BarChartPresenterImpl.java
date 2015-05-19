package deltagraphs.norrisviewer.presenter.graphsPresenter;

import java.util.Observable;
import java.util.Observer;

import deltagraphs.norrisviewer.model.graphsModel.BarChart;
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
 * 0.0.1 2015-05-15 Davide Trivellato Creazione file
 *
 * ===============================================================
 *
 */

public class BarChartPresenterImpl extends GraphPresenter implements BarChartPresenter, Observer{

        BarChartView graphView;
        BarChart lineChartInstance;
// PreviewLineChartView previewLineChartView;
// LineChartData lineChartData=new LineChartData();


public BarChartPresenterImpl(BarChartView view,String url, CharSequence title){
        super(url,title);
        graphView=view;
        graphView.setChartTitle(title);
        //lineChartInstance = new LineChartImpl(jsonData);
        this.setUpViews();
        }



@Override
public void setUpViews(){
       /* previewLineChartView=(lecho.lib.hellocharts.view.PreviewLineChartView)findViewById(R.id.chart);
        previewLineChartView.setLineChartData(dataModel.getData());
        previewLineChartView.setLineChartData(previewData);
        // Disable zoom/scroll for previewed chart, visible chart ranges depends on preview chart viewport so
        // zoom/scroll is unnecessary.
        previewLineChartView.setZoomEnabled(false);
        previewLineChartView.setScrollEnabled(false);*/
        }

@Override
public void update(Observable observable,Object data){
       /* if(observable instanceof LineChartModel){
            // in quanto potremmo avere piu modelli dati
            // verifichiamo su quale modello � avvenuto un cambiamento dei dati
            // prima di effettuare il cast
            MyDataModel m=(MyDataModel)observable;
            myChart.setLineChartData(m.getData());*/
        }

public void viewPointLegend(){

        }
}