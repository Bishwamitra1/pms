package com.CSE3311.personalhealthmanagementsystem.loginsignup;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

public class SaveSharedPreference {
    static final String PREF_USER_ID= "UID";

    static SharedPreferences getSharedPreferences(Context ctx) {
        return PreferenceManager.getDefaultSharedPreferences(ctx);
    }

    public static void setUserId(Context ctx, int userId)
    {
        SharedPreferences.Editor editor = getSharedPreferences(ctx).edit();
        editor.putInt(PREF_USER_ID, userId);
        editor.apply();
    }

    public static int getUserId(Context ctx)
    {
        return getSharedPreferences(ctx).getInt(PREF_USER_ID, -1);
    }

    public static void nullifyUserId(Context ctx)
    {
        SharedPreferences.Editor editor = getSharedPreferences(ctx).edit();
        editor.putInt(PREF_USER_ID, -1);
        editor.apply();
    }

}
