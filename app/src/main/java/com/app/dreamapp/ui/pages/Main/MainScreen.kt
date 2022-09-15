package com.app.dreamapp.ui.pages.Main

import androidx.activity.compose.BackHandler
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.app.dreamapp.ui.theme.DreamAppTheme
import com.app.dreamapp.ui.components.DrawerComponent.DrawerBody
import com.app.dreamapp.ui.components.DrawerHeader
import com.app.dreamapp.ui.components.DreamAppFooterBar.BottomBarScreen
import com.app.dreamapp.ui.components.DreamAppFooterBar.BottomNavigationBar
import com.app.dreamapp.ui.components.DreamAppHeaderBar.DreamAppHeaderBar
import com.app.dreamapp.ui.components.DreamAppHeaderBar.TopBarDrawerMenuItems
import com.app.dreamapp.ui.graphs.MainNavGraph
import com.app.dreamapp.ui.pages.Settings.SettingsScreenViewModel
import com.app.dreamapp.ui.pages.Settings.SettingsScreenViewState
import com.app.dreamapp.ui.screens.AddContactBottomSheet
import com.app.dreamapp.ui.screens.AddMessageBottomSheet
import com.app.dreamapp.ui.screens.AddNewsBottomSheet
import com.app.dreamapp.ui.screens.ContactChats.ContactsChatsScreenViewModel
import com.app.dreamapp.ui.screens.ContactsMessages.ContactMessagesScreenViewModel
import com.app.dreamapp.ui.screens.Timeline.TimelineScreenViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

//@PreviewParameter(SimpleMainScreenViewModelProvider::class)
//@PreviewParameter(SimpleSettingsBundleProvider::class)

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun MainScreen(
    modifier: Modifier,
    navController: NavHostController = rememberNavController(),
    scaffoldState: ScaffoldState,
    currentUserState: State<CurrentUserViewState?>,
    scope: CoroutineScope,
    settingsScreenViewModel: SettingsScreenViewModel,
    settingsScreenViewState: State<SettingsScreenViewState?>,
) {

    val contactsChatsScreenViewModel = ContactsChatsScreenViewModel()

    val timelineScreenViewModel = TimelineScreenViewModel()

    val contactMessagesScreenViewModel = ContactMessagesScreenViewModel()

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
            contactMessagesScreenViewModel = contactMessagesScreenViewModel,
            contactsChatsScreenViewModel = contactsChatsScreenViewModel,
            settingsScreenViewModel = settingsScreenViewModel,
            settingsScreenViewState = settingsScreenViewState,
        )
    }
}
