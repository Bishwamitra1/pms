package com.CSE3311.personalhealthmanagementsystem.navbarui.exercise;

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
import com.CSE3311.personalhealthmanagementsystem.Medication;
import com.CSE3311.personalhealthmanagementsystem.R;
import com.CSE3311.personalhealthmanagementsystem.navbarui.medication.AddMedFragment;
import com.CSE3311.personalhealthmanagementsystem.navbarui.medication.MedViewFragment;
import com.CSE3311.personalhealthmanagementsystem.navbarui.medication.MedicationAdapter;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

import static com.CSE3311.personalhealthmanagementsystem.HomePageActivity.localDB;
import static com.CSE3311.personalhealthmanagementsystem.HomePageActivity.userId;


public class ExercisesFragment extends Fragment implements ExerciseAdapter.OnClickAction, View.OnClickListener {

    private List<Exercise> exercises;
    public ExercisesFragment() {
        // Required empty public constructor
    }

    private FloatingActionButton addExer_button;
    private ImageView backToHealthFromMed;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v =  inflater.inflate(R.layout.fragment_exercises, container, false);
        exercises = localDB.daointerface().getExercisesById(userId);

        addExer_button = v.findViewById(R.id.addExer_button);
        backToHealthFromMed = v.findViewById(R.id.backToHealthFromMed);

        addExer_button.setOnClickListener(this);

        backToHealthFromMed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getParentFragmentManager().popBackStackImmediate();
            }
        });

        RecyclerView recyclerView = v.findViewById(R.id.recyclerView);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(mLayoutManager);

        ExerciseAdapter adapter = new ExerciseAdapter(exercises);
        recyclerView.setAdapter(adapter);

        adapter.setOnClickAction(this);

        return v;
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.addExer_button:
                final FragmentTransaction ft = getParentFragmentManager().beginTransaction();
                ft.replace(R.id.nav_host_fragment, new AddExerFragment()).addToBackStack(null);
                ft.commit();
                break;
        }
    }

    public void onClickActionMethod(int position) {
        Exercise exer = exercises.get(position);

        final FragmentTransaction ft = getParentFragmentManager().beginTransaction();
        ft.replace(R.id.nav_host_fragment, new ExerViewFragment(exer)).addToBackStack(null);
        ft.commit();
    }
}