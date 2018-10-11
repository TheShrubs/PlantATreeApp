package com.example.theshrubs.plantatree.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

import com.example.theshrubs.plantatree.R;
import com.example.theshrubs.plantatree.database.DatabaseHelper;
import com.example.theshrubs.plantatree.models.Tree;
import com.example.theshrubs.plantatree.models.Wishlist;

import java.util.ArrayList;
import java.util.List;

public class WishListActivity extends AppCompatActivity implements View.OnClickListener {

    private List<Wishlist> wishListObjects;
    private int USER_ID;
    private DatabaseHelper database;
    private BottomNavigationView navigationView;
    private BottomNavigationMenu navigationControl;
    private Tree tree;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.wishlist);

        wishListObjects = new ArrayList<>();
        this.database = new DatabaseHelper(this);

        Bundle extras = getIntent().getExtras();
        USER_ID = extras.getInt("USER_ID");

        navigationView = (BottomNavigationView) findViewById(R.id.wishlist_navigation);
        navigationControl = new BottomNavigationMenu();
        navigationControl.getBottomNavigation(this, navigationView, USER_ID);

        List<Object> objectList = database.loadAllContents(USER_ID, "Wishlist");
        for (int i = 0; i < objectList.size(); i++) {
            Wishlist object = (Wishlist) objectList.get(i);
            wishListObjects.add(object);
        }


        final ListView listView = (ListView) findViewById(R.id.productListView);
        listView.setAdapter(new WishlistAdapter(this, wishListObjects, USER_ID));

    }

    @Override
    public void onClick(View v) {
        database.clearWish();
        Toast.makeText(WishListActivity.this, "Wishlist Cleared", Toast.LENGTH_SHORT).show();
        Intent cartIntent = new Intent(WishListActivity.this, WishListActivity.class);
        cartIntent.putExtra("USER_ID", USER_ID);
        startActivity(cartIntent);

    }


}
