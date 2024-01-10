package com.example.sqlliteex.representation.mainScreen

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box


import androidx.compose.foundation.layout.Column

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Button
import androidx.compose.material.Card
import androidx.compose.material.DropdownMenu
import androidx.compose.material.DropdownMenuItem
import androidx.compose.material.MaterialTheme.shapes
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.sqlliteex.data.local.ReadBookEntity
import com.example.sqlliteex.domain.model.Person
import com.example.sqlliteex.domain.model.ReadBook
import com.example.sqlliteex.representation.mainScreen.compenent.dropMenu
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.emptyFlow
import kotlinx.coroutines.flow.take

@Composable
fun MyDropdownMenuScreen(view: mainScreenView ) {

//test(view)
    val list=view.listBook.collectAsState(initial = emptyList())
    Column(
        modifier = Modifier.fillMaxWidth()
    ) {
        // Other content above LazyColumn
        AddPerson(view = view)
        DropMenu(view = view)
        AddBook(view = view)
        LazyColumn(
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth()
        ) {
            items(list.value) { item ->
                // LazyColumn items
                Text(text = item.bookName)
                Text(text = item.bookWriter)
                Text(text = item.PersonId)
            }
        }

        // Other content below LazyColumn
    }



}
       
    //   DropMenu(view = view)


@Composable
fun AddBook(modifier: Modifier = Modifier,
            view:mainScreenView)
{
Card(modifier = modifier) {
    Column (
        verticalArrangement = Arrangement.spacedBy(10.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.padding(10.dp)

    )
    {
        //val listBook = view.listBook.collectAsState(initial = emptyList()).value
        Text(text = "Add Book Which is read by Person")
        OutlinedTextField(
            value = view.state.bookName,
            singleLine = true,
            label={ Text(text = "Please Book Name")},
            shape= shapes.large,
            modifier = Modifier.fillMaxWidth(),
            onValueChange ={view.onEvent(mainScreenEvent.bookName(it))} )


        OutlinedTextField(
            value = view.state.bookWriter,
            singleLine = true,
            label={ Text(text = "Please Book Writer")},
            shape= shapes.large,
            modifier = Modifier.fillMaxWidth(),
            onValueChange ={view.onEvent(mainScreenEvent.bookWriter(it))} )
    Button(onClick = { view.onEvent(mainScreenEvent.addBook) }) {
        Text(text = "Add Book")
    }

    }


}
    
}    

@Composable
fun test(view:mainScreenView){
    val list=view.listBook.collectAsState(initial = emptyList())
    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        // Other content above LazyColumn
          AddPerson(view = view)
        DropMenu(view = view)
        AddBook(view = view)
        LazyColumn(
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth()
        ) {
            items(list.value) { item ->
                // LazyColumn items
                Text(text = "Item ${item.bookName}")
            }
        }

        // Other content below LazyColumn
    }

}

@Composable
fun AddPerson(
    modifier: Modifier = Modifier,
   view:mainScreenView
){
    Card (modifier = modifier
    ){
            Column(
              verticalArrangement = Arrangement.spacedBy(10.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.padding(10.dp)

            ) {
                Text(text = "Add Person")
                OutlinedTextField(
                    value = view.state.personName,
                   singleLine = true,
                    label={ Text(text = "Please Person Name")},
                    shape= shapes.large,
                   modifier = Modifier.fillMaxWidth(),
                    onValueChange ={view.onEvent(mainScreenEvent.personName(it))} )


                OutlinedTextField(
                    value = view.state.personSurname,
                    singleLine = true,
                    label={ Text(text = "Please Person Surname")},
                    shape= shapes.large,
                    modifier = Modifier.fillMaxWidth(),
                    onValueChange ={view.onEvent(mainScreenEvent.personSurname(it))} )
          Button(onClick = { view.onEvent(mainScreenEvent.personCommit) }) {
              Text(text = "Add Person")
          }
            }

    }
}
@Composable
fun DropMenu(view: mainScreenView){
     val list = view._listPerson.collectAsState(initial = emptyList())
     // val list = listOf("Suzan","Mehmet")
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