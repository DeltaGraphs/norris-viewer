package deltagraphs.norrisviewer.model.flowModel;

/*
 * Name : TableFlow.java
 * Module : deltagraphs.norrisviewer.model.flowModel
 * Location : norrisviewer\model\flowModel
 *
 * History :

 * Version Date Programmer Description
 * ===============================================================
 *
 * 0.1.0 2015-05-14 Enrico Savoca Codifica degli attributi e dei parametri
 *
 * 0.0.1 2015-05-14 Enrico Creazione file
 *
 * ===============================================================
 *
 */

import org.json.JSONObject;

import java.util.ArrayList;

public class TableFlow implements FlowModel.Record {
    private ArrayList<String> columnKeys;
    private ArrayList<String> columnFormat;
    private String fontColourKey;
    private String BackgroundColourKey;
    private String BackgroundColourFormat;
    private int maxItems;


}