package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_add_note.*
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread

class AddNoteActivity : AppCompatActivity() {

    lateinit var appDatabase: AppDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_note)

        setupDatabase()

        btnAddNote.setOnClickListener {
            val content = edtContent.text.toString()
            val author = edtAuthor.text.toString()

            val currentNote = Note(content = content, author = author)

            doAsync {
                appDatabase.notesDao().insertNote(currentNote)

                uiThread {
                    finish()
                }
            }
        }

    }

    fun setupDatabase() {
        appDatabase = AppDatabase.getInstance(this)
    }
}
