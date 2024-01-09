package com.example.sqlliteex.representation.mainScreen
import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf

import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember

import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.sqlliteex.ApplicationContext
import com.example.sqlliteex.data.local.BookDatabase
import com.example.sqlliteex.data.local.BookRecordDao
import com.example.sqlliteex.data.local.PersonEntity

import com.example.sqlliteex.data.local.ReadBookEntity
import com.example.sqlliteex.data.local.RecordDao
import com.example.sqlliteex.data.local.toConvertModel
import com.example.sqlliteex.data.local.toConvertPerson
import com.example.sqlliteex.domain.model.Person
import com.example.sqlliteex.domain.model.ReadBook
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.emptyFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class mainScreenView:ViewModel() {
   private lateinit var personRecordDao: RecordDao
    var  listBook:Flow<List<ReadBookEntity>> = emptyFlow()


    //   private var bookRecordDao=BookDatabase.getBookdao(ApplicationContext.getAppContext())
   private var _state by mutableStateOf(mainScreenSatete())
 val state:mainScreenSatete
     get() = _state
    init {
            try {
                personRecordDao =BookDatabase.getDao(ApplicationContext.getAppContext())
                   viewModelScope.launch {
                       listBook =  personRecordDao.getallReadBooks()
                   }

            }
            catch (ex:Exception){

                _state = _state.copy(
                    error = ex.toString()
                )

            }
    }


  val _listPerson : Flow<List<String>> = personRecordDao.getAllPerson().map {
     it.map {
         it.toConvertPerson()
     }.map {
         it.id.toString() +"="+it.name+it.surname

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

                 try {
                Log.d("PersonInsert",_state.personName+_state.personSurname)
                viewModelScope.launch {
                 personRecordDao.insertPerson(PersonEntity(0,_state.personName,_state.personSurname))
                    _state=_state.copy(
                        personCount = personRecordDao.getCount()
                    )


                    _state = _state.copy(
                        personName = "",
                        personSurname = ""
                    )
                }

                 }
                 catch (ex:Exception){
                     _state = _state.copy(
                         error = ex.toString()
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
                try {

                viewModelScope.launch {
                personRecordDao.InsertReadBook(ReadBookEntity(0,state.currentValue,_state.bookName,_state.bookWriter))

                    _state = _state.copy(
                        bookName = "",
                        bookWriter = "",
                        currentValue = "",
                        personCount = personRecordDao.getCount()

                    )

                }

                }
                catch (ex:Exception)
                {
                    _state=_state.copy(
                        error = ex.toString()
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

}