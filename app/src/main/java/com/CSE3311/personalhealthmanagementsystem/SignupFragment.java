package com.CSE3311.personalhealthmanagementsystem;


import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


/**
 * A simple {@link Fragment} subclass.
 */
public class SignupFragment extends Fragment implements View.OnClickListener {

    private Button buttonBack, buttonSignup;
    private EditText editUsername, editPassword, editPasswordCheck;

    public SignupFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_signup, container, false);
        buttonBack = v.findViewById(R.id.back);
        buttonBack.setOnClickListener(this);

        buttonSignup = v.findViewById(R.id.sign_up);
        buttonSignup.setOnClickListener(this);
        editUsername = v.findViewById(R.id.username);
        editPassword = v.findViewById(R.id.password);
        editPasswordCheck = v.findViewById(R.id.check_password);
        return v;
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == buttonBack.getId()){
            FragmentManager fm = getActivity().getSupportFragmentManager();
            fm.popBackStack ();
        }
        if(v.getId() == buttonSignup.getId()){
            String username = editUsername.getText().toString();
            String password = editPassword.getText().toString();
            String passwordCheck = editPasswordCheck.getText().toString();

            if(!(password.equals(passwordCheck))){
                Toast passwordError = Toast.makeText(getContext(), "passwords do not match, try again", Toast.LENGTH_SHORT);
                passwordError.show();
            }
            else{
                UserAccount user = new UserAccount();
                user.setUsername(username);
                user.setPassword(password);

                MainActivity.localDB.daointerface().addUser(user);
                Toast.makeText(getContext(),"User created", Toast.LENGTH_SHORT).show();

            }
        }
    }
}
