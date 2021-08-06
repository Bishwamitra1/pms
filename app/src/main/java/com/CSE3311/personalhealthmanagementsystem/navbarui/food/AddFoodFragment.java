package com.CSE3311.personalhealthmanagementsystem.navbarui.food;

import android.graphics.Color;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;

import com.CSE3311.personalhealthmanagementsystem.Exercise;
import com.CSE3311.personalhealthmanagementsystem.Food;
import com.CSE3311.personalhealthmanagementsystem.R;

import static com.CSE3311.personalhealthmanagementsystem.HomePageActivity.userId;
import static com.CSE3311.personalhealthmanagementsystem.MainActivity.localDB;

public class AddFoodFragment extends Fragment {

    private Food localFood;
    private boolean flagIfEdit = false;
    public AddFoodFragment() {
        // Required empty public constructor
    }
    public AddFoodFragment(Food food) {  localFood = food;  flagIfEdit = true; }

    EditText inputNameFood, inputCalorieCount, inputMicroCount;
    ImageView backToFood, saveFood;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_add_food, container, false);
        inputNameFood = v.findViewById(R.id.displayFood);
        inputCalorieCount = v.findViewById(R.id.displayCalorieCount);
        inputMicroCount = v.findViewById(R.id.displayMicroCount);
        backToFood = v.findViewById(R.id.backToFood);
        saveFood = v.findViewById(R.id.editFood);

        if(flagIfEdit) {
            inputNameFood.setText(localFood.getFavoriteFood());
            inputCalorieCount.setText(String.valueOf(localFood.getCalorieCount()));
            inputMicroCount.setText(String.valueOf(localFood.getMicroNutrientsCount()));
        }
        else {
            localFood = new Food();
        }

        backToFood.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getParentFragmentManager().popBackStackImmediate();
            }
        });

        saveFood.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean check = true;
                if(inputNameFood.getText().toString().equals(""))
                {
                    inputNameFood.setHint("*Required");
                    inputNameFood.setHintTextColor(Color.RED);
                    check = false;
                }

                int calorieCount = 0;
                try {
                    calorieCount = Integer.parseInt(inputCalorieCount.getText().toString());
                }
                catch (NumberFormatException e)
                {
                    calorieCount = -2;
                }

                if(inputCalorieCount.getText().toString().equals("") || calorieCount == -2)
                {
                    if(calorieCount == -2)
                    {
                        inputCalorieCount.setHint("*Invalid Calories");
                    }
                    else {
                        inputCalorieCount.setHint("*Required");
                    }
                    inputCalorieCount.setHintTextColor(Color.RED);
                    check = false;
                }

                int microCount = 0;
                try {
                    microCount = Integer.parseInt(inputMicroCount.getText().toString());
                }
                catch (NumberFormatException e)
                {
                    microCount = -2;
                }

                if(inputMicroCount.getText().toString().equals("") || microCount == -2)
                {
                    if(microCount == -2)
                    {
                        inputMicroCount.setHint("*Invalid microCount");
                    }
                    else {
                        inputMicroCount.setHint("*Required");
                    }
                    inputMicroCount.setHintTextColor(Color.RED);
                    check = false;
                }

                if(check) {

                    if (flagIfEdit) {
                        localFood.setFavoriteFood(inputNameFood.getText().toString());
                        localFood.setCalorieCount(Integer.parseInt(inputCalorieCount.getText().toString()));
                        localFood.setMicroNutrientsCount(Integer.parseInt(inputMicroCount.getText().toString()));
                    } else {
                        localFood.setUseFoodId(userId);
                        localFood.setFavoriteFood(inputNameFood.getText().toString());
                        localFood.setCalorieCount(Integer.parseInt(inputCalorieCount.getText().toString()));
                        localFood.setMicroNutrientsCount(Integer.parseInt(inputMicroCount.getText().toString()));
                    }
                    localDB.daointerface().addFood(localFood);
                    getParentFragmentManager().popBackStackImmediate();
                }
            }
        });
        
        return v;
    }
}