package com.example.hp.votingsystemv1.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatButton;
import android.view.View;
import android.widget.TextView;

import com.example.hp.votingsystemv1.R;

public class SignupActivity extends AppCompatActivity implements View.OnClickListener{

    TextView linkSignin;
    AppCompatButton btnSignin;




    public void reference(){
        linkSignin =findViewById(R.id.link_login);
        btnSignin =findViewById(R.id.btn_signup);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        reference();
        linkSignin.setOnClickListener(this);
        btnSignin.setOnClickListener(this);
    }




    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.link_login:
                Intent intent=new Intent(SignupActivity.this,SigninActivity.class);
                startActivity(intent);
                finish();
                break;
            case R.id.btn_signup:
                Intent intentMain=new Intent(SignupActivity.this,ConfirmProfileActivity.class);
                startActivity(intentMain);
                finish();
                break;
        }

    }
}
