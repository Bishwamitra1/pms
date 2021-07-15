package com.CSE3311.personalhealthmanagementsystem.navbarui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.CSE3311.personalhealthmanagementsystem.R;

import static com.CSE3311.personalhealthmanagementsystem.HomePageActivity.UserID;
import static com.CSE3311.personalhealthmanagementsystem.HomePageActivity.localDB;

public class HomeFragment extends Fragment {


    public HomeFragment() {
        // Required empty public constructor
    }
    private TextView txtv;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_home, container, false);

        txtv = v.findViewById(R.id.healthText);
        String toText = String.valueOf(UserID);
        txtv.setText("The current user's id is " + toText +"\nThe current users username is " + localDB.daointerface().getUserById(UserID).getUsername());
        return v;
    }
}