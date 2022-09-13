package com.app.dreamapp.ui.pages.Settings

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.app.dreamapp.R
import com.app.dreamapp.ui.components.ColorCard
import com.app.dreamapp.ui.components.DrawerComponent.DrawerBody
import com.app.dreamapp.ui.components.DrawerComponent.DrawerMenuItem
import com.app.dreamapp.ui.components.DrawerHeader
import com.app.dreamapp.ui.components.DreamAppFooterBar.BottomBarScreen
import com.app.dreamapp.ui.components.DreamAppFooterBar.BottomNavigationBar
import com.app.dreamapp.ui.components.DreamAppHeaderBar.DreamAppHeaderBar
import com.app.dreamapp.ui.components.DreamAppHeaderBar.TopBarDrawerMenuItems
import com.app.dreamapp.ui.components.MenuItem
import com.app.dreamapp.ui.components.MenuItemModel
import com.app.dreamapp.ui.graphs.MainNavGraph
import com.app.dreamapp.ui.theme.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

//@PreviewParameter(SimpleMainScreenViewModelProvider::class)
//@PreviewParameter(SimpleSettingsBundleProvider::class)

@Composable
fun SettingsScreen(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
    settingsScreenViewModel: SettingsScreenViewModel,
    settingsScreenViewState: State<SettingsScreenViewState?>,
) {
//    val pagesMain = listOf(
//        BottomBarScreen.Timeline,
//        BottomBarScreen.ContactsChats,
//        BottomBarScreen.LinkTree,
//    )
//
//    var pagesDetails = listOf(
//        TopBarDrawerMenuItems.Account,
//        TopBarDrawerMenuItems.AddPerson,
//        TopBarDrawerMenuItems.Settings,
//        TopBarDrawerMenuItems.AdminPanel,
//        TopBarDrawerMenuItems.Logout,
//    )

    Surface(
        modifier = modifier,
        color = DreamAppTheme.colors.primaryBackground,
    ) {
        Column(
            modifier = Modifier.fillMaxSize()
        ) {

            MenuItem(
                model = MenuItemModel(
                    title = stringResource(id = R.string.title_font_size),
                    currentIndex = when (settingsScreenViewState.value?.textSize) {
                        DreamAppTextSize.Small -> 0
                        DreamAppTextSize.Medium -> 1
                        DreamAppTextSize.Big -> 2
                        else -> {
                            0
                        }
                    },
                    values = listOf(
                        stringResource(id = R.string.title_font_size_small),
                        stringResource(id = R.string.title_font_size_medium),
                        stringResource(id = R.string.title_font_size_big)
                    )
                ),
                onItemSelected = {
                    val textSize = when (it) {
                        0 -> DreamAppTextSize.Small
                        1 -> DreamAppTextSize.Medium
                        2 -> DreamAppTextSize.Big
                        else -> throw NotImplementedError("No valid value for this $it")
                    }
                    settingsScreenViewModel.setCurrentSettingsTextSize(textSize)
                }
            )

            MenuItem(
                model = MenuItemModel(
                    title = stringResource(id = R.string.title_padding_size),
                    currentIndex = when (settingsScreenViewState.value?.paddingSize) {
                        DreamAppTextSize.Small -> 0
                        DreamAppTextSize.Medium -> 1
                        DreamAppTextSize.Big -> 2
                        else -> {
                            0
                        }
                    },
                    values = listOf(
                        stringResource(id = R.string.title_padding_small),
                        stringResource(id = R.string.title_padding_medium),
                        stringResource(id = R.string.title_padding_big)
                    )
                ),
                onItemSelected = {
                    val paddingSize = when (it) {
                        0 -> DreamAppTextSize.Small
                        1 -> DreamAppTextSize.Medium
                        2 -> DreamAppTextSize.Big
                        else -> throw NotImplementedError("No valid value for this $it")
                    }
                    settingsScreenViewModel.setCurrentSettingsPaddingSize(paddingSize)
                }
            )

            Row(
                modifier = Modifier
                    .padding(DreamAppTheme.shapes.padding)
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                ColorCard(
                    color = if (settingsScreenViewState.value?.isDarkMode == true) purpleDarkPalette.tintColor else purpleLightPalette.tintColor,
                    select = settingsScreenViewState.value?.colorStyle == DreamAppColorStyle.Purple,
                    onClick = {
                        settingsScreenViewModel.setCurrentSettingsColorStyle(DreamAppColorStyle.Purple)
                    }
                )
                ColorCard(
                    color = if (settingsScreenViewState.value?.isDarkMode == true) orangeDarkPalette.tintColor else orangeLightPalette.tintColor,
                    select = settingsScreenViewState.value?.colorStyle == DreamAppColorStyle.Orange,
                    onClick = {
                        settingsScreenViewModel.setCurrentSettingsColorStyle(DreamAppColorStyle.Orange)
                    }
                )
                ColorCard(
                    color = if (settingsScreenViewState.value?.isDarkMode == true) blueDarkPalette.tintColor else blueLightPalette.tintColor,
                    select = settingsScreenViewState.value?.colorStyle == DreamAppColorStyle.Blue,
                    onClick = {
                        settingsScreenViewModel.setCurrentSettingsColorStyle(DreamAppColorStyle.Blue)
                    }
                )
            }

            Row(
                modifier = Modifier
                    .padding(DreamAppTheme.shapes.padding)
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                ColorCard(
                    color = if (settingsScreenViewState.value?.isDarkMode == true) redDarkPalette.tintColor else redLightPalette.tintColor,
                    select = settingsScreenViewState.value?.colorStyle == DreamAppColorStyle.Red,
                    onClick = {
                        settingsScreenViewModel.setCurrentSettingsColorStyle(DreamAppColorStyle.Red)
                    }
                )
                ColorCard(
                    color = if (settingsScreenViewState.value?.isDarkMode == true) greenDarkPalette.tintColor else greenLightPalette.tintColor,
                    select = settingsScreenViewState.value?.colorStyle == DreamAppColorStyle.Green,
                    onClick = {
                        settingsScreenViewModel.setCurrentSettingsColorStyle(DreamAppColorStyle.Green)
                    }
                )
            }
        }
    }
}
