package com.example.hp.votingsystemv1.Loaders;

import android.content.Context;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.AsyncTaskLoader;

import java.io.IOException;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class ConfirmProfileAsyncTaskLoader extends AsyncTaskLoader<String> {
    String email,firstName,lastName,phoneNo,birthdate,gender,city,password,department_name;
    public ConfirmProfileAsyncTaskLoader(Context context,String email,String firstName,String lastName,String phoneNo,String birthdate,String gender, String city,String password,String department_name) {
        super(context);
        this.email=email;
        this.firstName=firstName;
        this.lastName=lastName;
        this.phoneNo=phoneNo;
        this.birthdate=birthdate;
        this.gender=gender;
        this.city=city;
        this.password=password;
        this.department_name=department_name;
    }

    @Override
    public String loadInBackground() {
        OkHttpClient client = new OkHttpClient();

        MediaType mediaType = MediaType.parse("multipart/form-data; boundary=----WebKitFormBoundary7MA4YWxkTrZu0gW");
        RequestBody body = RequestBody.create(mediaType, "------WebKitFormBoundary7MA4YWxkTrZu0gW\r\nContent-Disposition: form-data;" +
                " name=\"email\"\r\n\r\n"+email+"\r\n------WebKitFormBoundary7MA4YWxkTrZu0gW\r\nContent-Disposition: form-data;" +
                " name=\"firstName\"\r\n\r\n"+firstName+"\r\n------WebKitFormBoundary7MA4YWxkTrZu0gW\r\nContent-Disposition: form-data; " +
                "name=\"lastName\"\r\n\r\n"+lastName+"\r\n------WebKitFormBoundary7MA4YWxkTrZu0gW\r\nContent-Disposition: form-data;" +
                " name=\"phoneNo\"\r\n\r\n"+phoneNo+"\r\n------WebKitFormBoundary7MA4YWxkTrZu0gW\r\nContent-Disposition: form-data;" +
                " name=\"birthdate\"\r\n\r\n"+birthdate+"\r\n------WebKitFormBoundary7MA4YWxkTrZu0gW\r\nContent-Disposition: form-data;" +
                " name=\"gender\"\r\n\r\n"+gender+"\r\n------WebKitFormBoundary7MA4YWxkTrZu0gW\r\nContent-Disposition: form-data;" +
                " name=\"city\"\r\n\r\n"+city+"\r\n------WebKitFormBoundary7MA4YWxkTrZu0gW\r\nContent-Disposition: form-data;" +
                " name=\"password\"\r\n\r\n"+password+"\r\n------WebKitFormBoundary7MA4YWxkTrZu0gW\r\nContent-Disposition: form-data;" +
                " name=\"department_name\"\r\n\r\n"+department_name+"\r\n------WebKitFormBoundary7MA4YWxkTrZu0gW--");
        Request request = new Request.Builder()
                .url("http://awrosoft.krd/voting/voting/API/addUser.php")
                .post(body)
                .addHeader("content-type", "multipart/form-data; boundary=----WebKitFormBoundary7MA4YWxkTrZu0gW")
                .addHeader("Cache-Control", "no-cache")
                .addHeader("Postman-Token", "e77becce-772d-4e16-bba4-2f1d95f3d20f")
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
