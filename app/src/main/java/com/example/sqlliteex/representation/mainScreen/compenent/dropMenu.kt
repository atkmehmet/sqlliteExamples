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
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import com.example.sqlliteex.domain.model.Person
import com.example.sqlliteex.representation.mainScreen.mainScreenEvent
import com.example.sqlliteex.representation.mainScreen.mainScreenView
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map

@Composable
fun dropMenu (view: mainScreenView
,list:List<Person>){

     val listNew= listOf(
          Person(1,"Yusuf","Durmaz")
      )
   // val expanded = remember{ mutableStateOf(false) }
   // val currentValue = remember{mutableStateOf(list[0])}

    Column(modifier = Modifier.fillMaxSize()) {
        Row(modifier = Modifier
            .fillMaxWidth()
            .clickable {
                view.onEvent(mainScreenEvent.expandedChange())
                //expanded.value = !expanded.value
            }) {
            Text(text = view.state.currentValue)
            Icon(imageVector = Icons.Filled.ArrowDropDown, contentDescription = null)
            
            DropdownMenu(expanded = view.state.expanded,
                onDismissRequest = { view.onEvent(mainScreenEvent.expandedChange(false) )})
            {
                     listNew.forEach {
                         DropdownMenuItem(onClick = {
                             view.onEvent(mainScreenEvent.currentValue(it.name))
                             view.onEvent(mainScreenEvent.expandedChange(false))
                         }) {
                             Text(text = it.name)
                         }
                     }
                 }
            }

        }
    }
