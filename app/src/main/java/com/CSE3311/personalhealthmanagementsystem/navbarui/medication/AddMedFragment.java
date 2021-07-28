package com.CSE3311.personalhealthmanagementsystem.navbarui.medication;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;

import androidx.fragment.app.Fragment;

import com.CSE3311.personalhealthmanagementsystem.Medication;
import com.CSE3311.personalhealthmanagementsystem.R;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import static com.CSE3311.personalhealthmanagementsystem.HomePageActivity.userId;
import static com.CSE3311.personalhealthmanagementsystem.MainActivity.localDB;

public class AddMedFragment extends Fragment {


    public AddMedFragment() {
        // Required empty public constructor
    }

    TextView inputStartTime,inputEndDate;
    ImageView backToMed,saveMed;
    EditText inputNameMed, inputQuantity, inputFrequency;
    Spinner spinType, spinFrequency;

    int hour, minute;
    Medication newMed;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        newMed = new Medication();

        View v = inflater.inflate(R.layout.fragment_add_med, container, false);
        inputStartTime = v.findViewById(R.id.inputStartTime);
        inputEndDate = v.findViewById(R.id.inputEndDate);
        backToMed = v.findViewById(R.id.backToMed);
        saveMed = v.findViewById(R.id.saveMed);
        inputNameMed = v.findViewById(R.id.inputNameMed);
        inputQuantity = v.findViewById(R.id.inputQuantity);
        inputFrequency = v.findViewById(R.id.inputFrequency);
        spinType = v.findViewById(R.id.spinType);
        spinFrequency = v.findViewById(R.id.spinFrequency);

        inputStartTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                TimePickerDialog timePickerDialog = new TimePickerDialog(
                        getContext(), android.R.style.Theme_Holo_Light_Dialog_MinWidth,
                        new TimePickerDialog.OnTimeSetListener() {
                            @Override
                            public void onTimeSet(TimePicker timePicker, int i, int i1) {
                                hour = i;
                                minute = i1;
                                String time = i+":"+i1;
                                SimpleDateFormat f24Hours = new SimpleDateFormat(
                                        "HH:mm"
                                );
                                try {
                                    Date date = f24Hours.parse(time);
                                    SimpleDateFormat f12Hours = new SimpleDateFormat(
                                            "hh:mm aa"
                                    );
                                    inputStartTime.setText(f12Hours.format(date));
                                } catch (ParseException e) {
                                    e.printStackTrace();
                                }
                            }
                        },12,0,false
                );
                timePickerDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                timePickerDialog.updateTime(hour,minute);
                timePickerDialog.show();
            }
        });

        inputEndDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar cal = Calendar.getInstance();
                int year = cal.get(Calendar.YEAR);
                int month = cal.get(Calendar.MONTH);
                int day = cal.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog dialog = new DatePickerDialog(
                        getContext(), android.R.style.Theme_Holo_Light_Dialog_MinWidth,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                                month = month + 1;

                                String date = month + "/" + day + "/" + year;
                                inputEndDate.setText(date);
                            }
                        },year,month,day);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();
            }
        });

        backToMed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getParentFragmentManager().popBackStackImmediate();
            }
        });

        saveMed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                newMed.setUseMedId(userId);
                newMed.setNameOfMed(inputNameMed.getText().toString());
                newMed.setTypeOfMed(spinType.getSelectedItem().toString());
                newMed.setQuantity(Integer.parseInt(inputQuantity.getText().toString()));
                newMed.setStartTime(inputStartTime.getText().toString());
                newMed.setFrequency(Integer.parseInt(inputFrequency.getText().toString()));
                newMed.setFrequencyUnit((spinFrequency.getSelectedItem().toString().equals("Hour(s)")?true:false));
                newMed.setEndDate(inputEndDate.getText().toString());
                localDB.daointerface().addMedication(newMed);
                getParentFragmentManager().popBackStackImmediate();
            }
        });

        return v;
    }
}