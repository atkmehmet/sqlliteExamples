package com.example.sqlliteex.representation.mainScreen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.material.DropdownMenu
import androidx.compose.material.DropdownMenuItem
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember

@Composable
fun MyDropdownMenuScreen(viewModel: MyViewModel ) {
    var expanded = remember { mutableStateOf(false) }
    val items = listOf("Item 1", "Item 2", "Item 3")

    Column {
        // Display selected item from ViewModel
        Text("Selected Item: ${viewModel.selectedItem.value}")

        // DropdownMenu with items
        Box {
            TextButton(onClick = { expanded.value = true }) {
                Text("Select an item")
            }

            DropdownMenu(
                expanded = expanded.value,
                onDismissRequest = { expanded.value = false }
            ) {
                items.forEach { item ->
                    DropdownMenuItem(onClick = {
                        expanded.value = false
                        viewModel.updateSelectedItem(item)
                    }) {
                        Text(item)
                    }
                }
            }
        }
    }
}