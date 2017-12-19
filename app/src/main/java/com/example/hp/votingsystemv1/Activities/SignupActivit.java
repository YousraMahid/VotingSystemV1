package com.example.hp.votingsystemv1.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatButton;
import android.text.method.LinkMovementMethod;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.hp.votingsystemv1.R;
import com.weiwangcn.betterspinner.library.material.MaterialBetterSpinner;

public class SignupActivit extends AppCompatActivity implements View.OnClickListener{

    MaterialBetterSpinner materialBetterSpinner ;
    TextView link_signin;
    AppCompatButton btn_signin;


    public void reference(){
        materialBetterSpinner = (MaterialBetterSpinner)findViewById(R.id.material_spinner1);
        link_signin=findViewById(R.id.link_login);
        btn_signin=findViewById(R.id.btn_signup);
    }

    String[] SPINNER_DATA = {"HR","PMO","IT","IP","Marketing"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        reference();

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(SignupActivit.this, android.R.layout.simple_dropdown_item_1line, SPINNER_DATA);

        materialBetterSpinner.setAdapter(adapter);
        link_signin.setMovementMethod(LinkMovementMethod.getInstance());
        link_signin.setOnClickListener(this);
        btn_signin.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.link_login:
                Intent intent=new Intent(SignupActivit.this,SigninActivity.class);
                startActivity(intent);
                break;
            case R.id.btn_signup:
                Intent intentMain=new Intent(SignupActivit.this,MainActivity.class);
                startActivity(intentMain);
                break;
        }

    }
}
