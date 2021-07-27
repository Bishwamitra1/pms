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
}
