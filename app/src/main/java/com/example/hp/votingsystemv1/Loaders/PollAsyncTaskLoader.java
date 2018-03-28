package com.example.hp.votingsystemv1.Loaders;

import android.content.Context;
import android.support.v4.content.AsyncTaskLoader;

import java.io.IOException;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by Shahida on 3/28/2018.
 */

public class PollAsyncTaskLoader extends AsyncTaskLoader<String> {
    String pollId;
    public PollAsyncTaskLoader(String pollId,Context context) {
        super(context);
        this.pollId=pollId;
    }

    @Override
    public String loadInBackground() {
        OkHttpClient client = new OkHttpClient();

        Request request = new Request.Builder()
                .url("http://awrosoft.krd/voting/voting/API/getOptions.php?poll_id="+pollId)
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
