package com.example.sqlliteex.representation.mainScreen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.TextField
import androidx.compose.runtime.remember
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.material3.*
import androidx.compose.ui.unit.dp
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
@Composable
fun MyComposeScreen(viewModel: MyViewModel = MyViewModel()){

    //val textValue by remember { viewModel.textValue }
    Column(modifier = Modifier
        .fillMaxSize()
        .padding(16.dp)) {


        TextField(value = viewModel.textValue, onValueChange ={ viewModel.updateTextValue(it)} )
        
        Text(text = viewModel.textValue)
    }
}