package com.example.theshrubs.plantatree.activity;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.theshrubs.plantatree.R;
import com.example.theshrubs.plantatree.database.DatabaseHelper;
import com.example.theshrubs.plantatree.models.PurchaseEmail;


public class OrderConfirmActivity extends AppCompatActivity implements View.OnClickListener {
    private DatabaseHelper database;
    private int orderNo;
    private String userEmail;
    private int USER_ID;
    private Button nextButton;
    private Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.order_confirmation);
        this.database = new DatabaseHelper(this);
        this.nextButton = (Button) findViewById(R.id.confirmBackButton);
        this.nextButton.setOnClickListener(this);

        Bundle extras = getIntent().getExtras();
        USER_ID = extras.getInt("USER_ID");
        orderNo = createOrder();
        confirmMessage(orderNo);

        //empties user's cart
        database.clearCartTable();
    }

    // send users to total cart activity if they press android back button instead of payment activity

    @Override
    public void onBackPressed() {
        this.intent = new Intent(OrderConfirmActivity.this, LandingPageActivity.class);
        startActivity(intent);
    }

    //displays confirmation message to user with their invoice number
    private void confirmMessage(int orderNo) {
        TextView confirmText = (TextView) findViewById(R.id.confirmText);
        confirmText.setText("Thank You! \nYour Order #" + orderNo + " has been placed successfully.");
        this.userEmail = database.getUserEmail();
        PurchaseEmail purchaseEmail = new PurchaseEmail(this, userEmail);
        purchaseEmail.execute();
    }

    //creates an invoice number
    public int createOrder() {
        int orderNo = (int) ((Math.random() * 90000) + 10000);
        return orderNo;
    }


    @Override
    public void onClick(View v) {

        this.intent = new Intent(OrderConfirmActivity.this, LandingPageActivity.class);
        intent.putExtra("USER_ID", USER_ID);
        startActivity(intent);
    }
}
