package com.app.dreamapp.ui.components.DrawerComponent

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.app.dreamapp.ui.components.DreamAppHeaderBar.TopBarDrawerMenuItems
import com.app.dreamapp.ui.theme.DreamAppTheme

@Composable
fun DrawerBody(
    items: List<TopBarDrawerMenuItems>,
    modifier: Modifier = Modifier,
    onItemClick: (TopBarDrawerMenuItems) -> Unit
) {
    LazyColumn(modifier = modifier
        .fillMaxWidth()
        .fillMaxHeight(1f)
        .background(DreamAppTheme.colors.primaryBackground)
    ) {
        items(items) { item -> Row(
            modifier = Modifier
                .clickable {
                    onItemClick(item)
                }
                .padding(12.dp)
        ) {
            Icon(
                imageVector = item.icon,
                contentDescription = item.contentDescription,
                tint = DreamAppTheme.colors.primaryText
            )
            Spacer(modifier = Modifier.width(16.dp))
            Text(
                color = DreamAppTheme.colors.primaryText,
                text = item.title,
                modifier = Modifier
                    .weight(1f)

            )
        }
        }
    }
}