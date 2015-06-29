package deltagraphs.norrisviewer.presenter.graphsPresenter;

import android.util.Log;

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
 * 0.3.1 2015-05-26 Davide Trivellato Fix and update method update(Observable observable, Object data)
 *
 * 0.3.0 2015-05-25 Davide Trivellato Other changes to update(Observable observable, Object data) and added setGraphParameters()
 *
 * 0.2.0 2015-05-25 Davide Trivellato Several changes to update(Observable observable, Object data)
 *
 * 0.1.0 2015-05-24 Davide Trivellato Coding of all methods and attributes
 *
 * 0.0.1 2015-05-23 Davide Trivellato Creation of the file
 *
 * ===============================================================
 *
 */

public class BarChartPresenterImpl extends GraphPresenter implements BarChartPresenter {

    protected BarChartView graphView;
    protected BarChart barChartInstance;

    /* constructor of BarChartPresenterImpl. It requires an activity and an url to be instatiated properly.
    The object will be associated to the activity and a new connection will be established with the
    given url. Furthermore, an instance of barchart model will be created. The object will observe the
    changes on it, according to the observer design pattern. */
    public BarChartPresenterImpl(BarChartView view, String url) {
        super(url);
        graphView = view;
        barChartInstance = new BarChartImpl(this);
        startSocket((BarChartActivity) view, barChartInstance);
    }

    /* This object is an observer of the bar chart model. When there are some changes on it, a signal is sent
    to this object and the following method is called. Its aim is to set or update informations that are
    shown on the activity. Data is always updated on it. In some cases, when a signal arrives with the value
    "configGraph" or "updateGraphProp", there is also an update of the graph parameters, on the activity.
    All of these informations are extracted from the model. */
    @Override
    public void update(Observable observable, Object data) {
        if (observable instanceof BarChartImpl) {
            String signal = (String) data;
            if ((signal == "configGraph") || (signal == "updateGraphProp"))
                setGraphParameters();
            graphView.setData(barChartInstance.getFlowList(), signal, barChartInstance.getHeaders());
        }
        Log.d("", "update bar");
    }

    /* this method is always called by the method "update".
    Its objective is to set graph parameters, extracting the informations from the model. */
    private void setGraphParameters() {
        graphView.setBarOrientation(barChartInstance.getBarOrientation());
        /*
        graphView.setAxis('x',
                barChartInstance.getAxisX().getName(),
                barChartInstance.getAxisX().getTicks()
        );
        graphView.setAxis('y',
                barChartInstance.getAxisY().getName(),
                barChartInstance.getAxisY().getTicks()
        );
        */
    }

    //when called, the socket connection is stopped
    public void stopConnection() {
        stopSocket();
    }

    //when called, the socket connection is started
    public void startConnection() {
        startSocket((BarChartActivity) graphView, barChartInstance);
    }

    @Override
    public void startListening() {
        super.startListening((BarChartActivity)graphView, barChartInstance);
    }

}