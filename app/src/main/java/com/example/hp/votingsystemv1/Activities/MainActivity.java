package com.example.hp.votingsystemv1.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

import com.example.hp.votingsystemv1.Fragments.HelpFragment;
import com.example.hp.votingsystemv1.Fragments.HomeFragment;
import com.example.hp.votingsystemv1.Fragments.NoteFragment;
import com.example.hp.votingsystemv1.Fragments.ProfileFragment;
import com.example.hp.votingsystemv1.R;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    public void reference() {

    }

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
                case R.id.help:
                    setTitle("Help");
                    HelpFragment fragmentDiscuss = new HelpFragment();
                    FragmentTransaction fragmentTransactionDiscuss = getSupportFragmentManager().beginTransaction();
                    fragmentTransactionDiscuss.replace(R.id.frame_layout, fragmentDiscuss);
                    fragmentTransactionDiscuss.commit();

                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        reference();


        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        setTitle("Home");

        HomeFragment fragmentTasks = new HomeFragment();
        FragmentTransaction fragmentTransactionTasks = getSupportFragmentManager().beginTransaction();
        fragmentTransactionTasks.replace(R.id.frame_layout, fragmentTasks);
        fragmentTransactionTasks.commit();
    }
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_profile) {
        } else if (id == R.id.nav_notification) {
        } else if (id == R.id.nav_setting) {

        } else if (id == R.id.nav_help) {
        } else if (id == R.id.nav_feedback) {
        } else if (id == R.id.nav_about) {
        } else if (id == R.id.nav_logout) {
        }
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}