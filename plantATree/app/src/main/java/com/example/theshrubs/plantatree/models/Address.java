package com.example.theshrubs.plantatree.models;

public class Address {

    private int userID;
    private int streetNumber;
    private String streeName;
    private String suburb;
    private String city;
    private int postcode;

    public Address(){

    }

    public Address(int sNumber, String sName, String sub, String city, int post){
        this.setStreetNumber(sNumber);
        this.setStreeName(sName);
        this.setSuburb(sub);
        this.setCity(city);
        this.setPostcode(post);
    }

    public Address(int id, int sNumber, String sName, String sub, String city, int post){
        this.setUserID(id);
        this.setStreetNumber(sNumber);
        this.setStreeName(sName);
        this.setSuburb(sub);
        this.setCity(city);
        this.setPostcode(post);
    }



    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public int getStreetNumber() {
        return streetNumber;
    }

    public void setStreetNumber(int streetNumber) {
        this.streetNumber = streetNumber;
    }

    public String getStreeName() {
        return streeName;
    }

    public void setStreeName(String streeName) {
        this.streeName = streeName;
    }

    public String getSuburb() {
        return suburb;
    }

    public void setSuburb(String suburb) {
        this.suburb = suburb;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public int getPostcode() {
        return postcode;
    }

    public void setPostcode(int postcode) {
        this.postcode = postcode;
    }

    public String toString(){
        return "ADDRESS " + getStreeName();
    }

}
