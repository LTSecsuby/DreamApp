package com.app.dreamapp.ui.screens.Timeline


import com.app.dreamapp.ui.screens.ContactChats.ContactChats

data class TimelineItem(
    val id: String,
    val title: String,
    val created: String,
    val description: String,
    val author: ContactChats,
)
