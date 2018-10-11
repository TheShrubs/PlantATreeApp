package com.example.theshrubs.plantatree.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.theshrubs.plantatree.R;
import com.example.theshrubs.plantatree.database.DatabaseHelper;
import com.example.theshrubs.plantatree.models.ShoppingCart;

import java.util.List;


public class ShoppingCartActivity extends AppCompatActivity implements View.OnClickListener {
    private int USER_ID;
    private DatabaseHelper database;
    private TextView cartSubTotal;
    private TextView cartDelivery;
    private TextView cartDiscount;
    private TextView catTotalCost;
    private Button checkoutButton;
    private LinearLayout cartListView;
    private BottomNavigationMenu navigationControl;
    private BottomNavigationView navigationView;
    private TextView congratsMessage;

    private double subTotal;
    private double delivery;
    private double total;
    private int quantity;
    private int type;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.shopping_cart);
        this.database = new DatabaseHelper(this);

        Bundle extras = getIntent().getExtras();
        USER_ID = extras.getInt("CART_ID");

        navigationView = (BottomNavigationView) findViewById(R.id.shopping_Navigation);
        navigationControl = new BottomNavigationMenu();
        navigationControl.getBottomNavigation(this, navigationView, USER_ID);

        subTotal = 0;
        delivery = 0;
        total = 0;
        quantity = 0;
        type = 0;

        this.cartSubTotal = (TextView) findViewById(R.id.cart_subtotal);
        this.cartDelivery = (TextView) findViewById(R.id.cart_delivery);
        this.cartDiscount = (TextView) findViewById(R.id.cart_discount);
        this.catTotalCost = (TextView) findViewById(R.id.cart_totalcost);
        this.checkoutButton = (Button) findViewById(R.id.checkoutButton);
        this.cartListView = (LinearLayout) findViewById(R.id.cartListView);
        this.congratsMessage = (TextView) findViewById(R.id.congrats_message);
        checkoutButton.setOnClickListener(this);

        //pull all objects in user's cart from the database
        List<Object> objectList = database.loadAllContents(USER_ID, "ShoppingCart");
        inflateShoppingCart(objectList);

        calculateCosts();
        setMessage();


    }

    public void reCalculation(List<ShoppingCart> cartObjectList) {
        subTotal = 0;
        delivery = 0;
        total = 0;
        quantity = 0;
        for (int i = 0; i < cartObjectList.size(); i++) {
            setCosts(cartObjectList.get(i));
        }

        calculateCosts();
        setMessage();

    }

    public void setCosts(ShoppingCart cart) {
        subTotal = subTotal + cart.getTotalCost();
        delivery = delivery + cart.getDeliveryCost();
        quantity = quantity + cart.getProductQuantity();

    }

    public void calculateCosts() {
        double discount = 0.0;
        double totalDelivery = quantity * delivery;
        total = (subTotal + totalDelivery) - discount;
        type = 0;

        if (quantity >= 10 && quantity <= 24) {
            discount = total * .1;

            cartSubTotal.setText("Sub-Total:    $ " + String.valueOf(subTotal));
            cartDelivery.setText("Delivery:       $ " + String.format("%.2f", totalDelivery));
            cartDiscount.setText("Discount:     $ " + String.format("%.2f", discount));
            catTotalCost.setText("Total Cost:   $ " + String.format("%.2f", total));
            type = 1;

        } else if (quantity >= 25) {
            discount = total * .1;
            delivery = 0.0;

            cartSubTotal.setText("Sub-Total:    $ " + String.valueOf(subTotal));
            cartDelivery.setText("Delivery:        FREE DELIVERY!");
            cartDiscount.setText("Discount:     $ " + String.format("%.2f", discount));
            catTotalCost.setText("Total Cost:   $ " + String.format("%.2f", total));
            type = 2;
        }else{
            cartSubTotal.setText("Sub-Total:    $ " + String.valueOf(subTotal));
            cartDelivery.setText("Delivery:       $ " + String.format("%.2f", totalDelivery));
            cartDiscount.setText("Discount:     $ " + String.format("%.2f", discount));
            catTotalCost.setText("Total Cost:   $ " + String.format("%.2f", total));
        }



    }


    //sets adapter for linear layout
    public void inflateShoppingCart(List<Object> cartList) {

        ShoppingCartAdapter adapter = new ShoppingCartAdapter(this, cartList, USER_ID);
        for (int i = 0; i < adapter.getCount(); i++) {
            View view = adapter.getView(i, null, cartListView);
            cartListView.addView(view);
        }
    }

    public void refreshView(int id, int userID) {
        database.deleteHandle("ShoppingCart", id);
        System.out.println("ID FOR DELECTED HANDLE IS " + id);
        finish();
        Intent i = new Intent(ShoppingCartActivity.this, ShoppingCartActivity.class);
        i.putExtra("CART_ID", userID);
        startActivity(i);
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent(ShoppingCartActivity.this, AddressActivity.class);
        intent.putExtra("TOTAL", total);
        intent.putExtra("USER_ID", USER_ID);
        startActivity(intent);

    }

    private void setMessage() {
        switch (type) {
            case 0:
                congratsMessage.setText("");
                break;
            case 1:
                congratsMessage.setText("Congratulations, you have added more than 10 trees to the cart, earning you 10% off!");
                break;
            case 2:
                congratsMessage.setText("Congratulations, you have added more than 25 trees to the cart, earning your 10% off and FREE DELIVERY!!");
                break;
            default:
                congratsMessage.setText("");
                break;
        }

    }
}
