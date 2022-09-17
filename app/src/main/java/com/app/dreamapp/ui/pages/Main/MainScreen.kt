package com.app.dreamapp.ui.pages.Main

import androidx.compose.material.*
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.app.dreamapp.ui.theme.DreamAppTheme
import com.app.dreamapp.ui.graphs.MainNavGraph
import com.app.dreamapp.ui.pages.Settings.SettingsScreenViewModel
import com.app.dreamapp.ui.pages.Settings.SettingsScreenViewState
import com.app.dreamapp.ui.screens.ContactChats.ContactsChatsScreenViewModel
import com.app.dreamapp.ui.screens.Timeline.TimelineScreenViewModel
import kotlinx.coroutines.CoroutineScope

//@PreviewParameter(SimpleMainScreenViewModelProvider::class)
//@PreviewParameter(SimpleSettingsBundleProvider::class)

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun MainScreen(
    modifier: Modifier,
    navController: NavHostController = rememberNavController(),
    scaffoldState: ScaffoldState,
    scope: CoroutineScope,
    currentUserState: State<CurrentUserViewState?>,
    settingsScreenViewModel: SettingsScreenViewModel,
    settingsScreenViewState: State<SettingsScreenViewState?>,
) {

    val contactsChatsScreenViewModel = ContactsChatsScreenViewModel()

    val timelineScreenViewModel = TimelineScreenViewModel()

    Surface(
        modifier = modifier,
        color = DreamAppTheme.colors.primaryBackground,
    ) {
        MainNavGraph(
            modifier = modifier,
            navController = navController,
            scope = scope,
            scaffoldState = scaffoldState,
            currentUserState = currentUserState,
            timelineScreenViewModel = timelineScreenViewModel,
            contactsChatsScreenViewModel = contactsChatsScreenViewModel,
            settingsScreenViewModel = settingsScreenViewModel,
            settingsScreenViewState = settingsScreenViewState,
        )
    }
}
