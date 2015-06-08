package deltagraphs.norrisviewer.view.graphsView;

/*
 * Name : { Nome del file }.java
 * Module : deltagraphs.norrisviewer.view.graphsView
 * Location : norrisviewer { Percorso in cui \'e presente il file }
 *
 * History :

 * Version Date Programmer Description
 * ===============================================================
 * 
 * 0.1.0 2015-05-X Enrico Savoca Codifica di tutti gli attributi e i metodi
 *
 * 0.0.1 2015-05-X Enrico Savoca Creazione file
 *
 * ===============================================================
 *
 */

import java.util.ArrayList;

import deltagraphs.norrisviewer.model.flowModel.FlowModel;

public interface BarChartView {
    public void setAxis(char axisXorY, String name, String appearance, Float maxIndex, Float minIndex, int ticks, int scale);

    public void setHeaders(ArrayList<String> headers);

    public void setBackground(String bg);

    public void setBarOrientation(String orientation);

    public void setSortable(Boolean choise);

    public void setGrid(Boolean grid);

    public void setLegendOnPoint(Boolean legend);

    public void setData(ArrayList<FlowModel> flowList, String signal, ArrayList<String> headers);
}
