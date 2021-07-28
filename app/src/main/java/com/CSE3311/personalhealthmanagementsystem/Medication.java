package com.CSE3311.personalhealthmanagementsystem;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

@Entity(foreignKeys = {@ForeignKey(entity = UserAccount.class,
        parentColumns = "userId",
        childColumns = "useMedId",
        onDelete = ForeignKey.CASCADE)
})
public
class Medication {

    @PrimaryKey(autoGenerate = true)
    private int medID;
    @ColumnInfo(index=true)
    private int useMedId;

    private String nameOfMed;
    private String typeOfMed;
    private int quantity;
    private String startTime;
    private String endDate;
    private int frequency;
    private boolean frequencyUnit; //true equals "hours", false equals "minutes"

    public int getMedID() {
        return medID;
    }

    public void setMedID(int medID) {
        this.medID = medID;
    }

    public int getUseMedId() {
        return useMedId;
    }

    public void setUseMedId(int useMedId) {
        this.useMedId = useMedId;
    }

    public String getNameOfMed() {
        return nameOfMed;
    }

    public void setNameOfMed(String nameOfMed) {
        this.nameOfMed = nameOfMed;
    }

    public String getTypeOfMed() {
        return typeOfMed;
    }

    public void setTypeOfMed(String typeOfMed) {
        this.typeOfMed = typeOfMed;
    }

    public int getQuantity() { return quantity; }

    public void setQuantity(int quantity) { this.quantity = quantity; }

    public String getStartTime() { return startTime; }

    public void setStartTime(String startTime) { this.startTime = startTime; }

    public String getEndDate() { return endDate; }

    public void setEndDate(String endDate) { this.endDate = endDate; }

    public int getFrequency() { return frequency; }

    public void setFrequency(int frequency) { this.frequency = frequency; }

    public boolean isFrequencyUnit() { return frequencyUnit; }

    public void setFrequencyUnit(boolean frequencyUnit) { this.frequencyUnit = frequencyUnit; }
}
