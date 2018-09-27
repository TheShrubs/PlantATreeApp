package com.example.theshrubs.plantatree.database;

import android.content.ContentValues;
import android.database.Cursor;

import com.example.theshrubs.plantatree.models.Address;

import java.util.ArrayList;
import java.util.List;

public class AddressTable {

         //Details for Cart table
        private static final String CART_ID = "UserID";
        private static final String STREET_NUMBER = "StreetNumber";
        private static final String STREET_NAME = "StreetName";
        private static final String SUBURB = "Suburb";
        private static final String CITY = "City";
        private static final String POSTCODE = "Postcode";



        public String createAddressTable(String TABLE_NAME) {
            String createTable = "CREATE TABLE " + TABLE_NAME +
                    "(" + CART_ID + " INTEGER PRIMARY KEY NOT NULL," +
                    STREET_NUMBER + " INTEGER," +
                    STREET_NAME + " NVARCHAR," +
                    SUBURB + " NVARCHAR," +
                    CITY + " NVARCHAR," +
                    POSTCODE + " INTEGER" + ");";

            return createTable;
        }


        public ContentValues getAddressContents(Address addressObject) {
            ContentValues values = new ContentValues();
            values.put(CART_ID, addressObject.getUserID());
            values.put(STREET_NUMBER, addressObject.getStreetNumber());
            values.put(STREET_NAME, addressObject.getStreeName());
            values.put(SUBURB, addressObject.getSuburb());
            values.put(CITY, addressObject.getCity());
            values.put(POSTCODE, addressObject.getPostcode());

            return values;
        }


        public Address findAddress(Cursor cursor){
            Address addressObject = new Address();
            if (cursor.moveToFirst()) {
                cursor.moveToFirst();
                addressObject.setUserID(cursor.getInt(0));
                addressObject.setStreetNumber(cursor.getInt(1));
                addressObject.setStreeName(cursor.getString(2));
                addressObject.setSuburb(cursor.getString(3));
                addressObject.setCity(cursor.getString(4));
                addressObject.setPostcode(cursor.getInt(5));
                cursor.close();
            }else{
                return null;
            }

            System.out.println(" addressObject," +addressObject.toString());
            return addressObject;
        }


}
