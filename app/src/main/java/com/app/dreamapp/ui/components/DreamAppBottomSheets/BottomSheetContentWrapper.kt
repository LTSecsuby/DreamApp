package com.app.dreamapp.ui.screens

import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import com.app.dreamapp.ui.components.DreamAppFooterBar.BottomBarScreen
import com.app.dreamapp.ui.screens.ContactChats.ContactsChatsScreenViewModel
import com.app.dreamapp.ui.screens.Timeline.TimelineScreenViewModel

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