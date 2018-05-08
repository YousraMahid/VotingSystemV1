package com.example.hp.votingsystemv1.Activities;

import android.content.Context;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.hp.votingsystemv1.Loaders.PollAsyncTaskLoader;
import com.example.hp.votingsystemv1.Loaders.SubmitVoteAsyncTaskLoader;
import com.example.hp.votingsystemv1.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class PollActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<String> {
    String pollID;
    RadioGroup optionsRG;
    TextView question;
    String optionID;
    Button submitBTN;
    String user_id;
    RadioButton option;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_poll_question);
        Toolbar toolbar = findViewById(R.id.toolbar);
        optionsRG = findViewById(R.id.rg_options);
        question = findViewById(R.id.tv_question);
        submitBTN = findViewById(R.id.bt_submit);
        question.setText(getIntent().getStringExtra("QUESTION"));
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setTitle(getIntent().getStringExtra("TITLE"));
        SharedPreferences sharedPreferences = getSharedPreferences("USER", MODE_PRIVATE);
        user_id = sharedPreferences.getString("USER_ID", "");
        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        final NetworkInfo info = connectivityManager.getActiveNetworkInfo();
        if (info == null || !info.isConnected()) {
            Toast.makeText(this, "there is no internet Connection4", Toast.LENGTH_SHORT).show();
        } else
            getSupportLoaderManager().initLoader(0, null, PollActivity.this).forceLoad();

        submitBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (optionsRG.getCheckedRadioButtonId() != -1) {
                    optionID = String.valueOf(optionsRG.getCheckedRadioButtonId());



                    if (info == null || !info.isConnected()) {
                        Toast.makeText(PollActivity.this, "there is no internet Connection4", Toast.LENGTH_SHORT).show();
                    } else {
                        getSupportLoaderManager().initLoader(1, null, PollActivity.this).forceLoad();
                    }
                } else
                    Toast.makeText(PollActivity.this, "Faiiledsubmitted vote", Toast.LENGTH_SHORT).show();
            }
        });


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
        pollID = getIntent().getStringExtra("POLL_ID");
        if (id == 0)
            return new PollAsyncTaskLoader(pollID, this);
        else
            return new SubmitVoteAsyncTaskLoader(this, user_id, pollID, optionID);
    }

    private void updateUI(String data) {
        try {
            JSONArray rootArray = new JSONArray(data);
            for (int i = 0; i < rootArray.length(); i++) {
                JSONObject object = rootArray.getJSONObject(i);
                if (object.has("option_id"))
                    optionID = object.getString("option_id");
                else
                    optionID = "";

                option = new RadioButton(this);
                option.setId(Integer.parseInt(optionID));
                if (object.has("option_name")) {
                    option.setText(object.getString("option_name"));
                } else
                    option.setText("");
                optionsRG.addView(option);
                Log.v("hello", "radio: " + option.getId());
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onLoadFinished(Loader<String> loader, String data) {
        if (data != null && !data.isEmpty()) {
            if (loader.getId() == 0) {
                getSupportLoaderManager().destroyLoader(0);
                updateUI(data);
                Log.v("OPTIONS_DATA", data);
            } else {
                getSupportLoaderManager().destroyLoader(1);
                Toast.makeText(this, data, Toast.LENGTH_SHORT).show();
            }
        } else
            Toast.makeText(this, "there is no internet connection6", Toast.LENGTH_SHORT).show();

    }


    @Override
    public void onLoaderReset(Loader<String> loader) {

    }
}
