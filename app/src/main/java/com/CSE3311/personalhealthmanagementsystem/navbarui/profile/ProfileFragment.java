package com.CSE3311.personalhealthmanagementsystem.navbarui.profile;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.fragment.app.Fragment;

import com.CSE3311.personalhealthmanagementsystem.MainActivity;
import com.CSE3311.personalhealthmanagementsystem.R;
//import com.CSE3311.personalhealthmanagementsystem.loginsignup.SaveSharedPreference;

public class ProfileFragment extends Fragment implements View.OnClickListener {

    public ProfileFragment() {
        // Required empty public constructor
    }

    Button logout;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_profile, container, false);




        logout = v.findViewById(R.id.logout_button);

        logout.setOnClickListener(this);


        return v;
    }

    @Override
    public void onClick(View v) {

        if(v == logout){
            //SaveSharedPreference.nullifyUserId(getActivity());

            Intent intent = new Intent(getActivity(), MainActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
            requireActivity().finish();
        }

    }
}