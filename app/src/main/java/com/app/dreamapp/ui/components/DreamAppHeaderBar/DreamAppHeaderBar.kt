package com.app.dreamapp.ui.components.DreamAppHeaderBar

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.app.dreamapp.R
import com.app.dreamapp.ui.theme.DreamAppTheme

@Composable
fun DreamAppHeaderBar(
    currentUserName: String?,
    onNavigationItemClick: () -> Unit
) {
    TopAppBar(
        backgroundColor = DreamAppTheme.colors.primaryBackground,
        contentColor = DreamAppTheme.colors.primaryText,
        elevation = 5.dp
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Box() {
                IconButton(onClick = onNavigationItemClick) {
                    Icon(
                        imageVector = Icons.Default.Menu,
                        contentDescription = "OpenProfileMenu",
                        tint = DreamAppTheme.colors.primaryText
                    )
                }
            }
            Row(
                modifier = Modifier
                    .padding(10.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                if (currentUserName != null) {
                    Text(
                        text = currentUserName,
                        color = DreamAppTheme.colors.primaryText,
                        fontSize = 20.sp
                    )
                } else {
                    Text(
                        text = "User Null",
                        fontSize = 20.sp,
                        color = DreamAppTheme.colors.primaryText,
                    )
                }
                Spacer(modifier = Modifier.width(16.dp))
                Image(
                    painter = painterResource(R.drawable.profile),
                    contentDescription = "image",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .size(30.dp)
                        .clip(CircleShape)
                )
            }
        }

    }
}