package com.CSE3311.personalhealthmanagementsystem.navbarui.medication;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

public class NotificationReceiver extends BroadcastReceiver {
    public static final String Channel_ID = "channel_id";
    @Override
    public void onReceive(Context context, Intent intent) {
        Toast.makeText(context,"Alarm recieved",Toast.LENGTH_SHORT).show();

//        NotificationChannel notificationChannel = new NotificationChannel(Channel_ID,"channel_id", NotificationManager.IMPORTANCE_HIGH);
//        Notification notification = new NotificationCompat.Builder(getApplicationContext(),Channel_ID)
//                .setContentTitle("hello")
//                .setContentText("hello1")
//                .setPriority(NotificationCompat.PRIORITY_HIGH)
//                .build();

    }
}

