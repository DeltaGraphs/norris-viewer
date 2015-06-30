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
 * 1.0.0 2015-06-21 Matteo Furlan Approve
 *
 * 0.2.0 2015-06-20 Enrico Savoca Verify
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

    protected LineChartView graphView;
    protected LineChart lineChartInstance;

    /* constructor of LineChartPresenterImpl. It requires an activity and an url to be instatiated properly.
    The object will be associated to the activity and a new connection will be established with the
    given url. Furthermore, an instance of linechart model will be created. The object will observe the
    changes on it, according to the observer design pattern. */
    public LineChartPresenterImpl(LineChartView view, String url) {
        super(url);
        graphView = view;
        lineChartInstance = new LineChartImpl(this);
        startSocket((LineChartActivity) view, lineChartInstance);
    }

    /* This object is an observer of the line chart model. When there are some changes on it, a signal is sent
    to this object and the following method is called. Its aim is to set or update informations that are
    shown on the activity. Data is always updated on it. In some cases, when a signal arrives with the value
    "configGraph" or "updateGraphProp", there is also an update of the graph parameters, on the activity.
    All of these informations are extracted from the model.*/
    @Override
    public void update(Observable observable, Object data) {
        if (observable instanceof LineChartImpl) {
            String signal = (String) data;
            if ((signal == "configGraph") || (signal == "updateGraphProp"))
                setGraphParameters();

            graphView.setData(lineChartInstance.getFlowList(), signal);
        }
    }

    /* this method is always called by the method "update".
    Its objective is to set graph parameters, extracting the informations from the model. */
    private void setGraphParameters() {
        graphView.setAxis('x',
                lineChartInstance.getAxisX().getName(),
                lineChartInstance.getAxisX().getTicks()
        );
        graphView.setAxis('y',
                lineChartInstance.getAxisY().getName(),
                lineChartInstance.getAxisY().getTicks()
        );
        graphView.setViewFinder(lineChartInstance.getViewFinder());
        graphView.setGrid(lineChartInstance.getHorizontalGrid(), lineChartInstance.getVerticalGrid());
    }

    //when called, the socket connection is stopped
    public void stopConnection() {
        stopSocket();
    }

    //when called, the socket connection is started
    public void startConnection() {
        startSocket((LineChartActivity) graphView, lineChartInstance);
    }

    //when called, the socket begins listening to some events
    @Override
    public void startListening() {
        super.startListening((LineChartActivity) graphView, lineChartInstance);
    }

    //when called, the socket and its connection are destroyed
    public void destroyConnection() {
        graphSocket.destroyConnection();
    }
}
