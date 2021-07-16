package com.CSE3311.personalhealthmanagementsystem;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

@Entity(foreignKeys = {@ForeignKey(entity = UserAccount.class,
        parentColumns = "userId",
        childColumns = "patientId",
        onDelete = ForeignKey.CASCADE)
})
class Doctor {

    @PrimaryKey(autoGenerate = true)
    private int doctorId;
    @ColumnInfo(index=true)
    private int patientId;
    private String doctorName;


    public int getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(int doctorId) {
        this.doctorId = doctorId;
    }

    public int getPatientId() {
        return patientId;
    }

    public void setPatientId(int patientId) {
        this.patientId = patientId;
    }

    public String getDoctorName() {
        return doctorName;
    }

    public void setDoctorName(String doctorName) {
        this.doctorName = doctorName;
    }
}
