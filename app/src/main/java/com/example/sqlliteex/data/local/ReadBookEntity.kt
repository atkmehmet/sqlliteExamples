package com.example.sqlliteex.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.sqlliteex.domain.model.ReadBook

@Entity(tableName = "ReadBook")
data class ReadBookEntity (
   @PrimaryKey(autoGenerate = true)
   val Id:Int,
   val PersonId:String,
   val bookName:String,
   val bookWriter:String

)
fun ReadBookEntity.toConvertModel():ReadBook{
   return ReadBook(
   Id=Id,
   PersonId=PersonId,
   bookName=bookName,
   bookWriter=bookWriter
   )
}