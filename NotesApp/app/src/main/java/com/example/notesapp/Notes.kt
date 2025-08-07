package com.example.notesapp

import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

data class Notes(
    val id: Int? = null,
    val title: String? = null,
    val content: String? = null,
    val createdDate: String? = getCurrentDate()
)

fun getCurrentDate(): String {
    val formatter = SimpleDateFormat("'created today' hh:mm a", Locale.getDefault())
    return formatter.format(Date())
}
