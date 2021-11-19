package com.example.notesapp.ViewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.notesapp.Model.Notes
import com.example.notesapp.Repository.NotesRepository
import com.example.notesapp.Room.NotesDATABASE
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class NotesViewModel(application: Application) : AndroidViewModel(application){

    val noteRepository:NotesRepository



    init{
        val dao=NotesDATABASE.getDatabase(application).notesDao()
        noteRepository =  NotesRepository(dao)

    }
    fun getNotes(): LiveData<List<Notes>> {
        return noteRepository.getNotes()
    }

      fun insertNote(note: Notes)=viewModelScope.launch(Dispatchers.IO){
          noteRepository.insertNote(note)
      }

      fun deleteNote(id: Long)=viewModelScope.launch(Dispatchers.IO){
        noteRepository.deleteNote(id)
    }

     fun updateNote(note: Notes)=viewModelScope.launch(Dispatchers.IO){
        noteRepository.updateNote(note)
    }
}