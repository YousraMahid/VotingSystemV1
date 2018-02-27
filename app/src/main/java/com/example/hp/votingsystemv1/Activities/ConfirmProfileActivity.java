package com.example.hp.votingsystemv1.Activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Layout;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.example.hp.votingsystemv1.R;
import com.weiwangcn.betterspinner.library.material.MaterialBetterSpinner;

import java.util.ArrayList;

public class ConfirmProfileActivity extends AppCompatActivity {
    MaterialBetterSpinner department;
    MaterialBetterSpinner gender;
    MaterialBetterSpinner city;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirm_profile);

        department = findViewById(R.id.material_spinner1);
        city = findViewById(R.id.spinner_cities);
        gender = findViewById(R.id.spinner_gender);
        //gender array
        ArrayList<String> genderList = new ArrayList<>();
        genderList.add("Female");
        genderList.add("Male");
        ArrayAdapter<String> genderAdapter = new ArrayAdapter<String>(this, R.layout.support_simple_spinner_dropdown_item, genderList);
        gender.setAdapter(genderAdapter);
        //city array
        ArrayList<String> citylist = new ArrayList<>();
        citylist.add("Erbil");
        citylist.add("Suli");
        ArrayAdapter<String> cityAdapter = new ArrayAdapter<String>(this, R.layout.support_simple_spinner_dropdown_item, citylist);
        city.setAdapter(cityAdapter);
        //department array
        ArrayList<String> departments = new ArrayList<>();
        departments.add("HR");
        departments.add("PMO");
        departments.add("IP");
        ArrayAdapter<String> departmentAdapter = new ArrayAdapter<String>(this, R.layout.support_simple_spinner_dropdown_item, departments);
        department.setAdapter(departmentAdapter);

    }
}
