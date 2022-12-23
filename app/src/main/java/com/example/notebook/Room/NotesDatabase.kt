package com.example.notebook.Room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.notebook.model.Notes

@Database(entities = [Notes::class], version = 1)
abstract  class NotesDatabase :RoomDatabase(){
    abstract  fun notesDao() : NotesDAO
    companion object
    {
        private  var INSTANCE : NotesDatabase? =  null

        fun getDatabase(context: Context):NotesDatabase{

            if ( INSTANCE == null)
            {
                synchronized(this) {
                    INSTANCE = Room.databaseBuilder(
                        context,
                        NotesDatabase::class.java,
                        "noteDatabase"
                    )
                        .allowMainThreadQueries()
                        .build()
                }
            }

            return  INSTANCE!!
        }
    }
}