package com.example.notesapp.Room

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.notesapp.Model.Notes

@Dao
interface NotesDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
     suspend fun insertNote(note: Notes)

    @Query("delete from notes where id = :id")
      suspend fun deleteNote(id:Long)

    @Update
      suspend fun updateNote(note: Notes)

    @Query("Select * FROM notes")
    fun getNotes():LiveData<List<Notes>>

}