package com.app.dreamapp.ui.screens.ContactChats

import android.graphics.drawable.Icon
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Icon
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Person
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.app.dreamapp.ui.components.DreamAppFooterBar.BottomBarScreen
import com.app.dreamapp.ui.graphs.Graph
import com.app.dreamapp.ui.theme.DreamAppTheme

@Composable
fun ContactsChatsScreen(
    modifier: Modifier,
    onContactClick: (itemId: String) -> Unit,
) {
    Surface(
        modifier = modifier,
        color = DreamAppTheme.colors.secondaryBackground,
    ) {
        val contacts: Array<ContactChats> = listOf(
            ContactChats(
                id = "ContactChats:1",
                name = "Контакт1",
                avatar = Icons.Default.Person
            ),
            ContactChats(
                id = "ContactChats:2",
                name = "Контакт2",
                avatar = Icons.Default.Person
            ),
            ContactChats(
                id = "ContactChats:3",
                name = "Контакт3",
                avatar = Icons.Default.Person
            ),
            ContactChats(
                id = "ContactChats:4",
                name = "Контакт4",
                avatar = Icons.Default.Person
            ),
            ContactChats(
                id = "ContactChats:5",
                name = "Контакт5",
                avatar = Icons.Default.Person
            ),
            ContactChats(
                id = "ContactChats:6",
                name = "Контакт6",
                avatar = Icons.Default.Person
            ),
            ContactChats(
                id = "ContactChats:7",
                name = "Контакт7",
                avatar = Icons.Default.Person
            )
        ).toTypedArray()
        LazyColumn(
            modifier = Modifier
                .fillMaxSize(),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            items(contacts) { contact ->
                Row(
                    modifier = Modifier
                        .padding(top = 16.dp, start = 16.dp, end = 16.dp)
                        .fillMaxWidth()
                        .background(DreamAppTheme.colors.primaryBackground)
                        .clickable { onContactClick(contact.id) },
                    horizontalArrangement = Arrangement.Start,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        modifier = Modifier
                            .clip(CircleShape)
                            .size(60.dp),
                        imageVector = contact.avatar,
                        contentDescription = contact.name,
                        tint = DreamAppTheme.colors.tintColor
                    )
                    Text(
                        modifier = Modifier,
                        text = contact.name,
                        color = DreamAppTheme.colors.primaryText,
                        style = DreamAppTheme.typography.heading,
                    )
                }
            }
        }
    }
}
