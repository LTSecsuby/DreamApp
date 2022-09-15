package com.app.dreamapp.ui.screens.ContactChats

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class ContactsChatsScreenViewModel: ViewModel() {

    private val _contactList = mutableStateListOf<ContactChats>(
        ContactChats(
            id = "ContactChats:1",
            name = "Контакт1",
            avatar = Icons.Default.Person
        ),
        ContactChats(
            id = "ContactChats:2",
            name = "Контакт2",
            avatar = Icons.Default.Person
        ),
        ContactChats(
            id = "ContactChats:3",
            name = "Контакт3",
            avatar = Icons.Default.Person
        ),
        ContactChats(
            id = "ContactChats:4",
            name = "Контакт4",
            avatar = Icons.Default.Person
        ),
        ContactChats(
            id = "ContactChats:5",
            name = "Контакт5",
            avatar = Icons.Default.Person
        ),
        ContactChats(
            id = "ContactChats:6",
            name = "Контакт6",
            avatar = Icons.Default.Person
        ),
        ContactChats(
            id = "ContactChats:7",
            name = "Контакт7",
            avatar = Icons.Default.Person
        )
    )
    val contactList: List<ContactChats> = _contactList

    fun addNewContacts(newContact: ContactChats) {
        viewModelScope.launch {
            _contactList.add(newContact)
        }
    }

}