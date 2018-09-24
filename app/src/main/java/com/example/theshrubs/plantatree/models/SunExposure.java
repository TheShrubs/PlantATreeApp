package com.example.theshrubs.plantatree.models;

public enum SunExposure {
    HIGH("High"), MEDIUM("Medium"), LOW("Low");

    private String name;

    private SunExposure(String n){
        name = n;
    }

    public String toString(){
        return name;
    }


}
