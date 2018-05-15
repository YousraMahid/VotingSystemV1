package com.example.hp.votingsystemv1.Activities;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatButton;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.hp.votingsystemv1.Loaders.SignUpAsyncTaskLoader;
import com.example.hp.votingsystemv1.R;

public class SignupActivity extends AppCompatActivity implements View.OnClickListener,LoaderManager.LoaderCallbacks<String>{
    private static final String EMAIL_REGEX = "^[a-zA-Z]+[a-zA-Z0-9_.+-]+@[a-zA-Z0-9-]+\\.{1}[a-zA-Z0-9-.]{2,}(?<!\\.)$";
    TextView linkSignin;
    AppCompatButton btnSignin;
    EditText inputEmailET;
    EditText inputPasswordET;
    String email;
    String password;
    TextInputLayout inputEmail;




    public void reference(){
        linkSignin =findViewById(R.id.link_login);
        btnSignin =findViewById(R.id.btn_signup);
        inputEmailET=findViewById(R.id.input_email);
        inputPasswordET=findViewById(R.id.input_password);
        inputEmail=findViewById(R.id.edit_input_email);
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
                validationAndPush();
                break;
        }

    }

    @Override
    public Loader<String> onCreateLoader(int id, Bundle args) {
        return new SignUpAsyncTaskLoader(this,email);
    }

    @Override
    public void onLoadFinished(Loader<String> loader, String data) {
        if (data != null && !data.isEmpty()) {
            if (data.equals("continue to registration")) {
                Toast.makeText(this, data, Toast.LENGTH_SHORT).show();
                Intent intentMain=new Intent(SignupActivity.this,ConfirmProfileActivity.class);
                intentMain.putExtra("EMAIL",email);
                intentMain.putExtra("PASSWORD",password);
                startActivity(intentMain);
                finish();
            } else {
                Toast.makeText(this, data+" please enter another email", Toast.LENGTH_SHORT).show();
            }
        } else
            Toast.makeText(this, "there is no internet connection6", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onLoaderReset(Loader<String> loader) {

    }

    private void validationAndPush() {
        final String EMPTY = getString(R.string.this_field_is_required);
        final String INVALID = getString(R.string.invalid_input);

        boolean valid = true;

        //Email Validation
        if(inputEmail.getEditText().getText().toString().isEmpty()){
            inputEmail.getEditText().setError(EMPTY);
            valid=false;
        }else if(! inputEmail.getEditText().getText().toString().matches(EMAIL_REGEX)){
            inputEmail.getEditText().setError(INVALID);
            valid=false;
        }


        if (valid) {
            email=inputEmailET.getText().toString();
            password=inputPasswordET.getText().toString();
            ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
            final NetworkInfo info = connectivityManager.getActiveNetworkInfo();
            if (info == null || !info.isConnected()) {
                Toast.makeText(this, "there is no internet Connection", Toast.LENGTH_SHORT).show();
            } else
                getSupportLoaderManager().initLoader(0, null, SignupActivity.this).forceLoad();
        }

    }

}
