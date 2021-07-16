package com.CSE3311.personalhealthmanagementsystem;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Flowable;
import io.reactivex.Single;

@Dao
public interface DaoInterface {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void addUser(UserAccount user);

    @Insert
    void addDoctor(Doctor doctor);

    @Insert
    void addNote(Note note);

    @Query("SELECT * FROM user_accounts WHERE username = :userName AND password = :password")
    UserAccount getUser(String userName, String password);

    @Query("SELECT * FROM user_accounts WHERE userID = :userId")
    UserAccount getUserById(int userId);

    @Query("SELECT * FROM note WHERE authorId= :userId")
    List<Note> getNotesById(int userId);


    //USE THIS METHOD WITH EXTREME RISK!!!!1!!!!1
    @Query("DELETE FROM user_accounts")
    public void nukeTable();

}
