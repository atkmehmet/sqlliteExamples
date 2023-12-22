package com.example.sqlliteex.representation.mainScreen.compenent

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.DropdownMenu
import androidx.compose.material.DropdownMenuItem
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier

@Composable
fun dropMenu (){

    val list= listOf("one","two","three","four","five")
    val expanded = remember{ mutableStateOf(false) }
    val currentValue = remember{mutableStateOf(list[0])}

    Column(modifier = Modifier.fillMaxSize()) {
        Row(modifier = Modifier
            .fillMaxWidth()
            .clickable {
                expanded.value = !expanded.value
            }) {
            Text(text = currentValue.value)
            Icon(imageVector = Icons.Filled.ArrowDropDown, contentDescription = null)
            
            DropdownMenu(expanded = expanded.value, onDismissRequest = { expanded.value=false }) {

                list.forEach { 
                    DropdownMenuItem(onClick = {
                        currentValue.value = it
                        expanded.value = false
                    }) {
                       Text(text = it)
                    }
                }
            }

        }
    }
}