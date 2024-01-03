package com.example.sqlliteex.representation.mainScreen

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
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
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.sqlliteex.domain.model.Person

@Composable
fun MyDropdownMenuScreen(view: mainScreenView ) {
    Column (
    modifier = Modifier
        .verticalScroll(rememberScrollState())
        .padding(20.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ){

        AddPerson(view = view)
        DropMenu(view = view)
        AddBook(view = view)


    }
}
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
    }
    Button(onClick = { view.onEvent(mainScreenEvent.addBook) }) {
        Text(text = "Add Book")
    }


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
            }
          Button(onClick = { view.onEvent(mainScreenEvent.personCommit) }) {
              Text(text = "Add Person")
          }

    }
}
@Composable
fun DropMenu(view: mainScreenView){
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