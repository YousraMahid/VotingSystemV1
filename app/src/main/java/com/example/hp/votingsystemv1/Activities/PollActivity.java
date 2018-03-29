package com.example.hp.votingsystemv1.Activities;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.hp.votingsystemv1.Adapters.HomeAdapter;
import com.example.hp.votingsystemv1.Loaders.PollAsyncTaskLoader;
import com.example.hp.votingsystemv1.Models.Home;
import com.example.hp.votingsystemv1.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class PollActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<String> {
   String pollID;
   RadioGroup optionsRG;
   TextView question;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_poll_question);
        Toolbar toolbar =findViewById(R.id.toolbar);
        optionsRG=findViewById(R.id.rg_options);
        question=findViewById(R.id.tv_question);
        question.setText(getIntent().getStringExtra("QUESTION"));
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setTitle("Poll");


        ConnectivityManager connectivityManager=(ConnectivityManager)getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo info=connectivityManager.getActiveNetworkInfo();
        if (info==null || !info.isConnected()){
            Toast.makeText(this, "there is no internet Connection4", Toast.LENGTH_SHORT).show();
        }else
            getSupportLoaderManager().initLoader(0,null,PollActivity.this).forceLoad();

    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home) {
            onBackPressed();
            finish();
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public Loader<String> onCreateLoader(int id, Bundle args) {
        pollID=getIntent().getStringExtra("POLL_ID");
        return new PollAsyncTaskLoader(pollID,this);
    }
    private void updateUI(String data){


        try {
            JSONArray rootArray=new JSONArray(data);
            for (int i = 0; i <rootArray.length() ; i++) {
                JSONObject object=rootArray.getJSONObject(i);

                RadioButton option=new RadioButton(this);
                option.setId(i);
                if (object.has("option_name")){
                    option.setText(object.getString("option_name"));
                }else
                    option.setText("");

                optionsRG.addView(option);
            }


        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onLoadFinished(Loader<String> loader, String data) {
        if (data != null && !data.isEmpty()){
            updateUI(data);
            Log.v("OPTIONS_DATA",data);
        }else
            Toast.makeText(this, "there is no internet connection6", Toast.LENGTH_SHORT).show();
    }



    @Override
    public void onLoaderReset(Loader<String> loader) {

    }
}
