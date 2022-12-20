package com.ali.noteapp.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.ali.noteapp.R
import com.ali.noteapp.databinding.ActivityUpdateNoteBinding
import com.ali.noteapp.model.Notes
import com.ali.noteapp.viewModels.NoteViewModel
import com.google.android.material.bottomsheet.BottomSheetDialog
import java.util.*

class UpdateNoteActivity : AppCompatActivity() {
    lateinit var binding: ActivityUpdateNoteBinding
    lateinit var model: NoteViewModel
    var priority: String = "1"
    var id: Int = 0
    lateinit var stitle: String
    lateinit var ssubtitle: String
    lateinit var sprio: String
    lateinit var snote: String
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUpdateNoteBinding.inflate(layoutInflater)
        setContentView(binding.root)



        id = intent.getIntExtra("id", 0)
        stitle = intent.getStringExtra("title").toString()
        ssubtitle = intent.getStringExtra("subtitle").toString()
        snote = intent.getStringExtra("notes").toString()
        sprio = intent.getStringExtra("priority").toString()

        binding.updateEtTitle.setText(stitle)
        binding.updateEtSubtitle.setText(ssubtitle)
        binding.updateEtNotes.setText(snote)

        if(sprio.equals("1")){
            binding.updateIvGreenPr.setImageResource(R.drawable.ic_baseline_done_24)
        }else if(sprio.equals("2")){
            binding.updateIvYellowPr.setImageResource(R.drawable.ic_baseline_done_24)
        }else if(sprio.equals("3")){
            binding.updateIvRedPr.setImageResource(R.drawable.ic_baseline_done_24)
        }

        model = ViewModelProvider(this).get(NoteViewModel::class.java)

        binding.updateIvGreenPr.setOnClickListener {
            binding.updateIvGreenPr.setImageResource(R.drawable.ic_baseline_done_24)
            binding.updateIvYellowPr.setImageResource(0)
            binding.updateIvRedPr.setImageResource(0)
            priority = "1"
        }
        binding.updateIvYellowPr.setOnClickListener {
            binding.updateIvGreenPr.setImageResource(0)
            binding.updateIvYellowPr.setImageResource(R.drawable.ic_baseline_done_24)
            binding.updateIvRedPr.setImageResource(0)
            priority = "2"
        }
        binding.updateIvRedPr.setOnClickListener {
            binding.updateIvGreenPr.setImageResource(0)
            binding.updateIvYellowPr.setImageResource(0)
            binding.updateIvRedPr.setImageResource(R.drawable.ic_baseline_done_24)
            priority = "3"
        }

        binding.updateFabBtn.setOnClickListener {

            var title:String = binding.updateEtTitle.text.toString()
            var subtitle:String = binding.updateEtSubtitle.text.toString()
            var note :String = binding.updateEtNotes.text.toString()
            updateNote(title, subtitle, note)
        }
    }

    private fun updateNote(title: String, subtitle: String, note: String) {
        var date = Date()
        var sequence: CharSequence = android.text.format.DateFormat.format("MMMM d yyyy", date.time)
        var notes = Notes(id, title, subtitle, sequence.toString(), note, priority)
        model.updateNote(notes)
        Toast.makeText(this, "Note Updated Successfully", Toast.LENGTH_LONG).show()
        finish()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.delete_icon,menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(item.itemId==R.id.delete){
            val dialog = BottomSheetDialog(this,R.style.bottom_sheet)
            val view :View = layoutInflater.inflate(R.layout.delete_buttom_sheet, findViewById(R.id.buttom_sheet))
            dialog.setContentView(view)
            val no:TextView=view.findViewById(R.id.del_sh_no)
            val yes:TextView=view.findViewById(R.id.del_sh_yes)

            yes.setOnClickListener {
                model.deleteNote(id)
                finish()
            }

            no.setOnClickListener {
                dialog.dismiss()
            }
            dialog.show()
        }

        return true
    }
}