package com.CSE3311.personalhealthmanagementsystem.navbarui.profile;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;

import androidx.fragment.app.Fragment;

import com.CSE3311.personalhealthmanagementsystem.R;
import com.CSE3311.personalhealthmanagementsystem.UserAccount;

import static com.CSE3311.personalhealthmanagementsystem.MainActivity.localDB;

public class EditProfileFragment extends Fragment implements View.OnClickListener {

    private UserAccount userAccount;
    ImageView backToProfile, completeProfile;
    private EditText etFirstName, etLastName, etUsername, etEmail, etAge, etWeight, etHeight, etFriendEmail, etOtherEmail;
    Spinner etGender;
    public EditProfileFragment() {
        // Required empty public constructor
    }
    public EditProfileFragment(UserAccount userAccount) {
        this.userAccount = userAccount;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_edit_profile, container, false);

        backToProfile = v.findViewById(R.id.backToProfile);
        completeProfile = v.findViewById(R.id.completeProfile);

        etFirstName = v.findViewById(R.id.etFirstName);
        etLastName = v.findViewById(R.id.etLastName);
        etUsername = v.findViewById(R.id.etUsername);
        etEmail = v.findViewById(R.id.etEmail);
        etAge = v.findViewById(R.id.etAge);
        etGender = v.findViewById(R.id.etGender);
        etWeight = v.findViewById(R.id.etWeight);
        etHeight = v.findViewById(R.id.etHeight);
        etFriendEmail = v.findViewById(R.id.etFriendEmail);
        etOtherEmail = v.findViewById(R.id.etOtherEmail);

        etFirstName.setText(userAccount.getFirstName());
        etLastName.setText(userAccount.getLastName());
        etUsername.setText(userAccount.getUsername());
        etEmail.setText(userAccount.getEmail());

        etAge.setText(String.valueOf(userAccount.getAge()));
        ArrayAdapter<CharSequence> genderAdapter = ArrayAdapter.createFromResource(this.getContext(),
                R.array.gender, android.R.layout.simple_spinner_item);
        genderAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        etGender.setAdapter(genderAdapter);
        etGender.setSelection(userAccount.getGender());

        etWeight.setText(String.valueOf(userAccount.getWeight()));
        etHeight.setText(String.valueOf(userAccount.getHeight()));
        etFriendEmail.setText(userAccount.getFriendEmail());
        etOtherEmail.setText(userAccount.getEmergEmail());

        backToProfile.setOnClickListener(this);
        completeProfile.setOnClickListener(this);
        return v;
    }

    @Override
    public void onClick(View v) {
        if(v==backToProfile) { getParentFragmentManager().popBackStackImmediate(); }
        else if(v==completeProfile) {
            userAccount.setFirstName(etFirstName.getText().toString());
            userAccount.setLastName(etLastName.getText().toString());
            userAccount.setUsername(etUsername.getText().toString());
            userAccount.setEmail(etEmail.getText().toString());
            userAccount.setAge(Integer.parseInt(etAge.getText().toString()));
            userAccount.setGender(etGender.getSelectedItemPosition());
            userAccount.setWeight(Double.parseDouble(etWeight.getText().toString()));
            userAccount.setHeight(Integer.parseInt(etHeight.getText().toString()));
            userAccount.setFriendEmail(etFriendEmail.getText().toString());
            userAccount.setEmergEmail(etOtherEmail.getText().toString());

            localDB.daointerface().addUser(userAccount);
            getParentFragmentManager().popBackStackImmediate();
        }
    }
}