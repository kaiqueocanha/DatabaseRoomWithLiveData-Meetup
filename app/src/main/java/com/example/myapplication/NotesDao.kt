package com.example.myapplication

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface NotesDao {

    @Query("SELECT * from notes")
    fun getAllNotes() : List<Note>

    @Query("SELECT * from notes")
    fun getAll() : LiveData<List<Note>>

    @Insert
    fun insertNote(notes : Note)

    @Delete
    fun deleteNote(note : Note)

}