package com.example.sqlliteex.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [PersonEntity::class,ReadBookEntity::class], version = 1, exportSchema = false)
abstract class BookDatabase:RoomDatabase() {
abstract val personRecordDao:PersonRecordDao
abstract val recordDao:BookRecordDao

companion object{

    @Volatile
    private var instancePerson:PersonRecordDao?=null
    private  var instanceBook:BookRecordDao?=null
    fun getPersonDao(context:Context):PersonRecordDao{
       var createInstance= instancePerson
        if (createInstance==null){
            createInstance= createDatabase(context).personRecordDao
        }
        return  createInstance
    }

    fun getBookdao(context: Context):BookRecordDao{
        var createInstance= instanceBook
        if (createInstance==null){
             createInstance= createDatabase(context).recordDao
        }
        return createInstance
    }

   private fun createDatabase(context: Context):BookDatabase=Room.databaseBuilder(
        context.applicationContext,
        BookDatabase::class.java,
       "BookRecord"
        ).fallbackToDestructiveMigration().build()

}
}