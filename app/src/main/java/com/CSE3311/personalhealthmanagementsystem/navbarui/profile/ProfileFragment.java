package com.CSE3311.personalhealthmanagementsystem.navbarui.profile;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.CSE3311.personalhealthmanagementsystem.MainActivity;
import com.CSE3311.personalhealthmanagementsystem.R;
import com.CSE3311.personalhealthmanagementsystem.UserAccount;
import com.CSE3311.personalhealthmanagementsystem.loginsignup.SaveSharedPreference;

import static com.CSE3311.personalhealthmanagementsystem.HomePageActivity.userId;
import static com.CSE3311.personalhealthmanagementsystem.MainActivity.localDB;

public class ProfileFragment extends Fragment implements View.OnClickListener {

    public ProfileFragment() {
        // Required empty public constructor
    }

    private UserAccount userAccount;
    Button logout;
    private TextView tvName, tvUsername, tvEmail, tvAge, tvGender, tvWeight, tvHeight, tvEmergEmail;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_profile, container, false);
        userAccount = localDB.daointerface().getUserById(userId);
        tvName = v.findViewById(R.id.tvName);
        tvUsername = v.findViewById(R.id.tvUsername);
        tvEmail = v.findViewById(R.id.tvEmail);
        tvAge = v.findViewById(R.id.tvAge);
        tvGender = v.findViewById(R.id.tvGender);
        tvWeight = v.findViewById(R.id.tvWeight);
        tvHeight = v.findViewById(R.id.tvHeight);
        tvEmergEmail = v.findViewById(R.id.tvEmergEmail);

        tvName.setText(userAccount.getFirstName()+" "+userAccount.getLastName());
        tvUsername.setText("@"+userAccount.getUsername());
        tvEmail.setText(userAccount.getEmail());
        tvAge.setText(String.valueOf(userAccount.getAge()));

        String gender;
        if(userAccount.getGender()==0) { gender = "Male";   }
        else if(userAccount.getGender()==1) { gender = "Female"; }
        else if(userAccount.getGender()==2) { gender = "Other"; }
        else { gender = "Prefer not to say"; }
        tvGender.setText(gender);
        tvWeight.setText(String.valueOf(userAccount.getWeight()));
        tvHeight.setText(String.valueOf(userAccount.getHeight()));
        tvEmergEmail.setText(userAccount.getEmergEmail());


        logout = v.findViewById(R.id.logout_button);

        logout.setOnClickListener(this);


        return v;
    }

    @Override
    public void onClick(View v) {

        if(v == logout){
            SaveSharedPreference.nullifyUserId(getActivity());

            Intent intent = new Intent(getActivity(), MainActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
            requireActivity().finish();
        }

    }
}