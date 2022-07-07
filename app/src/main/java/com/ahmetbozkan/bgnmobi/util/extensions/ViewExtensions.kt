package com.ahmetbozkan.bgnmobi.util.extensions

import android.view.View

fun View.goneView() {
    this.visibility = View.GONE
}

fun View.showView() {
    this.visibility = View.VISIBLE
}

fun View.hideView() {
    this.visibility = View.INVISIBLE
}