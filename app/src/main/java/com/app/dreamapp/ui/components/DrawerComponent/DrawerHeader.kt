package com.app.dreamapp.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DarkMode
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.app.dreamapp.R
import com.app.dreamapp.ui.pages.Settings.SettingsScreenViewModel
import com.app.dreamapp.ui.pages.Settings.SettingsScreenViewState
import com.app.dreamapp.ui.theme.DreamAppColorStyle
import com.app.dreamapp.ui.theme.DreamAppTheme
import com.app.dreamapp.ui.theme.SettingsBundle

@Composable
fun DrawerHeader(
    currentUserName: String?,
    settingsScreenViewModel: SettingsScreenViewModel,
    settingsScreenViewState: State<SettingsScreenViewState?>,
) {

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(DreamAppTheme.colors.primaryBackground)
            .padding(vertical = 16.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Column(
            modifier = Modifier
                .padding(start = 10.dp),
            horizontalAlignment = Alignment.Start
        ) {
            Image(
                painter = painterResource(R.drawable.profile),
                contentDescription = "image",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(60.dp)
                    .clip(CircleShape)
            )
            if (currentUserName != null) {
                Text(
                    currentUserName,
                    modifier = Modifier
                        .padding(top = 16.dp),
                    color = DreamAppTheme.colors.primaryText,
                )
            } else {
                Text(
                    "User Null",
                    modifier = Modifier
                        .padding(top = 16.dp),
                    color = DreamAppTheme.colors.primaryText,
                )
            }
        }
        Row(
            modifier = Modifier
                .padding(end = 16.dp),
            horizontalArrangement = Arrangement.End,
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Icon(
                imageVector = Icons.Default.DarkMode,
                contentDescription = stringResource(R.string.app_dark_mode),
                tint = DreamAppTheme.colors.primaryText
            )
            Spacer(modifier = Modifier.width(16.dp))
            Text(
                text = stringResource(R.string.app_dark_mode),
                color = DreamAppTheme.colors.primaryText,
            )
            Spacer(modifier = Modifier.width(16.dp))
            DreamAppDefaultToggleButton(
                modifier = Modifier,
                backgroundColor = DreamAppTheme.colors.tintColor,
                isChecked = settingsScreenViewState.value!!.isDarkMode,
                onCheckedChange = {
                    settingsScreenViewModel.setCurrentSettingsIsDarkMode(!settingsScreenViewState.value!!.isDarkMode)
                },
            )
        }

    }
    Box(modifier = Modifier
        .fillMaxWidth()
        .height(1.dp)
        .shadow(
            elevation = 5.dp,
            ambientColor = DreamAppTheme.colors.primaryText,
            spotColor = DreamAppTheme.colors.primaryText
        )
    )
}