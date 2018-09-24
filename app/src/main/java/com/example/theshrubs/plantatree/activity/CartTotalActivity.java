package com.example.theshrubs.plantatree.activity;



import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.theshrubs.plantatree.R;


public class CartTotalActivity extends AppCompatActivity {

    Double deliveryCost, productCost, invoiceTotal, discoutCost;


    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart_total);
        configureContinueButton();
        configureBackButton();

        // get values for product cost and total cost passed through intent from addressActivity
        Bundle b = getIntent().getExtras();
        productCost = b.getDouble("productCost");
        deliveryCost = b.getDouble("deliveryCost");
        //TODO implement discount calculation method
        discoutCost = 0.00;
        displayTotals();



    }

    private void displayTotals(){
        invoiceTotal = deliveryCost + productCost - discoutCost;
        TextView productText = (TextView) findViewById(R.id.ProductTotal);
        productText.setText("$"+productCost );
        TextView deliveryText = (TextView) findViewById(R.id.DeliveryTotal);
        deliveryText.setText("$"+deliveryCost);
        TextView InvoiceText = (TextView) findViewById(R.id.InvoiceTotal);
        InvoiceText.setText("$"+invoiceTotal);
        TextView DiscountText = (TextView) findViewById(R.id.DiscountTotal);
        DiscountText.setText("$"+discoutCost);


    }

    // creates a continue button that connects to Payment Activity
    private void configureContinueButton(){
        Button nextButton = (Button) findViewById(R.id.CartTotalContinueButton);
        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override

            //start the Payment Activity and link the current activity to it
            public void onClick(View view){
                startActivity(new Intent(CartTotalActivity.this,PaymentActivity.class));
            }
        });
    }

    // creates a back button that returns to address Activity
    private void configureBackButton(){
        Button backButton = (Button) findViewById(R.id.CartTotalBackButton);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override

            //Ends this activity and returns to previous activity
            public void onClick(View view){
                finish();
            }
        });
    }

}
