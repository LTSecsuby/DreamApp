package com.app.dreamapp.ui.graphs

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.material.*
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.app.dreamapp.ui.components.DreamAppDefaultButton
import com.app.dreamapp.ui.pages.Main.MainScreenViewModel
import com.app.dreamapp.ui.screens.ChoiceAuthScreen
import com.app.dreamapp.ui.screens.DetailsScreen
import com.app.dreamapp.ui.theme.DreamAppTheme
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

fun NavGraphBuilder.authNavGraph(
    modifier: Modifier,
    navController: NavHostController,
    scaffoldState: ScaffoldState,
    scope: CoroutineScope,
    mainScreenViewModel: MainScreenViewModel,
) {
    navigation(
        route = Graph.AUTHENTICATION.route,
        startDestination = AuthScreen.Choice.route
    ) {
        composable(route = AuthScreen.Choice.route) {
            ChoiceAuthScreen(
                modifier = modifier,
                onLoginClick = {
                    navController.navigate(AuthScreen.Login.route)
                },
                onSignUpClick = {
                    navController.navigate(AuthScreen.SignUp.route)
                },
                onForgotClick = {
                    navController.navigate(AuthScreen.Forgot.route)
                }
            )
        }
        composable(route = AuthScreen.Login.route) {
            DetailsScreen(
                modifier = modifier,
                "Авторизация",
                navController,
                isShowTopBar = true,
                contentHeader = {

                },
                contentBody = {
                    Surface(
                        modifier = modifier,
                        color = DreamAppTheme.colors.secondaryBackground,
                    ) {
                        Column(
                            modifier = Modifier
                                .fillMaxSize(),
                            verticalArrangement = Arrangement.Center,
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            val message = remember {mutableStateOf("")}
                            OutlinedTextField(
                                message.value,
                                {message.value = it},
                                modifier = Modifier
                                    .padding(start = 16.dp, end = 16.dp)
                                    .fillMaxWidth(),
                                textStyle = DreamAppTheme.typography.body,
                                colors = TextFieldDefaults.outlinedTextFieldColors(
                                    textColor = DreamAppTheme.colors.primaryText,
                                    backgroundColor = DreamAppTheme.colors.secondaryBackground,
                                    focusedBorderColor= DreamAppTheme.colors.tintColor,
                                    unfocusedBorderColor = DreamAppTheme.colors.secondaryText,
                                ),
                                label = {
                                    Text(
                                        text = "Введите имя",
                                        color = DreamAppTheme.colors.primaryText,
                                        style = DreamAppTheme.typography.body
                                    )
                                },
                                singleLine = true,
                                placeholder = {
                                    Text(
                                        text = "Ваше имя",
                                        color = DreamAppTheme.colors.primaryText,
                                        style = DreamAppTheme.typography.body
                                    )
                                },
                                keyboardActions = KeyboardActions(
                                    onDone = {
                                        if (message.value.isNotEmpty()) {
                                            mainScreenViewModel.setCurrentUserName(message.value)
                                            navController.popBackStack()
                                            navController.navigate(Graph.MAIN.route)
                                        } else {
                                            scope.launch {
                                                scaffoldState.snackbarHostState.showSnackbar("Введите имя")
                                            }
                                        }
                                    }
                                )
                            )
                            Spacer(modifier = Modifier.height(16.dp))
                            DreamAppDefaultButton(
                                colorText = DreamAppTheme.colors.primaryText,
                                backgroundColor = DreamAppTheme.colors.primaryBackground,
                                onClick = {
                                    if (message.value.isNotEmpty()) {
                                        mainScreenViewModel.setCurrentUserName(message.value)
                                        navController.popBackStack()
                                        navController.navigate(Graph.MAIN.route)
                                    } else {
                                        scope.launch {
                                            scaffoldState.snackbarHostState.showSnackbar("Введите имя")
                                        }
                                    }
                                },
                                styleText = DreamAppTheme.typography.caption,
                                text = "Авторизация",
                                border = BorderStroke(1.dp, DreamAppTheme.colors.secondaryText),
                                enabled = message.value.isNotEmpty()
                            )
                        }
                    }
                }
            )
        }
        composable(route = AuthScreen.SignUp.route) {
            DetailsScreen(
                modifier = modifier,
                "Регистрация",
                navController,
                isShowTopBar = true,
                contentHeader = {

                },
                contentBody = {
                    Box(
                        modifier = Modifier
                            .fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = "Регистрация",
                            color =  DreamAppTheme.colors.primaryText,
                        )
                    }
                }
            )
        }
        composable(route = AuthScreen.Forgot.route) {
            DetailsScreen(
                modifier = modifier,
                "Забыли пароль?",
                navController,
                isShowTopBar = true,
                contentHeader = {

                },
                contentBody = {
                    Box(
                        modifier = Modifier
                            .fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = "Забыли пароль?",
                            color =  DreamAppTheme.colors.primaryText,
                        )
                    }
                }
            )
        }
    }
}

sealed class AuthScreen(val route: String) {
    object Choice : AuthScreen(route = "CHOICE")
    object Login : AuthScreen(route = "LOGIN")
    object SignUp : AuthScreen(route = "SIGN_UP")
    object Forgot : AuthScreen(route = "FORGOT")
}