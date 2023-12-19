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
suspend fun getAllPerson():Flow<List<PersonEntity>>
}