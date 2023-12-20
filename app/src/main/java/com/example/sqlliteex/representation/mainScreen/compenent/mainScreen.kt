package com.example.sqlliteex.representation.mainScreen.compenent

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.sqlliteex.representation.mainScreen.mainScreenView

@Composable
fun mainScreen(view: mainScreenView=mainScreenView()) {

    Column(modifier = Modifier.fillMaxSize()) {
         Text(text = "Hello World")
    }
}