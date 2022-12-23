package com.example.notebook.ViewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.example.notebook.Repository.NotesRepository

import com.example.notebook.Room.NotesDatabase
import com.example.notebook.model.Notes

class NotesViewModel(application: Application):AndroidViewModel(application)
{
    val repository  : NotesRepository
    init {
        val dao = NotesDatabase.getDatabase(application).notesDao()
        repository= NotesRepository(dao)
    }

    fun insertNote(notes: Notes){
        repository.insertNote(notes)
    }
    fun getFinanceNotes(data:String):LiveData<List<Notes>>
    {
        return  repository.getFinanceNotes(data)
    }
    fun getScheduleNotes(data:String):LiveData<List<Notes>>
    {
        return  repository.getScheduleNotes(data)
    }


    fun getTODoNotes(data:String):LiveData<List<Notes>>
    {
        return  repository.getTODoNotes(data)
    }


    fun getBasicNotes(data:String):LiveData<List<Notes>>
    {
        return  repository.getBasicNotes(data)
    }

    fun getQuotesNotes(data:String):LiveData<List<Notes>>
    {
        return  repository.getQuotesNotes(data)
    }

    fun deleteNote(id:Int){
        repository.deleteNote(id)
    }
    fun updateNote(notes: Notes){
        repository.updateNote(notes)
    }
    fun getAllNotes():LiveData<List<Notes>>{
        return  repository.getAllNotes()
    }

}