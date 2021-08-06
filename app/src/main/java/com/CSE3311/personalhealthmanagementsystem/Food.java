package com.CSE3311.personalhealthmanagementsystem;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

@Entity(foreignKeys = {@ForeignKey(entity = UserAccount.class,
        parentColumns = "userId",
        childColumns = "useFoodId",
        onDelete = ForeignKey.CASCADE)
})

public class Food {
    @PrimaryKey(autoGenerate = true)
    private int foodId;
    @ColumnInfo(index=true)
    private int useFoodId;

    private String favoriteFood;
    private int calorieCount;
    private int microNutrientsCount;

    public int getFoodId() {
        return foodId;
    }

    public void setFoodId(int exerId) {
        this.foodId = exerId;
    }

    public int getUseFoodId() {
        return useFoodId;
    }

    public void setUseFoodId(int useFoodId) {
        this.useFoodId = useFoodId;
    }

    public String getFavoriteFood() {
        return favoriteFood;
    }

    public void setFavoriteFood(String favoriteFood) {
        this.favoriteFood = favoriteFood;
    }

    public int getCalorieCount() { return calorieCount; }

    public void setCalorieCount(int calorieCount) { this.calorieCount = calorieCount; }

    public int getMicroNutrientsCount() {
        return microNutrientsCount;
    }

    public void setMicroNutrientsCount(int microNutrientsCount) {
        this.microNutrientsCount = microNutrientsCount;
    }

}
