package com.CSE3311.personalhealthmanagementsystem.navbarui.medication;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.widget.Toast;

import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import com.CSE3311.personalhealthmanagementsystem.R;

public class NotificationReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        Toast.makeText(context,"Alarm received!",Toast.LENGTH_SHORT).show();

        createNotificationChannel(context);

        Intent notiIntent = new Intent(context,NotificationHelper.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(context.getApplicationContext(),1,notiIntent, 0);

        NotificationCompat.Builder builder = new NotificationCompat.Builder(context,"hello")
                .setSmallIcon(R.drawable.ic_baseline_medical_services_24)
                .setLargeIcon(BitmapFactory.decodeResource(context.getResources(),R.drawable.ic_baseline_medical_services_24))
                .setContentTitle("REMINDER ! ! !")
                .setContentText("It's time to take your Medication!")
                .setStyle(new NotificationCompat.BigTextStyle().bigText("It's time to take your Medication!"))
                .setPriority(NotificationCompat.PRIORITY_MAX)
                .setContentIntent(pendingIntent)
                .setChannelId("medication")
                .setAutoCancel(true);

        NotificationManagerCompat notificationManagerCompat = NotificationManagerCompat.from(context);
        notificationManagerCompat.notify(1,builder.build());



    }
        private void createNotificationChannel(Context context) {
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel notificationChannel = new NotificationChannel("medication","medication", NotificationManager.IMPORTANCE_HIGH);
            notificationChannel.setDescription("medication");
            notificationChannel.setShowBadge(true);
            NotificationManager notificationManager = context.getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(notificationChannel);
        }
    }

}

