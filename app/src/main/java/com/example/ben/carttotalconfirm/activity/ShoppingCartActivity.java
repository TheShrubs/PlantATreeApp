package com.example.ben.carttotalconfirm.activity;

import android.app.ActionBar;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.ben.carttotalconfirm.R;

public class ShoppingCartActivity extends AppCompatActivity {

    //TODO remove
    Double productCost = 45.99;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getSupportActionBar().setTitle("Shopping Cart(placeholder)"); //  set actionbar title
        getSupportActionBar().setDisplayHomeAsUpEnabled(true); // add back arrow in action bar

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shopping_cart);

        configureContinueButton();
        configureBackButton();

    }

    // creates a continue button that connects to Payment Activity
    private void configureContinueButton() {
        Button nextButton = (Button) findViewById(R.id.ShoppingCartContinueButton);
        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override

            //start the Payment Activity and link the current activity to it. also pass the product value of the cart to the next activity
            public void onClick(View view) {
                Intent intent = new Intent(ShoppingCartActivity.this, AddressActivity.class);
                Bundle extras = new Bundle();
                extras.putDouble("productCost",productCost);
                intent.putExtras(extras);
                startActivity(intent);
            }
        });
    }

    // creates a back button that returns to ______
    private void configureBackButton() {
        Button backButton = (Button) findViewById(R.id.ShoppingCartBackButton);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override

            //Ends this activity and returns to previous activity
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(), "I'd Go back if I could", Toast.LENGTH_SHORT).show();
            }
        });
    }
}

