package com.example.sqlliteex

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.sqlliteex.representation.mainScreen.compenent.dropMenu
import com.example.sqlliteex.representation.mainScreen.compenent.mainScreen
import com.example.sqlliteex.representation.mainScreen.mainScreenView
import com.example.sqlliteex.ui.theme.SqlLiteExTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {

          //   val view:mainScreenView=mainScreenView()
            //       mainScreen(view)
            dropMenu()
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    SqlLiteExTheme {
        Greeting("Android")
    }
}