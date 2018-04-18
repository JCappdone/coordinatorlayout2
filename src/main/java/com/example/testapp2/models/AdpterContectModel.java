package com.example.testapp2.models;

public class AdpterContectModel {

    private int mID;
    private String mName;
    private String mNumber;

    public AdpterContectModel(String name, String number) {
        mName = name;
        mNumber = number;
    }

    public int getID() {
        return mID;
    }

    public void setID(int ID) {
        mID = ID;
    }

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        mName = name;
    }

    public String getNumber() {
        return mNumber;
    }

    public void setNumber(String number) {
        mNumber = number;
    }
}
