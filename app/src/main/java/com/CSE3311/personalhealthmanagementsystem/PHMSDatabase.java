package com.CSE3311.personalhealthmanagementsystem;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {UserAccount.class, Doctor.class, Note.class}, version=1)
public abstract class PHMSDatabase extends RoomDatabase {
    public abstract DaoInterface daointerface();
}
