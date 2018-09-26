package com.example.theshrubs.plantatree.activity;



import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.theshrubs.plantatree.R;
import com.example.theshrubs.plantatree.database.DatabaseHelper;
import com.example.theshrubs.plantatree.models.ShoppingCart;

import java.util.ArrayList;
import java.util.List;


public class CartTotalActivity extends AppCompatActivity {

    Double deliveryCost, productCost, invoiceTotal, discoutCost;
    boolean delivery;
    private List<ShoppingCart> cartObjectList = new ArrayList<>();
    private int USER_ID;
    private DatabaseHelper database;


    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart_total);


        // get values for product cost and total cost passed through intent from addressActivity
        Bundle b = getIntent().getExtras();
        delivery = b.getBoolean("delivery");

        this.database = new DatabaseHelper(this);

        if(savedInstanceState == null){
            Bundle extras = getIntent().getExtras();
            if(extras == null){
                USER_ID = 1;
                System.out.println("Bundle extra was NULL user");
            }else{
                USER_ID = extras.getInt("CART_ID");
            }
        }else{
            USER_ID = (Integer) savedInstanceState.getSerializable("CART_ID");
            System.out.println("savedInstance was NULL");
        }

        List<Object> objectList = new ArrayList<>();
        objectList = database.loadAllContents(USER_ID, "ShoppingCart");
        ShoppingCart cartObject = new ShoppingCart();
        for(int i = 0; i < objectList.size(); i++) {
            cartObject = (ShoppingCart) objectList.get(i);

//            loading car
            System.out.println("ADDING " + cartObject.toString() + "to cart array");
            cartObjectList.add(cartObject);
        }

        calcTotals();
        calcDelivery();
        calcDiscount();
        displayTotals();
        configureContinueButton();
        configureBackButton();


    }

    //displays invoice totals to user
    private void displayTotals(){

       invoiceTotal = 0.00;

        invoiceTotal = (deliveryCost + productCost) + discoutCost;
       // Log.d("tag2", "inv cost: " + invoiceTotal);
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

    // iterates through User's shopping cart and gets the item and delivery totals
    private void calcTotals(){
        Double tempDel =0.00;
        Double tempProd = 0.00;
        for(int i=0; i< cartObjectList.size();i++) {
            tempDel +=(cartObjectList.get(i).getDeliveryCost()*cartObjectList.get(i).getProductQuantity());
            tempProd +=(cartObjectList.get(i).getTotalCost());
        }
        deliveryCost = tempDel;
        productCost = tempProd-tempDel;
    }

    // checks if user selected delivery on the previous Address Activity, if they did not there is no charge
    private void calcDelivery(){
        if(!delivery){
            deliveryCost = 0.00;
        }
    }

    //checks if user has more than 10 items in their cart, if they do they qualify for free shipping
    private void calcDiscount(){
        int totalItems=0;
        for(int i=0; i< cartObjectList.size();i++){
            totalItems += cartObjectList.get(i).getProductQuantity();
        }
        if(totalItems>=10)  {
            discoutCost = deliveryCost * -1;
            ((TextView)findViewById(R.id.discountDesc)).setText("Discount Applied: \n (Bulk purchase discount)");
        }else{
            discoutCost =0.00;
        }
    }

}
