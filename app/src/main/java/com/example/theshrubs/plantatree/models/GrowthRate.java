package com.example.theshrubs.plantatree.models;

public enum GrowthRate {
    HIGH("High"), MEDIUM("Medium"), LOW("Low");

    private String name;

    private GrowthRate(String n){
        name = n;
    }

    public String toString(){
        return name;
    }
}
