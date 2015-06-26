package deltagraphs.norrisviewer.presenter.graphsPresenter;

/*
 * Name : TablePresenterImpl.java
 * Module : deltagraphs.norrisviewer.presenter.graphsPresenter
 * Location : norrisviewer\presenter\graphsPresenter
 *
 * History :

 * Version Date Programmer Description
 * ===============================================================
 *
 * 0.1.0 2015-05-10 Davide Trivellato Coding of all methods and attributes
 *
 * 0.0.1 2015-05-09 Davide Trivellato Creation of the file
 *
 * ===============================================================
 *
 */

import java.util.Observable;

import deltagraphs.norrisviewer.model.graphsModel.*;
import deltagraphs.norrisviewer.view.graphsView.*;

public class TablePresenterImpl extends GraphPresenter implements TablePresenter {

    protected TableView graphView;
    protected Table tableInstance;

    /* constructor of TablePresenterImpl. It requires an activity and an url to be instatiated properly.
     The object will be associated to the activity and a new connection will be established with the
     given url. Furthermore, an instance of table model will be created. The object will observe the
     changes on it, according to the observer design pattern. */
    public TablePresenterImpl(TableView view, String url) {
        super(url);
        graphView = view;
        tableInstance = new TableImpl(this);
        startSocket((TableActivity) view, tableInstance);
    }

    /* This object is an observer of the table model. When there are some changes on it, a signal is sent
    to this object and the following method is called. Its aim is to set or update informations that are
    shown on the activity. Data is always updated on it. In some cases, when a signal arrives with the value
    "configGraph" or "updateGraphProp", there is also an update of the graph parameters, on the activity.
    All of these informations are extracted from the model.*/
    @Override
    public void update(Observable observable, Object data) {
        if (observable instanceof TableImpl) {
            String signal = (String) data;
            if ((signal == "configGraph") || (signal == "updateGraphProp"))
                setGraphParameters();
            graphView.setData(tableInstance.getFlowList(), tableInstance.getNumberOfColumns());
        }
    }

    /* this method is always called by the method "update".
    Its objective is to set graph parameters, extracting the informations from the model. */
    private void setGraphParameters() {
        graphView.setSortOrder(tableInstance.getSortOrder());
        String[] headers = new String[tableInstance.getNumberOfColumns()];
        //column parameters
        for (int index = 0; index < tableInstance.getNumberOfColumns(); index++) {
            headers[index]=tableInstance.getHeaderValue(index);
        }
        graphView.setHeaders(headers);
        graphView.setSortByCol(tableInstance.sortByCol());
    }

    //when called, the socket connection is stopped
    public void stopConnection() {
        stopSocket();
    }

    //when called, the socket connection is started
    public void startConnection() {
        startSocket((TableActivity) graphView, tableInstance);
    }

    //when called, the socket and its connection are destroyed
    public void destroyConnection() {
        destroySocket();
    }
}
