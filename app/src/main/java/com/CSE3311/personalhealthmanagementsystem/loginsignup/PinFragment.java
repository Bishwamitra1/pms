package com.CSE3311.personalhealthmanagementsystem.loginsignup;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.CSE3311.personalhealthmanagementsystem.HomePageActivity;
import com.CSE3311.personalhealthmanagementsystem.MainActivity;
import com.CSE3311.personalhealthmanagementsystem.R;

import static com.CSE3311.personalhealthmanagementsystem.MainActivity.localDB;


public class PinFragment extends Fragment implements View.OnClickListener {

    public PinFragment() {
        // Required empty public constructor
    }

    Button logIn;
    EditText pin;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_pin, container, false);

        pin = v.findViewById(R.id.edit_text_pin);

        logIn = v.findViewById(R.id.pin_log_in);
        logIn.setOnClickListener(this);

        return v;
    }

    @Override
    public void onClick(View v) {
        if(v == logIn){
            int userPin = localDB.daointerface().getUserById(SaveSharedPreference.getUserId(getActivity())).getPin();
            int trialPin = Integer.parseInt(pin.getText().toString());
            if(userPin == trialPin){
                int userId = SaveSharedPreference.getUserId(getActivity());
                Toast.makeText(getContext(), "Login successful", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getActivity(), HomePageActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                intent.putExtra("userID", userId);
                startActivity(intent);
                SaveSharedPreference.setUserId(this.getContext(),userId);
                requireActivity().finish();
            }
            else{
                Toast.makeText(getContext(), "Pin incorrect, please try again", Toast.LENGTH_SHORT).show();
            }
        }
    }
}