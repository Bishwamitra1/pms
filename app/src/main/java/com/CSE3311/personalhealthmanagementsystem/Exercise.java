package com.CSE3311.personalhealthmanagementsystem;


import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

@Entity(foreignKeys = {@ForeignKey(entity = UserAccount.class,
        parentColumns = "userId",
        childColumns = "useExerId",
        onDelete = ForeignKey.CASCADE)
})

public class Exercise {

    @PrimaryKey(autoGenerate = true)
    private int exerId;
    @ColumnInfo(index=true)
    private int useExerId;

    private String nameOfExercise;
    private String descriptionOfExercise;
    private int caloriesBurned;
    private int heartRate;
    private int lengthOfExercise;

    public int getExerId() {
        return exerId;
    }

    public void setExerId(int exerId) {
        this.exerId = exerId;
    }

    public int getUseExerId() {
        return useExerId;
    }

    public void setUseExerId(int useExerId) {
        this.useExerId = useExerId;
    }

    public String getNameOfExercise() {
        return nameOfExercise;
    }

    public void setNameOfExercise(String nameOfExercise) {
        this.nameOfExercise = nameOfExercise;
    }

    public String getDescriptionOfExercise() { return descriptionOfExercise; }

    public void setDescriptionOfExercise(String descriptionOfExercise) { this.descriptionOfExercise = descriptionOfExercise; }

    public int getCaloriesBurned() {
        return caloriesBurned;
    }

    public void setCaloriesBurned(int caloriesBurned) {
        this.caloriesBurned = caloriesBurned;
    }

    public int getHeartRate() { return heartRate; }

    public void setHeartRate(int heartRate) { this.heartRate = heartRate; }

    public int getLengthOfExercise() { return lengthOfExercise; }

    public void setLengthOfExercise(int lengthOfExercise) { this.lengthOfExercise = lengthOfExercise; }
}
