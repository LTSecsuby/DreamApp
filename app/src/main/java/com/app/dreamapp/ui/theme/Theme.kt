package com.app.dreamapp.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.State
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.app.dreamapp.ui.pages.Settings.SettingsScreenViewModel
import com.app.dreamapp.ui.pages.Settings.SettingsScreenViewState
import com.google.accompanist.systemuicontroller.rememberSystemUiController

@Composable
fun DreamAppMainTheme(
    settingsScreenViewState: State<SettingsScreenViewState?>,
//    colorStyle: DreamAppColorStyle = DreamAppColorStyle.Purple,
//    textSize: DreamAppTextSize = DreamAppTextSize.Medium,
//    paddingSize: DreamAppTextSize = DreamAppTextSize.Medium,
//    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {

    // Set status bar color
    val systemUiController = rememberSystemUiController()
    SideEffect {
        systemUiController.setSystemBarsColor(
            color = if (settingsScreenViewState.value!!.isDarkMode) baseDarkPalette.primaryBackground else baseLightPalette.primaryBackground,
            darkIcons = !settingsScreenViewState.value!!.isDarkMode
        )
    }

    val colors = when (settingsScreenViewState.value!!.isDarkMode) {
        true -> {
            when (settingsScreenViewState.value!!.colorStyle) {
                DreamAppColorStyle.Purple -> purpleDarkPalette
                DreamAppColorStyle.Blue -> blueDarkPalette
                DreamAppColorStyle.Orange -> orangeDarkPalette
                DreamAppColorStyle.Red -> redDarkPalette
                DreamAppColorStyle.Green -> greenDarkPalette
            }
        }
        false -> {
            when (settingsScreenViewState.value!!.colorStyle) {
                DreamAppColorStyle.Purple -> purpleLightPalette
                DreamAppColorStyle.Blue -> blueLightPalette
                DreamAppColorStyle.Orange -> orangeLightPalette
                DreamAppColorStyle.Red -> redLightPalette
                DreamAppColorStyle.Green -> greenLightPalette
            }
        }
    }

    val typography = DreamAppTypography(
        heading = TextStyle(
            fontSize = when (settingsScreenViewState.value!!.textSize) {
                DreamAppTextSize.Small -> 24.sp
                DreamAppTextSize.Medium -> 28.sp
                DreamAppTextSize.Big -> 32.sp
            },
            fontWeight = FontWeight.Bold
        ),
        body = TextStyle(
            fontSize = when (settingsScreenViewState.value!!.textSize) {
                DreamAppTextSize.Small -> 14.sp
                DreamAppTextSize.Medium -> 16.sp
                DreamAppTextSize.Big -> 18.sp
            },
            fontWeight = FontWeight.Normal
        ),
        toolbar = TextStyle(
            fontSize = when (settingsScreenViewState.value!!.textSize) {
                DreamAppTextSize.Small -> 14.sp
                DreamAppTextSize.Medium -> 16.sp
                DreamAppTextSize.Big -> 18.sp
            },
            fontWeight = FontWeight.Medium
        ),
        caption = TextStyle(
            fontSize = when (settingsScreenViewState.value!!.textSize) {
                DreamAppTextSize.Small -> 10.sp
                DreamAppTextSize.Medium -> 12.sp
                DreamAppTextSize.Big -> 14.sp
            }
        )
    )

    val shapes = DreamAppShape(
        padding = when (settingsScreenViewState.value!!.paddingSize) {
            DreamAppTextSize.Small -> 12.dp
            DreamAppTextSize.Medium -> 16.dp
            DreamAppTextSize.Big -> 20.dp
        }
    )

    CompositionLocalProvider(
        LocalDreamAppColors provides colors,
        LocalDreamAppTypography provides typography,
        LocalDreamAppShape provides shapes,
        content = content
    )
}