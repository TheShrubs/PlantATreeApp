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
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.Toast;

import java.util.ArrayList;
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


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.landing_page);

        Bundle extras = getIntent().getExtras();
        currentUser = extras.getInt("USER_ID");

        BottomNavigationView bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottom_naviation);
        BottomNavigationActivity.getBottomNavigation(this, bottomNavigationView, currentUser);
        this.dbHandler = new DatabaseHelper(this);
        dbHandler.populateDatabase();

        treeList = new ArrayList<>();
        List<Object> objectList = dbHandler.loadAllContents(1, "Landing");
        landingList = (ListView) findViewById(R.id.treeList);

        for (int i = 0; i < objectList.size(); i++) {
            Tree model = (Tree) objectList.get(i);
            treeList.add(model);
        }

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

//    @Override
//    public void onClick(View v) {
//
//        String keyword = searchKeyword.getText().toString().trim();
//        landingAdapter.filter(keyword);
//
//    }
}