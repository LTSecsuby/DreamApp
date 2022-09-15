package com.app.dreamapp.ui.screens

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.BorderStroke
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
import com.app.dreamapp.ui.screens.ContactChats.ContactChats
import com.app.dreamapp.ui.screens.Timeline.TimelineItem
import com.app.dreamapp.ui.screens.Timeline.TimelineScreenViewModel
import com.app.dreamapp.ui.theme.DreamAppTheme
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun TimelineScreen(
    modifier: Modifier,
    scaffoldState: ScaffoldState,
    scope: CoroutineScope,
    navController: NavHostController,
    currentUserState: State<CurrentUserViewState?>,
    timelineScreenViewModel: TimelineScreenViewModel,
    settingsScreenViewModel: SettingsScreenViewModel,
    settingsScreenViewState: State<SettingsScreenViewState?>,
    onNewsClick: (itemId: String) -> Unit,
) {
    val bottomSheetScaffoldState = rememberBottomSheetScaffoldState(
        bottomSheetState = rememberBottomSheetState(BottomSheetValue.Collapsed)
    )

    BottomSheetScaffold(
        scaffoldState = bottomSheetScaffoldState,
        sheetContent = {
            AddNewsBottomSheet(
                modifier = modifier,
                onErrorClick = {
                    // Error no text news here
                },
                onDoneClick = {
                    timelineScreenViewModel.addNewNews(it)
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
                            contentDescription = "floating_add_news_icon"
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
                    items(timelineScreenViewModel.newsList) { timelineItem ->
                        Card(
                            modifier = Modifier
                                .padding(top = 16.dp, start = 16.dp, end = 16.dp)
                                .fillMaxWidth()
                                .clickable {
                                    // navController.navigate(TimeLinePage.News.route)
                                    onNewsClick(timelineItem.id)
                                },
                            border = BorderStroke(1.dp, DreamAppTheme.colors.tintColor),
                            backgroundColor = DreamAppTheme.colors.primaryBackground
                        ){
                            Column(
                                modifier = Modifier
                                    .fillMaxWidth(),
                                verticalArrangement = Arrangement.Top,
                                horizontalAlignment = Alignment.Start
                            ) {
                                Row(
                                    modifier = Modifier
                                        .padding(start = 16.dp, end = 16.dp, top = 10.dp)
                                        .fillMaxWidth(),
                                    horizontalArrangement = Arrangement.SpaceBetween,
                                    verticalAlignment = Alignment.Top
                                ) {
                                    Text(
                                        modifier = Modifier,
                                        text = timelineItem.title,
                                        color = DreamAppTheme.colors.primaryText,
                                        style = DreamAppTheme.typography.heading,
                                    )
                                    Text(
                                        modifier = Modifier,
                                        text = timelineItem.created,
                                        color = DreamAppTheme.colors.primaryText,
                                        style = DreamAppTheme.typography.caption,
                                    )
                                }
                                Box(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(top = 16.dp, start = 16.dp, end = 16.dp),
                                ) {
                                    Text(
                                        text = timelineItem.description,
                                        color = DreamAppTheme.colors.primaryText,
                                        style = DreamAppTheme.typography.body,
                                    )
                                }
                                Row(
                                    modifier = Modifier
                                        .padding(top = 16.dp, start = 16.dp, end = 16.dp, bottom = 16.dp)
                                        .fillMaxWidth(),
                                    horizontalArrangement = Arrangement.End,
                                    verticalAlignment = Alignment.Bottom
                                ) {
                                    Icon(
                                        modifier = Modifier
                                            .clip(CircleShape)
                                            .size(24.dp),
                                        imageVector = timelineItem.author.avatar,
                                        contentDescription = timelineItem.author.name,
                                        tint = DreamAppTheme.colors.tintColor
                                    )
                                    Text(
                                        modifier = Modifier,
                                        text = timelineItem.author.name,
                                        color = DreamAppTheme.colors.primaryText,
                                        style = DreamAppTheme.typography.caption,
                                    )
                                }
                            }
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
