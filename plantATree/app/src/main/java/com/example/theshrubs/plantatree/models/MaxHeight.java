package com.example.theshrubs.plantatree.models;

public enum MaxHeight {

    HIGH("High", 1, 30, 60),
    MEDIUM("Medium", 2, 15, 29),
    LOW("Low",3,0,14);

    private String name;
    private int heightID;
    private int minimum;
    private int maximum;

    private MaxHeight(String n, int id, int min, int max){
        name = n;
        heightID = id;
        minimum = min;
        maximum = max;
    }

    @Override
    public String toString(){
        return name;
    }

    public String getInformation(){
        return  minimum + " - " + maximum + " ft.";
    }


}