package com.CSE3311.personalhealthmanagementsystem;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
class Doctor {
    @PrimaryKey
    private int doctorID;


    public int getDoctorID() {
        return doctorID;
    }

    public void setDoctorID(int doctorID) {
        this.doctorID = doctorID;
    }
    //TODO
    //Add getters and setters and variables
}
