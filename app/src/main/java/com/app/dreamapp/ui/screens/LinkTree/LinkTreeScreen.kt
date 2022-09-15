package com.app.dreamapp.ui.screens.LinkTree


import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import com.app.dreamapp.ui.components.DrawerComponent.DrawerBody
import com.app.dreamapp.ui.components.DrawerHeader
import com.app.dreamapp.ui.components.DreamAppFooterBar.BottomBarScreen
import com.app.dreamapp.ui.components.DreamAppFooterBar.BottomNavigationBar
import com.app.dreamapp.ui.components.DreamAppHeaderBar.DreamAppHeaderBar
import com.app.dreamapp.ui.components.DreamAppHeaderBar.TopBarDrawerMenuItems
import com.app.dreamapp.ui.pages.Main.CurrentUserViewState
import com.app.dreamapp.ui.pages.Settings.SettingsScreenViewModel
import com.app.dreamapp.ui.pages.Settings.SettingsScreenViewState
import com.app.dreamapp.ui.theme.DreamAppTheme
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Composable
fun LinkTreeScreen(
    modifier: Modifier = Modifier,
    scaffoldState: ScaffoldState,
    scope: CoroutineScope,
    navController: NavHostController,
    currentUserState: State<CurrentUserViewState?>,
    settingsScreenViewModel: SettingsScreenViewModel,
    settingsScreenViewState: State<SettingsScreenViewState?>,
) {
    Surface(
        modifier = modifier,
        color = DreamAppTheme.colors.secondaryBackground,
    ) {
        Scaffold(
            scaffoldState = scaffoldState,
            topBar = {
                DreamAppHeaderBar(
                    currentUserName = currentUserState.value?.name,
                    onNavigationItemClick = {
                        scope.launch {
                            scaffoldState.drawerState.open()
                        }
                    }
                )
            },
            bottomBar = {
                val pagesMain = listOf(
                    BottomBarScreen.Timeline,
                    BottomBarScreen.ContactsChats,
                    BottomBarScreen.LinkTree,
                )
                BottomNavigationBar(
                    navController = navController,
                    pagesMain
                )
            },
            drawerContent = {
                DrawerHeader(
                    currentUserName = currentUserState.value?.name,
                    settingsScreenViewModel = settingsScreenViewModel,
                    settingsScreenViewState = settingsScreenViewState
                )
                val pagesDetails = listOf(
                    TopBarDrawerMenuItems.Account,
                    TopBarDrawerMenuItems.AddPerson,
                    TopBarDrawerMenuItems.Settings,
                    TopBarDrawerMenuItems.AdminPanel,
                    TopBarDrawerMenuItems.Logout,
                )
                DrawerBody(
                    items = pagesDetails,
                    onItemClick = {
                        navController.navigate(it.route)
                        scope.launch {
                            scaffoldState.drawerState.close()
                        }
                    }
                )
            },
            backgroundColor = DreamAppTheme.colors.secondaryBackground,
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(),
                contentAlignment = Alignment.Center,
            ) {
                Text(
                    text = "LinkTreeScreen",
                    color = DreamAppTheme.colors.primaryText
                )
            }
        }
    }
}