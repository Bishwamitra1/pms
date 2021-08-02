package com.CSE3311.personalhealthmanagementsystem.navbarui.medication;

import android.app.AlarmManager;
import android.app.DatePickerDialog;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
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
import android.widget.Toast;

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

    private Medication localMed;
    private boolean flagIfEdit = false;
    public AddMedFragment() {
        // Required empty public constructor
    }
    public AddMedFragment(Medication medication) {  localMed = medication;  flagIfEdit = true; }

    TextView inputStartTime,inputEndDate;
    ImageView backToMed,saveMed;
    EditText inputNameMed, inputUseFor, inputQuantity, inputFrequency;
    Spinner spinType, spinFrequency;

    int hour, minute, year, month, day;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View v = inflater.inflate(R.layout.fragment_add_med, container, false);
        inputStartTime = v.findViewById(R.id.displayStartTime);
        inputEndDate = v.findViewById(R.id.displayEndDate);
        backToMed = v.findViewById(R.id.backToMed);
        saveMed = v.findViewById(R.id.editMed);
        inputNameMed = v.findViewById(R.id.displayNameMed);
        inputUseFor = v.findViewById(R.id.displayUseFor);
        inputQuantity = v.findViewById(R.id.displayQuantity);
        inputFrequency = v.findViewById(R.id.displayFrequency);
        spinType = v.findViewById(R.id.displayType);
        spinFrequency = v.findViewById(R.id.spinFrequency);

        if(flagIfEdit) {
            int type,frequencyUnit;

            if(localMed.getTypeOfMed().equals("Capsule(s)")) { type = 0;}
            else if(localMed.getTypeOfMed().equals("Liquid")) { type = 1; }
            else {  type = 2;}

            if(localMed.isFrequencyUnit()) {    frequencyUnit = 0;}
            else {  frequencyUnit = 1;}

            inputNameMed.setText(localMed.getNameOfMed());
            inputUseFor.setText(localMed.getDescriptionOfMed());
            spinType.setSelection(type);
            inputQuantity.setText(String.valueOf(localMed.getQuantity()));
            inputStartTime.setText(localMed.getStartTime());
            inputFrequency.setText(String.valueOf(localMed.getFrequency()));
            spinFrequency.setSelection(frequencyUnit);
            inputEndDate.setText(localMed.getEndDate());
        }
        else {
            localMed = new Medication();
        }

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
                year = cal.get(Calendar.YEAR);
                month = cal.get(Calendar.MONTH);
                day = cal.get(Calendar.DAY_OF_MONTH);

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
                boolean check = true;
                if(inputNameMed.getText().toString().equals(""))
                {
                    inputNameMed.setHint("*Required");
                    inputNameMed.setHintTextColor(Color.RED);
                    check = false;
                }
                if(inputUseFor.getText().toString().equals(""))
                {
                    inputUseFor.setHint("*Required");
                    inputUseFor.setHintTextColor(Color.RED);
                    check = false;
                }

                int quantity = 0;
                try {
                    quantity = Integer.parseInt(inputQuantity.getText().toString());
                }
                catch (NumberFormatException e)
                {
                    quantity = -2;
                }

                if(inputQuantity.getText().toString().equals("") || quantity == -2)
                {
                    if(quantity == -2)
                    {
                        inputQuantity.setHint("*Invalid Quantity");
                    }
                    else {
                        inputQuantity.setHint("*Required");
                    }
                    inputQuantity.setHintTextColor(Color.RED);
                    check = false;
                }
                if(inputStartTime.getText().toString().equals(""))
                {
                    inputStartTime.setHint("*Required");
                    inputStartTime.setHintTextColor(Color.RED);
                    check = false;
                }

                int frequency = 0;
                try {
                    frequency = Integer.parseInt(inputFrequency.getText().toString());
                }
                catch (NumberFormatException e)
                {
                    frequency = -2;
                }

                if(inputFrequency.getText().toString().equals("") || frequency == -2)
                {
                    if(frequency == -2)
                    {
                        inputFrequency.setHint("*Invalid Frequency");
                    }
                    else {
                        inputFrequency.setHint("*Required");
                    }
                    inputFrequency.setHintTextColor(Color.RED);
                    check = false;
                }
                if(inputEndDate.getText().toString().equals(""))
                {
                    inputEndDate.setHint("*Required");
                    inputEndDate.setHintTextColor(Color.RED);
                    check = false;
                }

                if(check) {
//                    Calendar c = Calendar.getInstance();
//                    c.set(Calendar.HOUR_OF_DAY,6);
//                    c.set(Calendar.MINUTE,2);
//                    c.set(Calendar.SECOND,0);

                    if (flagIfEdit) {
                        //cancelAlarm();
                        localMed.setNameOfMed(inputNameMed.getText().toString());
                        localMed.setDescriptionOfMed(inputUseFor.getText().toString());
                        localMed.setTypeOfMed(spinType.getSelectedItem().toString());
                        localMed.setQuantity(Integer.parseInt(inputQuantity.getText().toString()));
                        localMed.setStartTime(inputStartTime.getText().toString());
                        localMed.setFrequency(Integer.parseInt(inputFrequency.getText().toString()));
                        localMed.setFrequencyUnit((spinFrequency.getSelectedItem().toString().equals("Hour(s)") ? true : false));
                        localMed.setEndDate(inputEndDate.getText().toString());
                    } else {
                        localMed.setUseMedId(userId);
                        localMed.setNameOfMed(inputNameMed.getText().toString());
                        localMed.setDescriptionOfMed(inputUseFor.getText().toString());
                        localMed.setTypeOfMed(spinType.getSelectedItem().toString());
                        localMed.setQuantity(Integer.parseInt(inputQuantity.getText().toString()));
                        localMed.setStartTime(inputStartTime.getText().toString());
                        localMed.setFrequency(Integer.parseInt(inputFrequency.getText().toString()));
                        localMed.setFrequencyUnit((spinFrequency.getSelectedItem().toString().equals("Hour(s)") ? true : false));
                        localMed.setEndDate(inputEndDate.getText().toString());

                        
                    }
                    //startAlarm(c);
                    localDB.daointerface().addMedication(localMed);
                    getParentFragmentManager().popBackStackImmediate();
                }
            }
        });
        
        

        return v;
    }

    private void startAlarm(Calendar c) {
        Intent intent = new Intent(getContext().getApplicationContext(),NotificationReceiver.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(getContext().getApplicationContext(),1,intent, 0);

        AlarmManager alarmManager = (AlarmManager) getActivity().getSystemService(Context.ALARM_SERVICE);
        alarmManager.set(AlarmManager.RTC_WAKEUP,c.getTimeInMillis(),pendingIntent);
        Toast.makeText(getContext(),c.get(Calendar.HOUR_OF_DAY)+" "+c.get(Calendar.MINUTE)+" ",Toast.LENGTH_SHORT).show();
    }


}