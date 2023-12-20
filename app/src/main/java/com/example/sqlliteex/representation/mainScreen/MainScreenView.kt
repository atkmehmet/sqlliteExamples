package com.example.sqlliteex.representation.mainScreen
import androidx.compose.runtime.mutableStateOf

import androidx.compose.runtime.getValue

import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.sqlliteex.ApplicationContext
import com.example.sqlliteex.data.local.BookDatabase
import com.example.sqlliteex.data.local.PersonEntity
import com.example.sqlliteex.data.local.PersonRecordDao
import kotlinx.coroutines.launch

class mainScreenView:ViewModel() {
   private var personRecordDao=BookDatabase.getPersonDao(ApplicationContext.getAppContext())
   private var _state by mutableStateOf(mainScreenSatete())
    val state:mainScreenSatete
        get() = _state
    init {
        viewModelScope.launch {
            personRecordDao.insertPerson(PersonEntity(0,"Mehmet","Durmaz"))
        }

    }

    fun onEvent(event: mainScreenEvent){
        when(event){
            is mainScreenEvent.personName ->{
             _state = _state.copy(
                 personName = event.name
             )
            }
            is mainScreenEvent.personSurname->{
                _state = _state.copy(
                    personSurname = event.surname
                )
            }
            is mainScreenEvent.personCommit->{
                viewModelScope.launch {
                 personRecordDao.insertPerson(PersonEntity(0,_state.personName,_state.personSurname))
                }
            }
        }
    }

}