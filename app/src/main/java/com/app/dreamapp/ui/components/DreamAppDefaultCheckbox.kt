package com.app.dreamapp.ui.components

import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color

@Composable
fun DreamAppDefaultCheckbox(
    modifier: Modifier,
    checkedColor: Color,
    uncheckedColor: Color,
    isChecked: Boolean,
    onCheckedChange: (Boolean) -> Unit,
    enabled: Boolean = true,
) {
    Checkbox(
        modifier = modifier,
        checked = isChecked,
        onCheckedChange = onCheckedChange,
        enabled = enabled,
        colors = CheckboxDefaults.colors(
            checkedColor = checkedColor,
            uncheckedColor = uncheckedColor
        )
    )

}