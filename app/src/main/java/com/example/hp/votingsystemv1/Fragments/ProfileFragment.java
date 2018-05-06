package com.example.hp.votingsystemv1.Fragments;


import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.hp.votingsystemv1.Loaders.ProfileAsyncTaskLoader;
import com.example.hp.votingsystemv1.R;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class ProfileFragment extends Fragment implements android.support.v4.app.LoaderManager.LoaderCallbacks<String> {

    EditText editEmail;
    EditText editPass;
    EditText editPhone;
    EditText editCity;
    Spinner gender;
    TextView date;
    TextView fNameLName;
    Spinner department;
    String fName;
    String stringDepartment;
    String lName,image;
    ArrayList<String> genderList;
    String stringGender;
    ArrayList<String> departments;
    ImageView profileImage;
    SharedPreferences sharedPreferences;
    public ProfileFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View  view=inflater.inflate(R.layout.fragment_profile, container, false);
        gender=view.findViewById(R.id.gender_spinner);
        date=view.findViewById(R.id.date_text_view);
        department=view.findViewById(R.id.department_spinner);
      //TODO spinner for city
        editEmail=view.findViewById(R.id.ed_email);
        editPass=view.findViewById(R.id.ed_pass);
        editPhone=view.findViewById(R.id.ed_phone);
        fNameLName=view.findViewById(R.id.et_display_name);
        profileImage=view.findViewById(R.id.iv_display_image);
        sharedPreferences = getActivity().getSharedPreferences("USER", Context.MODE_PRIVATE);


        genderList=new ArrayList<>();
        genderList.add("Female");
        genderList.add("Male");


        //Department Spinner
         departments=new ArrayList<>();
        ArrayAdapter<String> departmentAdapter=new ArrayAdapter<String>(getContext(),R.layout.support_simple_spinner_dropdown_item,departments);
        department.setAdapter(departmentAdapter);

        setHasOptionsMenu(true);

        ConnectivityManager connectivityManager=(ConnectivityManager)getActivity().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo info=connectivityManager.getActiveNetworkInfo();
        if (info==null || !info.isConnected()){
            Toast.makeText(getContext(), "there is no internet Connection", Toast.LENGTH_SHORT).show();
        }else
            getLoaderManager().initLoader(1,null,this).forceLoad();
        return view;


    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.edit:

                break;

            default:
                break;
        }

        return super.onOptionsItemSelected(item);
    }


    private void updateUI(String data){

        try {
            JSONArray rootArray=new JSONArray(data);
            for (int i = 0; i < rootArray.length(); i++) {
                JSONObject object=rootArray.getJSONObject(i);

                if (object.has("first_name")){
                    fName=object.getString("first_name");
                }else
                    fName="";
                if (object.has("department_id")){
                     stringDepartment=object.getString("department_id");
                }else
                    stringDepartment="";
                departments.add(stringDepartment);

                if (object.has("gender")){
                    stringGender=object.getString("gender");
                }else
                    stringGender="";
                genderList.add(stringDepartment);

                if (object.has("last_name")){
                    lName=object.getString("last_name");
                }else
                    lName="";

                if (object.has("password")){
                    editPass.setText(object.getString("password"));
                }else
                    editPass.setText("");

                if (object.has("email")){
                    editEmail.setText(object.getString("email"));
                }else
                    editEmail.setText("");

                if (object.has("phone_no")){
                    editPhone.setText(object.getString("phone_no"));
                }else
                    editPhone.setText("");

                if (object.has("image"))
                    image=object.getString("image");
                else
                    image="";

                byte[] imageAsBytes = Base64.decode(image.getBytes(), Base64.DEFAULT);
                Bitmap imageBit= BitmapFactory.decodeByteArray(imageAsBytes, 0, imageAsBytes.length);
                profileImage.setImageBitmap(imageBit);

                sharedPreferences.edit().putString("IMAGE",image);



//                Uri imageUri = Uri.parse(sharedPreferences.getString("IMAGE", ""));
//
//                Picasso.get().load(imageUri).into(profileImage);


//                if (object.has("city")){
//                    editCity.setText(object.getString("city"));
//                }else
//                    editCity.setText("");

                if (object.has("birthdate")){
                    date.setText(object.getString("birthdate"));
                }else
                    date.setText("");

                fNameLName.setText(fName +" "+lName);

                Log.i("xxx", stringGender);
                if(stringGender.equals("male"))
                    gender.setSelection(0);
                else gender.setSelection(1);
                //awha runi ka
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }


    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.menu_item,menu);
    }


    @Override
    public android.support.v4.content.Loader<String> onCreateLoader(int id, Bundle args) {
        return new ProfileAsyncTaskLoader(getContext());
    }

    @Override
    public void onLoadFinished(android.support.v4.content.Loader<String> loader, String data) {
        if (data != null && !data.isEmpty()){
            updateUI(data);
            Log.v("DATA_PROFILE",data);
        }else
            Toast.makeText(getContext(), "there is no internet connection1", Toast.LENGTH_SHORT).show();

    }

    @Override
    public void onLoaderReset(android.support.v4.content.Loader<String> loader) {

    }




}
