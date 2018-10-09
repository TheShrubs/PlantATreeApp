package com.example.theshrubs.plantatree.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;


import com.example.theshrubs.plantatree.database.DatabaseHelper;
import com.example.theshrubs.plantatree.models.ShoppingCart;

import java.util.ArrayList;
import java.util.List;

public class ShoppingCartActivity extends AppCompatActivity implements View.OnClickListener {

    private List<ShoppingCart> cartObjectList = new ArrayList<>();
    private int USER_ID;
    private DatabaseHelper database;
    private TextView cartSubTotal;
    private TextView cartDelivery;
    private TextView cartDiscount;
    private TextView catTotalCost;
    private Button checkoutButton;
    private LinearLayout cartListView;

    private double subTotal;
    private double delivery;
    private double total;
    private int quantity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.shopping_cart);
        this.database = new DatabaseHelper(this);

        Bundle extras = getIntent().getExtras();
//        USER_ID = extras.getInt("CART_ID");
        USER_ID = 3;

        BottomNavigationView bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottom_naviation);
        BottomNavigationActivity.getBottomNavigation(this, bottomNavigationView, USER_ID);

        subTotal = 0;
        delivery = 0;
        total = 0;
        quantity = 0;

        cartSubTotal = (TextView) findViewById(R.id.cart_subtotal);
        cartDelivery = (TextView) findViewById(R.id.cart_delivery);
        cartDiscount = (TextView) findViewById(R.id.cart_discount);
        catTotalCost = (TextView) findViewById(R.id.cart_totalcost);
        checkoutButton = (Button) findViewById(R.id.checkoutButton);
        this.cartListView = (LinearLayout) findViewById(R.id.shoppingCartList);
        checkoutButton.setOnClickListener(this);

        List<Object> objectList = new ArrayList<>();
        objectList = database.loadAllContents(USER_ID, "ShoppingCart");
        inflateShoppingCart(objectList);

        double discount = 0.0;
        if (quantity >= 10) {
            double beforeDiscount = subTotal + delivery;
            System.out.println("Before Discount " + beforeDiscount);
            discount = beforeDiscount * .25;
            System.out.println("CurrentDiscount" + discount);
        }
        double totalDelivery = quantity * delivery;
        total = (subTotal + delivery) - discount;
        cartSubTotal.setText("Sub-Total:    $" + String.valueOf(subTotal));
        cartDelivery.setText("Delivery:       $" + String.valueOf(totalDelivery));
        cartDiscount.setText("Discount:     $" + String.format("%.2f", discount));
        catTotalCost.setText("Total Cost:   $" + String.valueOf(total));

    }

    public void setCosts(ShoppingCart cart) {
        subTotal = subTotal + cart.getTotalCost();
        delivery = delivery + cart.getDeliveryCost();
        quantity = quantity + cart.getProductQuantity();
    }

    public void inflateShoppingCart(List<Object> cartList) {

        ShoppingCartAdapter adapter = new ShoppingCartAdapter(this, cartList, USER_ID);
        for (int i = 0; i < adapter.getCount(); i++) {
            View view = adapter.getView(i, null, cartListView);
            cartListView.addView(view);
        }
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent(ShoppingCartActivity.this, AddressActivity.class);
        intent.putExtra("TOTAL", total);
        intent.putExtra("USER_ID", USER_ID);
        startActivity(intent);

    }

}
