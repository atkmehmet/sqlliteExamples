package com.example.sqlliteex.data.local

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [PersonEntity::class,ReadBookEntity::class], version = 1, exportSchema = false)
abstract class BookDatabase:RoomDatabase() {
abstract val personRecordDao:PersonRecordDao
abstract val recordDao:BookRecordDao
}