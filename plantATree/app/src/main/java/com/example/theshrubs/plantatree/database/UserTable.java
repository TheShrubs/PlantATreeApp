package com.example.theshrubs.plantatree.database;

import android.content.ContentValues;
import android.database.Cursor;

import com.example.theshrubs.plantatree.models.User;

public class UserTable {
    //Details for User table
    private static final String USER_ID = "UserID";
    private static final String USER_NAME = "Username";
    private static final String USER_EMAIL = "Email";
    private static final String USER_PASSWORD = "Password";

    private DatabaseHelper dbHandler;
    private User currentUser;
    private Object object;

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

        System.out.println("User add new !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");

        return values;
    }

    public ContentValues getExistingUser(User userObject){
        ContentValues values = new ContentValues();
        values.put(USER_ID, userObject.getUserID());
        values.put(USER_NAME, userObject.getUserName());
        values.put(USER_EMAIL, userObject.getUserEmail());
        values.put(USER_PASSWORD, userObject.getUserPassword());

        System.out.println("User add new !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");

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


            System.out.println("in find user" + cursor.getInt(0));
            cursor.close();
        }else{

            System.out.println("cursor was NULL");
            return null;
        }

        return userObject;
    }

    public User checkLogin(String user, String pass, DatabaseHelper db){

        this.dbHandler = db;
        dbHandler.setUser(user, pass);
        System.out.println("from login "+ user + " pass " + pass);

        object = dbHandler.findHandle(1, "findExistingUser");
        if(object == null){
            System.out.println("check login was  null");
//            return null;
        }else{
            currentUser = (User) object;

        }
        return currentUser;
    }


//    public User checkLogin(String user, String pass, DatabaseHelper db){
//
//        this.dbHandler = db;
//        User currentUser = new User();
////        dbHandler.setUser(user, pass);
//
//        Object object = dbHandler.findHandle(user, "User");
//        if(object == null){
//            System.out.println("check login was  null");
//            return null;
//        }else{
//            currentUser = (User) object;
//            if(currentUser.getUserPassword().equals(pass)){
//                System.out.println("User from check login " + currentUser);
//                return currentUser;
//            }else{
//                return null;
//            }
//
//        }
////        return currentUser;
//    }
//
//
//    public boolean checkUser(String email) {
//        String[] columns = {
//                USER_ID
//        };
//        //call SQLite DB
//        SQLiteDatabase db = this.getWritableDatabase();
//        String selection = USER_EMAIL + " = ?";
//        String[] selectionArgs = {email};
//
//        Cursor cursor = db.query(USER_TABLE, columns, selection, selectionArgs, null, null, null);
//        int cursorCount = cursor.getCount();
//        cursor.close();
//        db.close();
//        if (cursorCount > 0) {
//            return true;
//        }
//        return false;
//    }
}
