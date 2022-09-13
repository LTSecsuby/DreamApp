package com.app.dreamapp.ui.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import com.app.dreamapp.ui.theme.DreamAppTheme

@Composable
fun ChoiceAuthScreen(
    modifier: Modifier,
    onLoginClick: () -> Unit,
    onSignUpClick: () -> Unit,
    onForgotClick: () -> Unit
) {
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
            Text(
                modifier = Modifier.clickable { onLoginClick() },
                text = "Авторизация",
                color = DreamAppTheme.colors.primaryText,
                style = DreamAppTheme.typography.heading,
            )
            Text(
                modifier = Modifier.clickable { onSignUpClick() },
                text = "Регистрация",
                color = DreamAppTheme.colors.primaryText,
                style = DreamAppTheme.typography.body,
            )
            Text(
                modifier = Modifier.clickable { onForgotClick() },
                text = "Забыли пароль?",
                color = DreamAppTheme.colors.primaryText,
                style = DreamAppTheme.typography.body,
            )
        }
    }
}