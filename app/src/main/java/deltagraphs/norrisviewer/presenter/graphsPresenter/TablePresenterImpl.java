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
 * 0.0.1 2015-05-15 Davide Trivellato Creazione file
 *
 * ===============================================================
 *
 */

import android.util.Log;

import java.util.Observable;
import java.util.Observer;

import deltagraphs.norrisviewer.model.graphsModel.*;
import deltagraphs.norrisviewer.view.graphsView.*;

public class TablePresenterImpl extends GraphPresenter implements TablePresenter{

    private TableView graphView;
    private Table tableInstance;

    public TablePresenterImpl(TableView view, String url) {
        super(url);
        graphView = view;
        tableInstance = (Table) new TableImpl(this);
        startSocket((TableActivity) view, tableInstance);
//        this.setUpViews();
    }


    private void startNewConnections(){ startSocket((TableActivity) graphView, tableInstance);}

    @Override
    public void update(Observable observable, Object data) {
        if (observable instanceof TableImpl) {
            String signal = (String) data;
            if((signal == "configGraph") || (signal=="updateGraphProp"))
                setGraphParameters();
            graphView.setData(tableInstance.getFlowList(), tableInstance.getNumberOfColumns(), signal);

            firstConnection = false;
            startNewConnections();
        }
    }

    @Override
    protected void setGraphParameters() {
            graphView.setAddRowOn(tableInstance.getAddRowOn());
            graphView.setMaxItemsDisplayedPerPage(tableInstance.getMaxItems());
            graphView.setSortable(tableInstance.getSortable());
            graphView.setSortByCol(tableInstance.sortByCol());
            graphView.setSortOrder(tableInstance.getSortOrder());
            graphView.setBorderWidth(tableInstance.getBorderWidth());
            graphView.setBorderColour(tableInstance.getBorderColour());

            //column parameters
            for(int index=0; index<tableInstance.getNumberOfColumns(); index++) {
                graphView.setHeader(index,
                        tableInstance.getHeaderValue(index),
                        tableInstance.getHeaderTextColour(index),
                        tableInstance.getHeaderBGColour(index));
                graphView.setRowEven(index, tableInstance.getRowEvenTC(index), tableInstance.getRowEvenBGColour(index));
                graphView.setRowOdd(index, tableInstance.getRowOddTC(index), tableInstance.getRowOddBGColour(index));
            }
    }
}
