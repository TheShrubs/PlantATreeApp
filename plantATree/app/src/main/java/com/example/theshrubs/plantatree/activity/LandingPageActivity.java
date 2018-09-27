package com.example.theshrubs.plantatree.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.SearchView;

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


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.landing_page);

        this.dbHandler = new DatabaseHelper(this);
//        dbHandler.populateDatabase();

        treeList = new ArrayList<>();
        List<Object> objectList = dbHandler.loadAllContents(1, "Landing");
        landingList = (ListView) findViewById(R.id.treeList);

        for (int i = 0; i < objectList.size(); i++) {
            Tree model = (Tree) objectList.get(i);
            treeList.add(model);
        }

        if (savedInstanceState == null) {
            Bundle extras = getIntent().getExtras();
            if (extras == null) {
                currentUser = 1;
                System.out.println("Bundle extra was NULL user");
            } else {
                currentUser = extras.getInt("USER_ID");
            }
        }


        System.out.println("USER ID FROM LANDING PAGE IS " + currentUser);


        landingAdapter = new LandingPageAdapter(this, treeList, currentUser);
        landingList.setAdapter(landingAdapter);


    }

}