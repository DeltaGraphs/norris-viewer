package deltagraphs.norrisviewer.model.graphsModel;

/*
 * Name : MapChart.java
 * Module : deltagraphs.norrisviewer.model.graphsModel
 * Location : norrisviewer\model\graphsModel
 *
 * History :

 * Version Date Programmer Description
 * ===============================================================
 *
 * 0.0.1 2015-05-17 Enrico Savoca Creazione file
 *
 * ===============================================================
 *
 */

public interface MapChart {
    public float getLatitude();
    public float getLongitude();
    public int getScale();
    public String getMapType();
    public Boolean getZoom();
    public Boolean getLegendOnPoint();
}
