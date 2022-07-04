package com.ahmetbozkan.bgnmobi.util.extensions

val String.Companion.EMPTY
    get() = ""

fun String?.orEmpty() = this ?: String.EMPTY