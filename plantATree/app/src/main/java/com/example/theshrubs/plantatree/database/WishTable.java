package com.example.theshrubs.plantatree.database;

import android.content.ContentValues;
import android.database.Cursor;

import com.example.theshrubs.plantatree.models.Wishlist;

import java.util.ArrayList;
import java.util.List;

public class WishTable {

    //Details for Cart table
    private static final String WISHLIST_ID = "UserID";
    private static final String PRODUCT_ID = "ProductID";
    private static final String PRODUCT_NAME = "ProductName";
    private static final String PRODUCT_COST = "ProductCost";
    private static final String PRODUCT_DESCRIPTION = "Description";
    private static final String PHOTO_ID = "PhotoID";



    public String createWishTable(String TABLE_NAME) {
        String createTable = "CREATE TABLE " + TABLE_NAME +
                "(" + WISHLIST_ID + " INTEGER NOT NULL," +
                PRODUCT_ID + " INTEGER," +
                PRODUCT_NAME + " NVARCHAR," +
                PRODUCT_COST + " REAL," +
                PRODUCT_DESCRIPTION + " NVARCHAR," +
                PHOTO_ID + " INTEGER" + ");";

        return createTable;
    }


    public ContentValues getWishContents(Wishlist object) {
        ContentValues values = new ContentValues();
        values.put(WISHLIST_ID, object.getWishlistID());
        values.put(PRODUCT_ID, object.getProductID());
        values.put(PRODUCT_NAME, object.getProductName());
        values.put(PRODUCT_COST, object.getProductCost());
        values.put(PRODUCT_DESCRIPTION, object.getProductDescription());
        values.put(PHOTO_ID, object.getPhotoID());

        System.out.println(object);

        return values;
    }


    public Wishlist findWish(Cursor cursor){
        Wishlist wishlist = new Wishlist();
        if (cursor.moveToFirst()) {
            cursor.moveToFirst();
            wishlist.setWishlistID(cursor.getInt(0));
            wishlist.setProductID(cursor.getInt(1));
            wishlist.setProductName(cursor.getString(2));
            wishlist.setProductCost(cursor.getDouble(3));
            wishlist.setProductDescription(cursor.getString(4));
            wishlist.setPhotoID(cursor.getInt(5));
            cursor.close();
        }else{
            return null;
        }

        return wishlist;
    }

    public List<Wishlist> loadWish(Cursor cursor){
        List<Wishlist> cartList = new ArrayList<>();

        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            Wishlist wishlist = new Wishlist();
            wishlist.setWishlistID(cursor.getInt(0));
            wishlist.setProductID(cursor.getInt(1));
            wishlist.setProductName(cursor.getString(2));
            wishlist.setProductCost(cursor.getDouble(3));
            wishlist.setProductDescription(cursor.getString(4));
            wishlist.setPhotoID(cursor.getInt(5));
            cartList.add(wishlist);

            cursor.moveToNext();
        }

        return cartList;

    }
}
