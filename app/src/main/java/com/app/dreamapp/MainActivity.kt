package com.app.dreamapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController
import com.app.dreamapp.ui.theme.DreamAppTheme
import com.app.dreamapp.ui.graphs.RootNavigationGraph
import com.app.dreamapp.ui.pages.Main.MainScreenViewModel
import com.app.dreamapp.ui.pages.Settings.SettingsScreenViewModel
import com.app.dreamapp.ui.theme.DreamAppMainTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            App()
        }
    }
}

@Composable
fun App() {
    val settingsScreenViewModel = SettingsScreenViewModel()
    val settingsScreenViewState = settingsScreenViewModel.settingsScreenViewState.observeAsState()

    val mainScreenViewModel = MainScreenViewModel()
    val currentUserState = mainScreenViewModel.currentUserViewState.observeAsState()

    DreamAppMainTheme(
        settingsScreenViewState = settingsScreenViewState
    ) {

        val scaffoldState = rememberScaffoldState()
        val scope = rememberCoroutineScope()

        Surface(
            modifier = Modifier,
            color = DreamAppTheme.colors.primaryBackground,
        ) {
            RootNavigationGraph(
                modifier = Modifier,
                navController = rememberNavController(),
                mainScreenViewModel = mainScreenViewModel,
                scaffoldState = scaffoldState,
                currentUserState = currentUserState,
                scope = scope,
                settingsScreenViewModel = settingsScreenViewModel,
                settingsScreenViewState = settingsScreenViewState
            )
        }
    }
}


@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    App()
}