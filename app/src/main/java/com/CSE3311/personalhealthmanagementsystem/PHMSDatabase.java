package com.CSE3311.personalhealthmanagementsystem;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {UserAccount.class, Doctor.class, Note.class, Medication.class, Exercise.class, Food.class}, version=2)
public abstract class PHMSDatabase extends RoomDatabase {
    public abstract DaoInterface daointerface();
}
