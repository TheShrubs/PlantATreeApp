package com.example.theshrubs.plantatree.database;

import android.content.ContentValues;
import android.database.Cursor;

import com.example.theshrubs.plantatree.models.ShoppingCart;

import java.util.ArrayList;
import java.util.List;

public class CartTable {

    //Details for Cart table
    private static final String CART_ID = "UserID";
    private static final String PRODUCT_ID = "ProductID";
    private static final String PRODUCT_NAME = "ProductName";
    private static final String PRODUCT_COST = "ProductCost";
    private static final String DELIVERY_COST = "DeliveryCost";
    private static final String QUANTITY = "Quantity";
    private static final String TOTAL_COST = "TotalCost";

    private double totalDelivery;
    private double totalCosting;



    public String createCartTable(String TABLE_NAME) {
        String createTable = "CREATE TABLE " + TABLE_NAME +
                "(" + CART_ID + " INTEGER NOT NULL," +
                PRODUCT_ID + " INTEGER," +
                PRODUCT_NAME + " NVARCHAR," +
                PRODUCT_COST + " REAL," +
                DELIVERY_COST + " REAL," +
                QUANTITY + " INTEGER," +
                TOTAL_COST + " REAL" +");";
//                "PRIMARY KEY (" + CART_ID + ", " + PRODUCT_ID +

        return createTable;
    }


    public ContentValues getCartContents(ShoppingCart cartObject) {
        ContentValues values = new ContentValues();
        values.put(CART_ID, cartObject.getCartID());
        values.put(PRODUCT_ID, cartObject.getProductID());
        values.put(PRODUCT_NAME, cartObject.getProductName());
        values.put(PRODUCT_COST, cartObject.getProductCost());
        values.put(DELIVERY_COST, cartObject.getDeliveryCost());
        values.put(QUANTITY, cartObject.getProductQuantity());
        values.put(TOTAL_COST, cartObject.getTotalCost());

        System.out.println("Cart table!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
        System.out.println("ITEM QUANTITY IS " + cartObject.getProductQuantity());

        return values;
    }


    public ShoppingCart findCart(Cursor cursor){
        ShoppingCart cartObject = new ShoppingCart();
        if (cursor.moveToFirst()) {
            cursor.moveToFirst();
            cartObject.setCartID(cursor.getInt(0));
            cartObject.setProductID(cursor.getInt(1));
            cartObject.setProductName(cursor.getString(2));
            cartObject.setProductCost(cursor.getDouble(3));
            cartObject.setDeliveryCost(cursor.getDouble(4));
            cartObject.setProductQuantity(cursor.getInt(5));
            cartObject.setTotalCost(cursor.getDouble(6));
            cursor.close();
        }else{
            return null;
        }

        return cartObject;
    }

    public List<ShoppingCart> loadCart(Cursor cursor){
        List<ShoppingCart> cartList = new ArrayList<>();
        ShoppingCart cartObject = new ShoppingCart();

        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            cartObject.setCartID(cursor.getInt(0));
            cartObject.setProductID(cursor.getInt(1));
            cartObject.setProductName(cursor.getString(2));
            cartObject.setProductCost(cursor.getDouble(3));
            cartObject.setDeliveryCost(cursor.getDouble(4));
            cartObject.setProductQuantity(cursor.getInt(5));
            cartObject.setTotalCost(cursor.getDouble(6));
            cartList.add(cartObject);
            totalCosting = totalCosting + cartObject.getTotalCost();
            totalDelivery = totalDelivery + cartObject.getDeliveryCost();

//            ShoppingCart

            cursor.moveToNext();
        }

        cartObject.setCartTotals(totalCosting, totalDelivery);

        return  cartList;
    }

}
