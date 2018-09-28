package com.example.theshrubs.plantatree.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.example.theshrubs.plantatree.R;
import com.example.theshrubs.plantatree.database.DatabaseHelper;
import com.example.theshrubs.plantatree.models.ShoppingCart;
import com.example.theshrubs.plantatree.models.Tree;

import java.util.ArrayList;
import java.util.List;

public class ShoppingCartActivity extends AppCompatActivity implements View.OnClickListener{

    private List<ShoppingCart> cartObjectList = new ArrayList<>();
    private int USER_ID;
    private DatabaseHelper database;
    private TextView cartSubTotal;
    private TextView cartDelivery;
    private TextView cartDiscount;
    private TextView catTotalCost;
    private Button checkoutButton;
    private double subTotal;
    private double delivery;
//    private double discount;
    private double total;
    private int quantity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.shopping_cart);
        this.database = new DatabaseHelper(this);

        if(savedInstanceState == null){
            Bundle extras = getIntent().getExtras();
            if(extras == null){
                System.out.println("Bundle extra was NULL user");
            }else{
                USER_ID = extras.getInt("CART_ID");
            }
        }else{
            USER_ID = (Integer) savedInstanceState.getSerializable("CART_ID");
            System.out.println("savedInstance was NULL");
        }

        subTotal = 0;
        delivery = 0;
//        discount = 0;
        total = 0;
        quantity = 0;

        cartSubTotal = (TextView) findViewById(R.id.cart_subtotal);
        cartDelivery = (TextView) findViewById(R.id.cart_delivery);
        cartDiscount = (TextView) findViewById(R.id.cart_discount);
        catTotalCost = (TextView) findViewById(R.id.cart_totalcost);
        checkoutButton = (Button) findViewById(R.id.checkoutButton);
        checkoutButton.setOnClickListener(this);

        List<Object> objectList = new ArrayList<>();
        objectList = database.loadAllContents(USER_ID, "ShoppingCart");
        ShoppingCart cartObject = new ShoppingCart();
        for(int i = 0; i < objectList.size(); i++){
            cartObject = (ShoppingCart) objectList.get(i);
            setCosts(cartObject);

            System.out.println("ADDING " + cartObject.toString() + "to cart array");
            cartObjectList.add(cartObject);
        }

        final ListView listView = (ListView) findViewById(R.id.productListView);
        listView.setAdapter(new ShoppingCartAdapter(this, cartObjectList, USER_ID));

        double discount = 0.0;
        if(quantity >= 10){
            double beforeDiscount = subTotal + delivery;
            System.out.println("Before Discount " + beforeDiscount);
            discount = beforeDiscount * .25;
            System.out.println("CurrentDiscount" + discount);
        }
        double totalDelivery = quantity * delivery;
        total = (subTotal + delivery) - discount;
        cartSubTotal.setText("$" + String.valueOf(subTotal));
        cartDelivery.setText("$" +String.valueOf(totalDelivery));
        cartDiscount.setText("$" +String.format("%.2f", discount));
        catTotalCost.setText("$" +String.valueOf(total));

    }

    public void setCosts(ShoppingCart cart){
        subTotal = subTotal + cart.getTotalCost();
        delivery = delivery + cart.getDeliveryCost();
        quantity = quantity + cart.getProductQuantity();
    }


    @Override
    public void onClick(View v) {
        Intent intent = new Intent(ShoppingCartActivity.this, AddressActivity.class);
        intent.putExtra("TOTAL", total);
        intent.putExtra("USER_ID", USER_ID);
        startActivity(intent);

    }
}
