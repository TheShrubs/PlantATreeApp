package com.example.theshrubs.plantatree.activity;

import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.example.theshrubs.plantatree.R;
import com.example.theshrubs.plantatree.database.DatabaseHelper;
import com.example.theshrubs.plantatree.models.User;

public class UserProfileActivity extends AppCompatActivity {

    private int currentUser;
    private BottomNavigationMenu navigationControl;
    private BottomNavigationView navigationView;
    private DatabaseHelper dbHelper;
    private User user;
    private TextView username;
    private TextView userEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_profile);

        Bundle extras = getIntent().getExtras();
        currentUser = extras.getInt("USER_ID");

        navigationView = (BottomNavigationView) findViewById(R.id.profile_Navigation);
        navigationControl = new BottomNavigationMenu();
        navigationControl.getBottomNavigation(this, navigationView, currentUser);

        this.dbHelper = new DatabaseHelper(this);
        this.user = (User) dbHelper.findHandle(currentUser, "User");

        this.username = (TextView) findViewById(R.id.profile_name);
        this.userEmail = (TextView) findViewById(R.id.profile_email);

        this.username.setText("Name: " + user.getUserName());
        this.userEmail.setText("Email: " + user.getUserEmail());
    }
}
