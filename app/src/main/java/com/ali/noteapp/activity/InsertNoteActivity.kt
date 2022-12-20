package com.ali.noteapp.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.ali.noteapp.R
import com.ali.noteapp.databinding.ActivityInsertNoteBinding
import com.ali.noteapp.model.Notes
import com.ali.noteapp.viewModels.NoteViewModel
import java.util.*

class InsertNoteActivity : AppCompatActivity() {
    lateinit var binding: ActivityInsertNoteBinding
    lateinit var model: NoteViewModel
    lateinit var title: String
    lateinit var subtitle: String
    lateinit var note: String
    var priority: String = "1"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityInsertNoteBinding.inflate(layoutInflater)
        setContentView(binding.root)

        model = ViewModelProvider(this).get(NoteViewModel::class.java)

        binding.insertIvGreenPr.setOnClickListener {
            binding.insertIvGreenPr.setImageResource(R.drawable.ic_baseline_done_24)
            binding.insertIvYellowPr.setImageResource(0)
            binding.insertIvRedPr.setImageResource(0)
            priority = "1"
        }
        binding.insertIvYellowPr.setOnClickListener {
            binding.insertIvGreenPr.setImageResource(0)
            binding.insertIvYellowPr.setImageResource(R.drawable.ic_baseline_done_24)
            binding.insertIvRedPr.setImageResource(0)
            priority = "2"
        }
        binding.insertIvRedPr.setOnClickListener {
            binding.insertIvGreenPr.setImageResource(0)
            binding.insertIvYellowPr.setImageResource(0)
            binding.insertIvRedPr.setImageResource(R.drawable.ic_baseline_done_24)
            priority = "3"
        }

        binding.insertFabBtn.setOnClickListener {
            title = binding.insertEtTitle.text.toString()
            subtitle = binding.insertEtSubtitle.text.toString()
            note = binding.insertEtNotes.text.toString()
            createNote(title, subtitle, note)
        }
    }

    private fun createNote(title: String, subtitle: String, note: String) {
        var date = Date()
        var sequence: CharSequence = android.text.format.DateFormat.format("MMMM d yyyy", date.time)
        var notes = Notes(0, title, subtitle, sequence.toString(), note, priority)
        model.insertNote(notes)
        Toast.makeText(this, "Note Created Successfully", Toast.LENGTH_LONG).show()
        finish()
    }
}