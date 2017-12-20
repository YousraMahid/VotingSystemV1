package com.example.hp.votingsystemv1.Fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.hp.votingsystemv1.R;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class ProFragment extends Fragment {

    Spinner gender;
    TextView date;
    Spinner department;
    public ProFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View  view=inflater.inflate(R.layout.fragment_, container, false);
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
        return view;


    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.menu_item,menu);
    }
}
