package com.example.theshrubs.plantatree.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.theshrubs.plantatree.R;


public class AddressActivity extends AppCompatActivity {

    boolean delivery;
    int USER_ID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_address);
        delivery = false;

        //gets the passed value of the products in users cart from shoppingCartActivity
        Bundle b = getIntent().getExtras();
        Bundle extras = getIntent().getExtras();
        USER_ID = extras.getInt("CART_ID");
        configureContinueButton();
        pickUpButtonPressed();
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
                extras.putBoolean("delivery",delivery);
                extras.putInt("CART_ID", USER_ID);
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
                delivery = true;
            }
        });
    }

    public void pickUpButtonPressed(){
        Button pickupButton = (Button) findViewById(R.id.PickupButton);
        pickupButton.setOnClickListener(new View.OnClickListener() {
            @Override

            //Ends this activity and returns to previous activity
            public void onClick(View view){
                Toast.makeText(getApplicationContext(), "You have requested to pick up in store", Toast.LENGTH_SHORT).show();
                //shipping is flat rate of 39.99 else no charge for pick up
                delivery = false;
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
