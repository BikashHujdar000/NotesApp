package com.example.notebook.Repository

import androidx.lifecycle.LiveData
import com.example.notebook.Room.NotesDAO
import com.example.notebook.model.Notes

class NotesRepository(val notesDAO: NotesDAO) {

    fun getAllNotes():LiveData<List<Notes>>
    {
        return notesDAO.getAllNotes()
    }
    fun insertNote(notes: Notes){
        return notesDAO.insertNote(notes)
    }
    fun updateNote(notes: Notes){
        return notesDAO.updateNote(notes)
    }
    fun getFinanceNotes(data:String):LiveData<List<Notes>>
    {
        return notesDAO.getFinanceNotes(data)
    }

    fun getScheduleNotes(data:String):LiveData<List<Notes>>
    {
        return  notesDAO.getScheduleNotes(data)
    }


    fun getTODoNotes(data:String):LiveData<List<Notes>>
    {
        return  notesDAO.getTODoNotes(data)
    }


    fun getBasicNotes(data:String):LiveData<List<Notes>>
    {
        return  notesDAO.getBasicNotes(data)
    }


    fun getQuotesNotes(data:String):LiveData<List<Notes>>
    {
        return  notesDAO.getQuotesNotes(data)
    }
    fun deleteNote(id:Int ?){
     if (id!= null)
     {
         notesDAO.deleteNote(id)
     }
    }



}