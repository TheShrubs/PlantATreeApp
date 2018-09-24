package com.example.ben.carttotalconfirm.activity;

import android.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.ben.carttotalconfirm.R;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;



public class OrderConfirmActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getSupportActionBar().setTitle("Invoice"); //  set actionbar title

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_confirm);
        configureBackButton();
        createOrder();

    }






    // creates a okay button that returns to Cart Total Activity
    //TODO send user to home page/search page not cart at order completion
    private void configureBackButton(){
        Button nextButton = (Button) findViewById(R.id.confirmBackButton);
        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            //Ends this activity and returns to total cart activity
            public void onClick(View view){
                startActivity(new Intent(OrderConfirmActivity.this,ShoppingCartActivity.class));
            }
        });
    }

    // send users to total cart activity if they press android back button instead of payment activity
    //TODO send user to home page/search page not cart page at order completion
    @Override
    public void onBackPressed() {
        startActivity(new Intent(OrderConfirmActivity.this,ShoppingCartActivity.class));
    }

    //
    private void confirmMessage(int orderNo){

        TextView confirmText = (TextView) findViewById(R.id.confirmText);
        confirmText.setText("Thank You! \nYour Order #" +orderNo +" has been placed successfully.");
    }

    private void createOrder(){
        //TODO insert order details into previous order table
        int orderNo =(int)((Math.random()*90000)+10000);
        confirmMessage(orderNo);
    }


}
