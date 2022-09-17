package com.app.dreamapp.ui.screens.Details

import android.annotation.SuppressLint
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.*
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavHostController
import com.app.dreamapp.ui.theme.DreamAppTheme
import com.app.dreamapp.ui.screens.AddMessageBottomSheet
import com.app.dreamapp.ui.screens.ContactChats.ContactChats
import com.app.dreamapp.ui.screens.ContactsMessages.ContactMessages
import com.app.dreamapp.ui.screens.DetailsScreen
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@SuppressLint("UnrememberedMutableState")
@OptIn(ExperimentalMaterialApi::class)
@Composable
fun ContactChatDetail(
    modifier: Modifier,
    navController: NavHostController,
    scope: CoroutineScope,
    contactId: String,
    contactChatsMessagesState: SnapshotStateList<ContactMessages>,
    contactChatsMessages: List<ContactMessages>,
) {

    val sheetState = rememberBottomSheetState(initialValue = BottomSheetValue.Collapsed)
    val bottomSheetScaffoldState = rememberBottomSheetScaffoldState(bottomSheetState = sheetState)

    DetailsScreen(
        modifier = modifier,
        name = contactId,
        navController = navController,
        contentBody = {
            Surface(
                modifier = modifier,
                color = DreamAppTheme.colors.secondaryBackground,
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth(),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = contactId,
                        color =  DreamAppTheme.colors.primaryText,
                    )
                }
                LazyColumn(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(DreamAppTheme.colors.secondaryBackground),
                    verticalArrangement = Arrangement.Top,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    items(contactChatsMessages) { message ->
                        ContactChatMessage(message)
                    }
                }
            }
        },
        isShowTopBar = true,
        bottomSheetScaffoldState = bottomSheetScaffoldState,
        contentBottomSheet = {
            AddMessageBottomSheet(
                modifier = modifier,
                onErrorClick = {
                    // Error no text message here
                },
                onDoneClick = {
                    scope.launch {
                        contactChatsMessagesState.add(it)
                        sheetState.collapse()
                    }
                },
                onCloseClick = {
                    scope.launch {
                        if (sheetState.isCollapsed) {
                            sheetState.expand()
                        } else {
                            sheetState.collapse()
                        }
                    }
                }
            )
        },
        onBottomSheetButtonClick = {
            scope.launch {
                if (sheetState.isCollapsed) {
                    sheetState.expand()
                } else {
                    sheetState.collapse()
                }
            }
        }
    )
    BackHandler(
        enabled = sheetState.isExpanded,
        onBack = {
            scope.launch {
                sheetState.collapse()
            }
        }
    )
}

@Composable
fun ContactChatMessage(message: ContactMessages) {
    Card(
        modifier = Modifier
            .padding(top = 16.dp, start = 16.dp, end = 16.dp)
            .fillMaxWidth()
            .clickable {
                // navController.navigate(TimeLinePage.News.route)
                // onNewsClick(timelineItem.id)
            },
        border = BorderStroke(1.dp, DreamAppTheme.colors.tintColor),
        backgroundColor = DreamAppTheme.colors.primaryBackground
    ){
        Column(
            modifier = Modifier
                .fillMaxWidth(),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.Start
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 16.dp, start = 16.dp, end = 16.dp),
            ) {
                Text(
                    text = message.text,
                    color = DreamAppTheme.colors.primaryText,
                    style = DreamAppTheme.typography.body,
                )
            }
            Row(
                modifier = Modifier
                    .padding(top = 16.dp, start = 16.dp, end = 16.dp, bottom = 16.dp)
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.End,
                verticalAlignment = Alignment.Bottom
            ) {
                Icon(
                    modifier = Modifier
                        .clip(CircleShape)
                        .size(24.dp),
                    imageVector = message.author.avatar,
                    contentDescription = message.author.name,
                    tint = DreamAppTheme.colors.tintColor
                )
                Text(
                    modifier = Modifier,
                    text = message.author.name,
                    color = DreamAppTheme.colors.primaryText,
                    style = DreamAppTheme.typography.caption,
                )
            }
        }
    }
}
