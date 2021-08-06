package com.CSE3311.personalhealthmanagementsystem.navbarui.food;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.CSE3311.personalhealthmanagementsystem.Food;
import com.CSE3311.personalhealthmanagementsystem.R;
import com.CSE3311.personalhealthmanagementsystem.navbarui.exercise.AddExerFragment;

import static com.CSE3311.personalhealthmanagementsystem.MainActivity.localDB;


public class FoodViewFragment extends Fragment {
    Food localFood;
    ImageView backToFood, editFood, deleteFood;

    TextView displayFood, displayCalorieCount, displayMicroCount;

    public FoodViewFragment(Food food) { localFood = food; }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_food_view, container, false);

        backToFood = v.findViewById(R.id.backToFood);
        editFood = v.findViewById(R.id.editFood);
        deleteFood = v.findViewById(R.id.deleteFood);
        displayFood = v.findViewById(R.id.displayFood);
        displayCalorieCount = v.findViewById(R.id.displayCalorieCount);
        displayMicroCount = v.findViewById(R.id.displayMicroCount);

        displayFood.setText(localFood.getFavoriteFood());
        displayCalorieCount.setText(String.valueOf(localFood.getCalorieCount()));
        displayMicroCount.setText(String.valueOf(localFood.getMicroNutrientsCount()));

        backToFood.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getParentFragmentManager().popBackStackImmediate();
            }
        });

        editFood.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final FragmentTransaction ft = getParentFragmentManager().beginTransaction();
                ft.replace(R.id.nav_host_fragment, new AddFoodFragment(localFood)).addToBackStack(null);
                ft.commit();
            }
        });

        deleteFood.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                localDB.daointerface().deleteFood(localFood);
                getParentFragmentManager().popBackStackImmediate();
            }
        });
        
        return v;
    }
}