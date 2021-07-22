package com.CSE3311.personalhealthmanagementsystem;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.room.Room;

import android.os.Bundle;

import com.CSE3311.personalhealthmanagementsystem.loginsignup.PinFragment;
import com.CSE3311.personalhealthmanagementsystem.loginsignup.SaveSharedPreference;
import com.CSE3311.personalhealthmanagementsystem.loginsignup.WelcomeFragment;

public class MainActivity extends AppCompatActivity {
    public static FragmentManager fragmentManager;
    public static PHMSDatabase localDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        fragmentManager = getSupportFragmentManager();
        localDB = Room.databaseBuilder(getApplicationContext(),PHMSDatabase.class, "userDB").allowMainThreadQueries().build();
        if(findViewById(R.id.fragment_container) !=null){
            if(savedInstanceState!=null){
                return;
            }
            if((SaveSharedPreference.getUserId(MainActivity.this) == -1)) {
                fragmentManager.beginTransaction().
                        setCustomAnimations(R.anim.fade_in, 0, 0, R.anim.fade_out).
                        add(R.id.fragment_container, new WelcomeFragment()).commit();
            }
            else {
                if(localDB.daointerface().getUserById(SaveSharedPreference.getUserId(MainActivity.this)).getPin() == 0) {
                    fragmentManager.beginTransaction().
                            setCustomAnimations(R.anim.fade_in, 0, 0, R.anim.fade_out).
                            add(R.id.fragment_container, new WelcomeFragment()).commit();
                }
                else {
                    fragmentManager.beginTransaction().
                            setCustomAnimations(R.anim.fade_in, 0, 0, R.anim.fade_out).
                            add(R.id.fragment_container, new PinFragment()).commit();
                }

            }
        }
    }
}
