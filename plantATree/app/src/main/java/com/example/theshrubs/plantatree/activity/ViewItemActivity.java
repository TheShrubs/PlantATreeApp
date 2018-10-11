package com.example.theshrubs.plantatree.activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.theshrubs.plantatree.R;
import com.example.theshrubs.plantatree.database.DatabaseHelper;
import com.example.theshrubs.plantatree.models.Product;
import com.example.theshrubs.plantatree.models.Tree;
import com.example.theshrubs.plantatree.models.Wishlist;

public class ViewItemActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView itemDescription;
    private TextView itemName;
    private TextView itemPrice;
    private TextView maxHeight;
    private TextView sunExposure;
    private TextView soilDrainage;
    private TextView maintenanceReq;
    private ImageView itemPhoto;
    private ImageView shoppingCart;
    private ImageView btnCamera;
    private ImageView wishlist;
    private static Product product;
    private Tree tree;
    private DatabaseHelper treeDB;
    private static Bitmap photo;
    private boolean inWishlist;
    private BottomNavigationView navigationView;
    private BottomNavigationMenu navigationControl;

    private int currentViewedTree;
    private int currentUser;

    private Wishlist currentCart = new Wishlist();
    private DatabaseHelper dbHandler = new DatabaseHelper(this);

    static final int REQUEST_IMAGE_CAPTURE = 1;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_item);
        itemDescription = (TextView) findViewById(R.id.itemDescription);
        itemName = (TextView) findViewById(R.id.itemName);
        itemPrice = (TextView) findViewById(R.id.itemPrice);
        maxHeight = (TextView) findViewById(R.id.maxHeight);
        sunExposure = (TextView) findViewById(R.id.exposure);
        soilDrainage = (TextView) findViewById(R.id.soilDrainage);
        maintenanceReq = (TextView) findViewById(R.id.maintenance);
        itemPhoto = (ImageView) findViewById(R.id.photo);
        shoppingCart = (ImageView) findViewById(R.id.addToCart);
        wishlist = (ImageView) findViewById(R.id.addToWish);
        btnCamera = (ImageView) findViewById(R.id.btnCamera);

        Bundle extras = getIntent().getExtras();
        currentUser = extras.getInt("USER_ID");
        currentViewedTree = extras.getInt("TREE_ID");

        navigationView = (BottomNavigationView) findViewById(R.id.view_navigation);
        navigationControl = new BottomNavigationMenu();
        navigationControl.getBottomNavigation(this, navigationView, currentUser);



        shoppingCart.setOnClickListener(this);
        wishlist.setOnClickListener(this);
        btnCamera.setOnClickListener(this);

        treeDB = new DatabaseHelper(this);
//        treeDB.populateDatabase();


        Object obj = treeDB.findHandle(currentViewedTree, "Tree");
        tree = new Tree();
        tree = (Tree) obj;
        if (tree == null){
            System.out.println("table is null");
        }else {
            setInformation(tree);
        }
    }



    private void dispatchTakePictureIntent() {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
            startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            Bundle extras = data.getExtras();
            this.photo = (Bitmap) extras.get("data");
            System.out.println("eneterd the on activity result !!!!!!!!!");
            Intent i = new Intent(ViewItemActivity.this, VisualizationActivity.class);
            Object treeObject = (Object) tree;
            i.putExtra("TREE", currentViewedTree);
            startActivity(i);
        }
    }


    public void setInformation(Tree tree){
        itemName.setText("Name: " + tree.getTreeName());
        itemDescription.setText("Description: "+ tree.getTreeDescription());
        itemPrice.setText("Price: " + tree.getPrice());
        maxHeight.setText("Maximum Height at Maturity: " + tree.getMaxHeight());
        sunExposure.setText("Sun Exposure: " + tree.getSunExposure());
        soilDrainage.setText("Soil Drainage: " + tree.getSunExposure());
        maintenanceReq.setText("Maintenance Requirements: " + tree.getMaintenanceReq());
        itemPhoto.setImageResource(tree.getPhotoID());

    }

    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.addToCart){
//            String name = itemName.getText().toString();
//            double price = tree.getPrice();
//            double shipCost = Double.valueOf(tree.getShippingCost());
            double cost =  tree.getPrice() + tree.getShippingCost();
            System.out.println("");

            this.product = new Product(tree.getTreeID(), tree.getTreeName(), tree.getPrice(), tree.getShippingCost(), cost, tree.getPhotoID());

            Intent intent = new Intent(ViewItemActivity.this, AddItemToCartActivity.class);
            intent.putExtra("USER_ID", currentUser);
            startActivity(intent);

        }else if (v.getId() == R.id.btnCamera){
            System.out.println("Camera was pressed!!!!!!!!");
            dispatchTakePictureIntent();

        }else if(v.getId() == R.id.addToWish) {
           // Toast.makeText(ViewItemActivity.this, "Add to Wishlist", Toast.LENGTH_SHORT).show();
            String message;
            int type;
            String name = itemName.getText().toString();
            double price = tree.getPrice();
            double shipCost = Double.valueOf(tree.getShippingCost());
            double cost = tree.getPrice() + tree.getShippingCost();

            System.out.println("");

            this.product = new Product(tree.getTreeID(), tree.getTreeName(), tree.getPrice(), tree.getShippingCost(), cost, tree.getPhotoID());
            product.setQuantity(1);
            //check if User has a cart!
            Object foundCart = dbHandler.findHandle(currentUser, "Wish");
            //if user is NOT found - then it creates cart for user;
            if (foundCart == null) {
                currentCart.setCartID(currentUser);
                currentCart.setProductID(product.getProductID());
                currentCart.setProductName(product.getProductName());
                currentCart.setProductCost(product.getProductPrice());
                currentCart.setDeliveryCost(product.getShipping());
                currentCart.setTotalCost(product.getProductTotal());
                currentCart.setPhotoID(product.getPhotoID());
                currentCart.setProductQuantity(product.getQuantity());
                dbHandler.addHandle(currentCart);
                showCustomDialog(product.getProductName() + " added to Wishlist");
            }
            //if cart is found - it searches the cart to see if product exists in cart.
            else {
                currentCart = (Wishlist) foundCart;
                inWishlist =dbHandler.search(product.getProductID(),"Wishlist");
                if (inWishlist) {
                    showCustomDialog(product.getProductName() + " already added to Wishlist");
                }else{
                    currentCart.setCartID(currentUser);
                    currentCart.setProductID(product.getProductID());
                    currentCart.setProductName(product.getProductName());
                    currentCart.setProductCost(product.getProductPrice());
                    currentCart.setDeliveryCost(product.getShipping());
                    currentCart.setTotalCost(product.getProductTotal());
                    currentCart.setPhotoID(product.getPhotoID());
                    currentCart.setProductQuantity(product.getQuantity());
                    dbHandler.addHandle(currentCart);
                    showCustomDialog(product.getProductName() +" added to Wishlist");

                }

            }

        }
    }

    public void showCustomDialog(String message) {
        final android.support.v7.app.AlertDialog.Builder dialog = new android.support.v7.app.AlertDialog.Builder(this);

        dialog.setTitle("Add To Wishlist");

        dialog.setMessage(message);
        dialog.setNegativeButton("Continue Shopping",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        finish();
                    }
                });
        dialog.setPositiveButton("Wishlist", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Intent intent = new Intent(ViewItemActivity.this, WishlistActivity.class);
                intent.putExtra("CART_ID", currentUser);
                intent.putExtra("PAGE_ID","ViewItem");
                startActivity(intent);
            }
        });


        dialog.show();

    }


    public static Bitmap getBitmap(){
        return photo;
    }

    public static Product getProduct(){
        return product;
    }
}
