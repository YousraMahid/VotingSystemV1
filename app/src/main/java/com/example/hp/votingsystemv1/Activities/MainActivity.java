package com.example.hp.votingsystemv1.Activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;

import com.example.hp.votingsystemv1.Fragments.HomeFragment;
import com.example.hp.votingsystemv1.Fragments.NoteFragment;
import com.example.hp.votingsystemv1.Fragments.ProfileFragment;
import com.example.hp.votingsystemv1.R;
import com.squareup.picasso.Picasso;

import de.hdodenhof.circleimageview.CircleImageView;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    DrawerLayout drawer;
    ActionBarDrawerToggle toggle;
    NavigationView navigationView;




    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.home:
                    setTitle("Home");
                    HomeFragment fragmentTasks = new HomeFragment();
                    FragmentTransaction fragmentTransactionTasks = getSupportFragmentManager().beginTransaction();
                    fragmentTransactionTasks.replace(R.id.frame_layout, fragmentTasks);
                    fragmentTransactionTasks.commit();

                    return true;
                case R.id.notification:
                    setTitle("Notification");
                    NoteFragment fragmentQA = new NoteFragment();
                    FragmentTransaction fragmentTransactionQa = getSupportFragmentManager().beginTransaction();
                    fragmentTransactionQa.replace(R.id.frame_layout, fragmentQA);
                    fragmentTransactionQa.commit();

                    return true;
                case R.id.profile:
                    setTitle("Profile");
                    ProfileFragment fragmentCashOut = new ProfileFragment();
                    FragmentTransaction fragmentTransactionCashout = getSupportFragmentManager().beginTransaction();
                    fragmentTransactionCashout.replace(R.id.frame_layout, fragmentCashOut);
                    fragmentTransactionCashout.commit();

                    return true;
            }
            return false;
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


         drawer = findViewById(R.id.drawer_layout);
         toggle = new ActionBarDrawerToggle(
                this, drawer, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);


        BottomNavigationView navigation =findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        setTitle("Home");

        HomeFragment fragmentTasks = new HomeFragment();
        FragmentTransaction fragmentTransactionTasks = getSupportFragmentManager().beginTransaction();
        fragmentTransactionTasks.replace(R.id.frame_layout, fragmentTasks);
        fragmentTransactionTasks.commit();

    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (toggle.onOptionsItemSelected(item)){
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.nav_setting) {
            Intent intent = new Intent(MainActivity.this, SettingActivity.class);
            startActivity(intent);
        }else if (id == R.id.nav_about) {
            Intent intent = new Intent(MainActivity.this, AboutActivity.class);
            startActivity(intent);
        }else if (id == R.id.nav_logout) {
            SigninActivity.setUserAuthenticacity(this,false);

            startActivity(new Intent(this, SigninActivity.class));
            finish();
        }
        else if (id == R.id.nav_feedback) {
            startActivity(new Intent(this, FeedbackActivity.class));
            finish();
        } else if (id == R.id.nav_profile) {
            ProfileFragment proFragment = new ProfileFragment();
            FragmentTransaction fragmentTransactionTasks = getSupportFragmentManager().beginTransaction();
            fragmentTransactionTasks.replace(R.id.frame_layout, proFragment);
            fragmentTransactionTasks.commit();
        }else if (id == R.id.nav_notification) {
            NoteFragment noteFragment = new NoteFragment();
            FragmentTransaction fragmentTransactionTasks = getSupportFragmentManager().beginTransaction();
            fragmentTransactionTasks.replace(R.id.frame_layout, noteFragment);
            fragmentTransactionTasks.commit();
        }
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}