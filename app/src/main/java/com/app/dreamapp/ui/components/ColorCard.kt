package com.app.dreamapp.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.ChevronRight
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.app.dreamapp.ui.theme.DreamAppTheme

@Composable
fun ColorCard(
    color: Color,
    select: Boolean,
    onClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .size(60.dp)
            .clickable {
                onClick.invoke()
            },
        backgroundColor = color,
        elevation = 8.dp,
    ) {
        if (select) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(color)
            ) {
                Icon(
                    modifier = Modifier
                        .padding(start = DreamAppTheme.shapes.padding / 4)
                        .size(60.dp),
                    imageVector = Icons.Default.Check,
                    contentDescription = "Check",
                    tint = DreamAppTheme.colors.secondaryBackground
                )
            }
        }
    }
}

