package com.example.friendsfeed.notificationManage;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.graphics.Color;
import android.util.Log;

import androidx.core.app.NotificationCompat;

import com.example.friendsfeed.ChatActivity;
import com.example.friendsfeed.R;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;


/**
 * @Project FriendsFeed
 * @Created_by Mayank Kumar on 09-07-2020 03:16 PM
 */
public class MyFirebaseMessagingService extends FirebaseMessagingService {
    private static final String TAG = "MyFirebaseMessagingServ";
    private static final String FCM_CHANNEL_ID = "FCM_CHANNEL_ID";

    @Override
    public void onNewToken(String token) {
        Log.d(TAG, "onNewToken: ");
        sendRegistrationToServer(token);
    }

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        Log.d(TAG, "onMessageReceived: ");

        // Check if message contains a notification payload.
        if (remoteMessage.getNotification() != null) {
            Log.d(TAG, "onMessageReceived: Notification received");
            String title = remoteMessage.getNotification().getTitle();
            String body = remoteMessage.getNotification().getBody();
            //String imageURL = remoteMessage.getNotification().getImageUrl().toString();
            showCustomNotification(title, body);
        }

        // Check if message contains a data payload.
        if (remoteMessage.getData().size() > 0) {
            Log.d(TAG, "onMessageReceived: Message Received");
            if (/* Check if data needs to be processed by long running job */ true) {
                // For long-running tasks (10 seconds or more) use WorkManager.
                scheduleJob();
            } else {
                // Handle message within 10 seconds
                handleNow();
            }
        }
    }

    @Override
    public void onDeletedMessages() {
        super.onDeletedMessages();
        Log.d(TAG, "onDeletedMessages: ");
    }


    private void scheduleJob() {
        Log.d(TAG, "scheduleJob: ");
    }

    private void handleNow() {
        Log.d(TAG, "handleNow: ");
    }

    public void showCustomNotification(String title, String body) {
        Log.d(TAG, "showCustomNotification: ");
        // create the pending intent and add to the notification

        Intent intent = new Intent(this, ChatActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, 0);

        Notification notification = new NotificationCompat.Builder(this, FCM_CHANNEL_ID)
                .setSmallIcon(R.drawable.ic_baseline_chat)
                .setContentTitle(title)
                .setContentText(body)
                .setColor(Color.BLUE)
                .setContentIntent(pendingIntent)
                .build();

        NotificationManager manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        assert manager != null;
        manager.notify(1010, notification);

    }

    private void sendRegistrationToServer(String token) {
        Log.d(TAG, "sendRegistrationToServer: ");
        Log.d(TAG, "sendRegistrationToServer: \nToken: "+token);

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.e(TAG, "onDestroy: FriendsFeed FCM Service Ended");
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.e(TAG, "onDestroy: FriendsFeed FCM Service Started");

    }

}
