package com.CSE3311.personalhealthmanagementsystem;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.net.UnknownServiceException;
import java.util.List;

import io.reactivex.Single;

public class LoginFragment extends Fragment implements View.OnClickListener {

    private Button buttonBack;
    private Button buttonLogin;
    private EditText editUsername;
    private EditText editPassword;

    public LoginFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_login, container, false);

        editUsername = v.findViewById(R.id.username);
        editPassword = v.findViewById(R.id.password);

        buttonBack = v.findViewById(R.id.back);
        buttonBack.setOnClickListener(this);

        buttonLogin = v.findViewById(R.id.log_in_button);
        buttonLogin.setOnClickListener(this);
        return v;
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == buttonBack.getId()){
            FragmentManager fm = getActivity().getSupportFragmentManager();
            fm.popBackStack ();
        }
        if (v.getId() == buttonLogin.getId()){
            if(MainActivity.localDB.daointerface().getUser(editUsername.getText().toString(), editPassword.getText().toString()) == null) {
                Toast.makeText(getContext(), "Username or password incorrect", Toast.LENGTH_SHORT).show();
            }
            else{
                Toast.makeText(getContext(), "Login successful", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
