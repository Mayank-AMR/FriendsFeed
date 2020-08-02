package com.example.friendsfeed.notificationManage;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;

import androidx.core.app.NotificationCompat;

import com.example.friendsfeed.R;

import java.net.ContentHandler;

/**
 * @Project FriendsFeed
 * @Created_by Mayank Kumar on 12-07-2020 10:13 AM
 */
public class MyNotificationManager {
    private Context mContext;
    private final int mNOTIFICATION_ID = 111;

    public MyNotificationManager(Context mContext) {
        this.mContext = mContext;
    }

    public void showNotification(String from, String notification, Intent intent) {
        PendingIntent pendingIntent = PendingIntent.getActivity(
                mContext, mNOTIFICATION_ID, intent, PendingIntent.FLAG_UPDATE_CURRENT);


        NotificationCompat.Builder builder = new NotificationCompat.Builder(mContext);
        Notification mNotification = builder.setSmallIcon(R.mipmap.ic_launcher)
                .setAutoCancel(true)
                .setContentIntent(pendingIntent)
                .setContentTitle(from)
                .setContentText(notification)
                .build();
                mNotification.flags |= Notification.FLAG_AUTO_CANCEL;

        NotificationManager notificationManager = (NotificationManager) mContext.getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify(mNOTIFICATION_ID, mNotification);
    }
}
