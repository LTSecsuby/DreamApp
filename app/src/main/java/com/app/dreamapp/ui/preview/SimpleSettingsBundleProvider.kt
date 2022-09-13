package com.app.dreamapp.ui.preview

import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import com.app.dreamapp.ui.theme.DreamAppColorStyle
import com.app.dreamapp.ui.theme.DreamAppTextSize
import com.app.dreamapp.ui.theme.SettingsBundle

class SimpleSettingsBundleProvider: PreviewParameterProvider<SettingsBundle> {
    private val settings = SettingsBundle(
        isDarkMode = true,
        colorStyle = DreamAppColorStyle.Purple,
        textSize = DreamAppTextSize.Medium,
        paddingSize = DreamAppTextSize.Medium
    )
    override val values: Sequence<SettingsBundle> = sequenceOf(settings)
}