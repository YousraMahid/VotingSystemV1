package com.example.hp.votingsystemv1.Loaders;

import android.content.Context;
import android.support.v4.content.AsyncTaskLoader;

import java.io.IOException;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class DepartmentsAsyncTaskLoader extends AsyncTaskLoader<String>{
    public DepartmentsAsyncTaskLoader(Context context) {
        super(context);
    }

    @Override
    public String loadInBackground() {
        OkHttpClient client = new OkHttpClient();

        Request request = new Request.Builder()
                .url("http://awrosoft.krd/voting/voting/API/getDepartments.php")
                .get()
                .addHeader("Cache-Control", "no-cache")
                .addHeader("Postman-Token", "34e7606a-d3d8-42f4-8941-173cb3dea2c3")
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
