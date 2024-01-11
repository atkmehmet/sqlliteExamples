package com.example.sqlliteex.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface RecordDao {
@Insert
suspend fun insertPerson(personEntity: PersonEntity)

@Query("SELECT * FROM Person")
 fun getAllPerson():Flow<List<PersonEntity>>
 @Query("SELECT COUNT(*) FROM Person")
 suspend fun getPersonCount():Int


 @Insert
 suspend fun InsertReadBook(bookEntity: ReadBookEntity)
 @Query("SELECT * FROM ReadBook WHERE PersonId=:PersonId")
 fun getBooksByPerson(PersonId:Int):Flow<List<ReadBookEntity>>

 @Query("SELECT * FROM ReadBook ")
suspend fun getReadBooks():List<ReadBookEntity>

 @Query("SELECT * FROM ReadBook order by Id desc ")
 fun getallReadBooks():Flow<List<ReadBookEntity>>

 @Query("select count (*) from ReadBook ")
 suspend fun getCount():Int


}