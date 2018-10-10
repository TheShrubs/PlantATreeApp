package com.example.theshrubs.plantatree.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import com.example.theshrubs.plantatree.R;
import com.example.theshrubs.plantatree.database.DatabaseHelper;
import com.example.theshrubs.plantatree.models.Tree;

import java.util.ArrayList;

public class LandingPageActivity extends AppCompatActivity {

    private ListView landingList;
    private LandingPageAdapter landingAdapter;
    private List<Tree> treeList;
    private DatabaseHelper dbHandler;
    private int currentUser;
    private EditText searchKeyword;
    private ImageView searchButton;
    private boolean sort;
    private Button sortButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.landing_page);


        if (savedInstanceState == null) {
            Bundle extras = getIntent().getExtras();
            if (extras == null) {
                System.out.println("Bundle extra was NULL user");
            } else {
                currentUser = extras.getInt("USER_ID");
                sort = false;
                sort = extras.getBoolean("sort");
            }
        }

        //adding bottom naviation view
        BottomNavigationView bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottom_naviation);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()) {
                    case R.id.action_home:
                        // Toast.makeText(LandingPageActivity.this, "Home Action Clicked", Toast.LENGTH_SHORT).show();
                        //finish();
                        startActivity(getIntent());
                        break;
//                    case R.id.action_search:
//                        Toast.makeText(LandingPageActivity.this, "Search Action Clicked", Toast.LENGTH_SHORT).show();
//                        break;
                    case R.id.action_wishlist:
                        //  Toast.makeText(LandingPageActivity.this, "WishList Action Clicked", Toast.LENGTH_SHORT).show();
                        Intent cartIntent = new Intent(LandingPageActivity.this, WishlistActivity.class);
                        cartIntent.putExtra("CART_ID", currentUser);
                        startActivity(cartIntent);
                        break;
                    case R.id.action_cart:
                        cartIntent = new Intent(LandingPageActivity.this, ShoppingCartActivity.class);
                        cartIntent.putExtra("CART_ID", currentUser);
                        startActivity(cartIntent);
                        //Toast.makeText(LandingPageActivity.this, "Cart Action Clicked", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.action_account:
                        //   Toast.makeText(LandingPageActivity.this, "Account Action Clicked", Toast.LENGTH_SHORT).show();
                        break;
                }

                return true;
            }
        });

        this.dbHandler = new DatabaseHelper(this);
        dbHandler.populateDatabase();

        treeList = new ArrayList<>();
        List<Object> objectList = dbHandler.loadAllContents(1, "Landing");
        landingList = (ListView) findViewById(R.id.treeList);

        for (int i = 0; i < objectList.size(); i++) {
            Tree model = (Tree) objectList.get(i);
            treeList.add(model);
        }
        configureSortButton();
        sortTrees(sort);

        landingAdapter = new LandingPageAdapter(this, treeList, currentUser);
        landingList.setAdapter(landingAdapter);

        searchKeyword = (EditText) findViewById(R.id.search_text);
        searchButton = (ImageView) findViewById(R.id.search_tree);

//        searchButton.setOnClickListener(this);

        searchKeyword.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                landingAdapter.filter(s.toString().trim(), dbHandler);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

    }

    public void loadLandingView(List<Object> objectList) {

        landingList = (ListView) findViewById(R.id.treeList);

        for (int i = 0; i < objectList.size(); i++) {
            Tree model = (Tree) objectList.get(i);
            treeList.add(model);
        }

        landingAdapter = new LandingPageAdapter(this, treeList, currentUser);
        landingList.setAdapter(landingAdapter);
    }

    //toggles the price based sort of tree list and refreshes view
    public void configureSortButton() {
        sortButton = findViewById(R.id.priceSortButton);
        sortButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               if(sort){
                   sort = false;
                   Intent cartIntent = new Intent(LandingPageActivity.this, LandingPageActivity.class);
                   cartIntent.putExtra("USER_ID", currentUser);
                   cartIntent.putExtra("sort", sort);
                   startActivity(cartIntent);
               } else {
                   sort = true;
                   Intent cartIntent = new Intent(LandingPageActivity.this, LandingPageActivity.class);
                   cartIntent.putExtra("USER_ID", currentUser);
                   cartIntent.putExtra("sort", sort);
                   startActivity(cartIntent);
               }
            }
        });
    }

    //sorts the list of trees by price in descending order if condition is met
    public void sortTrees(boolean sort) {
        if (sort) {
            Collections.sort(treeList, new Comparator<Tree>() {
                @Override
                public int compare(Tree t1, Tree t2) {
                    return Double.compare(t1.getPrice(), t2.getPrice());
                }
            });

        }
    }

//    @Override
//    public void onClick(View v) {
//
//        String keyword = searchKeyword.getText().toString().trim();
//        landingAdapter.filter(keyword);
//
//    }
}