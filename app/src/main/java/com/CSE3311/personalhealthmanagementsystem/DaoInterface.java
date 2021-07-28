package com.CSE3311.personalhealthmanagementsystem;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

@Dao
public interface DaoInterface {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void addUser(UserAccount user);

    @Insert
    void addDoctor(Doctor doctor);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void addNote(Note note);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void addMedication(Medication medication);

    @Query("SELECT * FROM user_accounts WHERE username = :userName AND password = :password")
    UserAccount getUser(String userName, String password);

    @Query("SELECT * FROM user_accounts WHERE userID = :userId")
    UserAccount getUserById(int userId);

    @Query("SELECT * FROM note WHERE authorId= :userId")
    List<Note> getNotesById(int userId);

    @Query("SELECT * FROM medication WHERE useMedId= :userId")
    List<Medication> getMedicationsById(int userId);

    @Delete
    void deleteMedication(Medication medication);

    //USE THIS METHOD WITH EXTREME RISK!!!!1!!!!1
    @Query("DELETE FROM user_accounts")
    void nukeTable();

}
