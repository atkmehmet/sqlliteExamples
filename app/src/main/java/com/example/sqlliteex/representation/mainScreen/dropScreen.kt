package com.example.sqlliteex.representation.mainScreen

import android.util.Log
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.material.DropdownMenu
import androidx.compose.material.DropdownMenuItem
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.sqlliteex.domain.model.Person

@Composable
fun MyDropdownMenuScreen(view: mainScreenView ) {
 //   var expanded by remember { mutableStateOf(false) }
    //var selectedItem = viewModel.selectedItem.collectAsState()
   // val myState = viewModel.myMutableState
     val list = view._listPerson.collectAsState(initial = emptyList())



    Column {
        // Display selected item from ViewModel
        Text("Selected Item: ${view.state.currentValue}")
      //  TextField(value = selectedItem.value, onValueChange ={viewModel.updateSelectedItem(it)})
        //OutlinedTextField(value = viewModel.selectedItem.collectAsState().value, onValueChange ={viewModel.updateSelectedItem(it)} )

        // DropdownMenu with items
        Box {
            TextButton(onClick = { view.onEvent(mainScreenEvent.expandedChange(true)) }) {
                Text("Select an item: ${view.state.currentValue}")
            }

            DropdownMenu(
                expanded = view.state.expanded,
                onDismissRequest = { view.onEvent(mainScreenEvent.expandedChange(false)) }
            ) {
                list.value.forEach { itemName ->
                    DropdownMenuItem(onClick = {
                        Log.d("DropdownMenu", "Selected item: $itemName")
                        view.onEvent(mainScreenEvent.expandedChange(false))
                            view.onEvent(mainScreenEvent.currentValue(itemName))
                    }) {
                        Text(itemName)
                    }
                }
            }
        }
    }
}