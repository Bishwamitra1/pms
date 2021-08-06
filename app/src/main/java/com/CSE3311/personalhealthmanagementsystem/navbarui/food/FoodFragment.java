package com.CSE3311.personalhealthmanagementsystem.navbarui.food;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.CSE3311.personalhealthmanagementsystem.Exercise;
import com.CSE3311.personalhealthmanagementsystem.Food;
import com.CSE3311.personalhealthmanagementsystem.R;
import com.CSE3311.personalhealthmanagementsystem.navbarui.exercise.AddExerFragment;
import com.CSE3311.personalhealthmanagementsystem.navbarui.exercise.ExerViewFragment;
import com.CSE3311.personalhealthmanagementsystem.navbarui.exercise.ExerciseAdapter;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

import static com.CSE3311.personalhealthmanagementsystem.HomePageActivity.localDB;
import static com.CSE3311.personalhealthmanagementsystem.HomePageActivity.userId;

public class FoodFragment extends Fragment implements FoodAdapter.OnClickAction, View.OnClickListener{

    private List<Food> foods;
    public FoodFragment() {
        // Required empty public constructor
    }

    private FloatingActionButton addFood_button;
    private ImageView backToHealthFromFood;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_food, container, false);

        foods = localDB.daointerface().getFoodsById(userId);

        addFood_button = v.findViewById(R.id.addFood_button);
        backToHealthFromFood = v.findViewById(R.id.backToHealthFromFood);

        addFood_button.setOnClickListener(this);

        backToHealthFromFood.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getParentFragmentManager().popBackStackImmediate();
            }
        });

        RecyclerView recyclerView = v.findViewById(R.id.recyclerView);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(mLayoutManager);

        FoodAdapter adapter = new FoodAdapter(foods);
        recyclerView.setAdapter(adapter);

        adapter.setOnClickAction(this);

        return v;
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.addFood_button:
                final FragmentTransaction ft = getParentFragmentManager().beginTransaction();
                ft.replace(R.id.nav_host_fragment, new AddFoodFragment()).addToBackStack(null);
                ft.commit();
                break;
        }
    }

    public void onClickActionMethod(int position) {
        Food food = foods.get(position);

        final FragmentTransaction ft = getParentFragmentManager().beginTransaction();
        ft.replace(R.id.nav_host_fragment, new FoodViewFragment(food)).addToBackStack(null);
        ft.commit();
    }
}