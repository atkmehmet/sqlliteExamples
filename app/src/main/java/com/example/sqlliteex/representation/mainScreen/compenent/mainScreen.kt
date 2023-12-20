package com.example.sqlliteex.representation.mainScreen.compenent

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.sqlliteex.representation.mainScreen.mainScreenEvent
import com.example.sqlliteex.representation.mainScreen.mainScreenView

@Composable
fun mainScreen(view: mainScreenView= mainScreenView()) {

    Column(modifier = Modifier.fillMaxSize()) {



        TextField(value =view.state.personName ,
            onValueChange ={view.onEvent(mainScreenEvent.personName(it))},
            placeholder = {"Please name Value"} )

        TextField(value = view.state.personSurname, onValueChange ={view.onEvent(mainScreenEvent.personSurname(it))} )
        Button(onClick = { view.onEvent(mainScreenEvent.personCommit) }) {
            
        }
    }
}