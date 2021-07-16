package com.CSE3311.personalhealthmanagementsystem.navbarui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.CSE3311.personalhealthmanagementsystem.R;
import com.CSE3311.personalhealthmanagementsystem.navbarui.notes.NotesFragment;

public class HealthFragment extends Fragment implements View.OnClickListener {

    public HealthFragment() {
        // Required empty public constructor
    }
    private CardView medication;
    private CardView vitalSigns;
    private CardView nutrition;
    private CardView notes;
    private CardView exercises;
    private CardView contact;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_health, container, false);

        medication=v.findViewById(R.id.medButton);
        medication.setOnClickListener(this);

        vitalSigns=v.findViewById(R.id.vitalSignsButton);
        vitalSigns.setOnClickListener(this);

        nutrition=v.findViewById(R.id.nutritionButton);
        nutrition.setOnClickListener(this);

        notes=v.findViewById(R.id.notesButton);
        notes.setOnClickListener(this);

        exercises=v.findViewById(R.id.exercisesButton);
        exercises.setOnClickListener(this);

        contact=v.findViewById(R.id.contactButton);
        contact.setOnClickListener(this);

        return v;
    }

    @Override
    public void onClick(View v) {
        if(v.getId() == notes.getId()){
            final FragmentTransaction ft = getParentFragmentManager().beginTransaction();
            ft.replace(R.id.nav_host_fragment, new NotesFragment()).addToBackStack(null);
            ft.commit();
        }
    }

}