package com.app.dreamapp.ui.screens.LinkTree

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class LinkTreeScreenViewModel: ViewModel() {

    private val _currentUser: MutableLiveData<String> = MutableLiveData("User")
    val currentUser: LiveData<String> = _currentUser

    fun setCurrentUser(newCurrentUser: String) {
        _currentUser.postValue(newCurrentUser)
    }
}