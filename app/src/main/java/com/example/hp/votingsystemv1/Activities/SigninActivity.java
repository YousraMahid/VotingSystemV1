package com.example.hp.votingsystemv1.Activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatButton;
import android.text.method.LinkMovementMethod;
import android.view.View;
import android.widget.TextView;

import com.example.hp.votingsystemv1.Fragments.Intro;
import com.example.hp.votingsystemv1.R;

public class SigninActivity extends AppCompatActivity implements View.OnClickListener {
    TextView link_signup;
    AppCompatButton btn_login;

    public void reference() {
        link_signup = findViewById(R.id.link_signup);
        btn_login = findViewById(R.id.btn_login);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signin);
        reference();

        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {

                SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getBaseContext());
                boolean isFirstTime = preferences.getBoolean("first", true);

                if (isFirstTime) {
                    startActivity(new Intent(SigninActivity.this, Intro.class));
                    SharedPreferences.Editor editor = preferences.edit();
                    editor.putBoolean("first", false);
                    editor.apply();
                }
            }
        });

        thread.start();
        link_signup.setMovementMethod(LinkMovementMethod.getInstance());
        link_signup.setOnClickListener(this);
        btn_login.setOnClickListener(this);


    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.link_signup:
                Intent intent = new Intent(SigninActivity.this, SignupActivit.class);
                startActivity(intent);
                break;

            case R.id.btn_login:
                Intent intentMain = new Intent(SigninActivity.this, MainActivity.class);
                startActivity(intentMain);
                break;
        }

    }
}
