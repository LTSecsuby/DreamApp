package com.app.dreamapp.ui.pages.Settings

import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.app.dreamapp.ui.theme.DreamAppColorStyle
import com.app.dreamapp.ui.theme.DreamAppTextSize
import com.app.dreamapp.ui.theme.SettingsBundle
import kotlinx.coroutines.launch

enum class SettingsRoute {
    SETTINGS1, SETTINGS2, SETTINGS3
}

data class SettingsScreenViewState (
    val colorStyle: DreamAppColorStyle = DreamAppColorStyle.Purple,
    val textSize: DreamAppTextSize = DreamAppTextSize.Medium,
    val paddingSize: DreamAppTextSize = DreamAppTextSize.Medium,
    val isDarkMode: Boolean = false,
)


class SettingsScreenViewModel: ViewModel() {

    val settingsScreenViewState: MutableLiveData<SettingsScreenViewState> = MutableLiveData(
        SettingsScreenViewState()
    )

    fun setCurrentSettingsIsDarkMode(newSettingsIsDarkMode: Boolean) {
        viewModelScope.launch {
            settingsScreenViewState.postValue(
                settingsScreenViewState.value?.copy(
                    isDarkMode = newSettingsIsDarkMode
                )
            )
        }
    }

    fun setCurrentSettingsColorStyle(newSettingsColorStyle: DreamAppColorStyle) {
        viewModelScope.launch {
            settingsScreenViewState.postValue(
                settingsScreenViewState.value?.copy(
                    colorStyle = newSettingsColorStyle
                )
            )
        }
    }

    fun setCurrentSettingsTextSize(newSettingsTextSize: DreamAppTextSize) {
        viewModelScope.launch {
            settingsScreenViewState.postValue(
                settingsScreenViewState.value?.copy(
                    textSize = newSettingsTextSize
                )
            )
        }
    }

    fun setCurrentSettingsPaddingSize(newSettingsPaddingSize: DreamAppTextSize) {
        viewModelScope.launch {
            settingsScreenViewState.postValue(
                settingsScreenViewState.value?.copy(
                    paddingSize = newSettingsPaddingSize
                )
            )
        }
    }

}