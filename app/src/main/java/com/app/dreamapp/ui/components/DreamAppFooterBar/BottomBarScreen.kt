package com.app.dreamapp.ui.components.DreamAppFooterBar

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.ui.graphics.vector.ImageVector
import com.app.dreamapp.ui.pages.Main.MainRoute

sealed class BottomBarScreen(
    var name: String,
    var route: String,
    var icon: ImageVector,
    var badgeCount: Int = 0,
) {
    object Timeline : BottomBarScreen(
        name = "Лента",
        route = "TIMELINE",
        icon = Icons.Default.Timeline,
        badgeCount = 1,
    )

    object ContactsChats : BottomBarScreen(
        name = "Контакты",
        route = "CONTACT_CHATS",
        icon = Icons.Default.Chat,
        badgeCount = 2,
    )

    object LinkTree : BottomBarScreen(
        name = "Дерево связей",
        route = "LINK_TREE",
        icon = Icons.Default.AccountTree,
        badgeCount = 3,
    )
}
