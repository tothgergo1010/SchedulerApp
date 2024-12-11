package com.example.schedulerapp.model

data class ScheduleItem(
    val title: String,
    val date: String,
    val time: String,
    var done: Boolean = false
)

