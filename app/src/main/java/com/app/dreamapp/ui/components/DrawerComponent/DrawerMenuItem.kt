package com.app.dreamapp.ui.components.DrawerComponent

import androidx.compose.ui.graphics.vector.ImageVector

data class DrawerMenuItem(
    var id: String,
    var title: String,
    var contentDescription: String,
    var icon: ImageVector,
    var route: String,
)
