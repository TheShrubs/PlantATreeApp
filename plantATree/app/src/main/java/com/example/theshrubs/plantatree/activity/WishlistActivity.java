package com.example.theshrubs.plantatree.activity;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.theshrubs.plantatree.R;
import com.example.theshrubs.plantatree.database.DatabaseHelper;
import com.example.theshrubs.plantatree.models.ShoppingCart;
import com.example.theshrubs.plantatree.models.Tree;
import com.example.theshrubs.plantatree.models.Wishlist;

import java.util.ArrayList;
import java.util.List;

public class
WishlistActivity extends AppCompatActivity implements View.OnClickListener{

    private List<Wishlist> cartObjectList = new ArrayList<>();
    private int USER_ID;
    private DatabaseHelper database;
    private TextView cartSubTotal;
    private TextView cartDelivery;
    private TextView cartDiscount;
    private TextView catTotalCost;
    private Button checkoutButton;
        private double subTotal;
    private double delivery;
    //    private double discount;
    private double total;
    private int quantity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wishlist);
        configureClearButton();
        //configureAddButton();


        //adding bottom naviation view
        BottomNavigationView bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottom_naviation);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch(menuItem.getItemId()){
                    case R.id.action_home:
                       // Toast.makeText(WishlistActivity.this, "Home Action Clicked", Toast.LENGTH_SHORT).show();
                        Intent cartIntent = new Intent(WishlistActivity.this, LandingPageActivity.class);
                        cartIntent.putExtra("USER_ID", USER_ID);
                        startActivity(cartIntent);
                        break;
//                    case R.id.action_search:
//                        Toast.makeText(LandingPageActivity.this, "Search Action Clicked", Toast.LENGTH_SHORT).show();
//                        break;
                    case R.id.action_wishlist:
                       // Toast.makeText(WishlistActivity.this, "WishList Action Clicked", Toast.LENGTH_SHORT).show();
                        cartIntent = new Intent(WishlistActivity.this, WishlistActivity.class);
                        cartIntent.putExtra("USER_ID", USER_ID);
                        startActivity(cartIntent);
                        break;
                    case R.id.action_cart:
                       // Toast.makeText(WishlistActivity.this, "Cart Action Clicked", Toast.LENGTH_SHORT).show();
                        cartIntent = new Intent(WishlistActivity.this, ShoppingCartActivity.class);
                        cartIntent.putExtra("USER_ID", USER_ID);
                        startActivity(cartIntent);
                        break;
                    case R.id.action_account:
                      //  Toast.makeText(WishlistActivity.this, "Account Action Clicked", Toast.LENGTH_SHORT).show();
                        break;
                }

                return true;
            }
        });

        this.database = new DatabaseHelper(this);

        if(savedInstanceState == null){
            Bundle extras = getIntent().getExtras();
            if(extras == null){
                System.out.println("Bundle extra was NULL user");
            }else{
                USER_ID = extras.getInt("CART_ID");
            }
        }else{
            USER_ID = (Integer) savedInstanceState.getSerializable("CART_ID");
            System.out.println("savedInstance was NULL");
        }

        subTotal = 0;
        delivery = 0;
//        discount = 0;
        total = 0;
        quantity = 0;

    //    cartSubTotal = (TextView) findViewById(R.id.cart_subtotal);
      //  cartDelivery = (TextView) findViewById(R.id.cart_delivery);
      //  cartDiscount = (TextView) findViewById(R.id.cart_discount);
    //    catTotalCost = (TextView) findViewById(R.id.cart_totalcost);
      //  checkoutButton = (Button) findViewById(R.id.checkoutButton);
//        checkoutButton.setOnClickListener(this);

        List<Object> objectList = new ArrayList<>();
        objectList = database.loadAllContents(USER_ID, "Wishlist");
        Wishlist cartObject = new Wishlist();
        for(int i = 0; i < objectList.size(); i++){
            cartObject = (Wishlist) objectList.get(i);
            //setCosts(cartObject);

            System.out.println("ADDING " + cartObject.toString() + "to cart array");
            cartObjectList.add(cartObject);
        }

        final ListView listView = (ListView) findViewById(R.id.productListView);
        listView.setAdapter(new WishlistAdapter(this, cartObjectList, USER_ID));

        double discount = 0.0;
        if(quantity >= 10){
            double beforeDiscount = subTotal + delivery;
            System.out.println("Before Discount " + beforeDiscount);
            discount = beforeDiscount * .25;
            System.out.println("CurrentDiscount" + discount);
        }
        double totalDelivery = quantity * delivery;
        total = (subTotal + delivery) - discount;
//        cartSubTotal.setText("$" + String.format("%.2f",subTotal));
      //  cartDelivery.setText("$" +String.format("%.2f",totalDelivery));
     //   cartDiscount.setText("$" +String.format("%.2f", discount));
    //    catTotalCost.setText("$" +String.format("%.2f", total));

    }

    public void setCosts(ShoppingCart cart){
        subTotal = subTotal + cart.getTotalCost();
        delivery = delivery + cart.getDeliveryCost();
        quantity = quantity + cart.getProductQuantity();
    }


    @Override
    public void onClick(View v) {
        Intent intent = new Intent(WishlistActivity.this, AddressActivity.class);
        intent.putExtra("TOTAL", total);
        intent.putExtra("USER_ID", USER_ID);
        startActivity(intent);

    }

   // public void removeClickHandler(View view) {
        //get the row the clicked button is in
  //      RelativeLayout viewwParentRow = (RelativeLayout)view.getParent();
//
  //      TextView child = (TextView) viewwParentRow.getChildAt(0);
 //       Button btnChild = (Button) viewwParentRow.getChildAt(1);
 //       btnChild.setText(child.getText());
  //      btnChild.setText("CLICK");
   // }

    private void configureClearButton(){
        Button clearButton = findViewById(R.id.clearWishButton);
        clearButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view){
                database.clearWish();
                Intent cartIntent = new Intent(WishlistActivity.this, WishlistActivity.class);
                cartIntent.putExtra("USER_ID", USER_ID);
                startActivity(cartIntent);
            }
        });
    }


}
