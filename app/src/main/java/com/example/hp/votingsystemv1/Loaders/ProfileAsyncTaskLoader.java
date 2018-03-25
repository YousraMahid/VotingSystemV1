package com.example.hp.votingsystemv1.Loaders;

import android.content.Context;
import android.support.v4.content.AsyncTaskLoader;

import java.io.IOException;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by Shahida on 2/25/2018.
 */

public class ProfileAsyncTaskLoader extends AsyncTaskLoader<String> {
    public ProfileAsyncTaskLoader(Context context) {
        super(context);
    }

    @Override
    public String loadInBackground() {
        OkHttpClient client = new OkHttpClient();

        Request request = new Request.Builder()
                .url("http://awrosoft.krd/voting/voting/API/getUserProfile.php")
                .get()
                .build();

        try {
            Response response = client.newCall(request).execute();
            return response.body().string();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
