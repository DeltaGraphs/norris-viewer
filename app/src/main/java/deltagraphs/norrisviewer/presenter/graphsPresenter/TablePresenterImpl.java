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

    public TablePresenterImpl(TableView view, String url) {
        super(url);
        graphView = view;
        tableInstance = new TableImpl(this);
        startSocket((TableActivity) view, tableInstance);
    }


    @Override
    public void update(Observable observable, Object data) {
        if (observable instanceof TableImpl) {
            String signal = (String) data;
            if ((signal == "configGraph") || (signal == "updateGraphProp"))
                setGraphParameters();
            graphView.setData(tableInstance.getFlowList(), tableInstance.getNumberOfColumns());
        }
    }

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

    public void stopConnection() {
        stopSocket();
    }

    public void destroyConnection() {
        destroySocket();
    }

    public void startConnection() {
        startSocket((TableActivity) graphView, tableInstance);
    }
}
