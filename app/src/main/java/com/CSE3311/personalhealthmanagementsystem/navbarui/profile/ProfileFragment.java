package com.CSE3311.personalhealthmanagementsystem.navbarui.profile;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

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
    ImageView logOut,editProfile;
    private TextView tvName, tvUsername, tvEmail, tvAgeAndGender, tvWeightAndHeight, tvFriendEmail, tvOtherEmail;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_profile, container, false);
        userAccount = localDB.daointerface().getUserById(userId);

        logOut = v.findViewById(R.id.logOut);
        editProfile = v.findViewById(R.id.editProfile);
        tvName = v.findViewById(R.id.tvName);
        tvUsername = v.findViewById(R.id.tvUsername);
        tvEmail = v.findViewById(R.id.tvEmail);
        tvAgeAndGender = v.findViewById(R.id.tvAgeAndGender);
        tvWeightAndHeight = v.findViewById(R.id.tvWeightAndHeight);
        tvFriendEmail = v.findViewById(R.id.tvFriendEmail);
        tvOtherEmail = v.findViewById(R.id.tvOtherEmail);

        tvName.setText(userAccount.getFirstName()+" "+userAccount.getLastName());
        tvUsername.setText("@"+userAccount.getUsername());
        tvEmail.setText(userAccount.getEmail());

        String gender;
        if(userAccount.getGender()==0) { gender = "Male";   }
        else if(userAccount.getGender()==1) { gender = "Female"; }
        else if(userAccount.getGender()==2) { gender = "Other"; }
        else { gender = "Prefer not to say"; }
        tvAgeAndGender.setText(String.valueOf(userAccount.getAge())+" - "+gender);

        tvWeightAndHeight.setText(String.valueOf(userAccount.getWeight())+" - "+String.valueOf(userAccount.getHeight()));
        tvFriendEmail.setText(userAccount.getFriendEmail());
        tvOtherEmail.setText(userAccount.getEmergEmail());


        logOut.setOnClickListener(this);
        editProfile.setOnClickListener(this);


        return v;
    }

    @Override
    public void onClick(View v) {

        if(v == logOut){
            SaveSharedPreference.nullifyUserId(getActivity());

            Intent intent = new Intent(getActivity(), MainActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
            requireActivity().finish();
        }
        else if(v == editProfile) {
            final FragmentTransaction ft = getParentFragmentManager().beginTransaction();
            ft.replace(R.id.nav_host_fragment, new EditProfileFragment(userAccount)).addToBackStack(null);
            ft.commit();
        }

    }
}