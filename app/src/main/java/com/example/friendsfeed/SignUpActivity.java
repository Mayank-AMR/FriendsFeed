package com.example.friendsfeed;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.icu.text.SimpleDateFormat;
import android.icu.util.TimeZone;
import android.os.Build;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.friendsfeed.apiHandle.APIHandler;
import com.example.friendsfeed.util.SignUpJSONParser;
import com.google.android.material.datepicker.MaterialDatePicker;
import com.google.android.material.datepicker.MaterialPickerOnPositiveButtonClickListener;
import com.google.android.material.textfield.TextInputLayout;

import org.json.JSONObject;

import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class SignUpActivity extends AppCompatActivity {
    private static final String TAG = "SignUpActivity";


    private EditText userFullName, userEmail, userCreatePassword, userConfirmPassword, userDOB;
    private RequestQueue mSignUpRequestQueue;
    private TextInputLayout textInputLayoutFN, textInputLayoutEmail, textInputLayoutCreatePassword, textInputLayoutConfirmPassword, textInputLayoutDOB;

    private Date dobDate;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d(TAG, "onCreate: ");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);


        findViewById(R.id.btnSignUpNext).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (entryValid())
                    prepareSignUpRequest();
            }
        });

        userFullName = findViewById(R.id.etFullName);
        //userFullName.addTextChangedListener();
        userEmail = findViewById(R.id.etEmail);
        userCreatePassword = findViewById(R.id.etCreatePassword);
        userConfirmPassword = findViewById(R.id.etConfirmPassword);
        userDOB = findViewById(R.id.etDOB);

        userDOB.addTextChangedListener(mDateEntryWatcher);
        TextInputLayout dobLayout = findViewById(R.id.textInputLayoutDOB);
        dobLayout.setEndIconOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MaterialDatePicker.Builder<Long> builder = MaterialDatePicker.Builder.datePicker();
                builder.setTitleText("Select Date of Birth");

                MaterialDatePicker<Long> picker = builder.build();
                Log.d(TAG, "onClick: " + picker.toString());

                picker.addOnPositiveButtonClickListener(new MaterialPickerOnPositiveButtonClickListener<Long>() {
                    @RequiresApi(api = Build.VERSION_CODES.N)
                    @Override
                    public void onPositiveButtonClick(Long selection) {
                        // Get the offset from our timezone and UTC.
                        TimeZone timeZoneUTC = TimeZone.getDefault();
                        // It will be negative, so that's the -1
                        Log.d(TAG, "onPositiveButtonClick: " + timeZoneUTC);
                        int offsetFromUTC = timeZoneUTC.getOffset(new Date().getTime()) * -1;

                        // Create a date format, then a date object with our offset
                        SimpleDateFormat simpleFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.US);
                        dobDate = new Date(selection + offsetFromUTC);

                        //dataEntry.setText(simpleFormat.format(lDate));
                        Log.d(TAG, "onPositiveButtonClick: " + simpleFormat.format(dobDate));
                        userDOB.setText(simpleFormat.format(dobDate));
                    }
                });
                picker.show(getSupportFragmentManager(), picker.toString());
            }
        });

//        findViewById(R.id.button_SignIn_SignUpActivity).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                startActivity(new Intent(SignUpActivity.this, SignInActivity.class));
//                finish();
//            }
//        });


    }


    private TextWatcher mDateEntryWatcher = new TextWatcher() {
        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

            String working = s.toString();
            boolean isValid = true;
            if (working.length() == 2 && before == 0) {
                if (Integer.parseInt(working) < 1 || Integer.parseInt(working) > 31) {
                    isValid = false;
                } else {
                    working += "/";
                    userDOB.setText(working);
                    userDOB.setSelection(working.length());
                }
            } else if (working.length() == 5 && before == 0) {
                String enteredMonth = working.substring(3);
                if (Integer.parseInt(enteredMonth) < 1 || Integer.parseInt(enteredMonth) > 12) {
                    isValid = false;
                } else {
                    working += "/";
                    userDOB.setText(working);
                    userDOB.setSelection(working.length());
                }
            } else if (working.length() == 10 && before == 0) {
                //String enteredYear = working.substring(3);
                String enteredYear = working.substring(6);
                int currentYear = Calendar.getInstance().get(Calendar.YEAR);
                if (Integer.parseInt(enteredYear) > currentYear) {
                    isValid = false;
                }
            } else if (working.length() != 10) {
                isValid = false;
            }
            if (!isValid) {
                userDOB.setError("Enter a valid date: DD/MM/YYYY");
            } else {
                userDOB.setError(null);
            }
        }

        @Override
        public void afterTextChanged(Editable s) {

        }

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
        }
    };

    private boolean entryValid() {
        Log.d(TAG, "signUpEntryValidate: ");
        // Check Name not null and greater than 2 character.
        if (TextUtils.isEmpty(userFullName.getText()) && userFullName.getText().length() < 2) {
            textInputLayoutFN = findViewById(R.id.textInputLayoutFullName);
            textInputLayoutFN.setError("Please Enter Full Name.");
            //userFullName.setFocusable(true);
            return false;
        }
        // Check email not null and in valid format.
        else if (TextUtils.isEmpty(userEmail.getText()) && !android.util.Patterns.EMAIL_ADDRESS.matcher(userEmail.getText().toString().trim()).matches()) {
            textInputLayoutEmail = findViewById(R.id.textInputLayoutEmail);
            textInputLayoutEmail.setError("Please enter valid email");
            return false;
        }
        // Check password not null and at least 6 character.
        else if (TextUtils.isEmpty(userCreatePassword.getText()) && userCreatePassword.getText().toString().trim().length() < 6) {
            textInputLayoutCreatePassword = findViewById(R.id.textInputLayoutCreatePassword);
            textInputLayoutCreatePassword.setError("Minimum 6 character required");
            return false;
        }
        // Check confirm password match password.
        else if (TextUtils.isEmpty(userConfirmPassword.getText()) && userConfirmPassword.getText().toString().trim().equals(userCreatePassword.getText().toString().trim())) {
            textInputLayoutConfirmPassword = findViewById(R.id.textInputLayoutConfirmPassword);
            textInputLayoutConfirmPassword.setError("Password not matched");
            return false;
        }
        // Check gender not null.
        //TODO: Need to enable commented code to check valid gender
        /*else if (TextUtils.isEmpty(userGender.getText().toString().trim())) {
            textInputLayoutGender.setError("Please select gender");
            return false;
        }*/

        // Check Date of Birth not null, must have 10 character  and not grater than current date.
        else if (TextUtils.isEmpty(userDOB.getText()) && userDOB.getText().toString().trim().length() != 10 /*TODO: Check not greater than current date*/) {
            textInputLayoutDOB = findViewById(R.id.textInputLayoutDOB);
            textInputLayoutDOB.setError("Please enter valid Birth date");
            return false;
        }
        // if all entry valid.
        else {
            return true;
        }
    }

    private void prepareSignUpRequest() {
        Log.d(TAG, "initialiseSignUpProcess: ");
        final String name = userEmail.getText().toString().trim();
        final String email = userEmail.getText().toString().trim();
        final String pass = userCreatePassword.getText().toString().trim();
        final String dob = userDOB.getText().toString().trim();
        //final String gender = userGender.getText().toString().trim();
        final String gender = "M";

        Date birthDate = null;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.US);
            try {
                birthDate = dateFormat.parse(dob);
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        // Create SignUp Request to the Database Server.........................................
        createSignUpRequest(APIHandler.signUpUriBuilder(name, email, pass, dobDate, gender));
    }

    private void createSignUpRequest(final String signUpAPIURL) {
        Log.d(TAG, "signUp: ");
        mSignUpRequestQueue = Volley.newRequestQueue(this);
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, signUpAPIURL, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.d(TAG, "onResponse: " + response);
                        if (SignUpJSONParser.signUpResultHandle(response)) {
                            Toast.makeText(SignUpActivity.this, "SignUp completed.", Toast.LENGTH_SHORT).show();
                            //TODO Important: Now perform Login operation to get user data and save in shared preference.
                        }
                        /*if (parser.signInJsonParse(response) != null) {
                            SharedPrefManager sp = SharedPrefManager.getInstance(SignInActivity.this);
                            sp.putSignInStatus(parser.signInJsonParse(response));
                            startActivity(new Intent(SignInActivity.this, HomeActivity.class));
                            finish();
                        }*/
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        });
        mSignUpRequestQueue.add(request);
    }
}