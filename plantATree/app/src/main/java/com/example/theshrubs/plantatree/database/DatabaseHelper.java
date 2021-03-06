package com.example.theshrubs.plantatree.database;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.theshrubs.plantatree.R;
import com.example.theshrubs.plantatree.activity.ViewItemActivity;
import com.example.theshrubs.plantatree.models.Address;
import com.example.theshrubs.plantatree.models.ShoppingCart;
import com.example.theshrubs.plantatree.models.Tree;
import com.example.theshrubs.plantatree.models.User;
import com.example.theshrubs.plantatree.models.Wishlist;

import java.util.ArrayList;
import java.util.List;


public class DatabaseHelper extends SQLiteOpenHelper {
    //information of database
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "PLANTATREE";
    //table names
    private static final String TREE_TABLE = "Trees";
    private static final String USER_TABLE = "User";
    private static final String ADDRESS_TABLE = "Address";
    private static final String BILLING = "Billing";
    private static final String CART_TABLE = "ShoppingCart";
    private static final String WISH_TABLE = "Wishlist";

    private static final String USER_ID = "UserID";
    private static final String USER_NAME = "UserName";
    private static final String USER_EMAIL = "Email";
    private static final String USER_PASSWORD = "Password";


    private TreeTable treeTable = new TreeTable();
    private WishTable wishTable = new WishTable();
    private CartTable cartTable = new CartTable();
    private UserTable userTable = new UserTable();
    private AddressTable addressTable = new AddressTable();

    private String username;
    private String password;

    //initialize the database
    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(treeTable.createTreeTable(TREE_TABLE));
        db.execSQL(wishTable.createWishTable(WISH_TABLE));
        db.execSQL(cartTable.createCartTable(CART_TABLE));
        db.execSQL(userTable.createTreeTable(USER_TABLE));
        db.execSQL(addressTable.createAddressTable(ADDRESS_TABLE));
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
        } else if (object instanceof User) {
            User userObject = (User) object;
            tableName = USER_TABLE;
            System.out.println("INSERTED " + userObject.toString());
            values = userTable.addNewUser(userObject);
        } else if (object instanceof Address) {
            Address addressObject = (Address) object;
            tableName = ADDRESS_TABLE;
            values = addressTable.getAddressContents(addressObject);
        } else if (object instanceof Wishlist) {
            Wishlist wishlistObject = (Wishlist) object;
            tableName = WISH_TABLE;
            values = wishTable.getWishContents(wishlistObject);
        }

        SQLiteDatabase db = this.getWritableDatabase();
        long i = db.insert(tableName, null, values);


        if (i == -1) {
            createSuccessful = false;
            System.out.println("could not populate");
        } else {
            createSuccessful = true;
            System.out.println("table populated");
        }
        System.out.println(createSuccessful);
        db.close();

        return createSuccessful;
    }


    public Object findHandle(int id, String tableName) {
        Object obj;
        String query = "";
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor;


        switch (tableName) {
            case "Tree":
                query = "Select * FROM " + TREE_TABLE + " WHERE TreeID" + " = " + "'" + id + "'";
                cursor = getReadableDatabase().rawQuery(query, null);
                Tree foundTree = treeTable.findTree(cursor);
                obj = (Object) foundTree;

                break;
            case "Cart":
                query = "Select * FROM " + CART_TABLE + " WHERE UserID" + " = " + "'" + id + "'";
                System.out.println(query);
                cursor = getReadableDatabase().rawQuery(query, null);
                ShoppingCart foundCart = cartTable.findCart(cursor);
                obj = (Object) foundCart;
                break;
            case "Wish":
                query = "Select * FROM " + WISH_TABLE + " WHERE UserID" + " = " + "'" + id + "'";
                System.out.println(query);
                cursor = getReadableDatabase().rawQuery(query, null);
                Wishlist wishCart = wishTable.findWish(cursor);
                obj = (Object) wishCart;
                break;
            case "User":
                query = "Select * FROM " + USER_TABLE + " WHERE UserID = '" + id + "'";
                System.out.println("User instance " + query);
                cursor = getReadableDatabase().rawQuery(query, null);
                User currentUser = userTable.findUser(cursor);
                obj = (Object) currentUser;
                break;
            case "findExistingUser":
                query = "Select * FROM " + USER_TABLE + " WHERE Email = '" + username + "' AND Password = '" + password + "'";
                System.out.println("find existng user " + query);
                cursor = getReadableDatabase().rawQuery(query, null);
                User foundUser = userTable.findUser(cursor);
                obj = (Object) foundUser;
                break;
            case "Address":
                query = "Select * FROM " + ADDRESS_TABLE + " WHERE UserID = '" + id + "'";
                cursor = getReadableDatabase().rawQuery(query, null);
                Address foundAddress = addressTable.findAddress(cursor);
                obj = (Object) foundAddress;
                break;
            case "ExistingWishlist":
                query = "Select * FROM " + WISH_TABLE + " WHERE UserID = '" + id + "' AND ProductID = '" + ViewItemActivity.getProduct().getProductID() + "'";
                cursor = getReadableDatabase().rawQuery(query, null);
                Wishlist wishlist = wishTable.findWish(cursor);
                System.out.println("in db helper lookin for wish list");
                obj = (Object) wishlist;
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


    public List<Object> loadAllContents(int id, String tableName) {
        List<Object> objectList = new ArrayList<>();

        String query = "";
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = null;

        switch (tableName) {
            case "ShoppingCart":
                query = "Select * FROM " + CART_TABLE + " WHERE UserID" + " = " + "'" + id + "'";
                cursor = getReadableDatabase().rawQuery(query, null);
                List<ShoppingCart> cartList = cartTable.loadCart(cursor);
                objectList = (List<Object>) (Object) cartList;
                break;
            case "Landing":
                query = "Select * FROM " + TREE_TABLE;// + " WHERE TreeID" + " = " + "'" + id + "'";
                cursor = getReadableDatabase().rawQuery(query, null);
                List<Tree> treeList = treeTable.loadTrees(cursor);
                objectList = (List<Object>) (Object) treeList;
                break;
            case "Wishlist":
                // db.execSQL(wishTable.createCartTable(WISH_TABLE));
                query = "Select * FROM " + WISH_TABLE + " WHERE UserID" + " = " + "'" + id + "'";
                cursor = getReadableDatabase().rawQuery(query, null);
                List<Wishlist> wishList = wishTable.loadWish(cursor);
                objectList = (List<Object>) (Object) wishList;
                break;


        }

        return objectList;
    }

    public void deleteHandle(String tableName, int id) {
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "";

        switch (tableName) {
            case "ShoppingCart":
                query = "DELETE FROM " + tableName + " WHERE ProductID = '" + id + "'";
                break;
            case "Wishlist" :
                query = "DELETE FROM " + tableName + " WHERE ProductID = '" + id + "'";
        }


        db.execSQL(query);
        db.close();
    }

    public void clearCartTable() {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("DELETE FROM " + CART_TABLE);
        db.close();
    }

    public void clearWish() {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("DELETE FROM " + WISH_TABLE);
        db.close();
    }

    public boolean search(int id, String tableName) {
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT * FROM " + tableName + " WHERE ProductID = " + id;
        Cursor cursor = db.rawQuery(query, null);

        if (cursor.getCount() <= 0) {
            cursor.close();
            db.close();
            return false;
        } else {
            cursor.close();
            db.close();
            return true;
        }
    }

    public void populateDatabase() {
        Tree tree1 = new Tree(0, "Austrian Pine", "The austrian pine is medium to large sized everdgreen, needle-leaved conifer.", "Non-flowering", 84.0, R.drawable.austrian_pine, R.drawable.tree1, R.drawable.drag1, 3, "High", "Low", "High", "High", "Low");
        Tree tree2 = new Tree(1, "Bristlecone Pine", "Pinus longaeva is a long-living species of bristlecone pine tree", "Non-Flowering", 54.5, R.drawable.bristlecone_pine, R.drawable.tree2, R.drawable.drag2, 2.9, "High", "High", "High", "High", "Low");
        Tree tree3 = new Tree(2, "White Ash", "A species of the ass tree native to eastern and central North America", "Flowering", 20.0, R.drawable.white_ash, R.drawable.tree3, R.drawable.drag3, 1.5, "High", "High", "High", "High", "High");
        Tree tree4 = new Tree(3, "Blue Spruce", "It is native to the Rocky Mountains o the United States", "Non-Flowering", 35.0, R.drawable.blue_spruce, R.drawable.tree4, R.drawable.drag4, 6, "High", "Low", "Medium", "High", "Low");
        Tree tree5 = new Tree(4, "Bonsai Cherry", "A cerry bonsai tree comes from a simle cerry seed", "Flowering", 15.0, R.drawable.bonsai_cherry, R.drawable.tree5, R.drawable.drag5, 2, "Low", "Low", "Medium", "High", "High");
        Tree tree6 = new Tree(5, "Honeycrisp Apple", "The Honeycrisp apple tree is compact (for small spaces) and exceptionally cold-hardy.", "Flowering", 69.0, R.drawable.tree_apple, R.drawable.tree6, R.drawable.drag6, 3.0, "Medium", "Medium", "High", "Medium", "Low");
        Tree tree7 = new Tree(6, "Red Maple", "A red maple tree gets its common name from its brilliant red foliage that become the focal point of the landscape in autumn.", "Flowering", 45.5, R.drawable.tree_maple, R.drawable.tree7, R.drawable.drag7, 2.5, "High", "Medium", "Medium", "High", "Low");
        Tree tree8 = new Tree(7, "White Oak", "Quercus alba, the white oak, is one of the preeminent hardwoods of eastern and central North America.", "Non-Flowering", 45.0, R.drawable.tree_oak, R.drawable.tree8, R.drawable.drag8, 4.5, "High", "High", "Medium", "Medium", "Low");
        Tree tree9 = new Tree(8, "Douglas Fir", "Boasting a pyramidal shape and blue to dark green needles,", "Non-FLowering", 55.0, R.drawable.tree_pine, R.drawable.tree9, R.drawable.drag9, 5.5, "High", "Low", "Low", "Low", "Medium");
        addHandle(tree1);
        addHandle(tree2);
        addHandle(tree3);
        addHandle(tree4);
        addHandle(tree5);
        addHandle(tree6);
        addHandle(tree7);
        addHandle(tree8);

        System.out.println("PASSED THROUGH POPULATE DATABASE");

    }

    public void setUser(String user, String pass) {
        this.username = user;
        this.password = pass;

    }

    public String getUserEmail() {
        return username;
    }
}
