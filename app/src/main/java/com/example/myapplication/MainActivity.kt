package com.example.myapplication

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread

class MainActivity : AppCompatActivity() {

    lateinit var appDatabase: AppDatabase

    private val adapter = Adapter()

    lateinit var notesList: LiveData<List<Note>>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setupDatabase()
        setupRecyclerView()

        notesList.observe(this, Observer {
            it?.let { list ->
                adapter.setData(list)
            }
        })

        fab.setOnClickListener {
            val newNoteActivity = Intent(this, AddNoteActivity::class.java)
            startActivity(newNoteActivity)
        }

    }

    fun setupDatabase() {
        appDatabase = AppDatabase.getInstance(this)
        notesList = appDatabase.notesDao().getAll()
    }

    fun setupRecyclerView() {
        rv.layoutManager = LinearLayoutManager(this)
        rv.adapter = adapter
        resetData()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item?.itemId) {
            R.id.refresh -> {
                resetData()
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun resetData() {

        doAsync {
            val result = queryData()
            uiThread {
                adapter.setData(result)
            }
        }

    }

    private fun queryData(): List<Note> {
        return appDatabase.notesDao().getAllNotes()
    }


}
