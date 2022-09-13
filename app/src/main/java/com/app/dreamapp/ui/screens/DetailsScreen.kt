package com.app.dreamapp.ui.screens


import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.app.dreamapp.ui.theme.DreamAppTheme

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun DetailsScreen(
    modifier: Modifier,
    name: String,
    navController: NavHostController,
    contentBody: @Composable () -> Unit,
    isShowTopBar: Boolean = false,
    contentHeader: @Composable () -> Unit = {},
    bottomSheetScaffoldState: BottomSheetScaffoldState? = null,
    contentBottomSheet: @Composable () -> Unit = {},
    onBottomSheetButtonClick: () -> Unit = {}
) {

    if (bottomSheetScaffoldState != null) {
        BottomSheetScaffold(
            scaffoldState = bottomSheetScaffoldState,
            sheetContent = {
                contentBottomSheet()
            },
            sheetBackgroundColor = DreamAppTheme.colors.tintColor,
            sheetPeekHeight = 0.dp
        ) {
            Surface(
                modifier = modifier,
                color = DreamAppTheme.colors.secondaryBackground,
            ) {
                Scaffold(
                    topBar = {
                        if (isShowTopBar) {
                            TopAppBar(
                                backgroundColor = DreamAppTheme.colors.primaryBackground,
                                contentColor = DreamAppTheme.colors.primaryText,
                                elevation = 5.dp
                            ) {
                                Row(
                                    modifier = Modifier
                                        .fillMaxWidth(),
                                    horizontalArrangement = Arrangement.SpaceBetween
                                ) {
                                    Box() {
                                        IconButton(
                                            onClick = {
                                                navController.popBackStack()
                                            }
                                        ) {
                                            Icon(
                                                imageVector = Icons.Default.ArrowBack,
                                                contentDescription = "BackScreen",
                                                tint = DreamAppTheme.colors.primaryText
                                            )
                                        }
                                    }
                                    Row(
                                        modifier = Modifier
                                            .padding(10.dp),
                                        verticalAlignment = Alignment.CenterVertically
                                    ) {
                                        if (name != null) {
                                            Text(
                                                text = name,
                                                color = DreamAppTheme.colors.primaryText,
                                                style = DreamAppTheme.typography.heading
                                            )
                                        }
                                        Spacer(modifier = Modifier.width(16.dp))
                                        contentHeader()
                                    }
                                }

                            }
                        }
                    },
                    floatingActionButtonPosition = FabPosition.End,
                    floatingActionButton = {
                        FloatingActionButton(
                            onClick = {
                                onBottomSheetButtonClick()
                            },
                            backgroundColor = DreamAppTheme.colors.tintColor,
                            contentColor = DreamAppTheme.colors.primaryText,
                        ){
                            Icon(
                                imageVector = Icons.Default.Add,
                                contentDescription = "floating_add_icon"
                            )
                        }

                    },
                ) {
                    contentBody()
                }
            }
        }
    }
    if (bottomSheetScaffoldState == null) {
        Surface(
            modifier = modifier,
            color = DreamAppTheme.colors.secondaryBackground,
        ) {
            Scaffold(
                topBar = {
                    if (isShowTopBar) {
                        TopAppBar(
                            backgroundColor = DreamAppTheme.colors.primaryBackground,
                            contentColor = DreamAppTheme.colors.primaryText,
                            elevation = 5.dp
                        ) {
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth(),
                                horizontalArrangement = Arrangement.SpaceBetween
                            ) {
                                Box() {
                                    IconButton(
                                        onClick = {
                                            navController.popBackStack()
                                        }
                                    ) {
                                        Icon(
                                            imageVector = Icons.Default.ArrowBack,
                                            contentDescription = "BackScreen",
                                            tint = DreamAppTheme.colors.primaryText
                                        )
                                    }
                                }
                                Row(
                                    modifier = Modifier
                                        .padding(10.dp),
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    Text(
                                        text = name,
                                        color = DreamAppTheme.colors.primaryText,
                                        style = DreamAppTheme.typography.heading
                                    )
                                    Spacer(modifier = Modifier.width(16.dp))
                                    contentHeader()
                                }
                            }

                        }
                    }
                },
            ) {
                contentBody()
            }
        }
    }
}