package com.app.dreamapp.ui.graphs

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.*
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.app.dreamapp.R
import com.app.dreamapp.ui.components.DreamAppFooterBar.BottomBarScreen
import com.app.dreamapp.ui.pages.Main.CurrentUserViewState
import com.app.dreamapp.ui.pages.Settings.SettingsScreen
import com.app.dreamapp.ui.pages.Settings.SettingsScreenViewModel
import com.app.dreamapp.ui.pages.Settings.SettingsScreenViewState
import com.app.dreamapp.ui.screens.ContactChats.ContactChats
import com.app.dreamapp.ui.screens.ContactChats.ContactsChatsScreen
import com.app.dreamapp.ui.screens.ContactChats.ContactsChatsScreenViewModel
import com.app.dreamapp.ui.screens.ContactsMessages.ContactMessages
import com.app.dreamapp.ui.screens.Details.ContactChatDetail
import com.app.dreamapp.ui.screens.DetailsScreen
import com.app.dreamapp.ui.screens.LinkTree.LinkTreeScreen
import com.app.dreamapp.ui.screens.Timeline.TimelineScreenViewModel
import com.app.dreamapp.ui.screens.TimelineScreen
import com.app.dreamapp.ui.theme.*
import kotlinx.coroutines.CoroutineScope

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun MainNavGraph(
    modifier: Modifier,
    navController: NavHostController,
    scope: CoroutineScope,
    scaffoldState: ScaffoldState,
    currentUserState: State<CurrentUserViewState?>,
    timelineScreenViewModel: TimelineScreenViewModel,
    contactsChatsScreenViewModel: ContactsChatsScreenViewModel,
    settingsScreenViewModel: SettingsScreenViewModel,
    settingsScreenViewState: State<SettingsScreenViewState?>,
) {

    Surface(
        modifier = modifier,
        color = DreamAppTheme.colors.primaryBackground,
    ) {
        NavHost(
            navController = navController,
            route = Graph.MAIN.route,
            startDestination = Graph.MAIN_NAV.route
        ) {

            //main navigation
            mainBottomNavGraph(
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

            //navbar navigation
            accountNavGraph(
                modifier = modifier,
                navController = navController,
                scope = scope,
            )
            addPersonsNavGraph(
                modifier = modifier,
                navController = navController,
                scope = scope,
            )
            settingsNavGraph(
                modifier = modifier,
                navController = navController,
                scope = scope,
                settingsScreenViewModel = settingsScreenViewModel,
                settingsScreenViewState = settingsScreenViewState
            )
            adminPanelNavGraph(
                modifier = modifier,
                navController = navController,
                scope = scope,
            )
            logoutNavGraph(
                modifier = modifier,
                navController = navController,
                scope = scope,
            )
        }
    }
}

@SuppressLint("UnrememberedMutableState")
@OptIn(ExperimentalMaterialApi::class)
fun NavGraphBuilder.mainBottomNavGraph(
    modifier: Modifier,
    navController: NavHostController,
    scope: CoroutineScope,
    scaffoldState: ScaffoldState,
    currentUserState: State<CurrentUserViewState?>,
    timelineScreenViewModel: TimelineScreenViewModel,
    contactsChatsScreenViewModel: ContactsChatsScreenViewModel,
    settingsScreenViewModel: SettingsScreenViewModel,
    settingsScreenViewState: State<SettingsScreenViewState?>,
) {
    navigation(
        route = Graph.MAIN_NAV.route,
        startDestination = BottomBarScreen.Timeline.route
    ) {

        composable(route = BottomBarScreen.Timeline.route) {
            TimelineScreen(
                modifier = modifier,
                scaffoldState = scaffoldState,
                scope = scope,
                navController = navController,
                currentUserState = currentUserState,
                timelineScreenViewModel = timelineScreenViewModel,
                settingsScreenViewModel = settingsScreenViewModel,
                settingsScreenViewState = settingsScreenViewState,
                onNewsClick = {
                    navController.navigate(DetailsPage.News.withArgs(it))
                }
            )
        }
        composable(route = BottomBarScreen.ContactsChats.route) {
            ContactsChatsScreen(
                modifier = modifier,
                scaffoldState = scaffoldState,
                scope = scope,
                navController = navController,
                currentUserState = currentUserState,
                contactsChatsScreenViewModel = contactsChatsScreenViewModel,
                settingsScreenViewModel = settingsScreenViewModel,
                settingsScreenViewState = settingsScreenViewState,
                onContactClick = {
                    navController.navigate(DetailsPage.Contact.withArgs(it))
                }
            )
        }
        composable(route = BottomBarScreen.LinkTree.route) {
            LinkTreeScreen(
                modifier = modifier,
                scaffoldState = scaffoldState,
                scope = scope,
                navController = navController,
                currentUserState = currentUserState,
                settingsScreenViewModel = settingsScreenViewModel,
                settingsScreenViewState = settingsScreenViewState,
            )
        }

        composable(
            route = DetailsPage.News.route  + "/{id}",
            arguments = listOf(
                navArgument("id") {
                    type = NavType.StringType
                    defaultValue = "Пусто"
                    nullable = true
                }
            )
        ) { entry ->
            val name = entry.arguments?.getString("id").toString()
            DetailsScreen(
                modifier = modifier,
                name = name,
                navController = navController,
                isShowTopBar = true,
                contentBody = {
                    Surface(
                        modifier = modifier,
                        color = DreamAppTheme.colors.secondaryBackground,
                    ) {
                        Box(
                            modifier = Modifier
                                .fillMaxSize(),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(
                                text = name,
                                color =  DreamAppTheme.colors.primaryText,
                            )
                        }
                    }
                }
            )
        }

        composable(
            route = DetailsPage.Contact.route + "/{id}",
            arguments = listOf(
                navArgument("id") {
                    type = NavType.StringType
                    defaultValue = "Пусто"
                    nullable = true
                }
            )
        ) { entry ->

            val contactChatsMessagesState = mutableStateListOf<ContactMessages>(
                ContactMessages(
                    id = "ContactMessages:1",
                    author = ContactChats(
                        id = "ContactChats:11",
                        name = "Контакт1",
                        avatar = Icons.Default.Person
                    ),
                    text = "Привет"
                ),
            )
            val contactChatsMessageListValue: List<ContactMessages> = contactChatsMessagesState

            val id = entry.arguments?.getString("id").toString()
            ContactChatDetail(
                modifier = modifier,
                navController = navController,
                scope = scope,
                contactId = id,
                contactChatsMessagesState = contactChatsMessagesState,
                contactChatsMessages = contactChatsMessageListValue,
            )
        }
    }
}

fun NavGraphBuilder.accountNavGraph(
    modifier: Modifier,
    navController: NavHostController,
    scope: CoroutineScope,
) {
    navigation(
        route = Graph.ACCOUNT.route,
        startDestination = DetailsPage.Account.route
    ) {
        composable(route = DetailsPage.Account.route) {
            DetailsScreen(
                modifier = modifier,
                "Профиль",
                navController,
                isShowTopBar = true,
                contentHeader = {
                    Image(
                        painter = painterResource(R.drawable.profile),
                        contentDescription = "image",
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .size(30.dp)
                            .clip(CircleShape)
                    )
                },
                contentBody = {
                    Surface(
                        modifier = modifier,
                        color = DreamAppTheme.colors.secondaryBackground,
                    ) {
                        Box(
                            modifier = Modifier
                                .fillMaxSize(),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(
                                text = "Профиль",
                                color = DreamAppTheme.colors.primaryText,
                            )
                        }
                    }
                }
            )
        }
    }
}

fun NavGraphBuilder.addPersonsNavGraph(
    modifier: Modifier,
    navController: NavHostController,
    scope: CoroutineScope,
) {
    navigation(
        route = Graph.ADD_PERSON.route,
        startDestination = DetailsPage.AddPersons.route
    ) {
        composable(route = DetailsPage.AddPersons.route) {
            DetailsScreen(
                modifier = modifier,
                "Пригласить",
                navController = navController,
                isShowTopBar = true,
                contentHeader = {

                },
                contentBody = {
                    Surface(
                        modifier = modifier,
                        color = DreamAppTheme.colors.secondaryBackground,
                    ) {
                        Box(
                            modifier = Modifier
                                .fillMaxSize(),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(
                                text = "Пригласить",
                                color = DreamAppTheme.colors.primaryText,
                            )
                        }
                    }
                }
            )
        }
    }
}

fun NavGraphBuilder.settingsNavGraph(
    modifier: Modifier,
    navController: NavHostController,
    scope: CoroutineScope,
    settingsScreenViewModel: SettingsScreenViewModel,
    settingsScreenViewState: State<SettingsScreenViewState?>,
) {
    navigation(
        route = Graph.SETTINGS.route,
        startDestination = DetailsPage.Settings.route
    ) {
        composable(route = DetailsPage.Settings.route) {
            DetailsScreen(
                modifier = modifier,
                "Настройки",
                navController = navController,
                isShowTopBar = true,
                contentHeader = {

                },
                contentBody = {
                    SettingsScreen(
                        modifier = modifier,
                        navController = navController,
                        settingsScreenViewModel = settingsScreenViewModel,
                        settingsScreenViewState = settingsScreenViewState
                    )
                }
            )
        }
    }
}

fun NavGraphBuilder.adminPanelNavGraph(
    modifier: Modifier,
    navController: NavHostController,
    scope: CoroutineScope,
) {
    navigation(
        route = Graph.ADMIN_PANEL.route,
        startDestination = DetailsPage.AdminPanel.route
    ) {
        composable(route = DetailsPage.AdminPanel.route) {
            DetailsScreen(
                modifier = modifier,
                "Администрирование",
                navController = navController,
                isShowTopBar = true,
                contentHeader = {

                },
                contentBody = {
                    Surface(
                        modifier = modifier,
                        color = DreamAppTheme.colors.secondaryBackground,
                    ) {
                        Box(
                            modifier = Modifier
                                .fillMaxSize(),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(
                                text = "Администрирование",
                                color = DreamAppTheme.colors.primaryText,
                            )
                        }
                    }
                }
            )
        }
    }
}

fun NavGraphBuilder.logoutNavGraph(
    modifier: Modifier,
    navController: NavHostController,
    scope: CoroutineScope,
) {
    navigation(
        route = Graph.LOGOUT.route,
        startDestination = DetailsPage.Logout.route
    ) {
        composable(route = DetailsPage.Logout.route) {
            DetailsScreen(
                modifier = modifier,
                "Выход",
                navController = navController,
                isShowTopBar = true,
                contentHeader = {

                },
                contentBody = {
                    Surface(
                        modifier = modifier,
                        color = DreamAppTheme.colors.secondaryBackground,
                    ) {
                        Box(
                            modifier = Modifier
                                .fillMaxSize(),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(
                                text = "Выход",
                                color = DreamAppTheme.colors.primaryText,
                            )
                        }
                    }
                }
            )
        }
    }
}

sealed class DetailsPage(val route: String) {

    object Account : DetailsPage(route = "ACCOUNT")
    object AddPersons : DetailsPage(route = "ADD_PERSONS")
    object Settings : DetailsPage(route = "SETTINGS")
    object AdminPanel : DetailsPage(route = "ADMIN_PANEL")
    object Logout : DetailsPage(route = "LOGOUT")

    object Contact : DetailsPage(route = "CONTACT")
    object News : DetailsPage(route = "NEWS")

    fun withArgs(vararg args: String): String {
        return buildString {
            append(route)
            args.forEach { arg ->
                append("/$arg")
            }
        }
    }
}
