package com.example.tto4.contactphoneapp.model;

public class Contact {
    private int id;
    private String mName;
    private String mNumber;
    private boolean isMale;

    public Contact() {

    }
    public Contact(String mName, String mNumber, boolean isMale) {
        this.mName = mName;
        this.mNumber = mNumber;
        this.isMale = isMale;
    }
    public Contact(int id, String mName, String mNumber, boolean isMale) {
        this.id = id;
        this.mName = mName;
        this.mNumber = mNumber;
        this.isMale = isMale;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getmName() {
        return mName;
    }

    public void setmName(String mName) {
        this.mName = mName;
    }

    public String getmNumber() {
        return mNumber;
    }

    public void setmNumber(String mNumber) {
        this.mNumber = mNumber;
    }

    public boolean isMale() {
        return isMale;
    }

    public void setMale(boolean male) {
        isMale = male;
    }
}
