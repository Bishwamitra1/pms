package com.CSE3311.personalhealthmanagementsystem.navbarui.exercise;

import android.graphics.Color;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;

import com.CSE3311.personalhealthmanagementsystem.Exercise;
import com.CSE3311.personalhealthmanagementsystem.Medication;
import com.CSE3311.personalhealthmanagementsystem.R;

import static com.CSE3311.personalhealthmanagementsystem.HomePageActivity.userId;
import static com.CSE3311.personalhealthmanagementsystem.MainActivity.localDB;


public class AddExerFragment extends Fragment {

    private Exercise localExer;
    private boolean flagIfEdit = false;
    public AddExerFragment() {
        // Required empty public constructor
    }
    public AddExerFragment(Exercise exercise) {  localExer = exercise;  flagIfEdit = true; }

    EditText inputNameExer, inputDescription, inputCalories, inputHeartRate, inputLength;
    ImageView backToExer, saveExer;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_add_exer, container, false);
        inputNameExer = v.findViewById(R.id.displayNameExer);
        inputDescription = v.findViewById(R.id.displayDescriptionOfExer);
        inputCalories = v.findViewById(R.id.displayCaloriesBurned);
        inputHeartRate = v.findViewById(R.id.displayHeartRate);
        inputLength = v.findViewById(R.id.displayLengthOfExer);
        backToExer = v.findViewById(R.id.backToExer);
        saveExer = v.findViewById(R.id.editExer);

        if(flagIfEdit) {
            inputNameExer.setText(localExer.getNameOfExercise());
            inputDescription.setText(localExer.getDescriptionOfExercise());
            inputCalories.setText(String.valueOf(localExer.getCaloriesBurned()));
            inputHeartRate.setText(String.valueOf(localExer.getHeartRate()));
            inputLength.setText(String.valueOf(localExer.getLengthOfExercise()));
        }
        else {
            localExer = new Exercise();
        }

        backToExer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getParentFragmentManager().popBackStackImmediate();
            }
        });

        saveExer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean check = true;
                if(inputNameExer.getText().toString().equals(""))
                {
                    inputNameExer.setHint("*Required");
                    inputNameExer.setHintTextColor(Color.RED);
                    check = false;
                }
                if(inputDescription.getText().toString().equals(""))
                {
                    inputDescription.setHint("*Required");
                    inputDescription.setHintTextColor(Color.RED);
                    check = false;
                }

                int caloriesBurned = 0;
                try {
                    caloriesBurned = Integer.parseInt(inputCalories.getText().toString());
                }
                catch (NumberFormatException e)
                {
                    caloriesBurned = -2;
                }

                if(inputCalories.getText().toString().equals("") || caloriesBurned == -2)
                {
                    if(caloriesBurned == -2)
                    {
                        inputCalories.setHint("*Invalid Calories");
                    }
                    else {
                        inputCalories.setHint("*Required");
                    }
                    inputCalories.setHintTextColor(Color.RED);
                    check = false;
                }

                int heartRate = 0;
                try {
                    heartRate = Integer.parseInt(inputHeartRate.getText().toString());
                }
                catch (NumberFormatException e)
                {
                    heartRate = -2;
                }

                if(inputHeartRate.getText().toString().equals("") || heartRate == -2)
                {
                    if(heartRate == -2)
                    {
                        inputHeartRate.setHint("*Invalid heartRate");
                    }
                    else {
                        inputHeartRate.setHint("*Required");
                    }
                    inputHeartRate.setHintTextColor(Color.RED);
                    check = false;
                }

                int length = 0;
                try {
                    length = Integer.parseInt(inputLength.getText().toString());
                }
                catch (NumberFormatException e)
                {
                    length = -2;
                }

                if(inputLength.getText().toString().equals("") || length == -2)
                {
                    if(length == -2)
                    {
                        inputLength.setHint("*Invalid heartRate");
                    }
                    else {
                        inputLength.setHint("*Required");
                    }
                    inputLength.setHintTextColor(Color.RED);
                    check = false;
                }


                if(check) {

                    if (flagIfEdit) {
                        localExer.setNameOfExercise(inputNameExer.getText().toString());
                        localExer.setDescriptionOfExercise(inputDescription.getText().toString());
                        localExer.setCaloriesBurned(Integer.parseInt(inputCalories.getText().toString()));
                        localExer.setHeartRate(Integer.parseInt(inputHeartRate.getText().toString()));
                        localExer.setLengthOfExercise(Integer.parseInt(inputLength.getText().toString()));
                    } else {
                        localExer.setUseExerId(userId);
                        localExer.setNameOfExercise(inputNameExer.getText().toString());
                        localExer.setDescriptionOfExercise(inputDescription.getText().toString());
                        localExer.setCaloriesBurned(Integer.parseInt(inputCalories.getText().toString()));
                        localExer.setHeartRate(Integer.parseInt(inputHeartRate.getText().toString()));
                        localExer.setLengthOfExercise(Integer.parseInt(inputLength.getText().toString()));
                    }
                    localDB.daointerface().addExercise(localExer);
                    getParentFragmentManager().popBackStackImmediate();
                }
            }
        });
        
        return v;
    }
}