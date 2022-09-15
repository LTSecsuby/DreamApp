package com.app.dreamapp.ui.screens.ContactChats

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Person
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
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
import com.app.dreamapp.ui.screens.AddContactBottomSheet
import com.app.dreamapp.ui.screens.AddNewsBottomSheet
import com.app.dreamapp.ui.theme.DreamAppTheme
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun ContactsChatsScreen(
    modifier: Modifier,
    scaffoldState: ScaffoldState,
    scope: CoroutineScope,
    navController: NavHostController,
    currentUserState: State<CurrentUserViewState?>,
    contactsChatsScreenViewModel: ContactsChatsScreenViewModel,
    settingsScreenViewModel: SettingsScreenViewModel,
    settingsScreenViewState: State<SettingsScreenViewState?>,
    onContactClick: (itemId: String) -> Unit,
) {
    val bottomSheetScaffoldState = rememberBottomSheetScaffoldState(
        bottomSheetState = rememberBottomSheetState(BottomSheetValue.Collapsed)
    )

    BottomSheetScaffold(
        scaffoldState = bottomSheetScaffoldState,
        sheetContent = {
            AddContactBottomSheet(
                modifier = modifier,
                onErrorClick = {
                    // Error no text message here
                },
                onDoneClick = {
                    contactsChatsScreenViewModel.addNewContacts(it)
                    scope.launch {
                        bottomSheetScaffoldState.bottomSheetState.collapse()
                    }
                },
                onCloseClick = {
                    scope.launch {
                        if (bottomSheetScaffoldState.bottomSheetState.isCollapsed) {
                            bottomSheetScaffoldState.bottomSheetState.expand()
                        } else {
                            bottomSheetScaffoldState.bottomSheetState.collapse()
                        }
                    }
                }
            )
        },
        sheetBackgroundColor = DreamAppTheme.colors.tintColor,
        sheetPeekHeight = 0.dp
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
                floatingActionButtonPosition = FabPosition.End,
                floatingActionButton = {
                    FloatingActionButton(
                        onClick = {
                            scope.launch {
                                if (bottomSheetScaffoldState.bottomSheetState.isCollapsed) {
                                    bottomSheetScaffoldState.bottomSheetState.expand()
                                } else {
                                    bottomSheetScaffoldState.bottomSheetState.collapse()
                                }
                            }
                        },
                        backgroundColor = DreamAppTheme.colors.tintColor,
                        contentColor = DreamAppTheme.colors.primaryText,
                    ){
                        Icon(
                            imageVector = Icons.Default.Add,
                            contentDescription = "floating_add_contact_icon"
                        )
                    }
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
                LazyColumn(
                    modifier = Modifier
                        .padding(bottom = 56.dp)
                        .fillMaxSize()
                        .background(DreamAppTheme.colors.secondaryBackground),
                    verticalArrangement = Arrangement.Top,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    items(contactsChatsScreenViewModel.contactList) { contact ->
                        Row(
                            modifier = Modifier
                                .padding(top = 1.dp)
                                .fillMaxWidth()
                                .background(DreamAppTheme.colors.primaryBackground)
                                .clickable {
                                    onContactClick(contact.id)
                                },
                            horizontalArrangement = Arrangement.Start,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Icon(
                                modifier = Modifier
                                    .clip(CircleShape)
                                    .size(60.dp),
                                imageVector = contact.avatar,
                                contentDescription = contact.name,
                                tint = DreamAppTheme.colors.tintColor
                            )
                            Text(
                                modifier = Modifier,
                                text = contact.name,
                                color = DreamAppTheme.colors.primaryText,
                                style = DreamAppTheme.typography.heading,
                            )
                        }
                    }
                }
            }
        }
    }
    BackHandler(
        enabled = bottomSheetScaffoldState.bottomSheetState.isExpanded,
        onBack = {
            scope.launch {
                bottomSheetScaffoldState.bottomSheetState.collapse()
            }
        }
    )
}

