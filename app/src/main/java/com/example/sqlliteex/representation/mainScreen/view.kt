package com.example.sqlliteex.representation.mainScreen

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel

class MyViewModel : ViewModel() {
    // MutableState for the selected item
    private val _selectedItem = mutableStateOf("None")
    val selectedItem: State<String> = _selectedItem

    // Function to update the selected item
    fun updateSelectedItem(newItem: String) {
        _selectedItem.value = newItem
    }
}