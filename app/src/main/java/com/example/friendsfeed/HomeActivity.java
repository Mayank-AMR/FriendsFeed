package com.example.friendsfeed;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import com.example.friendsfeed.fragment.DescribeFragment;
import com.example.friendsfeed.fragment.FavoriteFragment;
import com.example.friendsfeed.fragment.MoreProfileFragment;
import com.example.friendsfeed.fragment.MyPostFragment;
import com.example.friendsfeed.fragment.ScheduleFragment;
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

        if (getSupportActionBar() == null) {
            // 5getSupportActionBar();
        }

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavigationView);
        bottomNavigationView.setOnNavigationItemSelectedListener(navListener);

    }


    private final BottomNavigationView.OnNavigationItemSelectedListener navListener =
            new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                    Fragment selectedFragment = null;

                    switch (item.getItemId()) {
                        case R.id.nav_Home:
                            selectedFragment = MyPostFragment.newInstance();
                            break;
                        case R.id.nav_Describe:
                            selectedFragment = DescribeFragment.newInstance("param 1", "Param 2");
                            break;

                        case R.id.nav_Schedule:
                            selectedFragment = ScheduleFragment.newInstance("param 1", "Param 2");
                            break;

                        case R.id.nav_Favorite:
                            selectedFragment = FavoriteFragment.newInstance("param 1", "Param 2");
                            break;

                        case R.id.nav_More:
                            selectedFragment = MoreProfileFragment.newInstance("parem 1", "parem 2");
                            break;
                    }
                    getSupportFragmentManager()
                            .beginTransaction()
                            .replace(R.id.frm_Home, selectedFragment)
                            .commit();
//                    startActivity(new Intent(HomeActivity.this,ChatActivity.class));

                    return true;
                }

            };

}
