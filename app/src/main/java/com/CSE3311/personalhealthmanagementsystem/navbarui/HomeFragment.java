package com.CSE3311.personalhealthmanagementsystem.navbarui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.CSE3311.personalhealthmanagementsystem.R;

import java.util.Calendar;
import java.util.Date;

import devs.mulham.horizontalcalendar.HorizontalCalendar;
import devs.mulham.horizontalcalendar.HorizontalCalendarListener;

public class HomeFragment extends Fragment {


    public HomeFragment() {
        // Required empty public constructor
    }
    //private TextView txtv;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_home, container, false);

//        txtv = v.findViewById(R.id.healthText);
//        String toText = String.valueOf(userId);
//        txtv.setText("The current user's id is " + toText +"\nThe current users username is " + localDB.daointerface().getUserById(userId).getUsername());

        //end after 1 month
        Calendar endDate = Calendar.getInstance();
        endDate.add(Calendar.MONTH, 1);

        //start before 1 month
        Calendar startDate = Calendar.getInstance();
        startDate.add(Calendar.MONTH, -1);

        HorizontalCalendar horizontalCalendar = new HorizontalCalendar.Builder(v,R.id.calendarView)
                .startDate(startDate.getTime())
                .endDate(endDate.getTime())
                .build();

        horizontalCalendar.setCalendarListener(new HorizontalCalendarListener() {
            @Override
            public void onDateSelected(Date date, int position) {

            }
        });
        return v;
    }
}