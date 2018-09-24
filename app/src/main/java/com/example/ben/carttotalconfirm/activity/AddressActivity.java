package com.example.ben.carttotalconfirm.activity;

import android.app.ActionBar;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.ben.carttotalconfirm.R;

public class AddressActivity extends AppCompatActivity {

    Double deliveryCost =0.0;
    Double productCost;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getSupportActionBar().setTitle("Delivery Address(placeholder)"); //  set actionbar title
        getSupportActionBar().setDisplayHomeAsUpEnabled(true); // add back arrow in action bar
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_address);

        //gets the passed value of the products in users cart from shoppingCartActivity
        Bundle b = getIntent().getExtras();
        productCost = b.getDouble("productCost");

        configureContinueButton();
        configureBackButton();
        deliveryButtonPressed();

    }

    // creates a continue button that connects to Payment Activity and sends product & delivery cost to CartTotalsActivity
    private void configureContinueButton(){
        Button nextButton = (Button) findViewById(R.id.AddressContinueButton);
        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override

            //start the Payment Activity and link the current activity to it
            public void onClick(View view){
                Intent intent = new Intent(AddressActivity.this,CartTotalActivity.class);
                Bundle extras = new Bundle();
                extras.putDouble("productCost",productCost);
                extras.putDouble("deliveryCost",deliveryCost);

                intent.putExtras(extras);
                startActivity(intent);
            }
        });
    }

    // if button is selected delivery fee will be charged
    public void deliveryButtonPressed(){
        Button deliveryButton = (Button) findViewById(R.id.DeliveryButton);
        deliveryButton.setOnClickListener(new View.OnClickListener() {
            @Override

            //Ends this activity and returns to previous activity
            public void onClick(View view){
                Toast.makeText(getApplicationContext(), "You have requested delivery", Toast.LENGTH_SHORT).show();
                //shipping is flat rate of 39.99 else no charge for pick up
                deliveryCost = 39.99;
            }
        });
    }




    // creates a back button that returns to address Activity
    private void configureBackButton(){
        Button backButton = (Button) findViewById(R.id.AddressBackButton);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override

            //Ends this activity and returns to previous activity
            public void onClick(View view){
                finish();
            }
        });
    }









}
