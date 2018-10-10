package com.example.theshrubs.plantatree.activity;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.theshrubs.plantatree.R;

public class BottomNavigationMenu {
    private int USER_ID;
    private Intent intent;

    public void getBottomNavigation(final AppCompatActivity activity, BottomNavigationView bottomNavigationView, int id) {
        USER_ID = id;
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()) {
                    case R.id.action_home:
//                        Toast.makeText(activity, "Home Action Clicked for " + USER_ID, Toast.LENGTH_SHORT).show();
                        intent = new Intent(activity, LandingPageActivity.class);
                        intent.putExtra("USER_ID", USER_ID);
                        activity.startActivity(intent);
                        break;
                    case R.id.action_wishlist:
                        Toast.makeText(activity, "WishList Action Clicked", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.action_cart:
                        intent = new Intent(activity, ShoppingCartActivity.class);
                        intent.putExtra("USER_ID", USER_ID);
                        activity.startActivity(intent);

                        break;
                    case R.id.action_account:
//                        Intent intent = new Intent(activity, UserProfileActivity.class);
                        activity.startActivity(intent);
                        break;
                }

                return true;
            }
        });
    }
}
