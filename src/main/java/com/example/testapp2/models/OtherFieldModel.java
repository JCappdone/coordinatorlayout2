package com.example.testapp2.models;

public class OtherFieldModel {

    private int mID;
    private String mFields;
    private String mFieldsValue;

    public OtherFieldModel(String fields, String fieldsValue) {
        mFields = fields;
        mFieldsValue = fieldsValue;
    }

    public int getID() {
        return mID;
    }

    public void setID(int ID) {
        mID = ID;
    }

    public String getFields() {
        return mFields;
    }

    public void setFields(String fields) {
        mFields = fields;
    }

    public String getFieldsValue() {
        return mFieldsValue;
    }

    public void setFieldsValue(String fieldsValue) {
        mFieldsValue = fieldsValue;
    }
}
