import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.material.TextField
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.lifecycle.ViewModel
import com.example.sqlliteex.representation.mainScreen.MyViewModel

@Composable
fun MyComposeScreenEx(viewModel: MyViewModel = MyViewModel()) {
    // Observe the mutable state from the ViewModel
    val myState = viewModel.myMutableState

    Column {
        // Display the mutable state
        Text(text = "Mutable State: $myState")
        TextField(value = viewModel.selectedItem.collectAsState().value, onValueChange ={viewModel.updateSelectedItem(it)} )

        // Button to update the mutable state
        Button(onClick = {
            viewModel.updateMutableState("New Value")
        }) {
            Text("Update Mutable State")
        }
    }
}
