package com.example.theshrubs.plantatree.activity;



import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.theshrubs.plantatree.R;
import com.example.theshrubs.plantatree.database.DatabaseHelper;


public class OrderConfirmActivity extends AppCompatActivity {
    private DatabaseHelper database;
    private int orderNo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_confirm);
        this.database = new DatabaseHelper(this);

        configureBackButton();
        orderNo=createOrder();
        confirmMessage(orderNo);

        //empties user's cart
        database.clearCartTable();
    }

    // creates a okay button that returns to Cart Total Activity
    //TODO send user to home page/search page not cart at order completion
    private void configureBackButton(){
        Button nextButton = (Button) findViewById(R.id.confirmBackButton);
        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            //Ends this activity and returns to total cart activity
            public void onClick(View view){
                startActivity(new Intent(OrderConfirmActivity.this,LandingPageActivity.class));
            }
        });
    }

    // send users to total cart activity if they press android back button instead of payment activity
    //TODO send user to home page/search page not cart page at order completion
    @Override
    public void onBackPressed() {
        startActivity(new Intent(OrderConfirmActivity.this,LandingPageActivity.class));
    }

    //displays confirmation message to user with their invoice number
    private void confirmMessage(int orderNo){

        TextView confirmText = (TextView) findViewById(R.id.confirmText);
        confirmText.setText("Thank You! \nYour Order #" +orderNo +" has been placed successfully.");
    }

    //creates an invoice number
    public int createOrder(){
        //TODO insert order details into previous order table
        int orderNo =(int)((Math.random()*90000)+10000);
        return orderNo;
    }

    // constructor
    OrderConfirmActivity(){ }
}
