package com.example.theshrubs.plantatree.activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.theshrubs.plantatree.R;
import com.example.theshrubs.plantatree.database.DatabaseHelper;
import com.example.theshrubs.plantatree.models.Product;
import com.example.theshrubs.plantatree.models.Tree;

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
    private static Product product;
    private Tree tree;
    private DatabaseHelper treeDB;
    private static Bitmap photo;

    private int currentViewedTree;
    private int currentUser;

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
        btnCamera = (ImageView) findViewById(R.id.btnCamera);

        if(savedInstanceState == null){
            Bundle extras = getIntent().getExtras();
            if(extras == null){
                currentViewedTree = 1;
                currentUser = 0;
                System.out.println("Bundle extra was NULL user");
            }else{
                currentViewedTree = extras.getInt("TREE_ID");
                currentUser = extras.getInt("USER_ID");
            }
        }


        shoppingCart.setOnClickListener(this);
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
            Intent i = new Intent(ViewItemActivity.this, ViewingTreeActivity.class);
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
            String name = itemName.getText().toString();
            double price = tree.getPrice();
            double shipCost = Double.valueOf(tree.getShippingCost());
            double cost =  tree.getPrice() + tree.getShippingCost();
            System.out.println("");

            this.product = new Product(tree.getTreeID(), tree.getTreeName(), tree.getPrice(), tree.getShippingCost(), cost, tree.getPhotoID());

            Intent intent = new Intent(ViewItemActivity.this, AddItemToCartActivity.class);
            intent.putExtra("USER_ID", currentUser);
            startActivity(intent);
        }else if (v.getId() == R.id.btnCamera){
            System.out.println("Camera was pressed!!!!!!!!");
            dispatchTakePictureIntent();
        }



    }

    public static Bitmap getBitmap(){
        return photo;
    }

    public static Product getProduct(){
        return product;
    }
}
