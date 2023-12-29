import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.lifecycle.ViewModel
import com.example.sqlliteex.representation.mainScreen.MyViewModel

@Composable
fun MyComposeScreen(viewModel: MyViewModel = MyViewModel()) {
    // Observe the mutable state from the ViewModel
    val myState = viewModel.myMutableState

    Column {
        // Display the mutable state
        Text(text = "Mutable State: $myState")

        // Button to update the mutable state
        Button(onClick = {
            viewModel.updateMutableState("New Value")
        }) {
            Text("Update Mutable State")
        }
    }
}
