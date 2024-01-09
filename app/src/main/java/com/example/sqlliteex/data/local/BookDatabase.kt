package com.example.sqlliteex.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase


@Database(entities = [PersonEntity::class,ReadBookEntity::class], version = 1, exportSchema = false)
abstract class BookDatabase:RoomDatabase() {
abstract val RecordDao:RecordDao





companion object{

    @Volatile
    private var instancePerson:RecordDao?=null

    fun getDao(context:Context):RecordDao{
        synchronized(this){


       var createInstance= instancePerson
        if (createInstance==null){
            createInstance= createDatabase(context).RecordDao
        }
        return  createInstance
    }
        }



   private fun createDatabase(context: Context):BookDatabase=Room.databaseBuilder(
        context.applicationContext,
        BookDatabase::class.java,
       "BookRecord"
        ).fallbackToDestructiveMigration().build()

}
}