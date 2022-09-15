package com.app.dreamapp.ui.screens.ContactsMessages

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.app.dreamapp.ui.screens.ContactChats.ContactChats
import kotlinx.coroutines.launch

class ContactMessagesScreenViewModel: ViewModel() {

    private val _messageList = mutableStateListOf<ContactMessages>(
        ContactMessages(
            id = "ContactMessages:1",
            author = ContactChats(
                id = "ContactChats:11",
                name = "Контакт1",
                avatar = Icons.Default.Person
            ),
            text = "Привет"
        )
    )
    val messageList: List<ContactMessages> = _messageList

    fun addNewMessage(newMessage: ContactMessages) {
        viewModelScope.launch {
            _messageList.add(newMessage)
        }
    }

}