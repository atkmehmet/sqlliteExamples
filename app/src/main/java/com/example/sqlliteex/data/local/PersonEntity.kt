package com.example.sqlliteex.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.sqlliteex.domain.model.Person

@Entity(tableName = "Person")
data class PersonEntity (
    @PrimaryKey(autoGenerate = true)
    val id:Int,
    val personName: String,
    val personSurname:String
)
fun PersonEntity.toConvertPerson(): Person{
    return  Person(
        id=id,
        name = personName,
        surname=personSurname
    )
}