package com.app.dreamapp.ui.pages.Main

import androidx.activity.compose.BackHandler
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.*
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
import com.app.dreamapp.ui.screens.AddNewsBottomSheet
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
    val pagesMain = listOf(
        BottomBarScreen.Timeline,
        BottomBarScreen.ContactsChats,
        BottomBarScreen.LinkTree,
    )

    var pagesDetails = listOf(
        TopBarDrawerMenuItems.Account,
        TopBarDrawerMenuItems.AddPerson,
        TopBarDrawerMenuItems.Settings,
        TopBarDrawerMenuItems.AdminPanel,
        TopBarDrawerMenuItems.Logout,
    )
    val sheetState = rememberBottomSheetState(initialValue = BottomSheetValue.Collapsed)
    val bottomSheetScaffoldState = rememberBottomSheetScaffoldState(bottomSheetState = sheetState)

    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination

    val topBarDestination = pagesMain.any { it.route == currentDestination?.route }

    BottomSheetScaffold(
        scaffoldState = bottomSheetScaffoldState,
        sheetContent = {
            AddNewsBottomSheet(
                modifier = modifier,
                onErrorClick = {
                    // Error no text news here
                },
                onDoneClick = {
                    // Add news here
                    scope.launch {
                        sheetState.collapse()
                    }
                },
                onCloseClick = {
                    scope.launch {
                        sheetState.collapse()
                    }
                }
            )
        },
        sheetBackgroundColor = DreamAppTheme.colors.tintColor,
        sheetPeekHeight = 0.dp
    ) {
        Surface(
            modifier = modifier,
            color = DreamAppTheme.colors.primaryBackground,
        ) {
            Scaffold(
                scaffoldState = scaffoldState,
                topBar = {
                    if (topBarDestination) {
                        DreamAppHeaderBar(
                            currentUserName = currentUserState.value?.name,
                            onNavigationItemClick = {
                                scope.launch {
                                    scaffoldState.drawerState.open()
                                }
                            }
                        )
                    }
                },
                floatingActionButtonPosition = FabPosition.End,
                floatingActionButton = {
                    if (BottomBarScreen.Timeline.route == currentDestination?.route) {
                        FloatingActionButton(
                            onClick = {
                                scope.launch {
                                    if (sheetState.isCollapsed) {
                                        sheetState.expand()
                                    } else {
                                        sheetState.collapse()
                                    }
                                }
                            },
                            backgroundColor = DreamAppTheme.colors.tintColor,
                            contentColor = DreamAppTheme.colors.primaryText,
                        ){
                            Icon(
                                imageVector = Icons.Default.Add,
                                contentDescription = "floating_add_icon"
                            )
                        }
                    }
                },
                bottomBar = {
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
                MainNavGraph(
                    modifier = modifier,
                    navController = navController,
                    scope = scope,
                    pages = pagesDetails,
                    settingsScreenViewModel = settingsScreenViewModel,
                    settingsScreenViewState = settingsScreenViewState,
                )
            }
        }
    }
    BackHandler(
        enabled = sheetState.isExpanded,
        onBack = {
            scope.launch {
                sheetState.collapse()
            }
        }
    )
}
