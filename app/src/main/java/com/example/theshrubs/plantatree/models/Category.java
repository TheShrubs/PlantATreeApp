package com.example.theshrubs.plantatree.models;

public enum Category {
    FLORA("Flowering", 0),
    FAUNA("Non-flowering", 1);

    private String stringValue;
    private int intValue;

    private Category(String toString, int value){
        stringValue = toString;
        intValue = value;
    }

    @Override
    public String toString(){
        return stringValue;
    }


}
