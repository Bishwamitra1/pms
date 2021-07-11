package com.CSE3311.personalhealthmanagementsystem;

import androidx.room.Dao;
import androidx.room.Insert;

@Dao
public interface DaoInterface {

    @Insert
    void addUser(UserAccount user);

    @Insert
    void addDoctor(Doctor doctor);

}
