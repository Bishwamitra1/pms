package com.CSE3311.personalhealthmanagementsystem;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.util.Log;
import android.widget.EditText;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.room.Room;

public class HomePageActivity extends AppCompatActivity {
    public static PHMSDatabase localDB;
    public static int userId;
    public static FragmentManager fragmentManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Intent mIntent = getIntent();
        userId = mIntent.getIntExtra("userID", 0);
        localDB = Room.databaseBuilder(getApplicationContext(),PHMSDatabase.class, "userDB").allowMainThreadQueries().build();
        fragmentManager = getSupportFragmentManager();

        if(doesUserHavePin(localDB.daointerface().getUserById(userId)))
            setPin(localDB.daointerface().getUserById(userId));
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);
        BottomNavigationView navView = findViewById(R.id.navigationView);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.home, R.id.health, R.id.profile)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        //NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(navView, navController);
    }

    private void setPin(final UserAccount user) {
        final EditText pinInput = new EditText(this);
        pinInput.setInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_VARIATION_PASSWORD);
        AlertDialog dialog = new AlertDialog.Builder(this)
                .setTitle("Set Pin")
                .setMessage("Please set your pin")
                .setView(pinInput)
                .setPositiveButton("Accept", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        int editTextInput;
                        try {
                            editTextInput = Integer.parseInt(pinInput.getText().toString());
                            user.setPin(editTextInput);
                            localDB.daointerface().addUser(user);
                        } catch (Exception e){
                            Log.e("set pin", "pin was not entered");
                        }
                    }
                })
                .create();
        dialog.show();
    }

    public static void checkPin(final int userId, Context context, final Runnable onCorrect){

        final EditText pinInput = new EditText(context);
        final UserAccount curUser = localDB.daointerface().getUserById(userId);

        pinInput.setInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_VARIATION_PASSWORD);
        AlertDialog dialog = new AlertDialog.Builder(context)
                .setTitle("Authentication")
                .setMessage("Please type your pin")
                .setView(pinInput)
                .setPositiveButton("ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        int editTextInput;
                        try {
                            editTextInput = Integer.parseInt(pinInput.getText().toString());
                            if (curUser.getPin() == editTextInput) {
                                onCorrect.run();
                            }
                        } catch (Exception e){
                            Log.e("check pin","pin was not entered");
                        }
                    }
                })
                .create();
        dialog.show();
    }

    Boolean doesUserHavePin(UserAccount user){
        if(user.getPin() != 0){
            return false;
        }
        else{
            return true;
        }
    }

}
