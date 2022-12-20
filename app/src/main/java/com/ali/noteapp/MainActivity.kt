package com.ali.noteapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.SearchView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.ali.noteapp.activity.InsertNoteActivity
import com.ali.noteapp.adapter.RecAdapter
import com.ali.noteapp.databinding.ActivityMainBinding
import com.ali.noteapp.model.Notes
import com.ali.noteapp.viewModels.NoteViewModel

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    lateinit var model: NoteViewModel
    lateinit var adapter:RecAdapter
    var filterAllNoates= emptyList<Notes>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        model = ViewModelProvider(this).get(NoteViewModel::class.java)

         adapter = RecAdapter(this)


        binding.mainFilterNoFilter.setOnClickListener {
            loadData(0);
            binding.mainFilterNoFilter.setBackgroundResource(R.drawable.filter_selected_shape)
            binding.mainFilterHtol.setBackgroundResource(R.drawable.filter_shape)
            binding.mainFilterLtoh.setBackgroundResource(R.drawable.filter_shape)
        }

        binding.mainFilterHtol.setOnClickListener {
            loadData(1);
            binding.mainFilterNoFilter.setBackgroundResource(R.drawable.filter_shape)
            binding.mainFilterHtol.setBackgroundResource(R.drawable.filter_selected_shape)
            binding.mainFilterLtoh.setBackgroundResource(R.drawable.filter_shape)
        }
        binding.mainFilterLtoh.setOnClickListener {
            loadData(2);
            binding.mainFilterNoFilter.setBackgroundResource(R.drawable.filter_shape)
            binding.mainFilterHtol.setBackgroundResource(R.drawable.filter_shape)
            binding.mainFilterLtoh.setBackgroundResource(R.drawable.filter_selected_shape)
        }

        binding.mainFabBtn.setOnClickListener {
            startActivity(Intent(this, InsertNoteActivity::class.java))
        }

        model.getAllNotes.observe(this, Observer {
            setAdapter(it.toList())
            filterAllNoates=it.toList()
        })

    }

    private fun loadData(i: Int) {
        if(i==0){
            model.getAllNotes.observe(this, Observer {
                setAdapter(it.toList())
                filterAllNoates=it.toList()
            })
        }else if(i==1){
            model.highToLow.observe(this, Observer {
                setAdapter(it.toList())
                filterAllNoates=it.toList()
            })
        }else if(i==2){
            model.lowToHigh.observe(this, Observer {
                setAdapter(it.toList())
                filterAllNoates=it.toList()
            })
        }
    }

    private fun setAdapter(it: List<Notes>) {
        binding.mainRv.layoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
        adapter.setlist(it)
        binding.mainRv.adapter = adapter
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.search_icon,menu)
        val menuItem:MenuItem= menu.findItem(R.id.search_item)
        val searchView:SearchView= menuItem.actionView as SearchView
        searchView.queryHint = "Search Note Here..."

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {

            override fun onQueryTextChange(newText: String): Boolean {
                notesFilter(newText)
                return false
            }

            override fun onQueryTextSubmit(query: String): Boolean {
                return false
            }

        })

        return super.onCreateOptionsMenu(menu)
    }

    private fun notesFilter(newText: String) {
        val filterNames= ArrayList<Notes>()
        for(notes in filterAllNoates){
            if(notes.noteTitle.contains(newText)||notes.noteSubtitle.contains(newText)){
                filterNames.add(notes)
            }
        }
        adapter.searchNotes(filterNames)
    }
}