package com.kotlin.awankkaley.footballmatchscedhule.utils

import android.annotation.SuppressLint
import android.view.View
import java.text.SimpleDateFormat
import java.util.*

fun View.visible() {
    visibility = View.VISIBLE
}

fun View.invisible() {
    visibility = View.INVISIBLE
}


@SuppressLint("SimpleDateFormat")

fun dateNewFormat(date: String?, isTime: Boolean): String {
    val format = SimpleDateFormat(if (isTime) "HH:mm:ss" else "yyyy-MM-dd")
    format.timeZone = TimeZone.getTimeZone("GMT")
    val dateTime: Date = format.parse(date)
    val newDateFormat = SimpleDateFormat(if (isTime)"HH:mm" else "EEE, dd MMM yyy", Locale.getDefault())
    return newDateFormat.format(dateTime)
}