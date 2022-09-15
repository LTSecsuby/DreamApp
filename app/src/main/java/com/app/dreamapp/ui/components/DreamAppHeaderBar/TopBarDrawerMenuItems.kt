package com.app.dreamapp.ui.components.DreamAppHeaderBar

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.ui.graphics.vector.ImageVector
import com.app.dreamapp.ui.graphs.DetailsPage

sealed class TopBarDrawerMenuItems(
    var id: String,
    var title: String,
    var contentDescription: String,
    var icon: ImageVector,
    var route: String,
) {
    object Account : TopBarDrawerMenuItems(
        id = "account",
        title = "Профиль",
        contentDescription = "OpenAccountPage",
        icon = Icons.Default.ManageAccounts,
        route = DetailsPage.Account.route,
    )

    object AddPerson : TopBarDrawerMenuItems(
        id = "add_persons",
        title = "Пригласить",
        contentDescription = "OpenAddPersonsPage",
        icon = Icons.Default.PersonAdd,
        route = DetailsPage.AddPersons.route,
    )

    object Settings : TopBarDrawerMenuItems(
        id = "settings",
        title = "Настройки",
        contentDescription = "OpenSettingsPage",
        icon = Icons.Default.Settings,
        route = DetailsPage.Settings.route,
    )
    object AdminPanel : TopBarDrawerMenuItems(
        id = "admin_panel",
        title = "Администрирование",
        contentDescription = "OpenAdminSettingsPage",
        icon = Icons.Default.AdminPanelSettings,
        route = DetailsPage.AdminPanel.route,
    )
    object Logout : TopBarDrawerMenuItems(
        id = "logout",
        title = "Выход",
        contentDescription = "OpenALogoutPage",
        icon = Icons.Default.Logout,
        route = DetailsPage.Logout.route,
    )

}
