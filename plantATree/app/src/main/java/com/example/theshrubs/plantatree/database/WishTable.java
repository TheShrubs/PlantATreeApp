package com.example.theshrubs.plantatree.database;

import android.content.ContentValues;
import android.database.Cursor;

import com.example.theshrubs.plantatree.models.ShoppingCart;
import com.example.theshrubs.plantatree.models.Wishlist;

import java.util.ArrayList;
import java.util.List;

public class WishTable {

    //Details for Cart table
    private static final String CART_ID = "UserID";
    private static final String PRODUCT_ID = "ProductID";
    private static final String PRODUCT_NAME = "ProductName";
    private static final String PRODUCT_COST = "ProductCost";
    private static final String DELIVERY_COST = "DeliveryCost";
    private static final String QUANTITY = "Quantity";
    private static final String TOTAL_COST = "TotalCost";
    private static final String PHOTO_ID = "PhotoID";



    public String createWishTable(String TABLE_NAME) {
        String createTable = "CREATE TABLE " + TABLE_NAME +
                "(" + CART_ID + " INTEGER NOT NULL," +
                PRODUCT_ID + " INTEGER," +
                PRODUCT_NAME + " NVARCHAR," +
                PRODUCT_COST + " REAL," +
                DELIVERY_COST + " REAL," +
                QUANTITY + " INTEGER," +
                TOTAL_COST + " REAL," +
                PHOTO_ID + " INTEGER" + ");";

        return createTable;
    }


    public ContentValues getWishContents(Wishlist cartObject) {
        ContentValues values = new ContentValues();
        values.put(CART_ID, cartObject.getCartID());
        values.put(PRODUCT_ID, cartObject.getProductID());
        values.put(PRODUCT_NAME, cartObject.getProductName());
        values.put(PRODUCT_COST, cartObject.getProductCost());
        values.put(DELIVERY_COST, cartObject.getDeliveryCost());
        values.put(QUANTITY, cartObject.getProductQuantity());
        values.put(TOTAL_COST, cartObject.getTotalCost());
        values.put(PHOTO_ID, cartObject.getPhotoID());

        System.out.println(cartObject);

        return values;
    }


    public Wishlist findWish(Cursor cursor){
        Wishlist cartObject = new Wishlist();
        if (cursor.moveToFirst()) {
            cursor.moveToFirst();
            cartObject.setCartID(cursor.getInt(0));
            cartObject.setProductID(cursor.getInt(1));
            cartObject.setProductName(cursor.getString(2));
            cartObject.setProductCost(cursor.getDouble(3));
            cartObject.setDeliveryCost(cursor.getDouble(4));
            cartObject.setProductQuantity(cursor.getInt(5));
            cartObject.setTotalCost(cursor.getDouble(6));
            cartObject.setPhotoID(cursor.getInt(7));
            cursor.close();
        }else{
            return null;
        }

        return cartObject;
    }

    public List<Wishlist> loadWish(Cursor cursor){
        List<Wishlist> cartList = new ArrayList<>();

        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            Wishlist cartObject = new Wishlist();
            cartObject.setCartID(cursor.getInt(0));
            cartObject.setProductID(cursor.getInt(1));
            cartObject.setProductName(cursor.getString(2));
            cartObject.setProductCost(cursor.getDouble(3));
            cartObject.setDeliveryCost(cursor.getDouble(4));
            cartObject.setProductQuantity(cursor.getInt(5));
            cartObject.setTotalCost(cursor.getDouble(6));
            cartObject.setPhotoID(cursor.getInt(7));
            cartList.add(cartObject);

            System.out.println("reading from getallcontents!!!!!!!!!!!");
            cursor.moveToNext();
        }

        return cartList;

    }
}
