package com.example.hp.votingsystemv1.Fragments;


import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.design.widget.BottomSheetBehavior;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.util.Base64;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.hp.votingsystemv1.Activities.AboutActivity;
import com.example.hp.votingsystemv1.Activities.MainActivity;
import com.example.hp.votingsystemv1.Loaders.ProfileAsyncTaskLoader;
import com.example.hp.votingsystemv1.R;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Calendar;


/**
 * A simple {@link Fragment} subclass.
 */
public class ProfileFragment extends Fragment implements android.support.v4.app.LoaderManager.LoaderCallbacks<String> {
    TextView editEmail;
    TextView editPass;
    TextView editPhone;
    String gender1 = "";
    Spinner gender;
    TextView date;
    TextView fNameLName;
    Spinner department;
    String fName;
    String stringDepartment;
    String lName, image;
    ArrayList<String> genderList;
    String stringGender;
    ArrayList<String> departments;
    ImageView profileImage;
    TextView txtGender;
    SharedPreferences sharedPreferences;

    public ProfileFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_profile, container, false);
        gender = view.findViewById(R.id.gender_spinner);
        date = view.findViewById(R.id.date_text_view);
        department = view.findViewById(R.id.department_spinner);
        //TODO spinner for city
        editEmail = view.findViewById(R.id.ed_email);
        editPass = view.findViewById(R.id.ed_pass);
        editPhone = view.findViewById(R.id.ed_phone);
        fNameLName = view.findViewById(R.id.et_display_name);
        txtGender = view.findViewById(R.id.txt_gender);
        profileImage = view.findViewById(R.id.iv_display_image);
        sharedPreferences = getActivity().getSharedPreferences("USER", Context.MODE_PRIVATE);


        genderList = new ArrayList<>();
        genderList.add("Female");
        genderList.add("Male");


        //Department Spinner
        departments = new ArrayList<>();
        ArrayAdapter<String> departmentAdapter = new ArrayAdapter<String>(getContext(), R.layout.support_simple_spinner_dropdown_item, departments);
        department.setAdapter(departmentAdapter);

        setHasOptionsMenu(true);

        ConnectivityManager connectivityManager = (ConnectivityManager) getActivity().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo info = connectivityManager.getActiveNetworkInfo();
        if (info == null || !info.isConnected()) {
            Toast.makeText(getContext(), "there is no internet Connection", Toast.LENGTH_SHORT).show();
        } else
            getLoaderManager().initLoader(1, null, this).forceLoad();
        return view;


    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.edit:
                showCustomDialog();
                break;

            default:
                break;
        }

        return super.onOptionsItemSelected(item);
    }


    private void updateUI(String data) {

        try {
            JSONArray rootArray = new JSONArray(data);
            for (int i = 0; i < rootArray.length(); i++) {
                JSONObject object = rootArray.getJSONObject(i);

                if (object.has("first_name")) {
                    fName = object.getString("first_name");
                } else
                    fName = "";
                if (object.has("department_id")) {
                    stringDepartment = object.getString("department_id");
                } else
                    stringDepartment = "";
                departments.add(stringDepartment);

                if (object.has("gender")) {
                    stringGender = object.getString("gender");
                } else
                    stringGender = "";
                genderList.add(stringDepartment);

                if (object.has("last_name")) {
                    lName = object.getString("last_name");
                } else
                    lName = "";

                if (object.has("password")) {
                    editPass.setText(object.getString("password"));
                } else
                    editPass.setText("");

                if (object.has("email")) {
                    editEmail.setText(object.getString("email"));
                } else
                    editEmail.setText("");

                if (object.has("phone_no")) {
                    editPhone.setText(object.getString("phone_no"));
                } else
                    editPhone.setText("");

                if (object.has("image"))
                    image = object.getString("image");
                else
                    image = "";

                byte[] imageAsBytes = Base64.decode(image.getBytes(), Base64.DEFAULT);
                Bitmap imageBit = BitmapFactory.decodeByteArray(imageAsBytes, 0, imageAsBytes.length);
                profileImage.setImageBitmap(imageBit);

                sharedPreferences.edit().putString("IMAGE", image);


//                Uri imageUri = Uri.parse(sharedPreferences.getString("IMAGE", ""));
//
//                Picasso.get().load(imageUri).into(profileImage);


//                if (object.has("city")){
//                    editCity.setText(object.getString("city"));
//                }else
//                    editCity.setText("");

                if (object.has("birthdate")) {
                    date.setText(object.getString("birthdate"));
                } else
                    date.setText("");

                fNameLName.setText(fName + " " + lName);

                Log.i("xxx", stringGender);
                if (stringGender.equals("male"))
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
        inflater.inflate(R.menu.menu_item, menu);
    }


    @Override
    public android.support.v4.content.Loader<String> onCreateLoader(int id, Bundle args) {
        return new ProfileAsyncTaskLoader(getContext());
    }

    @Override
    public void onLoadFinished(android.support.v4.content.Loader<String> loader, String data) {
        if (data != null && !data.isEmpty()) {
            updateUI(data);
            Log.v("DATA_PROFILE", data);
        } else
            Toast.makeText(getContext(), "there is no internet connection1", Toast.LENGTH_SHORT).show();

    }

    @Override
    public void onLoaderReset(android.support.v4.content.Loader<String> loader) {

    }

    private void showCustomDialog() {

        final Dialog customDialog = new Dialog(getContext());
        customDialog.setContentView(R.layout.edit_profile);

        final TextInputLayout customDFname, customDLname, edtPhone, edt_dob;
        final RadioGroup genderRadioGroup;
        final RadioButton maleRadioButton, femaleRadioButton;
        final TextView save,cancle;

        gender1 ="";

        Window window = customDialog.getWindow();
        window.setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);
        window.setGravity(Gravity.CENTER);

        genderRadioGroup = customDialog.findViewById(R.id.genderRadioGroup);
        maleRadioButton = customDialog.findViewById(R.id.maleRadioButton);
        femaleRadioButton = customDialog.findViewById(R.id.femaleRadioButton);
        customDFname = customDialog.findViewById(R.id.et_f_name);
        customDLname = customDialog.findViewById(R.id.et_l_name);
        edt_dob = customDialog.findViewById(R.id.et_dob);
        edtPhone = customDialog.findViewById(R.id.et_phone_number);
        save = customDialog.findViewById(R.id.save);

        genderRadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, @IdRes int checkedId) {

                if (checkedId == R.id.maleRadioButton) {
                    gender1 = "Male";
                } else if (checkedId == R.id.femaleRadioButton) {
                    gender1 = "Female";
                }
            }
        });
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                if (edtPhone.getEditText().getText().toString().trim().isEmpty()) {
                    edtPhone.setError("Please Enter Phone Number");
                    edtPhone.requestFocus();
                }
                if (edtPhone.getEditText().getText().toString().trim().length() < 10) {
                    edtPhone.setError("Please Enter Valid Phone Number");
                    edtPhone.requestFocus();
                }

                if (customDFname.getEditText().getText().toString().trim().isEmpty()) {
                    customDFname.setError("Please Entere User Name");
                    customDFname.requestFocus();
                }
                if (customDLname.getEditText().getText().toString().trim().isEmpty()) {
                    customDLname.setError("Please Entere User Name");
                    customDLname.requestFocus();
                }

                if (gender1.isEmpty()) {
                    Toast.makeText(getContext(), "Please Select Gender", Toast.LENGTH_SHORT).show();
                }

                if (!edtPhone.getEditText().getText().toString().isEmpty() &&
                        !edtPhone.getEditText().getText().toString().isEmpty() &&
                        edtPhone.getEditText().getText().toString().trim().length() == 10 &&
                        !customDFname.getEditText().getText().toString().isEmpty() &&
                        !customDLname.getEditText().getText().toString().isEmpty() &&
                        !gender1.isEmpty()) {

                    fNameLName.setText(customDFname.getEditText().getText().toString()+customDLname.getEditText().getText().toString());
                    editPhone.setText(edtPhone.getEditText().getText().toString());
                    txtGender.setText(gender1);
                    customDialog.dismiss();
                }
            }
        });
        customDialog.show();// this show() method is used to show custom dialog
    }


}
