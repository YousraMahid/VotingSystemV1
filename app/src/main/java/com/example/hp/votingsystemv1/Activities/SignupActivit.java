package com.example.hp.votingsystemv1.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatButton;
import android.view.View;
import android.widget.TextView;

import com.example.hp.votingsystemv1.R;

public class SignupActivit extends AppCompatActivity implements View.OnClickListener{

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
                Intent intent=new Intent(SignupActivit.this,SigninActivity.class);
                startActivity(intent);
                finish();
                break;
            case R.id.btn_signup:
                // lera yaksar xlas dabi 3amlyay sign upaka?aa wab, aw sign upa chand asana hhhhh hh na yak dawaqa
                SigninActivity.setUserAuthenticacity(SignupActivit.this,true);
                Intent intentMain=new Intent(SignupActivit.this,ConfirmProfileActivity.class);
                startActivity(intentMain);
                finish();
                break;
        }

    }
}
