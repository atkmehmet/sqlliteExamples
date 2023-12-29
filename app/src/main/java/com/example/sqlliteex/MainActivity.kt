package com.example.sqlliteex

import MyComposeScreen
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.sqlliteex.representation.mainScreen.MyDropdownMenuScreen
import com.example.sqlliteex.representation.mainScreen.MyViewModel
import com.example.sqlliteex.representation.mainScreen.compenent.dropMenu
import com.example.sqlliteex.representation.mainScreen.compenent.mainScreen
import com.example.sqlliteex.representation.mainScreen.mainScreenView
import com.example.sqlliteex.ui.theme.SqlLiteExTheme
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.first

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {

            //  MyComposeScreen()
            val view:mainScreenView=mainScreenView()
            val viewNew:MyViewModel= MyViewModel()
            //       mainScreen(view)
           MyDropdownMenuScreen(viewModel = viewNew
                ,view.lisPerson.collectAsState().value.collectAsState(initial = emptyList()).value)
          //  dropMenu(view,view.state,view::onEvent,view.lisPerson.collectAsState().value.collectAsState(initial = emptyList()).value)
        }
    }
}
