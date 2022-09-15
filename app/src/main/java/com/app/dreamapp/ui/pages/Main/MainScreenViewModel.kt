package com.app.dreamapp.ui.pages.Main

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

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