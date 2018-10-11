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
import com.example.theshrubs.plantatree.models.Product;
import com.example.theshrubs.plantatree.models.ShoppingCart;
import com.example.theshrubs.plantatree.models.Tree;
import com.example.theshrubs.plantatree.models.User;
import com.example.theshrubs.plantatree.models.Wishlist;

import java.util.ArrayList;
import java.util.List;

public class
WishlistActivity extends AppCompatActivity implements View.OnClickListener{

    private List<Wishlist> cartObjectList;
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
    private Tree tree;
    private ShoppingCart currentCart = new ShoppingCart();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wishlist);
        configureClearButton();

        cartObjectList =  new ArrayList<Wishlist>();

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


        List<Object> objectList = new ArrayList<>();
        objectList = database.loadAllContents(USER_ID, "Wishlist");
        Wishlist cartObject = new Wishlist();
        for(int i = 0; i < objectList.size(); i++){
            cartObject = (Wishlist) objectList.get(i);
            cartObjectList.add(cartObject);
        }


        final ListView listView = (ListView) findViewById(R.id.productListView);
        listView.setAdapter(new WishlistAdapter(this, cartObjectList, USER_ID));

    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent(WishlistActivity.this, AddressActivity.class);
        intent.putExtra("TOTAL", total);
        intent.putExtra("USER_ID", USER_ID);
        startActivity(intent);

    }

    private void configureClearButton(){
        Button clearButton = findViewById(R.id.clearWishButton);
        clearButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view){
                database.clearWish();
                Toast.makeText(WishlistActivity.this, "Wishlist Cleared", Toast.LENGTH_SHORT).show();
                Intent cartIntent = new Intent(WishlistActivity.this, WishlistActivity.class);
                cartIntent.putExtra("USER_ID", USER_ID);
                startActivity(cartIntent);
            }
        });
    }


}
