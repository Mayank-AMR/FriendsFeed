package com.example.friendsfeed.app;

import android.app.Application;
import android.app.NotificationChannel;
import android.app.NotificationManager;

/**
 * @Project FriendsFeed
 * @Created_by Mayank Kumar on 16-07-2020 01:48 PM
 */
public class App extends Application {
    private static final String TAG = "App";
    public static final String FCM_CHANNEL_ID = "FCM_CHANNEL_ID";
    public static final String FCM_CHANNEL_NAME = "FCM_Channel";

    @Override
    public void onCreate() {
        super.onCreate();

        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O ){
            NotificationChannel fcmChannel = new NotificationChannel(
                    FCM_CHANNEL_ID,
                    FCM_CHANNEL_NAME,
                    NotificationManager.IMPORTANCE_HIGH);
            NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
            assert notificationManager != null;
            notificationManager.createNotificationChannel(fcmChannel);
        }
    }
}
