package com.example.sqlliteex.representation.mainScreen

import android.util.Log
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.material.DropdownMenu
import androidx.compose.material.DropdownMenuItem
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import com.example.sqlliteex.domain.model.Person

@Composable
fun MyDropdownMenuScreen(viewModel: MyViewModel ,list:List<Person>) {
    var expanded by remember { mutableStateOf(false) }
    val selectedItem = viewModel.selectedItem.collectAsState()
    val myState = viewModel.myMutableState

    val items = list.map {
        it.name+"="+it.id
    }

    Column {
        // Display selected item from ViewModel
        Text("Selected Item: ${myState}")

        // DropdownMenu with items
        Box {
            TextButton(onClick = { expanded = true }) {
                Text("Select an item: ${myState}")
            }

            DropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false }
            ) {
                items.forEach { itemName ->
                    DropdownMenuItem(onClick = {
                        Log.d("DropdownMenu", "Selected item: $itemName")
                        expanded = false
                            viewModel.updateMutableState(itemName)
                    }) {
                        Text(itemName)
                    }
                }
            }
        }
    }
}