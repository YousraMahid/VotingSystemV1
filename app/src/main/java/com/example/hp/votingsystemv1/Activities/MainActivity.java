package com.example.hp.votingsystemv1.Activities;

import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;

import com.example.hp.votingsystemv1.Fragments.BlankFragment;
import com.example.hp.votingsystemv1.Fragments.callsFragment;
import com.example.hp.votingsystemv1.Fragments.recentsFragment;
import com.example.hp.votingsystemv1.Fragments.tripsFragment;
import com.example.hp.votingsystemv1.R;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
//    private static final String TAG_FRAGMENT_CALLS = "tag_frag_calls";
//    private static final String TAG_FRAGMENT_RECENTS = "tag_frag_recents";
//    private static final String TAG_FRAGMENT_TRIPS = "tag_frag_trips";
    private BottomNavigationView bottomNavigationView;
//    private List<BlankFragment> fragments = new ArrayList<>(3);

    private Fragment fragment;
    private FragmentManager fragmentManager;

    public void reference(){
        bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottom_nav);
        fragmentManager = getSupportFragmentManager();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        reference();

        bottomNavigationView.setOnNavigationItemSelectedListener(
                new BottomNavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                        switch (item.getItemId()) {
                            case R.id.bottombaritem_calls:
                                fragment = new callsFragment();
//                                switchFragment(0, TAG_FRAGMENT_CALLS);
                                return true;
                            case R.id.bottombaritem_recents:
//                                switchFragment(1, TAG_FRAGMENT_RECENTS);
                                fragment = new recentsFragment();
                                return true;
                            case R.id.bottombaritem_trips:
//                               switchFragment(2, TAG_FRAGMENT_TRIPS);
                                fragment = new tripsFragment();
                                return true;
                        }
                        final FragmentTransaction transaction = fragmentManager.beginTransaction();
                        transaction.replace(R.id.main_container, fragment).commit();
                        return true;

                    }
                });
//        buildFragmentsList();

//       switchFragment(0, TAG_FRAGMENT_CALLS);

    }










//    private void buildFragmentsList() {
//        BlankFragment callsFragment = buildFragment("Calls");
//        BlankFragment recentsFragment = buildFragment("Recents");
//        BlankFragment tripsFragment = buildFragment("Trips");
//
//        fragments.add(callsFragment);
//        fragments.add(recentsFragment);
//        fragments.add(tripsFragment);
//    }
//    private BlankFragment buildFragment(String title) {
//        BlankFragment fragment = new BlankFragment();
//        Bundle bundle = new Bundle();
//        bundle.putString(BlankFragment.ARG_TITLE, title);
//        fragment.setArguments(bundle);
//        return fragment;
//    }
//    private void switchFragment(int pos, String tag) {
//        getSupportFragmentManager()
//                .beginTransaction()
//                .replace(R.id.frame_fragmentholder, fragments.get(pos), tag)
//                .commit();
//    }
}
