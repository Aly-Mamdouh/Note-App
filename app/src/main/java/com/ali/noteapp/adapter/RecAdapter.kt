package com.ali.noteapp.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.ali.noteapp.MainActivity
import com.ali.noteapp.R
import com.ali.noteapp.activity.InsertNoteActivity
import com.ali.noteapp.activity.UpdateNoteActivity
import com.ali.noteapp.databinding.ItemNoteLayoutBinding
import com.ali.noteapp.model.Notes

class RecAdapter(val context: Context) : RecyclerView.Adapter<RecAdapter.NotesViewHolder>() {
    var lst = emptyList<Notes>()
    var allNotesItem = emptyList<Notes>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NotesViewHolder {
        val bind = ItemNoteLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return NotesViewHolder(bind)
    }

    fun searchNotes(filterNotes:List<Notes>){
        this.lst=filterNotes
        notifyDataSetChanged()
    }
    override fun onBindViewHolder(holder: NotesViewHolder, position: Int) {
        val notes = lst.get(position)
        if (notes.notePriority.equals("1")) {
            holder.binding.itemPriority.setBackgroundResource(R.drawable.green_shape)
        } else if (notes.notePriority.equals("2")) {
            holder.binding.itemPriority.setBackgroundResource(R.drawable.yellow_shape)
        } else if (notes.notePriority.equals("3")) {
            holder.binding.itemPriority.setBackgroundResource(R.drawable.red_shape)
        }

        holder.binding.itemTitle.text = notes.noteTitle
        holder.binding.itemSubtitle.text = notes.noteSubtitle
        holder.binding.itemDate.text = notes.noteDate
        holder.itemView.setOnClickListener {
            val intent = Intent(context, UpdateNoteActivity::class.java)

            intent.putExtra("id", notes.id)
            intent.putExtra("title", notes.noteTitle)
            intent.putExtra("subtitle", notes.noteSubtitle)
            intent.putExtra("priority", notes.notePriority)
            intent.putExtra("notes", notes.notes)

            context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {
        return lst.size
    }

    fun setlist(list: List<Notes>) {
        this.lst = list
        notifyDataSetChanged()
    }

    class NotesViewHolder(val binding: ItemNoteLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {

    }

}