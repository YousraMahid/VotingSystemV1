package com.example.hp.votingsystemv1.Fragments;


import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.hp.votingsystemv1.Loaders.ProfileAsyncTaskLoader;
import com.example.hp.votingsystemv1.R;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class ProfileFragment extends Fragment implements android.support.v4.app.LoaderManager.LoaderCallbacks<String> {

    Spinner gender;
    TextView date;
    Spinner department;
    public ProfileFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View  view=inflater.inflate(R.layout.fragment_profile, container, false);
        gender=view.findViewById(R.id.gender_spinner);
        date=view.findViewById(R.id.date_text_view);
        department=view.findViewById(R.id.department_spinner);

        ArrayList<String> genderList=new ArrayList<>();
        genderList.add("Female");
        genderList.add("Male");

        ArrayAdapter<String> genderAdapter=new ArrayAdapter<String>(getContext(),R.layout.support_simple_spinner_dropdown_item,genderList);
        gender.setAdapter(genderAdapter);

        ArrayList<String> departments=new ArrayList<>();
        departments.add("HR");
        departments.add("PMO");
        departments.add("IP");

        ArrayAdapter<String> departmentAdapter=new ArrayAdapter<String>(getContext(),R.layout.support_simple_spinner_dropdown_item,departments);
        department.setAdapter(departmentAdapter);
        setHasOptionsMenu(true);
        ConnectivityManager connectivityManager=(ConnectivityManager)getActivity().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo info=connectivityManager.getActiveNetworkInfo();
        if (info==null || !info.isConnected()){
            Toast.makeText(getContext(), "there is no internet Connection", Toast.LENGTH_SHORT).show();
        }else
            getLoaderManager().initLoader(1,null,this).forceLoad();
        return view;


    }
    private void updateUI(String data){

    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.menu_item,menu);
    }


    @Override
    public android.support.v4.content.Loader<String> onCreateLoader(int id, Bundle args) {
        return new ProfileAsyncTaskLoader(getContext());
    }

    @Override
    public void onLoadFinished(android.support.v4.content.Loader<String> loader, String data) {
        if (data != null && !data.isEmpty()){
            updateUI(data);
            Log.v("DATA_PROFILE",data);
        }else
            Toast.makeText(getContext(), "there is no internet connection1", Toast.LENGTH_SHORT).show();

    }

    @Override
    public void onLoaderReset(android.support.v4.content.Loader<String> loader) {

    }




}
