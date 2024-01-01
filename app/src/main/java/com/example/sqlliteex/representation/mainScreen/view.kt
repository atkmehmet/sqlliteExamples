package com.example.sqlliteex.representation.mainScreen

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue

import androidx.compose.runtime.setValue
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class MyViewModel : ViewModel() {




    var textValue by mutableStateOf("")
        private set

    // Function to update the text value
    fun updateTextValue(newText: String) {
        textValue = newText
    }



    var myMutableState by mutableStateOf("Initial Value")
        private set

    // Function to update the mutable state
    fun updateMutableState(newValue: String) {
        myMutableState = newValue
    }

    // MutableState for the selected item
    private var _selectedItem = MutableStateFlow("")
    val selectedItem =_selectedItem.asStateFlow()

  //  val selectedItem by viewModel.selectedItem.collectAsState()

    // Function to update the selected item
    fun updateSelectedItem(newItem: String) {
        Log.d("DropdownMenu","view")
        _selectedItem.update {
            newItem
        }
      //  _selectedItem.value = newItem
        //Log.d("DropdownMenu",newItem)
        Log.d("DropdownMenu",_selectedItem.value)
    }
}