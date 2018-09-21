package com.example.theshrubs.plantatree.database;

import android.content.ContentValues;
import android.database.Cursor;

import com.example.theshrubs.plantatree.models.User;

public class UserTable {
    //Details for Tree table
    private static final String USER_ID = "UserID";
    private static final String USER_NAME = "UserName";
    private static final String USER_EMAIL = "Email";
    private static final String USER_PASSWORD = "Password";

    public String createTreeTable(String TABLE_NAME) {
        String createTable = "CREATE TABLE " + TABLE_NAME +
                "(" + USER_ID + " INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL," +
                USER_NAME + " NVARCHAR," +
                USER_EMAIL + " NVARCHAR," +
                USER_PASSWORD + " NVARCHAR" +");";

        return createTable;
    }

    public ContentValues addNewUser(User userObject){
        ContentValues values = new ContentValues();
        values.put(USER_NAME, userObject.getUserName());
        values.put(USER_EMAIL, userObject.getUserEmail());
        values.put(USER_PASSWORD, userObject.getUserPassword());
        return values;
    }

    public ContentValues getExistingUser(User userObject){
        ContentValues values = new ContentValues();
        values.put(USER_ID, userObject.getUserID());
        values.put(USER_NAME, userObject.getUserName());
        values.put(USER_EMAIL, userObject.getUserEmail());
        values.put(USER_PASSWORD, userObject.getUserPassword());

        return values;
    }





    public User findUser(Cursor cursor){
        User userObject = new User();
        if (cursor.moveToFirst()) {
            cursor.moveToFirst();
            userObject.setUserID(cursor.getInt(0));
            userObject.setUserName(cursor.getString(1));
            userObject.setUserEmail(cursor.getString(2));
            userObject.setUserPassword(cursor.getString(3));

            cursor.close();
        }else{
            return null;
        }

        return userObject;
    }


}
