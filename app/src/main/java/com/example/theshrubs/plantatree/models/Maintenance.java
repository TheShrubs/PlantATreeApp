package com.example.theshrubs.plantatree.models;

public enum Maintenance {
    HIGH("High"), MEDIUM("Medium"), LOW("Low");

    private String name;

    private Maintenance(String n){
        name = n;
    }

    public String toString(){
        return name;
    }
}
