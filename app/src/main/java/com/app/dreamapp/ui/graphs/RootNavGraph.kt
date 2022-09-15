package com.app.dreamapp.ui.graphs

import androidx.compose.material.ScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.app.dreamapp.ui.pages.Main.CurrentUserViewState
import com.app.dreamapp.ui.pages.Main.MainScreen
import com.app.dreamapp.ui.pages.Main.MainScreenViewModel
import com.app.dreamapp.ui.pages.Settings.SettingsScreenViewModel
import com.app.dreamapp.ui.pages.Settings.SettingsScreenViewState
import com.app.dreamapp.ui.theme.SettingsBundle
import kotlinx.coroutines.CoroutineScope

@Composable
fun RootNavigationGraph(
    modifier: Modifier,
    navController: NavHostController,
    mainScreenViewModel: MainScreenViewModel,
    scaffoldState: ScaffoldState,
    currentUserState: State<CurrentUserViewState?>,
    scope: CoroutineScope,
    settingsScreenViewModel: SettingsScreenViewModel,
    settingsScreenViewState: State<SettingsScreenViewState?>,
) {
    NavHost(
        navController = navController,
        route = Graph.ROOT.route,
        startDestination = Graph.AUTHENTICATION.route
    ) {
        authNavGraph(
            modifier = modifier,
            navController = navController,
            scaffoldState = scaffoldState,
            scope = scope,
            mainScreenViewModel = mainScreenViewModel
        )
        composable(route = Graph.MAIN.route) {
            MainScreen(
                modifier = modifier,
                scaffoldState = scaffoldState,
                currentUserState = currentUserState,
                scope = scope,
                settingsScreenViewModel = settingsScreenViewModel,
                settingsScreenViewState = settingsScreenViewState,
            )
        }
    }
}

sealed class Graph(val route: String) {
    object ROOT : Graph("root_graph")
    object AUTHENTICATION : Graph("auth_graph")
    object MAIN : Graph("main_graph")

    object MAIN_NAV : Graph("main_nav_graph")

    object SETTINGS : Graph("settings_graph")
    object LOGOUT : Graph("logout_graph")
    object ADMIN_PANEL : Graph("admin_panel_graph")
    object ADD_PERSON : Graph("add_person_graph")
    object ACCOUNT : Graph("account_graph")

//    fun withArgs(vararg args: String): String {
//        return buildString {
//            append(route)
//            args.forEach { arg ->
//                append("/$arg")
//            }
//        }
//    }
}
