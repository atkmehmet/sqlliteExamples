package com.example.sqlliteex.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "ReadBook")
data class ReadBookEntity (
   @PrimaryKey(autoGenerate = true)
   val Id:Int,
   val PersonId:Int,
   val bookName:String,
   val bookWriter:String

)