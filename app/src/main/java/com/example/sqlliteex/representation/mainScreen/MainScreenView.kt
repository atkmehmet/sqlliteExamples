package com.example.sqlliteex.representation.mainScreen
import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf

import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember

import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.sqlliteex.ApplicationContext
import com.example.sqlliteex.data.local.BookDatabase
import com.example.sqlliteex.data.local.PersonEntity
import com.example.sqlliteex.data.local.PersonRecordDao
import com.example.sqlliteex.data.local.ReadBookEntity
import com.example.sqlliteex.data.local.toConvertPerson
import com.example.sqlliteex.domain.model.Person
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.emptyFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class mainScreenView:ViewModel() {
   private var personRecordDao=BookDatabase.getPersonDao(ApplicationContext.getAppContext())
   private var bookRecordDao=BookDatabase.getBookdao(ApplicationContext.getAppContext())
   private var _state by mutableStateOf(mainScreenSatete())
   private var _listPerson = MutableStateFlow(emptyFlow<List<Person>>())
   private var _current= MutableStateFlow("")
    val lisPerson = _listPerson.asStateFlow()
     val  current = _current.asStateFlow()


    val state:mainScreenSatete
        get() = _state

    init {

      viewModelScope.launch {
          _listPerson.value = personRecordDao.getAllPerson().map {
              it.map {
                  it.toConvertPerson()
              }
          }
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
            is mainScreenEvent.addBook->{
                viewModelScope.launch {
                bookRecordDao.InsertReadBook(ReadBookEntity(0,_state.personId,_state.bookName,_state.bookWriter))
                    _state = _state.copy(
                        personCount=bookRecordDao.getCount()
                    )

                }
            }
            is mainScreenEvent.expandedChange->{
                _state = _state.copy(
                    expanded = event.expanded
                )
            }
            is mainScreenEvent.currentValue->{
                Log.d("DropdownMenu",event.dropMenuValue)
                _state = _state.copy(
                    currentValue = event.dropMenuValue
                )
                Log.d("DropdownMenu","value"+_state.currentValue)

            }
        }
    }

    fun changeValue(value:String){
        _current.value = value
    }

}