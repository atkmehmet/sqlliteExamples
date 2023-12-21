package com.example.sqlliteex.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface PersonRecordDao {
@Insert
suspend fun insertPerson(personEntity: PersonEntity)

@Query("SELECT * FROM Person")
 fun getAllPerson():Flow<List<PersonEntity>>
 @Query("SELECT COUNT(*) FROM Person")
 suspend fun getPersonCount():Int

}