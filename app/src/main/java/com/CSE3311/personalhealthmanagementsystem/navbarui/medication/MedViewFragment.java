package com.CSE3311.personalhealthmanagementsystem.navbarui.medication;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.CSE3311.personalhealthmanagementsystem.Medication;
import com.CSE3311.personalhealthmanagementsystem.R;

import static com.CSE3311.personalhealthmanagementsystem.MainActivity.localDB;

public class MedViewFragment extends Fragment {

    Medication localMed;
    ImageView backToMed, editMed, deleteMed;
    TextView inputNameMed, inputUseFor, spinType, inputQuantity, inputStartTime, inputFrequency, inputEndDate;

    public MedViewFragment(Medication medication) { localMed = medication; }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_med_view, container, false);
        backToMed = v.findViewById(R.id.backToMed);
        editMed = v.findViewById(R.id.editMed);
        deleteMed = v.findViewById(R.id.deleteMed);
        inputNameMed = v.findViewById(R.id.displayNameMed);
        inputUseFor = v.findViewById(R.id.displayUseFor);
        spinType = v.findViewById(R.id.displayType);
        inputQuantity = v.findViewById(R.id.displayQuantity);
        inputStartTime = v.findViewById(R.id.displayStartTime);
        inputFrequency = v.findViewById(R.id.displayFrequency);
        inputEndDate = v.findViewById(R.id.displayEndDate);

        inputNameMed.setText(localMed.getNameOfMed());
        inputUseFor.setText(localMed.getDescriptionOfMed());
        spinType.setText(localMed.getTypeOfMed());
        inputQuantity.setText(String.valueOf(localMed.getQuantity()));
        inputStartTime.setText(localMed.getStartTime());
        inputFrequency.setText("Every "+String.valueOf(localMed.getFrequency())+(localMed.isFrequencyUnit()?" Hour(s)":" Minute(s)"));
        inputEndDate.setText(localMed.getEndDate());

        backToMed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getParentFragmentManager().popBackStackImmediate();
            }
        });

        editMed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final FragmentTransaction ft = getParentFragmentManager().beginTransaction();
                ft.replace(R.id.nav_host_fragment, new AddMedFragment(localMed)).addToBackStack(null);
                ft.commit();
            }
        });

        deleteMed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                localDB.daointerface().deleteMedication(localMed);
                getParentFragmentManager().popBackStackImmediate();
            }
        });

        return v;
    }

}