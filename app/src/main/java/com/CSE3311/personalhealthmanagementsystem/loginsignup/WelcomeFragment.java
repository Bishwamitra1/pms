package com.CSE3311.personalhealthmanagementsystem.loginsignup;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.CSE3311.personalhealthmanagementsystem.MainActivity;
import com.CSE3311.personalhealthmanagementsystem.R;

public class WelcomeFragment extends Fragment implements View.OnClickListener {

    private Button buttonLogIn;
    private Button buttonSignUp;

    public WelcomeFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_welcome, container, false);

        buttonLogIn = v.findViewById(R.id.log_in);
        buttonLogIn.setOnClickListener(this);

        buttonSignUp = v.findViewById(R.id.sign_up);
        buttonSignUp.setOnClickListener(this);
        return v;
    }

    @Override
    public void onClick(View v) {

        if (v.getId() == buttonLogIn.getId()){
            MainActivity.fragmentManager.beginTransaction().
                    setCustomAnimations( R.anim.slide_in_right, 0, 0, R.anim.slide_out_right).
                    replace(R.id.fragment_container,new LoginFragment()).
                    addToBackStack(null).commit();
        }

        if (v.getId() == buttonSignUp.getId()){
            MainActivity.fragmentManager.beginTransaction().
                    setCustomAnimations( R.anim.slide_in_right, 0, 0, R.anim.slide_out_right).
                    replace(R.id.fragment_container,new SignupFragment()).
                    addToBackStack(null).commit();
        }
    }
}
