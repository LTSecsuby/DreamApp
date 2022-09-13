package com.app.dreamapp.ui.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.height
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import com.app.dreamapp.ui.theme.DreamAppTheme

@Composable
fun DreamAppDefaultButton(
    colorText: Color,
    backgroundColor: Color,
    border: BorderStroke,
    onClick: () -> Unit,
    styleText: TextStyle,
    text: String? = null,
    enabled: Boolean = true,
    content: @Composable RowScope.() -> Unit = {}
) {
    Button(
        onClick = onClick,
        modifier = Modifier
            .height(48.dp),
        enabled = enabled,
        border = border,
        colors = ButtonDefaults.buttonColors(
            backgroundColor = backgroundColor,
            disabledBackgroundColor = backgroundColor.copy(
                alpha = 0.3f
            )
        )
    ) {
        text?.let {
            Text(
                text = it,
                style = styleText,
                color = colorText
            )
        } ?: content.invoke(this)
    }
}