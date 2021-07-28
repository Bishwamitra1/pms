package com.CSE3311.personalhealthmanagementsystem.navbarui.medication;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.CSE3311.personalhealthmanagementsystem.Medication;
import com.CSE3311.personalhealthmanagementsystem.R;

public class MedViewFragment extends Fragment {

    Medication localMed;
    ImageView backToMed, editMed;
    TextView inputNameMed, spinType, inputQuantity, inputStartTime, inputFrequency, inputEndDate;

    public MedViewFragment(Medication medication) { localMed = medication; }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_med_view, container, false);
        backToMed = v.findViewById(R.id.backToMed);
        editMed = v.findViewById(R.id.editMed);
        inputNameMed = v.findViewById(R.id.displayNameMed);
        spinType = v.findViewById(R.id.displayType);
        inputQuantity = v.findViewById(R.id.displayQuantity);
        inputStartTime = v.findViewById(R.id.displayStartTime);
        inputFrequency = v.findViewById(R.id.displayFrequency);
        inputEndDate = v.findViewById(R.id.displayEndDate);

        inputNameMed.setText(localMed.getNameOfMed());
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

        return v;
    }
}