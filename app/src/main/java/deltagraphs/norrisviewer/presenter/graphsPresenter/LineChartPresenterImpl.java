package deltagraphs.norrisviewer.presenter.graphsPresenter;

import java.net.Socket;
import java.util.Observable;
import java.util.Observer;

import deltagraphs.norrisviewer.presenter.SocketManager;
import deltagraphs.norrisviewer.view.graphsView.*;
import deltagraphs.norrisviewer.model.graphsModel.*;

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
    LineChartPresenterImpl(LineChartView view, String url) {
        super(url);
        graphView = view;
        //lineChartInstance = new LineChartImpl(jsonData);
        this.setUpViews();
    }

    LineChartPresenterImpl(){super("url");}


    @Override
    public void setUpViews() {

    }

    @Override
    public void update(Observable observable, Object data) {

    }

    public void viewPointLegend(){

    }
}
