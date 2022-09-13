package com.app.dreamapp.ui.pages.Main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.app.dreamapp.ui.pages.Settings.SettingsScreenViewState
import com.app.dreamapp.ui.theme.DreamAppColorStyle
import com.app.dreamapp.ui.theme.DreamAppTextSize
import kotlinx.coroutines.launch

enum class MainRoute {
    TIMELINE, CONTACT_CHATS, LINK_TREE
}

data class CurrentUserViewState(
    val name: String = "User",
)

class MainScreenViewModel: ViewModel() {

    val currentUserViewState: MutableLiveData<CurrentUserViewState> = MutableLiveData(
        CurrentUserViewState()
    )

    fun setCurrentUserName(newCurrentUser: String) {
        viewModelScope.launch {
            currentUserViewState.postValue(
                currentUserViewState.value?.copy(
                    name = newCurrentUser
                )
            )
        }
    }

}