package com.example.friendsfeed;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

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
//    private BottomBar mBottomBar;

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
//        setupBottomBar();
//        FrameLayout frameLayout = (FrameLayout) findViewById(R.id.framelayout);
//        mBottomBar = findViewById(R.id.bottombar);
//        mBottomBar.setOnTabSelectListener(new OnTabSelectListener() {
//
//            @Override
//            public void onTabSelected(@IdRes int tabId) {
//                Fragment fragment = null;
//                switch (tabId) {
//                    case R.id.home:
//
//                        replace_fragment(new HomeFragment());
//                        break;
//
//
//                }
//
//
//            }
//        });


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
                            selectedFragment = new MyPostFragment();

                            //--Below code only for testing--------------
                            getSupportFragmentManager()
                            .beginTransaction()
                            .replace(R.id.frm_Home, selectedFragment)
                            .commit();
                            //------------------------------
                            break;
                        case R.id.nav_Me:
                            selectedFragment = new MyPostFragment();
                            startActivity(new Intent(HomeActivity.this,ChatActivity.class));
                            break;

                    }
//                    getSupportFragmentManager()
//                            .beginTransaction()
//                            .replace(R.id.frm_Home, selectedFragment)
//                            .commit();
                    //startActivity(new Intent(HomeActivity.this,ChatActivity.class));

                    return true;
                }

            };

//    private void setupBottomBar() {
//        mBottomBar = (BottomBar) findViewById(R.id.bottombar);
//
//        for (int i = 0; i < mBottomBar.getTabCount(); i++) {
//            BottomBarTab tab = mBottomBar.getTabAtPosition(i);
//            tab.setGravity(Gravity.CENTER);
//
//            View icon = tab.findViewById(com.roughike.bottombar.R.id.bb_bottom_bar_icon);
//            // the paddingTop will be modified when select/deselect,
//            // so, in order to make the icon always center in tab,
//            // we need set the paddingBottom equals paddingTop
//            icon.setPadding(0, icon.getPaddingTop(), 0, icon.getPaddingTop());
//
//            View title = tab.findViewById(com.roughike.bottombar.R.id.bb_bottom_bar_title);
//            title.setVisibility(View.GONE);
//        }
//    }
//    public void replace_fragment(Fragment fragment) {
//        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
//        transaction.replace(R.id.framelayout, fragment);
//        transaction.commit();
//    }

}
