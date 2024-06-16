package io.github.aptemkov.todoapp.presentation.adapter

import android.annotation.SuppressLint
import java.text.SimpleDateFormat
import java.util.Date

@SuppressLint("SimpleDateFormat")
fun Long.toDate(): String {
    val date = Date(this)
    val format = SimpleDateFormat("dd.MM.yyyy HH:mm")
    return format.format(date)
}

fun Long.currentTimeToLong(): Long {
    return System.currentTimeMillis()
}