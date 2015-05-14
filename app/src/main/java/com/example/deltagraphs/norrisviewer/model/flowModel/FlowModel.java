package com.example.deltagraphs.norrisviewer.model.flowModel;

/*
 * Name : FlowModel.java
 * Module : norrisviewer::model::flowModel
 * Location : norrisviewer\model\flowModel
 *
 * History :

 * Version Date Programmer Description
 * ===============================================================
 *
 * 0.1.0 2015-05-14 Enrico Savoca Codifica degli attributi e dei parametri
 *
 * 0.0.1 2015-05-13 Enrico Savoca Creazione file
 *
 * ===============================================================
 *
 */

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Objects;

public abstract class FlowModel {
    class FilterModel{
        class ConditionModel{

            private String key;
            private String operator;
            private Object value;

        //constructor
            public ConditionModel(String key, String operator, Object value){
                this.key = key;
                this.operator = operator;
                this.value = value;
            }

        //validate a record according to conditions
            Boolean validateRecord(JSONObject record){
                return false;

                // TO DO !!! ****************************************************************************************
            }

        }

        private ArrayList<ConditionModel> conditions;

        FilterModel(){
            conditions = new ArrayList<ConditionModel>();
        }

        public Boolean ValidateRecord(JSONObject record){

            // test is passed by default ( no tests to do)
            // following rows test if the record is valid within applied conditions

            Boolean test = true;
            for(int i=0; ((i<conditions.size()) && (test==true)); i++){
                test = conditions.get(i).validateRecord(record);
            }
            return test;
        }

        void addCondition(String key, String operator, Object value){
            conditions.add(new ConditionModel(key, operator,value));
        }

    }

    private String flowId;
    private String flowName;
    private ArrayList<Flow> values = new ArrayList<Flow>();
    private FilterModel filters;

    public void addRecord(JSONObject data){
        // TO DO!!!
    }

    public abstract void createFlow(JSONObject data);
    public abstract void updateFlow(JSONObject data);


    public String getFlowName() { return flowName; }
    public ArrayList<Flow> getValues() { return values; }
    public String getFlowId() { return flowId; }

    public interface Flow {}

}

