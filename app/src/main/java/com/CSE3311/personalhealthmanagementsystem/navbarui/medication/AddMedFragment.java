package com.CSE3311.personalhealthmanagementsystem.navbarui.medication;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.CSE3311.personalhealthmanagementsystem.R;

public class AddMedFragment extends Fragment {


    public AddMedFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_add_med, container, false);
        return v;
    }
}