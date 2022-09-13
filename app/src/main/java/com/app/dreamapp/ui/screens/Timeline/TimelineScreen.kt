package com.app.dreamapp.ui.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.app.dreamapp.ui.screens.ContactChats.ContactChats
import com.app.dreamapp.ui.screens.Timeline.TimelineItem
import com.app.dreamapp.ui.theme.DreamAppTheme

@Composable
fun TimelineScreen(
    modifier: Modifier,
    onNewsClick: (itemId: String) -> Unit,
) {
    Surface(
        modifier = modifier,
        color = DreamAppTheme.colors.secondaryBackground,
    ) {
        val timelineItems: Array<TimelineItem> = listOf(
            TimelineItem(
                id = "TimelineItem:1",
                title = "Новость1",
                created = "сейчас",
                description = "Это самая первая новость",
                author = ContactChats(
                    id = "ContactChats:8",
                    name = "Женя",
                    avatar = Icons.Default.Person,
                )
            ),
            TimelineItem(
                id = "TimelineItem:2",
                title = "Новость2",
                created = "сегодня",
                description = "Это вторая новость",
                author = ContactChats(
                    id = "ContactChats:9",
                    name = "Не Женя",
                    avatar = Icons.Default.Person,
                )
            ),
            TimelineItem(
                id = "TimelineItem:3",
                title = "Новость3",
                created = "вчера",
                description = "Это третья новость",
                author = ContactChats(
                    id = "ContactChats:10",
                    name = "Точно не Женя",
                    avatar = Icons.Default.Person,
                )
            ),
        ).toTypedArray()
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .background(DreamAppTheme.colors.secondaryBackground),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            items(timelineItems) { timelineItem ->
                Card(
                    modifier = Modifier
                        .padding(top = 16.dp, start = 16.dp, end = 16.dp)
                        .fillMaxWidth()
                        .clickable { onNewsClick(timelineItem.id) },
                    border = BorderStroke(1.dp, DreamAppTheme.colors.tintColor),
                    backgroundColor = DreamAppTheme.colors.primaryBackground
                ){
                    Column(
                        modifier = Modifier
                            .fillMaxWidth(),
                        verticalArrangement = Arrangement.Top,
                        horizontalAlignment = Alignment.Start
                    ) {
                        Row(
                            modifier = Modifier
                                .padding(start = 16.dp, end = 16.dp, top = 10.dp)
                                .fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.Top
                        ) {
                            Text(
                                modifier = Modifier,
                                text = timelineItem.title,
                                color = DreamAppTheme.colors.primaryText,
                                style = DreamAppTheme.typography.heading,
                            )
                            Text(
                                modifier = Modifier,
                                text = timelineItem.created,
                                color = DreamAppTheme.colors.primaryText,
                                style = DreamAppTheme.typography.caption,
                            )
                        }
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(top = 16.dp, start = 16.dp, end = 16.dp),
                        ) {
                            Text(
                                text = timelineItem.description,
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
                                imageVector = timelineItem.author.avatar,
                                contentDescription = timelineItem.author.name,
                                tint = DreamAppTheme.colors.tintColor
                            )
                            Text(
                                modifier = Modifier,
                                text = timelineItem.author.name,
                                color = DreamAppTheme.colors.primaryText,
                                style = DreamAppTheme.typography.caption,
                            )
                        }
                    }
                }
            }
        }
    }
}