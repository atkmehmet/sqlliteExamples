package com.example.sqlliteex.representation.mainScreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.sqlliteex.ApplicationContext
import com.example.sqlliteex.data.local.BookDatabase
import com.example.sqlliteex.data.local.PersonEntity
import com.example.sqlliteex.data.local.PersonRecordDao
import kotlinx.coroutines.launch

class mainScreenView:ViewModel() {
    val personRecordDao=BookDatabase.getPersonDao(ApplicationContext.getAppContext())

    init {
        viewModelScope.launch {
            personRecordDao.insertPerson(PersonEntity(0,"Mehmet","Durmaz"))
        }

    }

}