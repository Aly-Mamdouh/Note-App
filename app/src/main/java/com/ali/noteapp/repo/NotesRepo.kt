package com.ali.noteapp.repo

import android.app.Application
import androidx.lifecycle.LiveData
import com.ali.noteapp.dao.NotesDAO
import com.ali.noteapp.database.NotesDB
import com.ali.noteapp.model.Notes

class NotesRepo {
    var notesDAO: NotesDAO
    var getAllNotes: LiveData<List<Notes>>
    var highToLow: LiveData<List<Notes>>
    var lowToHigh: LiveData<List<Notes>>
    constructor(application: Application) {
        var notedb = NotesDB.getDatabase(application)
        notesDAO = notedb.notesDao()
        getAllNotes = notesDAO.getAllNotes()
        highToLow=notesDAO.highToLow()
        lowToHigh=notesDAO.lowToHigh()
    }

    fun insertNote(notes: Notes) {
        notesDAO.insertNote(notes)
    }

    fun deleteNote(id: Int) {
        notesDAO.deleteNote(id)
    }

    fun updateNote(notes: Notes) {
        notesDAO.updateNote(notes)
    }

}