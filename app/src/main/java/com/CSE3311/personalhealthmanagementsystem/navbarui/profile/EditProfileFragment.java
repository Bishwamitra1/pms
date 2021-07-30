package com.CSE3311.personalhealthmanagementsystem.navbarui.profile;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.fragment.app.Fragment;

import com.CSE3311.personalhealthmanagementsystem.R;

public class EditProfileFragment extends Fragment implements View.OnClickListener {

    ImageView backToProfile, completeProfile;
    public EditProfileFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_edit_profile, container, false);

        backToProfile = v.findViewById(R.id.backToProfile);
        completeProfile = v.findViewById(R.id.completeProfile);


        backToProfile.setOnClickListener(this);
        completeProfile.setOnClickListener(this);
        return v;
    }

    @Override
    public void onClick(View v) {
        if(v==backToProfile) { getParentFragmentManager().popBackStackImmediate(); }
        else if(v==completeProfile) {
            getParentFragmentManager().popBackStackImmediate();
        }
    }
}