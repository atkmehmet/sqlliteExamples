package com.example.sqlliteex.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Person")
data class Person (
    @PrimaryKey(autoGenerate = true)
    val id:Int,
    val personName: String,
    val personLastName:String
)