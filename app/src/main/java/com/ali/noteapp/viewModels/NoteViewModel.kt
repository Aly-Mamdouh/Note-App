package com.ali.noteapp.viewModels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.ali.noteapp.model.Notes
import com.ali.noteapp.repo.NotesRepo

class NoteViewModel : AndroidViewModel {

    var noterepo: NotesRepo
    var getAllNotes: LiveData<List<Notes>>
    var highToLow: LiveData<List<Notes>>
    var lowToHigh: LiveData<List<Notes>>

    constructor(application: Application) : super(application) {
        noterepo = NotesRepo(application)
        getAllNotes = noterepo.getAllNotes
        highToLow=noterepo.highToLow
        lowToHigh=noterepo.lowToHigh
    }

    fun insertNote(notes: Notes) {
        noterepo.insertNote(notes)
    }

    fun deleteNote(id: Int) {
        noterepo.deleteNote(id)
    }

    fun updateNote(notes: Notes) {
        noterepo.updateNote(notes)
    }
}