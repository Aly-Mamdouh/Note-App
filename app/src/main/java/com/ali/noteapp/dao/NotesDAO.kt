package com.ali.noteapp.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.ali.noteapp.model.Notes

@Dao
interface NotesDAO {
    @Query("select * from notes_table")
    fun getAllNotes(): LiveData<List<Notes>>

    @Query("select * from notes_table order by notePriority desc")
    fun highToLow(): LiveData<List<Notes>>

    @Query("select * from notes_table order by notePriority asc")
    fun lowToHigh(): LiveData<List<Notes>>

    @Insert
    fun insertNote(note: Notes)

    @Query("delete from notes_table where id=:id")
    fun deleteNote(id: Int)

    @Update
    fun updateNote(note: Notes)
}