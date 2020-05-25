package com.example.myapplication

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity(tableName = "notes")
class Note(
    @PrimaryKey
    @ColumnInfo(name = "id")
    var uuid: String = UUID.randomUUID().toString(),
    var content: String = "",
    var author: String = ""
)