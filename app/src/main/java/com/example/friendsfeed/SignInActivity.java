package com.example.friendsfeed;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.friendsfeed.SharedPreference.SharedPrefManager;
import com.example.friendsfeed.apiHandle.APIHandler;
import com.example.friendsfeed.postpackage.HomeActivity;
import com.example.friendsfeed.util.SignInJSONParser;

import org.json.JSONObject;

public class SignInActivity extends AppCompatActivity /*implements GestureDetector.OnGestureListener*/ {
    private static final String TAG = "SignInActivity";
    private EditText loginEmail;
    private EditText loginPassword;
    private Button signInButton;
    private Button registerButton;
    private RequestQueue mRequestQueue;

    /*
    // Variable declaration for side scroll feature
    private GestureDetector gestureDetector;
    private float x1, x2, y1, y2;
    private static int MIN_DISTANCE = 150;
    */


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }
/*
        //Initialising GestureDetector....
        this.gestureDetector = new GestureDetector(SignInActivity.this, this);
*/

        loginEmail = findViewById(R.id.tvFullName);
        loginPassword = findViewById(R.id.editText_login_password);

        signInButton = findViewById(R.id.sign_in_button);
        registerButton = findViewById(R.id.register_button);

        signInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(entryValid())
                    prepareSignInRequest();
            }
        });
        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SignInActivity.this,SignUpActivity.class));
            }
        });


    }

    private void createSignInRequest(final String loginAPIURL) {
        mRequestQueue = Volley.newRequestQueue(this);
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, loginAPIURL, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.d(TAG, "onResponse: " + response);
                        SignInJSONParser parser = new SignInJSONParser();
                        if (parser.signInJsonParse(response) != null) {
                            SharedPrefManager sp = SharedPrefManager.getInstance(SignInActivity.this);
                            sp.putSignInStatus(parser.signInJsonParse(response));
                            startActivity(new Intent(SignInActivity.this, HomeActivity.class));
                            finish();
                        }

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        });
        mRequestQueue.add(request);

    }

    private boolean entryValid() {
        if (TextUtils.isEmpty(loginEmail.getText())) {
            loginEmail.setError("please enter email.");
            loginEmail.setFocusable(true);
            return false;
        } else if (TextUtils.isEmpty(loginPassword.getText())) {
            loginPassword.setError("please enter password");
            loginPassword.setFocusable(true);
            return false;
        } else {
            return true;
        }
    }

    private void prepareSignInRequest(){
        String uName = loginEmail.getText().toString();
        String uPass = loginPassword.getText().toString();
//            final String loginAPIURL = "https://diready.co/api/login?email=" + uName + "&password=" + uPass;
//            signIn(loginAPIURL);
        createSignInRequest(APIHandler.signInUriBuilder(uName,uPass));
    }
/*
// Side Scrolling feature

    @Override
    public boolean onDown(MotionEvent e) {
        return false;
    }

    @Override
    public void onShowPress(MotionEvent e) {

    }

    @Override
    public boolean onSingleTapUp(MotionEvent e) {
        return false;
    }

    @Override
    public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
        return false;
    }

    @Override
    public void onLongPress(MotionEvent e) {

    }

    @Override
    public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
        return false;
    }

    // Override onTouch event
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        gestureDetector.onTouchEvent(event);
        switch (event.getAction()) {

            // Starting time swipe gesture
            case MotionEvent.ACTION_DOWN:
                x1 = event.getX();
                y1 = event.getY();
                break;

            //Ending time swipe gesture
            case MotionEvent.ACTION_UP:
                x2 = event.getX();
                y2 = event.getY();

                // Calculating value of horizontal swipe
                float valueX = x2 - x1;

                // Calculating value of horizontal swipe
                float valueY = y2 - y1;

                if (Math.abs(valueX) > MIN_DISTANCE) {

                    // detect left to right swipe
                    if (x2 > x1) {
                        // right swipe
                        startActivity(new Intent(SignInActivity.this, SignUpActivity.class));
                    } else {
                        // left swipe
                        // perform Action
                    }
                }
        }
        return super.onTouchEvent(event);
    }
 */

}
