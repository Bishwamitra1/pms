package com.CSE3311.personalhealthmanagementsystem.navbarui.medication;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.CSE3311.personalhealthmanagementsystem.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;


public class MedicineFragment extends Fragment implements View.OnClickListener {


    public MedicineFragment() {
        // Required empty public constructor
    }

    private FloatingActionButton addMed_button;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_medicine, container, false);
        addMed_button = v.findViewById(R.id.addMed_button);
        addMed_button.setOnClickListener(this);
        return v;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            //copy and paste any case to add other fragments
            case R.id.addMed_button:
                final FragmentTransaction ft = getParentFragmentManager().beginTransaction();
                ft.replace(R.id.nav_host_fragment, new AddMedFragment()).addToBackStack(null);
                ft.commit();
                break;
        }
    }




}