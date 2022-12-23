package com.example.notebook.Room

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.notebook.model.Notes



@Dao
interface NotesDAO {

   @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertNote( notes: Notes )

   @Query("SELECT * FROM Notes")
   fun getAllNotes(): LiveData<List<Notes>>

   @Query("DELETE FROM Notes WHERE id=:id")
   fun deleteNote(id:Int)

   @Update(onConflict = OnConflictStrategy.REPLACE)
   fun updateNote(notes: Notes)

   @Query("SELECT * FROM Notes WHERE  category=:data")
   fun getFinanceNotes(data:String):LiveData<List<Notes>>

   @Query("SELECT * FROM Notes WHERE  category=:data")
   fun getScheduleNotes(data:String):LiveData<List<Notes>>

   @Query("SELECT * FROM Notes WHERE  category=:data")
   fun getTODoNotes(data:String):LiveData<List<Notes>>

   @Query("SELECT * FROM Notes WHERE  category=:data")
   fun getBasicNotes(data:String):LiveData<List<Notes>>

   @Query("SELECT * FROM Notes WHERE  category=:data")
   fun getQuotesNotes(data:String):LiveData<List<Notes>>


//








}