package com.app.dreamapp.ui.screens

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Person
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import com.app.dreamapp.ui.components.DreamAppDefaultButton
import com.app.dreamapp.ui.screens.ContactChats.ContactChats
import com.app.dreamapp.ui.screens.ContactsMessages.ContactMessages
import com.app.dreamapp.ui.theme.DreamAppTheme
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterialApi::class, ExperimentalComposeUiApi::class)
@Composable
fun AddMessageBottomSheet(
    modifier: Modifier,
    onErrorClick: () -> Unit,
    onDoneClick: (ContactMessages) -> Unit,
    onCloseClick: () -> Unit
) {
    Surface(
        modifier = modifier,
        color = DreamAppTheme.colors.secondaryBackground
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(1.dp)
                .background(color = DreamAppTheme.colors.secondaryText)
        )
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.5f),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            val message = remember { mutableStateOf("") }
            val keyboardController = LocalSoftwareKeyboardController.current

            Row(
                modifier = Modifier
                    .padding(start = 16.dp, top = 16.dp)
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Text(
                    text = "Добавить сообщение",
                    color = DreamAppTheme.colors.primaryText,
                    style = DreamAppTheme.typography.heading
                )
                IconButton(onClick = {
                    onCloseClick()
                }) {
                    Icon(
                        imageVector = Icons.Default.Close,
                        contentDescription = "Close_Add_Message",
                        tint = DreamAppTheme.colors.primaryText
                    )
                }
            }
            Spacer(modifier = Modifier.height(16.dp))
            OutlinedTextField(
                message.value,
                {message.value = it},
                modifier = Modifier
                    .padding(start = 16.dp, end = 16.dp)
                    .fillMaxWidth()
                    .fillMaxHeight(0.5f),
                textStyle = DreamAppTheme.typography.body,
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    textColor = DreamAppTheme.colors.primaryText,
                    backgroundColor = DreamAppTheme.colors.secondaryBackground,
                    focusedBorderColor= DreamAppTheme.colors.tintColor,
                    unfocusedBorderColor = DreamAppTheme.colors.secondaryText,
                ),
                label = {
                    Text(
                        text = "Сообщение",
                        color = DreamAppTheme.colors.primaryText,
                        style = DreamAppTheme.typography.body
                    )
                },
                maxLines = 4,
                placeholder = {
                    Text(
                        text = "Введите сообщение",
                        color = DreamAppTheme.colors.primaryText,
                        style = DreamAppTheme.typography.body
                    )
                },
                keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done),
                keyboardActions = KeyboardActions(
                    onDone = {
                        keyboardController?.hide()
                    }
                ),
            )
            Spacer(modifier = Modifier.height(16.dp))
            DreamAppDefaultButton(
                colorText = DreamAppTheme.colors.primaryText,
                backgroundColor = DreamAppTheme.colors.primaryBackground,
                onClick = {
                    if (message.value.isNotEmpty()) {
                        onDoneClick(
                            ContactMessages(
                                id = "ContactMessages:2",
                                author = ContactChats(
                                    id = "ContactChats:11",
                                    name = "Контакт11",
                                    avatar = Icons.Default.Person
                                ),
                                text = message.value
                            )
                        )
                    } else {
                        onErrorClick()
                    }
                },
                styleText = DreamAppTheme.typography.caption,
                text = "Добавить",
                border = BorderStroke(1.dp, DreamAppTheme.colors.secondaryText),
                enabled = message.value.isNotEmpty()
            )
        }
    }
}