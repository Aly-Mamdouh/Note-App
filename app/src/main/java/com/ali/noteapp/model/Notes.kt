package com.ali.noteapp.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "notes_table")
data class Notes(
    @PrimaryKey(autoGenerate = true)
    var id: Int,
    var noteTitle: String,
    var noteSubtitle: String,
    var noteDate: String,
    var notes: String,
    var notePriority: String
)
