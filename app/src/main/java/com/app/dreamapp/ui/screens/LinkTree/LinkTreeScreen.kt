package com.app.dreamapp.ui.screens.LinkTree

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import com.app.dreamapp.ui.theme.DreamAppTheme

@Composable
fun LinkTreeScreen(
    modifier: Modifier = Modifier,
    navController: NavHostController,
) {
    Surface(
        modifier = modifier,
        color = DreamAppTheme.colors.primaryBackground,
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