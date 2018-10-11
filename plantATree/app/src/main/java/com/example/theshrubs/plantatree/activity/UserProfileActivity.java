package com.example.theshrubs.plantatree.activity;

import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.theshrubs.plantatree.R;

public class UserProfileActivity extends AppCompatActivity {

    private int currentUser;
    private BottomNavigationMenu navigationControl;
    private BottomNavigationView navigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);

        Bundle extras = getIntent().getExtras();
        currentUser = extras.getInt("USER_ID");

        navigationView = (BottomNavigationView) findViewById(R.id.profile_Navigation);
        navigationControl = new BottomNavigationMenu();
        navigationControl.getBottomNavigation(this, navigationView, currentUser);
    }
}
