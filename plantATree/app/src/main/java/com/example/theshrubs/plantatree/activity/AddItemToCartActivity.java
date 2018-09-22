package com.example.theshrubs.plantatree.activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.theshrubs.plantatree.R;
import com.example.theshrubs.plantatree.database.DatabaseHelper;
import com.example.theshrubs.plantatree.models.Product;
import com.example.theshrubs.plantatree.models.ShoppingCart;
import com.example.theshrubs.plantatree.models.User;

public class AddItemToCartActivity extends AppCompatActivity implements View.OnClickListener{

    private TextView itemName;
    private TextView itemPrice;
    private TextView shipping;
    private TextView totalCost;
    private EditText quantity;
    private Button addItem;
    private Product productItem;
    private ImageView productImage;

    private DatabaseHelper dbHandler = new DatabaseHelper(this);
    private User currentUser = new User();
    private ShoppingCart currentCart = new ShoppingCart();

    private int currentViewedTree;
    private int currentUSER_ID;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_item_to_cart);

        itemName = (TextView) findViewById(R.id.treeName);
        itemPrice = (TextView) findViewById(R.id.treePrice);
        shipping = (TextView) findViewById(R.id.treeShip);
        totalCost = (TextView) findViewById(R.id.treeTotal);
        addItem = (Button) findViewById(R.id.treeBuyButton);
        quantity = (EditText) findViewById(R.id.itemQuantity);
        productImage = (ImageView) findViewById(R.id.addPhoto);
        addItem.setEnabled(false);

        addItem.setOnClickListener(this);
        setInformation(ViewItemActivity.getProduct());

        if(savedInstanceState == null){
            Bundle extras = getIntent().getExtras();
            if(extras == null){
                currentViewedTree = 1;
                currentUSER_ID = 1;
                System.out.println("Bundle extra was NULL user");
            }else{
                currentViewedTree = extras.getInt("TREE_ID");
                currentUSER_ID = extras.getInt("USER_ID");
            }

        }


        System.out.println("USER ID FROM ADD ITEM TO CART ACTIVITY IS " + currentUSER_ID);

        quantity.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                double quant = Double.parseDouble(quantity.getText().toString());
                double newTotal = (productItem.getProductPrice() + productItem.getShipping()) * quant;
                totalCost.setText("Total Cost: $" + newTotal);
                productItem.setProductTotal(newTotal);
                addItem.setEnabled(true);

            }
        });
    }

    public void setInformation(Product product) {
        productItem = product;
        itemName.setText("Name: " + product.getProductName());
        itemPrice.setText("Price: $" + product.getProductPrice());
        shipping.setText("Shipping Cost: $" + product.getShipping());
        totalCost.setText("Total Cost: $" + product.getProductTotal());
        productImage.setImageResource(product.getPhotoID());


    }
    @Override
    public void onClick(View v) {

        String message = productItem.getProductName() + " has been to cart. Proceed to shopping cart?";


        showCustomDialog(message);

    }

    public void showCustomDialog(String message){
        final android.support.v7.app.AlertDialog.Builder dialog = new android.support.v7.app.AlertDialog.Builder(this);
        dialog.setTitle("Add To Cart");
        dialog.setMessage(message);
        dialog.setNegativeButton("Continue Shopping",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        finish();
                    }
                });
        dialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

//
                Object foundUser = dbHandler.findHandle(currentUSER_ID,"User");
                if(foundUser == null){
//                    User newUser = new User("user", "user@gmail.com", "Upass");
//                    dbHandler.addHandle(newUser);
//                    currentUser = newUser;
                    System.out.println("user was empty");
                }else{
                    currentUser = (User) foundUser;
                }
                Object foundCart = dbHandler.findHandle(currentUser.getUserID(), "Cart");
                if(foundCart == null){
                    System.out.println("cart was NULL");
//                }
//                    currentCart.setCartID(currentUser.getUserID());
//                    currentCart.getProdproductItem.getProductID())
                        currentCart.setCartID(currentUser.getUserID());
                        currentCart.setProductID(productItem.getProductID());
                        currentCart.setProductName(productItem.getProductName());
                        currentCart.setDeliveryCost(productItem.getShipping());
                        currentCart.setTotalCost(productItem.getProductTotal());
                        dbHandler.addHandle(currentCart);



                }else{
                    currentCart = (ShoppingCart) foundCart;
                    if(currentCart.getProductID() != productItem.getProductID()){
                        currentCart.setCartID(currentUser.getUserID());
                        currentCart.setProductID(productItem.getProductID());
                        currentCart.setProductName(productItem.getProductName());
                        currentCart.setDeliveryCost(productItem.getShipping());
                        currentCart.setTotalCost(productItem.getProductTotal());
                        dbHandler.addHandle(currentCart);
                    }
                    else{
                        showDialog_existingProduct(productItem.getProductName() + " already exists in your cart");

                    }
                }

                Intent intent = new Intent(AddItemToCartActivity.this, ShoppingCartActivity.class);
                intent.putExtra("CART_ID", currentUser.getUserID());
                startActivity(intent);

            }
        });

        dialog.show();
    }
    public void showDialog_existingProduct(String message){
        final android.support.v7.app.AlertDialog.Builder dialog = new android.support.v7.app.AlertDialog.Builder(this);
        dialog.setTitle("Product Exists");
        dialog.setMessage(message);
        dialog.setPositiveButton("OK",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        finish();
                    }
                });

        dialog.show();
    }
}
