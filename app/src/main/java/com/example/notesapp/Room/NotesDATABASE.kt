package com.example.notesapp.Room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.Room.databaseBuilder
import androidx.room.RoomDatabase
import com.example.notesapp.Model.Notes

// Annotates class to be a Room Database with a table (entity) of the Notes class
@Database(entities = [Notes::class], version = 1)
public abstract class NotesDATABASE  : RoomDatabase() {

    abstract fun notesDao(): NotesDAO

    companion object {
        // Singleton prevents multiple instances of database opening at the
        // same time.
        @Volatile
        private var INSTANCE: NotesDATABASE? = null

        fun getDatabase(context: Context): NotesDATABASE {
            // if the INSTANCE is null, then create the database,
            // if it is not, then return it

            if(INSTANCE==null){
                synchronized(this) {
                    INSTANCE = Room.databaseBuilder(
                        context.applicationContext,
                        NotesDATABASE::class.java,
                        "notes_database"
                    ).build()
                }
            }
            return INSTANCE!!

        }
    }


}