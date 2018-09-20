package com.example.theshrubs.plantatree.models;

public enum SoilDrainage {
    HIGH("High"), MEDIUM("Medium"), LOW("Low");

    private String name;

    private SoilDrainage(String n){
        name = n;
    }

    public String toString(){
        return name;
    }

}
