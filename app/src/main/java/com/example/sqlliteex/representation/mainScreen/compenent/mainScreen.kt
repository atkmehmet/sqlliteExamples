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
fun mainScreen(view: mainScreenView) {

    Column(modifier = Modifier.fillMaxSize()) {

        TextField(value = view.state.bookName, onValueChange ={view.onEvent(mainScreenEvent.bookName(it))} )
        TextField(value = view.state.bookWriter, onValueChange ={view.onEvent(mainScreenEvent.bookWriter(it))} )
        TextField(value = view.state.personId.toString(), onValueChange = {view.onEvent(mainScreenEvent.personId(it.toInt()))})
        Button(onClick = { view.onEvent(mainScreenEvent.addBook) }) {
            Text(text = view.state.personCount.toString())
        }
    }
}