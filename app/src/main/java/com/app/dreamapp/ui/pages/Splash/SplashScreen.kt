package com.app.dreamapp.ui.pages.Splash

import androidx.compose.foundation.layout.*
import androidx.compose.material.LinearProgressIndicator
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.app.dreamapp.ui.graphs.Graph
import com.app.dreamapp.ui.theme.DreamAppTheme
import kotlinx.coroutines.delay
import kotlin.time.Duration.Companion.seconds
import kotlin.time.ExperimentalTime


@OptIn(ExperimentalTime::class)
@Composable
fun SplashScreen(
    modifier: Modifier,
    navController: NavController
) {
    Surface(
        modifier = modifier,
        color = DreamAppTheme.colors.secondaryBackground,
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
        ) {
            Column(modifier = Modifier.align(Alignment.Center)) {
                Text(
                    modifier = Modifier.fillMaxWidth(),
                    text = "Dream App",
                    style = DreamAppTheme.typography.heading,
                    color = DreamAppTheme.colors.primaryText,
                    textAlign = TextAlign.Center
                )
                Text(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 4.dp),
                    text = "App for you!",
                    style = DreamAppTheme.typography.body,
                    color = DreamAppTheme.colors.secondaryText,
                    textAlign = TextAlign.Center
                )
                CustomLinearProgressBar()
            }
        }
    }

    LaunchedEffect(key1 = Unit, block = {
        delay(5.seconds)
        navController.popBackStack(navController.graph.startDestinationId, true)
        navController.graph.setStartDestination(Graph.AUTHENTICATION.route)
        navController.navigate(Graph.AUTHENTICATION.route)
    })
}

@Composable
private fun CustomLinearProgressBar() {
    Column(
        modifier = Modifier
            .padding(top = 15.dp, start = 50.dp, end = 50.dp)
            .fillMaxWidth()
    ) {
        LinearProgressIndicator(
            modifier = Modifier
                .fillMaxWidth()
                .height(5.dp),
            backgroundColor = DreamAppTheme.colors.secondaryBackground,
            color = DreamAppTheme.colors.primaryText
        )
    }
}