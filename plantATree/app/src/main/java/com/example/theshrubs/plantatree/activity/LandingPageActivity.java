package com.example.theshrubs.plantatree.activity;

import android.os.Bundle;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.ListView;

import com.example.theshrubs.plantatree.R;
import com.example.theshrubs.plantatree.database.DatabaseHelper;
import com.example.theshrubs.plantatree.models.Tree;

import java.util.ArrayList;
import java.util.List;

public class LandingPageActivity extends AppCompatActivity {

    private ListView landingList;
    private LandingPageAdapter landingAdapter;
    private List<Tree> treeList;
    private DatabaseHelper dbHandler;
    private int currentUser;
    private EditText searchKeyword;
    private BottomNavigationView navigationView;
    private BottomNavigationMenu navigationControl;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.landing_page);

        Bundle extras = getIntent().getExtras();
        currentUser = extras.getInt("USER_ID");

        navigationView = (BottomNavigationView) findViewById(R.id.landing_Navigation);
        navigationControl = new BottomNavigationMenu();
        navigationControl.getBottomNavigation(this, navigationView, currentUser);


        this.dbHandler = new DatabaseHelper(this);
//        dbHandler.populateDatabase();

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


}