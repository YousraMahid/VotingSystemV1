package com.example.hp.votingsystemv1.Activities;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

import com.example.hp.votingsystemv1.Fragments.HelpFragment;
import com.example.hp.votingsystemv1.Fragments.HomeFragment;
import com.example.hp.votingsystemv1.Fragments.NoteFragment;
import com.example.hp.votingsystemv1.Fragments.ProFragment;
import com.example.hp.votingsystemv1.R;

public class MainActivity extends AppCompatActivity {

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
                    ProFragment fragmentCashOut = new ProFragment();
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
}