package com.ali.noteapp.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.ali.noteapp.dao.NotesDAO
import com.ali.noteapp.model.Notes

@Database(
    entities = [Notes::class],
    version = 1
)
abstract class NotesDB : RoomDatabase() {
    abstract fun notesDao(): NotesDAO

    companion object {
        @Volatile
        var INSTANCE: NotesDB? = null
        fun getDatabase(context: Context): NotesDB {
            if (INSTANCE == null) {
                synchronized(this) {
                    INSTANCE = buildDatabase(context)
                }
            }
            return INSTANCE!!
        }

        private fun buildDatabase(context: Context): NotesDB {
            return Room.databaseBuilder(
                context.applicationContext, NotesDB::class.java, "Notes_DB"
            )
                .fallbackToDestructiveMigration()
                .allowMainThreadQueries()
                .build()
        }
    }
}