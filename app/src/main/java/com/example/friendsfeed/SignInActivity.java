package com.example.friendsfeed;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.friendsfeed.SharedPreference.SavedLoginAuthentication;
import com.example.friendsfeed.SharedPreference.SharedPrefManager;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class SignInActivity extends AppCompatActivity {
    private static final String TAG = "SignInActivity";
    private EditText loginEmail;
    private EditText loginPassword;
    private Button signInButton;
    private Button registerButton;
    private RequestQueue mRequestQueue;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }

        loginEmail = findViewById(R.id.editText_login_email);
        loginPassword = findViewById(R.id.editText_login_password);

        signInButton = findViewById(R.id.sign_in_button);
        registerButton = findViewById(R.id.register_button);

        signInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                entryValidate();
            }
        });


    }

    private void signIn(final String loginAPIURL) {
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, loginAPIURL, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.d(TAG, "onResponse: " + response);
                        try {
                            int status = response.getInt("status");
                            if (status == 200) {
                                JSONArray message = response.getJSONArray("message");
                                JSONObject detail = message.getJSONObject(0);

                                String id = String.valueOf(detail.getInt("id"));
                                String name = detail.getString("name");
                                String username = detail.getString("username");
                                String email = detail.getString("email");
                                String email_verified_at = detail.getString("email_verified_at");
                                String profileImage = detail.getString("profileImage");
                                String profileCover = detail.getString("profileCover");
                                String following = detail.getString("following");
                                String followers = detail.getString("followers");
                                String bio = detail.getString("bio");
                                String country = detail.getString("country");
                                String website = detail.getString("website");
                                String created_at = detail.getString("created_at");
                                String updated_at = detail.getString("updated_at");

                                SavedLoginAuthentication sla = new
                                        SavedLoginAuthentication(id, name, username, email, email_verified_at,
                                        profileImage, profileCover, following, followers, bio, country, website,
                                        created_at, updated_at);

                                SharedPrefManager sp = SharedPrefManager.getInstance(SignInActivity.this);
                                sp.putSignInStatus(sla);

                                Toast.makeText(SignInActivity.this, "Success.", Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(SignInActivity.this, HomeActivity.class));
                                finish();
                            } else {
                                Toast.makeText(SignInActivity.this, "" + response.getString("message"), Toast.LENGTH_SHORT).show();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
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

    private void entryValidate() {
        if (TextUtils.isEmpty(loginEmail.getText())) {
            loginEmail.setError("please enter email.");
            loginEmail.setFocusable(true);
        } else if (TextUtils.isEmpty(loginPassword.getText())) {
            loginPassword.setError("please enter password");
            loginPassword.setFocusable(true);
        } else {
            mRequestQueue = Volley.newRequestQueue(this);

            String uName = loginEmail.getText().toString();
            String uPass = loginPassword.getText().toString();
            final String loginAPIURL = "https://diready.co/api/login?email="+uName+"&password="+uPass;
            signIn(loginAPIURL);
        }
    }
}
