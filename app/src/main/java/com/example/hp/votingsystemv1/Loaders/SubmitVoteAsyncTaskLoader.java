package com.example.hp.votingsystemv1.Loaders;

import android.content.Context;
import android.support.v4.content.AsyncTaskLoader;
import android.util.Log;

import java.io.IOException;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * Created by Shahida on 3/30/2018.
 */

public class SubmitVoteAsyncTaskLoader extends AsyncTaskLoader<String> {
    String pollId;
    String optionId;
    String user_id;

    public SubmitVoteAsyncTaskLoader(Context context,String user_id,String pollId,String optionId) {
        super(context);
        this.pollId=pollId;
        this.optionId=optionId;
        this.user_id=user_id;

    }

    @Override
    public String loadInBackground() {
        OkHttpClient client = new OkHttpClient();

        MediaType mediaType = MediaType.parse("multipart/form-data; boundary=----WebKitFormBoundary7MA4YWxkTrZu0gW");
        RequestBody body = RequestBody.create(mediaType, "------WebKitFormBoundary7MA4YWxkTrZu0gW\r\nContent-Disposition: form-data; name=\"poll_id\"\r\n\r\n"+pollId+"\r\n------WebKitFormBoundary7MA4YWxkTrZu0gW\r\nContent-Disposition: form-data; name=\"option_id\"\r\n\r\n"+optionId+"\r\n------WebKitFormBoundary7MA4YWxkTrZu0gW\r\nContent-Disposition: form-data; name=\"user_id\"\r\n\r\n"+user_id+"\r\n------WebKitFormBoundary7MA4YWxkTrZu0gW--");
        Request request = new Request.Builder()
                .url("http://awrosoft.krd/voting/voting/API/updateVotes.php")
                .post(body)
                .addHeader("content-type", "multipart/form-data; boundary=----WebKitFormBoundary7MA4YWxkTrZu0gW")
                .addHeader("Cache-Control", "no-cache")
                .addHeader("Postman-Token", "d538e17b-d2ce-406b-b1bc-2b0b5b7ee1e5")
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
