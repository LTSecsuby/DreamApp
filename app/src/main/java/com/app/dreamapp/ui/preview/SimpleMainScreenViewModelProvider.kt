package com.app.dreamapp.ui.preview

import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import com.app.dreamapp.ui.pages.Main.MainScreenViewModel

class SimpleMainScreenViewModelProvider: PreviewParameterProvider<MainScreenViewModel> {
    private val mainScreenViewModel = MainScreenViewModel()
    override val values: Sequence<MainScreenViewModel> = sequenceOf(mainScreenViewModel)
}