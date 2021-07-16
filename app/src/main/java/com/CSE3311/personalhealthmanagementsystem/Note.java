package com.CSE3311.personalhealthmanagementsystem;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

@Entity(foreignKeys = {@ForeignKey(entity = UserAccount.class,
        parentColumns = "userId",
        childColumns = "authorId",
        onDelete = ForeignKey.CASCADE)
})
public class Note {

    @PrimaryKey(autoGenerate = true)
    private int noteId;
    @ColumnInfo(index=true)
    private int authorId;
    private String noteTitle;
    private String noteContent;
    private String type;

    public int getNoteId() {
        return noteId;
    }

    public void setNoteId(int noteId) {
        this.noteId = noteId;
    }

    public int getAuthorId() {
        return authorId;
    }

    public void setAuthorId(int authorId) {
        this.authorId = authorId;
    }

    public String getNoteTitle() {
        return noteTitle;
    }

    public void setNoteTitle(String noteTitle) {
        this.noteTitle = noteTitle;
    }

    public String getNoteContent() {
        return noteContent;
    }

    public void setNoteContent(String noteContent) {
        this.noteContent = noteContent;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
