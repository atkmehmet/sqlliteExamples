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
import com.example.sqlliteex.data.local.ReadBookEntity
import kotlinx.coroutines.launch

class mainScreenView:ViewModel() {
   private var personRecordDao=BookDatabase.getPersonDao(ApplicationContext.getAppContext())
   private var bookRecordDao=BookDatabase.getBookdao(ApplicationContext.getAppContext())
   private var _state by mutableStateOf(mainScreenSatete())

    val state:mainScreenSatete
        get() = _state

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
                    _state=_state.copy(
                        personCount = personRecordDao.getPersonCount()
                    )
                }

            }
            is mainScreenEvent.personId->{
                _state=_state.copy(
                    personId = event.id
                )
            }

            is mainScreenEvent.bookName->{
                _state=_state.copy(
                    bookName = event.bookName
                )
            }

            is mainScreenEvent.bookWriter->{
                _state=_state.copy(
                    bookWriter = event.bookWriter
                )
            }
            is mainScreenEvent.addBookgit ->{
                viewModelScope.launch {
                bookRecordDao.InsertReadBook(ReadBookEntity(0,_state.personId,_state.bookName,_state.bookWriter))
                    _state = _state.copy(
                        personCount=bookRecordDao.getCount()
                    )

                }
            }
        }
    }

}