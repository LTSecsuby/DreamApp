package com.app.dreamapp.ui.screens.ContactsMessages

import com.app.dreamapp.ui.screens.ContactChats.ContactChats

data class ContactMessages(
    val id: String,
    val author: ContactChats,
    val text: String,
)
