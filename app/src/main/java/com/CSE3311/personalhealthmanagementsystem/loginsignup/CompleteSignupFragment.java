package com.CSE3311.personalhealthmanagementsystem.loginsignup;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.CSE3311.personalhealthmanagementsystem.HomePageActivity;
import com.CSE3311.personalhealthmanagementsystem.MainActivity;
import com.CSE3311.personalhealthmanagementsystem.R;
import com.CSE3311.personalhealthmanagementsystem.UserAccount;


public class CompleteSignupFragment extends Fragment implements View.OnClickListener {
    public CompleteSignupFragment() {
        // Required empty public constructor
    }

    private UserAccount curUser;
    private EditText fName, lName, email, height, weight, age, eEmail, fEmail;
    Spinner gender;
    public CompleteSignupFragment(UserAccount user) {
        curUser = user;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_complete_signup, container, false);
        Button btnFinish = v.findViewById(R.id.finish_signup);


        fName = v.findViewById(R.id.first_name);
        lName = v.findViewById(R.id.last_name);
        email = v.findViewById(R.id.email);
        height = v.findViewById(R.id.height);
        weight = v.findViewById(R.id.weight);
        gender = v.findViewById(R.id.gender);
            ArrayAdapter<CharSequence> genderAdapter = ArrayAdapter.createFromResource(this.getContext(),
                    R.array.gender, android.R.layout.simple_spinner_item);
            genderAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            gender.setAdapter(genderAdapter);
        age = v.findViewById(R.id.age);
        eEmail = v.findViewById(R.id.emergency_email);
        fEmail = v.findViewById(R.id.friend_email);

        btnFinish.setOnClickListener(this);

        return v;
    }

    @Override
    public void onClick(View v) {
        curUser.setFirstName(fName.getText().toString());
        curUser.setLastName(lName.getText().toString());
        curUser.setEmail(email.getText().toString());
        curUser.setHeight(Integer.parseInt(height.getText().toString()));
        curUser.setWeight(Double.parseDouble(weight.getText().toString()));
        curUser.setGender(gender.getSelectedItemPosition());
        curUser.setAge(Integer.parseInt(age.getText().toString()));
        curUser.setEmergEmail(eEmail.getText().toString());
        curUser.setFriendEmail(fEmail.getText().toString());

        MainActivity.localDB.daointerface().addUser(curUser);
        Toast.makeText(getContext(),"User created", Toast.LENGTH_SHORT).show();

        Intent intent = new Intent(getActivity(), HomePageActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        intent.putExtra(
                "userID",
                ((MainActivity.localDB.daointerface().getUser(curUser.getUsername(), curUser.getPassword())).getUserId())
        );
        startActivity(intent);
        requireActivity().finish();


    }
}