package com.app.dreamapp.ui.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Person
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import com.app.dreamapp.ui.components.DreamAppDefaultButton
import com.app.dreamapp.ui.components.DreamAppFooterBar.BottomBarScreen
import com.app.dreamapp.ui.screens.ContactChats.ContactChats
import com.app.dreamapp.ui.screens.ContactChats.ContactsChatsScreenViewModel
import com.app.dreamapp.ui.screens.ContactsMessages.ContactMessagesScreenViewModel
import com.app.dreamapp.ui.screens.Timeline.TimelineItem
import com.app.dreamapp.ui.screens.Timeline.TimelineScreenViewModel
import com.app.dreamapp.ui.theme.DreamAppTheme
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterialApi::class, ExperimentalComposeUiApi::class)
@Composable
fun BottomSheetContentWrapper(
    modifier: Modifier,
    route: String,
    contactsChatsScreenViewModel: ContactsChatsScreenViewModel,
    timelineScreenViewModel: TimelineScreenViewModel,
    onErrorClick: () -> Unit,
    onDoneClick: () -> Unit,
    onCloseClick: () -> Unit
) {
    if (BottomBarScreen.Timeline.route == route) {
        AddNewsBottomSheet(
            modifier = modifier,
            onErrorClick = {
                onErrorClick()
            },
            onDoneClick = {
                timelineScreenViewModel.addNewNews(it)
                onDoneClick()
            },
            onCloseClick = {
                onCloseClick()
            }
        )
    }
    if (BottomBarScreen.ContactsChats.route == route) {
        AddContactBottomSheet(
            modifier = modifier,
            onErrorClick = {
                onErrorClick()
            },
            onDoneClick = {
                contactsChatsScreenViewModel.addNewContacts(it)
                onDoneClick()
            },
            onCloseClick = {
                onCloseClick()
            }
        )
    }
}