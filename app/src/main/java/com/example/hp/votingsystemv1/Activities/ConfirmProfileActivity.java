package com.example.hp.votingsystemv1.Activities;

import android.Manifest;
import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.design.widget.BottomSheetBehavior;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.view.animation.BounceInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.hp.votingsystemv1.Loaders.ConfirmProfileAsyncTaskLoader;
import com.example.hp.votingsystemv1.Loaders.DepartmentsAsyncTaskLoader;
import com.example.hp.votingsystemv1.R;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import de.hdodenhof.circleimageview.CircleImageView;

public class ConfirmProfileActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<String> {
    private static final String EMAIL_REGEX = "^[a-zA-Z]+[a-zA-Z0-9_.+-]+@[a-zA-Z0-9-]+\\.{1}[a-zA-Z0-9-.]{2,}(?<!\\.)$";
    private Calendar mCalendar;
    private static final int ACTIVITY_REQUEST_CODE_CAMERA = 1991;
    private static final int PERMISSION_REQUEST_CODE_STORAGE_READ = 2990;
    private static final int ACTIVITY_REQUEST_CODE_GALLERY = 1992;
    private DatePickerDialog.OnDateSetListener mDatePickedListener;
    private Intent cameraIntent, galleryIntent;
    private ProgressDialog mCreatingAccountProgressDialog;
    private boolean isAnimating = false; // a work around for image animation
    private BottomSheetBehavior mIntentHandlerChooser;
    private Uri uri;
    SharedPreferences.Editor editor;
    //component
    FloatingActionButton bChangePic;
    LinearLayout root;
    TextInputLayout fName,lName,phone,dob;
    Spinner department, cities, genders;
    Button submit;
    String imageText;
    CircleImageView userImage;
    TextView departmentError,intentGallery,intentCamera,emailTV;
    LinearLayout intentsChooserContainer;
    String email,firstName,lastName,DOB,gender,city,password,departmentString,phoneNumber;
    Bitmap image;




    public void reference() {
        department = findViewById(R.id.material_spinner1);
        cities = findViewById(R.id.spinner_cities);
        genders = findViewById(R.id.spinner_gender);
        fName=findViewById(R.id.et_f_name);
        lName=findViewById(R.id.et_l_name);
        phone=findViewById(R.id.et_phone_number);
        dob=findViewById(R.id.et_dob);
        submit=findViewById(R.id.b_submit);
        departmentError=findViewById(R.id.tv_spinner_dep_error);
        userImage=findViewById(R.id.iv_display_image);
        intentsChooserContainer=findViewById(R.id.intents_chooser_container);
        intentCamera=findViewById(R.id.intent_camera);
        intentGallery=findViewById(R.id.intent_gallery);
        bChangePic=findViewById(R.id.b_change_picture);
        root=findViewById(R.id.root);
        emailTV=findViewById(R.id.tv_email);


    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirm_profile);
        reference();
        email=getIntent().getStringExtra("EMAIL");
        emailTV.setText(email);



        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        final NetworkInfo info = connectivityManager.getActiveNetworkInfo();
        if (info == null || !info.isConnected()) {
            Toast.makeText(this, "there is no internet Connection4", Toast.LENGTH_SHORT).show();
        } else
            getSupportLoaderManager().initLoader(0, null, ConfirmProfileActivity.this).forceLoad();

        //submit action
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                validationAndPush();
//                firstName=fName.getEditText().getText().toString();
//                lastName=lName.getEditText().getText().toString();
//                phoneNumber=phone.getEditText().getText().toString();
//                gender=genders.getSelectedItem().toString();
//                city=cities.getSelectedItem().toString();
//                password=getIntent().getStringExtra("PASSWORD");
//                departmentString=department.getSelectedItem().toString();
//                String mytime=dob.getEditText().getText().toString();
//                SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
//                Date myDate = null;
//                try {
//                    myDate = dateFormat.parse(mytime);
//
//                } catch (ParseException e) {
//                    e.printStackTrace();
//                }
//
//                SimpleDateFormat timeFormat = new SimpleDateFormat("yyyy-MM-dd");
//                DOB = timeFormat.format(myDate);
//
//                if (info == null || !info.isConnected()) {
//                    Toast.makeText(ConfirmProfileActivity.this, "there is no internet Connection4", Toast.LENGTH_SHORT).show();
//                } else
//                    getSupportLoaderManager().initLoader(1, null, ConfirmProfileActivity.this).forceLoad();

            }
        });

        editor=getSharedPreferences("USER",MODE_PRIVATE).edit();

        mCalendar = Calendar.getInstance();
        mDatePickedListener = new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear,
                                  int dayOfMonth) {
                mCalendar.set(Calendar.YEAR, year);
                mCalendar.set(Calendar.MONTH, monthOfYear);
                mCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                updateDobField();
            }
        };


        dob.getEditText().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new DatePickerDialog(ConfirmProfileActivity.this,
                        mDatePickedListener,
                        mCalendar.get(Calendar.YEAR),
                        mCalendar.get(Calendar.MONTH),
                        mCalendar.get(Calendar.DAY_OF_MONTH))
                        .show();
            }
        });

        department.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (position != 0) {
                    departmentError.setText("");
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });



        mIntentHandlerChooser = BottomSheetBehavior.from(intentsChooserContainer);
        mIntentHandlerChooser.setHideable(true);
        mIntentHandlerChooser.setState(BottomSheetBehavior.STATE_HIDDEN);
        setupPictureIntents();
        bChangePic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ActivityCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.READ_EXTERNAL_STORAGE)
                        == PackageManager.PERMISSION_GRANTED) {
                    if (mIntentHandlerChooser.getState() == BottomSheetBehavior.STATE_HIDDEN) {
                        mIntentHandlerChooser.setState(BottomSheetBehavior.STATE_EXPANDED);
                    } else {
                        mIntentHandlerChooser.setState(BottomSheetBehavior.STATE_HIDDEN);
                    }
                } else {
                   requestStoragePermission();
                }
            }
        });
        mCreatingAccountProgressDialog = new ProgressDialog(this);
        mCreatingAccountProgressDialog.setCancelable(false);
        mCreatingAccountProgressDialog.setTitle(getString(R.string.updating_account));
        mCreatingAccountProgressDialog.setCanceledOnTouchOutside(false);
        mCreatingAccountProgressDialog.setMessage(getString(R.string.please_wait));




    }
    private void updateDobField() {
        String format = "dd/MM/yyyy";
        SimpleDateFormat sdf = new SimpleDateFormat(format, Locale.US);
        dob.setError("");
        dob.getEditText().setText(sdf.format(mCalendar.getTime()));
    }

    private void validationAndPush(){
        final String EMPTY = getString(R.string.this_field_is_required);
        final String INVALID = getString(R.string.invalid_input);
        final String INVALID_AGE = getString(R.string.age_greater_than18);
        final String INVALID_CITY = getString(R.string.select_valid_city);
        final String INVALID_DEP=getString(R.string.dep_invalide);

        boolean valid=true;

        //Email Validation
//        if(email.getEditText().getText().toString().isEmpty()){
//            email.getEditText().setError(EMPTY);
//            valid=false;
//        }else if(! email.getEditText().getText().toString().matches(EMAIL_REGEX)){
//            email.getEditText().setError(INVALID);
//            valid=false;
//        }

        //First Name Validator
        if (fName.getEditText().getText().toString().isEmpty()) {
            fName.getEditText().setError(EMPTY);
            valid = false;
        } else if (fName.getEditText().getText().toString().length() < 2) {
            fName.getEditText().setError(INVALID);
            valid = false;
        }

        //Last Name Validator
        if (lName.getEditText().getText().toString().isEmpty()) {
            lName.getEditText().setError(EMPTY);
            valid = false;
        } else if (lName.getEditText().getText().toString().length() < 2) {
            lName.getEditText().setError(INVALID);
            valid = false;
        }

        //Phone Number Validator
        if (phone.getEditText().getText().toString().isEmpty()) {
            phone.getEditText().setError(EMPTY);
            valid = false;
        } else if (phone.getEditText().getText().toString().length() < 11) {
            phone.getEditText().setError(INVALID);
            valid = false;
        }


        //DOB Validator
        if (dob.getEditText().getText().toString().isEmpty()) {
            dob.setError(EMPTY);
            valid = false;
        } else if (isYoung()) {
            dob.setError(INVALID_AGE);
            valid = false;
        } else {
            dob.setError(""); //We need to clear only this field because set to the layout
        }


        //Department Validator
        if (department.getSelectedItemPosition() == 0) {
            ((TextView) department.getSelectedView()).setError(INVALID_CITY);
           departmentError.setText(INVALID_CITY);
            valid = false;
        } else {
            departmentError.setText(""); //We need to clear only this field because set to the layout
        }

//        //Image Validator
        if (userImage.getTag() == null) {
            if (!isAnimating) {
                isAnimating = true;
               userImage.animate().yBy(-50).setInterpolator(new DecelerateInterpolator()).setDuration(200).withEndAction(new Runnable() {
                    @Override
                    public void run() {
                   userImage.animate().yBy(50).setInterpolator(new BounceInterpolator()).setDuration(400).withEndAction(new Runnable() {
                            @Override
                            public void run() {
                                isAnimating = false;
                            }
                        }).start();
                    }
                }).start();
            }
            valid = false;
        }

        //Push Data
        if (valid) {
            //TODO create user account in database
        }



    }

    private boolean isYoung() {
        Date today = Calendar.getInstance().getTime();
        final double acceptableDiff = 18 * 365.25d * 24 * 3600 * 1000; //18 years * 365 days * 24 hrs * 3600 secs * 1000 ms
        long diff = today.getTime() - mCalendar.getTimeInMillis();
        Log.v("RESULT", String.valueOf(mCalendar.getTimeInMillis()));

        return diff < acceptableDiff;
    }


    //Gallery and Camera part




    private void setupPictureIntents() {
        cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        galleryIntent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        PackageManager pm = getPackageManager();
        final ComponentName cameraComponent = cameraIntent.resolveActivity(pm);
        ComponentName galleryComponent = galleryIntent.resolveActivity(pm);

        //Instantiate camera intent
        if (cameraComponent == null) {
            intentCamera.setVisibility(View.GONE);
        }

        //Instantiate gallery intent
        if (galleryComponent != null) {
            galleryIntent.setType("image/*");
            galleryIntent.putExtra(MediaStore.MEDIA_IGNORE_FILENAME, ".nomedia");
        } else {
            intentGallery.setVisibility(View.GONE);
        }



       intentCamera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               //TODO next version

            }
        });

       intentGallery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivityForResult(galleryIntent, ACTIVITY_REQUEST_CODE_GALLERY);
                mIntentHandlerChooser.setState(BottomSheetBehavior.STATE_HIDDEN);
            }
        });
    }



    private void requestStoragePermission() {
        if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                Manifest.permission.READ_EXTERNAL_STORAGE)) {
            // Provide an additional rationale to the user if the permission was not granted
            // and the user would benefit from additional context for the use of the permission.
            // Display a SnackBar with a button to request the missing permission.
            Snackbar.make( root, R.string.storage_permission,
                    Snackbar.LENGTH_INDEFINITE).setAction("OK", new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    // Request the permission
                    ActivityCompat.requestPermissions(ConfirmProfileActivity.this,
                            new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                            PERMISSION_REQUEST_CODE_STORAGE_READ);
                }
            }).show();

        } else {
            // Request the permission. The result will be received in onRequestPermissionResult().
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                    PERMISSION_REQUEST_CODE_STORAGE_READ);
        }
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode,
                                    Intent imageReturnedIntent) {
        super.onActivityResult(requestCode, resultCode, imageReturnedIntent);
        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                case ACTIVITY_REQUEST_CODE_CAMERA:
                    break;

                case ACTIVITY_REQUEST_CODE_GALLERY:
                            Toast.makeText(this, "image selected", Toast.LENGTH_SHORT).show();
                            uri = imageReturnedIntent.getData();
                    try {
                        image=MediaStore.Images.Media.getBitmap(getContentResolver(),uri);
                        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                        image.compress(Bitmap.CompressFormat.PNG, 100, byteArrayOutputStream);
                        byte[] byteArray = byteArrayOutputStream .toByteArray();

                        imageText = Base64.encodeToString(byteArray, Base64.DEFAULT);
                        Log.v("hello","image"+imageText);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    editor.putString("IMAGE",String.valueOf(uri));
                            editor.apply();

                            Picasso.get().load(String.valueOf(uri)).into(userImage);


                        }

                    }
                }

    @Override
    public Loader<String> onCreateLoader(int id, Bundle args) {
        if (id==0)
        return new DepartmentsAsyncTaskLoader(this);
        else
            return new ConfirmProfileAsyncTaskLoader(this,email,firstName,lastName,phoneNumber,DOB,gender,city,password,departmentString,imageText);
    }

    @Override
    public void onLoadFinished(Loader<String> loader, String data) {
        if (data!=null||!data.isEmpty()){
            if (loader.getId()==0)
            updateUI(data);
            else{
                getSupportLoaderManager().destroyLoader(1);
                Toast.makeText(this, data, Toast.LENGTH_SHORT).show();
            }
        }else
            Toast.makeText(this, "there might be an error in connection", Toast.LENGTH_SHORT).show();
    }

    private void updateUI(String data){
        ArrayList<String> departments=new ArrayList<>();
        departments.add("Departments");
        try {
            JSONArray rootArray=new JSONArray(data);
            for (int i = 0; i <rootArray.length() ; i++) {
                JSONObject rootObject=rootArray.getJSONObject(i);
                if (rootObject.has("department_name"))
                    departments.add(rootObject.getString("department_name"));
                else
                    departments.add("");
            }
            ArrayAdapter<String> departmentsAdapter=new ArrayAdapter<String>(this,R.layout.support_simple_spinner_dropdown_item,departments);
            department.setAdapter(departmentsAdapter);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onLoaderReset(Loader<String> loader) {

    }
}






