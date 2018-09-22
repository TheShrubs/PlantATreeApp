package com.example.theshrubs.plantatree.activity;

import android.content.Intent;
import android.os.Bundle;
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
    private static Product product;
    private Tree tree;
    private DatabaseHelper treeDB;

    private int currentViewedTree;
    private int currentUser;



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
        String name = itemName.getText().toString();
        double price = tree.getPrice();
        double shipCost = Double.valueOf(tree.getShippingCost());
        double cost =  tree.getPrice() + tree.getShippingCost();
        System.out.println("");

        this.product = new Product(tree.getTreeID(), tree.getTreeName(), tree.getPrice(), tree.getShippingCost(), cost, tree.getPhotoID());

        Intent intent = new Intent(ViewItemActivity.this, AddItemToCartActivity.class);
        intent.putExtra("USER_ID", currentUser);
        startActivity(intent);


    }

    public static Product getProduct(){
        return product;
    }
}
