package com.CSE3311.personalhealthmanagementsystem.navbarui.exercise;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.CSE3311.personalhealthmanagementsystem.Exercise;
import com.CSE3311.personalhealthmanagementsystem.R;

import static com.CSE3311.personalhealthmanagementsystem.MainActivity.localDB;


public class ExerViewFragment extends Fragment {

    Exercise localExer;
    ImageView backToExer, editExer, deleteExer;

    TextView inputNameExer, inputDescription, inputCalories, inputHeartRate, inputLength;

    public ExerViewFragment(Exercise exercise) { localExer = exercise; }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_exer_view, container, false);


        backToExer = v.findViewById(R.id.backToExer);
        editExer = v.findViewById(R.id.editExer);
        deleteExer = v.findViewById(R.id.deleteExer);
        inputNameExer = v.findViewById(R.id.displayNameExer);
        inputDescription = v.findViewById(R.id.displayDescription);
        inputCalories = v.findViewById(R.id.displayCaloriesBurned);
        inputHeartRate = v.findViewById(R.id.displayHeartRate);
        inputLength = v.findViewById(R.id.displayLength);

        inputNameExer.setText(localExer.getNameOfExercise());
        inputDescription.setText(localExer.getDescriptionOfExercise());
        inputCalories.setText(String.valueOf(localExer.getCaloriesBurned()));
        inputHeartRate.setText(String.valueOf(localExer.getHeartRate()));
        inputLength.setText(String.valueOf(localExer.getLengthOfExercise()));

        
        backToExer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getParentFragmentManager().popBackStackImmediate();
            }
        });

        editExer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final FragmentTransaction ft = getParentFragmentManager().beginTransaction();
                ft.replace(R.id.nav_host_fragment, new AddExerFragment(localExer)).addToBackStack(null);
                ft.commit();
            }
        });

        deleteExer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                localDB.daointerface().deleteExercise(localExer);
                getParentFragmentManager().popBackStackImmediate();
            }
        });
        
        return v;
    }
}