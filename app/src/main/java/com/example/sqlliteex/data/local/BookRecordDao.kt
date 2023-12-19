package com.example.sqlliteex.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface BookRecordDao {
@Insert
suspend fun InsertReadBook(bookEntity: ReadBookEntity)
@Query("SELECT * FROM ReadBook WHERE PersonId=:PersonId")
suspend fun getBooksByPerson(PersonId:Int):Flow<List<ReadBookEntity>>

@Query("SELECT * FROM ReadBook order by id desc")
suspend fun getallReadBooks():Flow<List<ReadBookEntity>>


}