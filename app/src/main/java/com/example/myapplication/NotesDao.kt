package com.example.myapplication

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface NotesDao {

    @Query("SELECT * from notes")
    fun getAllNotes(): List<Note>

    @Insert
    fun insertNote(notes: Note)

    @Delete
    fun deleteNote(note: Note)

}