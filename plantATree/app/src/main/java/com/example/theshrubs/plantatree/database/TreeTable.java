package com.example.theshrubs.plantatree.database;

import android.content.ContentValues;
import android.database.Cursor;

import com.example.theshrubs.plantatree.models.Maintenance;
import com.example.theshrubs.plantatree.models.ShoppingCart;
import com.example.theshrubs.plantatree.models.Tree;

public class TreeTable {

    //Details for Tree table
    private static final String TREE_ID = "TreeID";
    private static final String TREE_NAME = "TreeName";
    private static final String TREE_DESCRIPTION = "TreeDescription";
    private static final String TREE_CATEGORY = "Category";
    private static final String TREE_PRICE = "Price";
    private static final String TREE_PHOTO = "Photo";
    private static final String TREE_SHIPPING_COST = "ShippingCost";
    private static final String TREE_MAX_HEIGHT = "MaxHeight";
    private static final String TREE_SOIL_DRAINAGE = "SoilDrainage";
    private static final String TREE_SUN_EXPOSURE = "SunExposure";
    private static final String TREE_GROWTH_RATE = "GrowthRate";
    private static final String TREE_MAINTENANCE_REQ = "MaintenanceRequirement";


    public String createTreeTable(String TABLE_NAME) {
        String createTable = "CREATE TABLE " + TABLE_NAME +
                "(" + TREE_ID + " INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL," +
                TREE_NAME + " NVARCHAR," +
                TREE_DESCRIPTION + " NVARCHAR," +
                TREE_CATEGORY + " INTEGER," +
                TREE_PRICE + " REAL," +
                TREE_PHOTO + " INTEGER," +
                TREE_SHIPPING_COST + " REAL," +
                TREE_MAX_HEIGHT + " NVARCHAR," +
                TREE_SOIL_DRAINAGE + " NVARCHAR," +
                TREE_SUN_EXPOSURE + " NVARCHAR," +
                TREE_GROWTH_RATE + " NVARCHAR," +
                TREE_MAINTENANCE_REQ + " NVARCHAR" + ");";

        return createTable;
    }

    public ContentValues getTreeContents(Tree treeObject) {
        ContentValues values = new ContentValues();
        values.put(TREE_ID, treeObject.getTreeID());
        values.put(TREE_NAME, treeObject.getTreeName());
        values.put(TREE_DESCRIPTION, treeObject.getTreeDescription());
        values.put(TREE_CATEGORY, treeObject.getCategory());
        values.put(TREE_PRICE, treeObject.getPrice());
        values.put(TREE_PHOTO, treeObject.getPhotoID());
        values.put(TREE_SHIPPING_COST, treeObject.getShippingCost());
        values.put(TREE_MAX_HEIGHT, treeObject.getMaxHeight());
        values.put(TREE_SOIL_DRAINAGE, treeObject.getSoilDrainage());
        values.put(TREE_SUN_EXPOSURE, treeObject.getSunExposure());
        values.put(TREE_GROWTH_RATE, treeObject.getGrowthRate());
        values.put(TREE_MAINTENANCE_REQ, treeObject.getMaintenanceReq());

        System.out.println("Tree table!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");

        return values;
    }




    public Tree findTree(Cursor cursor){
        Tree treeObject = new Tree();
        if (cursor.moveToFirst()) {
            cursor.moveToFirst();
            treeObject.setTreeID(cursor.getInt(0));
            treeObject.setTreeName(cursor.getString(1));
            treeObject.setTreeDescription(cursor.getString(2));
            treeObject.setCategory(cursor.getString(3));
            treeObject.setPrice(cursor.getDouble(4));
            treeObject.setPhotoID(cursor.getInt(5));
            treeObject.setShippingCost(cursor.getDouble(6));
            treeObject.setMaxHeight(cursor.getString(7));
            treeObject.setSoilDrainage(cursor.getString(8));
            treeObject.setSunExposure(cursor.getString(9));
            treeObject.setGrowthRate(cursor.getString(10));
            treeObject.setMaintenanceReq(cursor.getString(11));
            cursor.close();
        }else{
            return null;
        }

        return treeObject;
    }

}
