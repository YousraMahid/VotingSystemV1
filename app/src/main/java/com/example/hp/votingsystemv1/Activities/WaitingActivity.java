package com.example.hp.votingsystemv1.Activities;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;

import com.example.hp.votingsystemv1.Loaders.ProfileAsyncTaskLoader;
import com.example.hp.votingsystemv1.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class WaitingActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<String> {

    String active;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_waiting);
        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo info = connectivityManager.getActiveNetworkInfo();
        if (info == null || !info.isConnected()) {
            Toast.makeText(this, "there is no internet Connection4", Toast.LENGTH_SHORT).show();
        } else {

            getSupportLoaderManager().initLoader(0, null, WaitingActivity.this).forceLoad();
        }
    }

    @Override
    public Loader<String> onCreateLoader(int id, Bundle args) {
        return new ProfileAsyncTaskLoader(this);
    }

    @Override
    public void onLoadFinished(Loader<String> loader, String data) {
        if (data != null || !data.isEmpty()) {
            getSupportLoaderManager().destroyLoader(0);
            updateUI(data);
        } else
            Toast.makeText(this, "there might be an error in connection", Toast.LENGTH_SHORT).show();
    }

    private void updateUI(String data) {
        JSONArray rootArray = null;
        try {
            rootArray = new JSONArray(data);
            for (int i = 0; i < rootArray.length(); i++) {
                JSONObject rootObject = rootArray.getJSONObject(i);
                if (rootObject.has("active"))
                    active = rootObject.getString("active");
                else
                    active = "";
                Intent mainIntent;
                if (active.equals("1")) {
                    mainIntent = new Intent(WaitingActivity.this, MainActivity.class);
                    startActivity(mainIntent);
                }else
                    Toast.makeText(this, "please wait", Toast.LENGTH_SHORT).show();


                Log.v("hello", "Active: " + active);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void onLoaderReset(Loader<String> loader) {

    }
}
