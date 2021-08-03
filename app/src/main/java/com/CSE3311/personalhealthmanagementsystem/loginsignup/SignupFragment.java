package com.CSE3311.personalhealthmanagementsystem.loginsignup;


import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.CSE3311.personalhealthmanagementsystem.MainActivity;
import com.CSE3311.personalhealthmanagementsystem.R;
import com.CSE3311.personalhealthmanagementsystem.UserAccount;

import java.util.Objects;

import static com.CSE3311.personalhealthmanagementsystem.HomePageActivity.localDB;


/**
 * A simple {@link Fragment} subclass.
 */
public class SignupFragment extends Fragment implements View.OnClickListener {

    private Button buttonSignup;
    private EditText editUsername, editPassword, editPasswordCheck;

    public SignupFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_signup, container, false);
        buttonSignup = v.findViewById(R.id.sign_up);
        buttonSignup.setOnClickListener(this);
        editUsername = v.findViewById(R.id.username);
        editPassword = v.findViewById(R.id.password);
        editPasswordCheck = v.findViewById(R.id.check_password);
        return v;
    }

    @Override
    public void onClick(View v) {
        if(v.getId() == buttonSignup.getId()) {
            String username = editUsername.getText().toString();
            String password = editPassword.getText().toString();
            String passwordCheck = editPasswordCheck.getText().toString();
            if (username.isEmpty() || password.isEmpty()){
                Toast emptyError = Toast.makeText(getContext(), "please fill out all fields", Toast.LENGTH_SHORT);
                emptyError.show();
            }
            else {
                try {
                    (localDB.daointerface().getUser(username, password)).getUserId();
                    Toast curUserError = Toast.makeText(getContext(), "User already exists!", Toast.LENGTH_SHORT);
                    curUserError.show();
                } catch (Exception e) {
                    Log.d("sign up", "User Does not already exist");
                    if (!(password.equals(passwordCheck))) {
                        Toast passwordError = Toast.makeText(getContext(), "passwords do not match, try again", Toast.LENGTH_SHORT);
                        passwordError.show();
                    }
                    else {
                        UserAccount user = new UserAccount();
                        user.setUsername(username);
                        user.setPassword(password);

                        MainActivity.fragmentManager.beginTransaction().
                                setCustomAnimations(R.anim.slide_in_right, 0, 0, R.anim.slide_out_right).
                                replace(R.id.fragment_container, new CompleteSignupFragment(user)).
                                addToBackStack(null).commit();


                    }
                }
            }
        }
    }
}
