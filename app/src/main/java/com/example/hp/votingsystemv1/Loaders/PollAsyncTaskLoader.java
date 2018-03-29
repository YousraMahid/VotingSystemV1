package com.example.hp.votingsystemv1.Loaders;

import android.content.Context;
import android.support.v4.content.AsyncTaskLoader;

import java.io.IOException;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
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

        MediaType mediaType = MediaType.parse("multipart/form-data; boundary=----WebKitFormBoundary7MA4YWxkTrZu0gW");
        RequestBody body = RequestBody.create(mediaType, "------WebKitFormBoundary7MA4YWxkTrZu0gW\r\nContent-Disposition: form-data; name=\"poll_id\"\r\n\r\n+"+pollId+"\r\n------WebKitFormBoundary7MA4YWxkTrZu0gW--");
        Request request = new Request.Builder()
                .url("http://awrosoft.krd/voting/voting/API/getOptions.php")
                .post(body)
                .addHeader("content-type", "multipart/form-data; boundary=----WebKitFormBoundary7MA4YWxkTrZu0gW")
                .addHeader("Cache-Control", "no-cache")
                .addHeader("Postman-Token", "4a5da962-8db0-39f5-5e66-b3c356c0e141")
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
