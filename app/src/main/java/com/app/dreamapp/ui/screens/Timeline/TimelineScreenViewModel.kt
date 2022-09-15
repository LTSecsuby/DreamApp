package com.app.dreamapp.ui.screens.Timeline

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.app.dreamapp.ui.screens.ContactChats.ContactChats
import kotlinx.coroutines.launch

class TimelineScreenViewModel: ViewModel() {

    private val _timelineItems = mutableStateListOf<TimelineItem>(
        TimelineItem(
            id = "TimelineItem:1",
            title = "Новость1",
            created = "сейчас",
            description = "Это самая первая новость",
            author = ContactChats(
                id = "ContactChats:8",
                name = "Женя",
                avatar = Icons.Default.Person,
            )
        ),
        TimelineItem(
            id = "TimelineItem:2",
            title = "Новость2",
            created = "сегодня",
            description = "Это вторая новость",
            author = ContactChats(
                id = "ContactChats:9",
                name = "Не Женя",
                avatar = Icons.Default.Person,
            )
        ),
        TimelineItem(
            id = "TimelineItem:3",
            title = "Новость3",
            created = "вчера",
            description = "Это третья новость",
            author = ContactChats(
                id = "ContactChats:10",
                name = "Точно не Женя",
                avatar = Icons.Default.Person,
            )
        ),
    )

    val newsList: List<TimelineItem> = _timelineItems

    fun addNewNews(newNews: TimelineItem) {
        viewModelScope.launch {
            _timelineItems.add(newNews)
        }
    }

}