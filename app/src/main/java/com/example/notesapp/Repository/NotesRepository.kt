package com.example.notesapp.Repository

import androidx.lifecycle.LiveData
import com.example.notesapp.Model.Notes
import com.example.notesapp.Room.NotesDAO

class NotesRepository(val notesDao: NotesDAO) {

    fun getNotes(): LiveData<List<Notes>>{
        return notesDao.getNotes()
    }

     suspend fun insertNote(note: Notes){
        notesDao.insertNote(note)
    }

     suspend fun deleteNote(id: Long){
        notesDao.deleteNote(id)
    }

      suspend fun updateNote(note: Notes){
        notesDao.updateNote(note)
    }


}