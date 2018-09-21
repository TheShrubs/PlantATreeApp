package com.example.theshrubs.plantatree.database;


import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.content.Context;
import android.content.ContentValues;
import android.database.Cursor;

import com.example.theshrubs.plantatree.models.Product;
import com.example.theshrubs.plantatree.models.ShoppingCart;
import com.example.theshrubs.plantatree.models.Tree;
import com.example.theshrubs.plantatree.models.User;

import java.util.ArrayList;
import java.util.List;


public class DatabaseHelper extends SQLiteOpenHelper {
    //information of database
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "PLANTTREE";
    //table names
    private static final String TREE_TABLE = "Trees";
    private static final String USER_TABLE = "User";
    private static final String ADDRESS_TABLE = "Address";
    private static final String BILLING = "Billing";
    private static final String CART_TABLE = "ShoppingCart";


    private TreeTable treeTable = new TreeTable();
    private CartTable cartTable = new CartTable();
    private UserTable userTable = new UserTable();


    //initialize the database
    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(treeTable.createTreeTable(TREE_TABLE));
        db.execSQL(cartTable.createCartTable(CART_TABLE));
        db.execSQL(userTable.createTreeTable(USER_TABLE));
    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS " + TREE_TABLE);
        onCreate(db);
    }


    public boolean addHandle(Object object) {
        boolean createSuccessful = false;
        ContentValues values = null;
        String tableName = "";

        if (object instanceof Tree) {
            Tree treeObject = (Tree) object;
            tableName = TREE_TABLE;
            values = treeTable.getTreeContents(treeObject);
        } else if (object instanceof ShoppingCart) {
            ShoppingCart cartObject = (ShoppingCart) object;
            tableName = CART_TABLE;
            values = cartTable.getCartContents(cartObject);
        } else if(object instanceof User){
            User userObject = (User) object;
            tableName = USER_TABLE;
            values = userTable.addNewUser(userObject);
        }

        SQLiteDatabase db = this.getWritableDatabase();
        long i = db.insert(tableName, null, values);


        if (i == -1) {
            createSuccessful = false;
            System.out.println("could not populate");
        } else {
            createSuccessful = true;
            System.out.println("ITEM was added to " + tableName);
        }
        System.out.println(createSuccessful);
        db.close();

        return createSuccessful;
    }


    public Object findHandle(int id, String tableName) {
        Object obj = new Object();
        String query = "";
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = null;


        switch (tableName) {
            case "Tree":
                query = "Select * FROM " + TREE_TABLE + " WHERE TreeID" + " = " + "'" + id + "'";
                cursor = getReadableDatabase().rawQuery(query, null);
                Tree foundTree = treeTable.findTree(cursor);
                obj = (Object) foundTree;

                break;
            case "Cart":
                query = "Select * FROM " + CART_TABLE + " WHERE UserID" + " = " + "'" + id + "'";
                cursor = getReadableDatabase().rawQuery(query, null);
                ShoppingCart foundCart = cartTable.findCart(cursor); //foundTree = treeTable.findTree(cursor);
                obj = (Object) foundCart;
                break;
            case "User":
                query = "Select * FROM " + USER_TABLE + " WHERE UserID" + " = " + "'" + id + "'";
                cursor = getReadableDatabase().rawQuery(query, null);
                User foundUser = userTable.findUser(cursor);
                obj = (Object) foundUser;
                break;

            default:
                query = "Select * FROM " + TREE_TABLE + " WHERE TreeID" + " = " + "'" + id + "'";
                cursor = getReadableDatabase().rawQuery(query, null);
                foundTree = treeTable.findTree(cursor);
                obj = (Object) foundTree;
                break;
        }

        db.close();
        return obj;
    }


    public List<ShoppingCart> getAllContents(int id){
                   String query = "Select * FROM " + CART_TABLE + " WHERE UserID" + " = " + "'" + id + "'";
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = getReadableDatabase().rawQuery(query, null);

        List<ShoppingCart> cartList = new ArrayList<>();
        cartList = cartTable.loadCart(cursor);


        return cartList;
    }
//
//
//        public void populateDatabase() {
//        Tree tree1 = new Tree(0, "Austrian Pine", "The austrian pine is medium to large sized everdgreen, needle-leaved conifer.", "Non-flowering Flowers", 54.5, 45, 3, "High", "Medium", "High", "Low", "High");
//        Tree tree2 = new Tree(1, "Bristlecone Pine", "Pinus longaeva is a long-living species of bristlecone pine tree", "Non-Flowering Flowers", 54.5, 45, 2, "High", "High", "High", "High", "High");
//        Tree tree3 = new Tree(2, "White Ash", "A species of the ass tree native to eastern and central North America", "Flowering Flowers", 54.5, 45, 5, "High", "High", "High", "High", "High");
//        Tree tree4 = new Tree(3, "Blue Spruce", "It is native to the Rocky Mountains o the United States", "Non-Flowering Flowers", 54.5, 45, 6, "High", "High", "High", "High", "High");
//        Tree tree5 = new Tree(4, "Bonsai Cherry", "A cerry bonsai tree comes from a simle cerry seed", "Flowering Flowers", 54.5, 45, 2, "High", "High", "High", "High", "High");
////
//////
//        addHandle(tree1);
//        addHandle(tree2);
//        addHandle(tree3);
//        addHandle(tree4);
//        addHandle(tree5);
////
//        System.out.println("PASSED THROUGH POPULATE DATABASE");
////
//    }
}
