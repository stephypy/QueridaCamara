package com.codepath.queridacamara;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import com.codepath.queridacamara.fragments.ComposeFragment;
import com.codepath.queridacamara.fragments.PostFragment;
import com.codepath.queridacamara.fragments.ProfileFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.parse.ParseUser;


public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    ParseUser currentUser;
    final FragmentManager fragmentManager = getSupportFragmentManager();
    BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bottomNavigationView = findViewById(R.id.bottom_navigation);
        currentUser = ParseUser.getCurrentUser();

        /*
        TODO: Move log out to profile fragment
        fabLogOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                logUserOut();
            }
        });
         */

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                Fragment fragment;
                switch (menuItem.getItemId()) {
                    case R.id.action_compose:
                        fragment = new ComposeFragment();
                        break;
                    case R.id.action_profile:
                        fragment = new ProfileFragment();
                        break;
                    // R.id.action_home
                    default:
                        fragment = new PostFragment();
                        break;
                }
                fragmentManager.beginTransaction().replace(R.id.flContainer, fragment).commit();
                return true;
            }
        });
        // Set default selection
        bottomNavigationView.setSelectedItemId(R.id.action_home);
    }

    private void logUserOut() {
        ParseUser.logOut();
        currentUser = ParseUser.getCurrentUser();
        Intent i = new Intent(this, LoginActivity.class);
        startActivity(i);
        finish();
    }
}
