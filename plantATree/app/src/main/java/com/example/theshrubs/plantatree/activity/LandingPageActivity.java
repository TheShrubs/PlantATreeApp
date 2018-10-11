package com.example.theshrubs.plantatree.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;

import com.example.theshrubs.plantatree.R;
import com.example.theshrubs.plantatree.database.DatabaseHelper;
import com.example.theshrubs.plantatree.models.Tree;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class LandingPageActivity extends AppCompatActivity implements View.OnClickListener {

    private ListView landingList;
    private LandingPageAdapter landingAdapter;
    private List<Tree> treeList;
    private DatabaseHelper dbHandler;
    private int currentUser;
    private EditText searchKeyword;
    private boolean sort;
    private ImageView sortButton;
    private BottomNavigationView navigationView;
    private BottomNavigationMenu navigationControl;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.landing_page);

        Bundle extras = getIntent().getExtras();
        currentUser = extras.getInt("USER_ID");
        sort = extras.getBoolean("sort");

        navigationView = (BottomNavigationView) findViewById(R.id.landing_Navigation);
        navigationControl = new BottomNavigationMenu();
        navigationControl.getBottomNavigation(this, navigationView, currentUser);


        this.dbHandler = new DatabaseHelper(this);
        dbHandler.populateDatabase();

        treeList = new ArrayList<>();
        List<Object> objectList = dbHandler.loadAllContents(1, "Landing");
        landingList = (ListView) findViewById(R.id.treeList);

        for (int i = 0; i < objectList.size(); i++) {
            Tree model = (Tree) objectList.get(i);
            treeList.add(model);
        }

        sortButton = (ImageView) findViewById(R.id.priceSortButton);
        sortButton.setOnClickListener(this);
        sortTrees(sort);

        landingAdapter = new LandingPageAdapter(this, treeList, currentUser);
        landingList.setAdapter(landingAdapter);

        sortTrees(sort);
        searchKeyword = (EditText) findViewById(R.id.search_text);


        searchKeyword.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) { }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                landingAdapter.filter(s.toString().trim(), dbHandler);
            }

            @Override
            public void afterTextChanged(Editable s) { }
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

    @Override
    public void onClick(View v) {
        if (sort) {
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

}