package com.CSE3311.personalhealthmanagementsystem.navbarui.medication;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

public class NotificationReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        Toast.makeText(context,"Alarm recieved",Toast.LENGTH_SHORT).show();




    }

}

