package com.app.dreamapp.ui.components.DreamAppFooterBar


import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.RowScope
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.unit.dp
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.app.dreamapp.ui.theme.DreamAppTheme


@Composable
fun BottomNavigationBar(
    navController: NavHostController,
    screens: List<BottomBarScreen>,
) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination

    val bottomBarDestination = screens.any { it.route == currentDestination?.route }
    if (bottomBarDestination) {
        BottomNavigation(
            backgroundColor = DreamAppTheme.colors.primaryBackground,
            contentColor = DreamAppTheme.colors.primaryText,
            elevation = 5.dp
        ) {
            screens.forEach { screen ->
                AddItem(
                    screen = screen,
                    currentDestination = currentDestination,
                    navController = navController
                )
            }
        }
    }
}

@Composable
fun RowScope.AddItem(
    screen: BottomBarScreen,
    currentDestination: NavDestination?,
    navController: NavHostController
) {
    BottomNavigationItem(
        selectedContentColor = DreamAppTheme.colors.secondaryText,
        unselectedContentColor = DreamAppTheme.colors.primaryText,
        label = {
            Text(text = screen.name)
        },
        icon = {
            Column(horizontalAlignment = CenterHorizontally) {
                if (screen.badgeCount > 0) {
                    BadgedBox(
                        badge = {
                            Badge(backgroundColor = DreamAppTheme.colors.tintColor) {
                                Text(
                                    text = screen.badgeCount.toString(),
                                    color = DreamAppTheme.colors.primaryBackground,
                                )
                            }
                        }
                    ) {
                        Icon(imageVector = screen.icon, contentDescription = screen.name)
                    }
                } else {
                    Icon(imageVector = screen.icon, contentDescription = screen.name)
                }
            }
        },
        selected = currentDestination?.hierarchy?.any {
            it.route == screen.route
        } == true,
        onClick = {
            navController.navigate(screen.route) {
                popUpTo(navController.graph.findStartDestination().id)
                launchSingleTop = true
            }
        }
    )
}