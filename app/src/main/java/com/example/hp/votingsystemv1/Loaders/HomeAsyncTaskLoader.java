package com.example.hp.votingsystemv1.Loaders;

import android.content.Context;
import android.support.v4.content.AsyncTaskLoader;

import java.io.IOException;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by hp on 2/18/2018.
 */

public class HomeAsyncTaskLoader extends AsyncTaskLoader<String> {
    public HomeAsyncTaskLoader(Context context) {
        super(context);
    }

    @Override
    public String loadInBackground() {

        OkHttpClient client = new OkHttpClient();

        Request request = new Request.Builder()
                .url("http://awrosoft.krd/voting/voting/API/getSubjects.php")
                .get()
                .build();

        Response response = null;
        try {
            response = client.newCall(request).execute();
            return response.body().string();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
