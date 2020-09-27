package com.example.friendsfeed.postpackage;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.MenuItem;

import com.example.friendsfeed.R;
import com.example.friendsfeed.fragment.CreatePostFragment;
import com.example.friendsfeed.fragment.FavoriteFragment;
import com.example.friendsfeed.fragment.MoreProfileFragment;
import com.example.friendsfeed.fragment.SearchFragment;
import com.example.friendsfeed.postpackage.allpost.AllPostFragment;
import com.example.friendsfeed.profile.ProfileFragment;
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

        getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.frm_Home, AllPostFragment.newInstance())
                .commit();

    }


    private final BottomNavigationView.OnNavigationItemSelectedListener navListener =
            new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                    Fragment selectedFragment = null;

                    switch (item.getItemId()) {
                        case R.id.nav_Home:
                            selectedFragment = AllPostFragment.newInstance();
                            break;
                        case R.id.nav_Search:
                            selectedFragment = SearchFragment.newInstance("Param 1", "Param 2");
                            break;

                        case R.id.nav_Create_Post:
                            selectedFragment = CreatePostFragment.newInstance("param 1", "Param 2");
                            break;

                        case R.id.nav_Favorite:
                            selectedFragment = FavoriteFragment.newInstance("param 1", "Param 2");
                            break;

                        case R.id.nav_More:
                            //selectedFragment = MoreProfileFragment.newInstance("Param 1", "Param 2");
                            selectedFragment = ProfileFragment.newInstance();
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
