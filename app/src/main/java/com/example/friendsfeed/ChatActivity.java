package com.example.friendsfeed;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.util.Log;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.friendsfeed.notificationManage.MyFirebaseMessagingService;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.imageview.ShapeableImageView;
import com.google.android.material.shape.CornerFamily;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.InstanceIdResult;
import com.mikhaellopez.circularimageview.CircularImageView;

public class ChatActivity extends AppCompatActivity {
    private static final String TAG = "ChatActivity";

    //String url = "https://image.shutterstock.com/image-photo/colorful-flower-on-dark-tropical-600w-721703848.jpg";
    String url = "https://lh3.googleusercontent.com/ogw/ADGmqu-RFgFjBiGiZbEtqbEzEmB4k3lTokLJSVGgWNI=s83-c-mo";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.testlayout);
        if (getSupportActionBar() == null) {
            getSupportActionBar();
        }

        ShapeableImageView imageView = findViewById(R.id.imageVDP);
        Glide
                .with(this)
                .load(url)

                .into(imageView);

        retrieveUserFCMToken();

//        MyFirebaseMessagingService mSensorService = new MyFirebaseMessagingService();
//        Intent mServiceIntent = new Intent(this, mSensorService.getClass());
//        if (!isMyServiceRunning(mSensorService.getClass())) {
//            startService(mServiceIntent);
//        }

    }

    public void retrieveUserFCMToken() {
        FirebaseInstanceId.getInstance().getInstanceId()
                .addOnCompleteListener(new OnCompleteListener<InstanceIdResult>() {
                    @Override
                    public void onComplete(@NonNull Task<InstanceIdResult> task) {
                        if (!task.isSuccessful()) {
                            Log.w(TAG, "getInstanceId failed", task.getException());
                            return;
                        }


                        // Get new Instance ID token
                        String token = task.getResult().getToken();



                        // Log and toast
                        //String msg = getString(R.string.msg_token_fmt, token);
                        Log.d(TAG, token);
                        Toast.makeText(ChatActivity.this, token, Toast.LENGTH_SHORT).show();
                    }
                });
    }

}