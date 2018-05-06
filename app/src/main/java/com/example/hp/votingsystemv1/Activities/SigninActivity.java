package com.example.hp.votingsystemv1.Activities;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatButton;
import android.text.Editable;
import android.text.TextWatcher;
import android.text.method.LinkMovementMethod;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.hp.votingsystemv1.Fragments.Intro;
import com.example.hp.votingsystemv1.Loaders.SigninAsyncTaskLoader;
import com.example.hp.votingsystemv1.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class SigninActivity extends AppCompatActivity implements View.OnClickListener, LoaderManager.LoaderCallbacks<String> {
    TextView link_signup;
    AppCompatButton btn_login;
    EditText emailET;
    EditText passwordET;
    SharedPreferences.Editor editor;
    String email;
    String pass;

    public void reference() {
        link_signup = findViewById(R.id.link_signup);
        btn_login = findViewById(R.id.btn_login);
        emailET = findViewById(R.id.input_email);
        passwordET = findViewById(R.id.input_password);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signin);
        reference();

        editor = getSharedPreferences("USER", MODE_PRIVATE).edit();

       /* emailET.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                email = emailET.getText().toString();

            }

            @Override
            public void afterTextChanged(Editable editable) {
                email = emailET.getText().toString();
                Log.v("EMAIL", email);
            }
        });*/


        passwordET.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                pass = passwordET.getText().toString();

            }

            @Override
            public void afterTextChanged(Editable editable) {
                pass = passwordET.getText().toString();
                Log.v("PASSWORD", pass);
            }
        });


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
                Intent intent = new Intent(SigninActivity.this, SignupActivity.class);
                startActivity(intent);
                break;

            case R.id.btn_login:
//                emailET.addTextChangedListener(new TextWatcher() {
//                    @Override
//                    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
//
//                    }
//
//                    @Override
//                    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
//                        email=emailET.getText().toString();
//
//                    }
//
//                    @Override
//                    public void afterTextChanged(Editable editable) {
//                        email=emailET.getText().toString();
//                    }
//                });
//
//                passwordET.addTextChangedListener(new TextWatcher() {
//                    @Override
//                    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
//
//                    }
//
//                    @Override
//                    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
//                        pass=passwordET.getText().toString();
//
//                    }
//
//                    @Override
//                    public void afterTextChanged(Editable editable) {
//                        pass=passwordET.getText().toString();
//                    }
//                });

                ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
                NetworkInfo info = connectivityManager.getActiveNetworkInfo();
                if (info == null || !info.isConnected()) {
                    Toast.makeText(this, "there is no internet Connection4", Toast.LENGTH_SHORT).show();
                } else {
                    email = emailET.getText().toString();
                    pass = passwordET.getText().toString();
                    getSupportLoaderManager().initLoader(0, null, SigninActivity.this).forceLoad();
                }
                break;
        }
    }

    private void updateUI(String data) {


        if (data != null) {
            if (data.equals("null"))
                Toast.makeText(this, "This user does not exist please make sure you entered email and password correctly", Toast.LENGTH_SHORT).show();
            else {
                try {
                    JSONArray rootArray = new JSONArray(data);
                    for (int i = 0; i < rootArray.length(); i++) {
                        JSONObject object = rootArray.getJSONObject(i);

                        String email;
                        if (object.has("email"))
                            email = object.getString("email");
                        else
                            email = "";

                        String userID;
                        if (object.has("user_id"))
                            userID = object.getString("user_id");
                        else
                            userID = "";

                        editor.putString("USER_ID", userID);
                        editor.putString("EMAIL", email);
                        editor.apply();

                        Intent intentMain = new Intent(SigninActivity.this, WaitingActivity.class);
                        startActivity(intentMain);


                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        } else
            Toast.makeText(this, "there might be poor internet connection", Toast.LENGTH_SHORT).show();
    }

    @Override
    public Loader<String> onCreateLoader(int id, Bundle args) {
        return new SigninAsyncTaskLoader(this, email, pass);
    }

    @Override
    public void onLoadFinished(Loader<String> loader, String data) {
        getSupportLoaderManager().destroyLoader(0);
        if (data != null && !data.isEmpty()) {
            updateUI(data);
            Log.v("SIGNIN_DATA", data);
        } else
            Toast.makeText(this, "there is no internet connection6", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onLoaderReset(Loader<String> loader) {

    }
}
