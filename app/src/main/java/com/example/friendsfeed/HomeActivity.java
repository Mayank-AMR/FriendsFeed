package com.example.friendsfeed;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.MenuItem;

import com.example.friendsfeed.fragment.HomeFragment;
import com.example.friendsfeed.fragment.MyPostFragment;
import com.example.friendsfeed.viewModel.MyPostModel;
import com.google.android.material.bottomnavigation.BottomNavigationView;

/**
 * @Project FriendsFeed
 * @Created_by Mayank Kumar on 10-04-2020 08:00 PM
 */

public class HomeActivity extends AppCompatActivity {
    private static final String TAG = "HomeActivity";
    private MyPostModel mainModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
//        setContentView(R.layout.home_screen_main_view);

        if (getSupportActionBar() == null) {
            getSupportActionBar();
        }
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavigationView);
        bottomNavigationView.setOnNavigationItemSelectedListener(navListener);

    }


    private BottomNavigationView.OnNavigationItemSelectedListener navListener =
            new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                    Fragment selectedFragment = null;

                    switch (item.getItemId()) {
                        case R.id.nav_Home:
                            selectedFragment = new HomeFragment();
                            break;
                        case R.id.nav_Search:
                            //selectedFragment = new MyPostFragment();
                            break;
                        case R.id.nav_Me:
                            selectedFragment = new MyPostFragment();
                            break;
                    }
                    getSupportFragmentManager()
                            .beginTransaction()
                            .replace(R.id.frm_Home, selectedFragment)
                            .commit();
                    return true;
                }

            };

}
