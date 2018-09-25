package com.example.theshrubs.plantatree.models;

public class User {

    private int UserID;
    private String userName;
    private String userEmail;
    private String userType;
    private String userPassword;

    //default constructor so user object can be instantiated with no parameters
    public User(){

    }

    //constructor for creating a NEW user with no assigned User ID
    public User(String name, String email, String pass){
        setUserName(name);
        setUserEmail(email);
        setUserPassword(pass);
    }

    //constructor for creating RETURNING user with existing ID
    public User(int id, String name, String email, String pass) {
        setUserID(id);
        setUserName(name);
        setUserEmail(email);
        setUserPassword(pass);
    }

    public int getUserID() {
        return UserID;
    }

    public void setUserID(int userID) {
        UserID = userID;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    @Override
    public String toString(){
        return "UserID:" + getUserID() + " USERNAME:" + getUserName() + " PASSWORD"+ getUserPassword();
    }

}
