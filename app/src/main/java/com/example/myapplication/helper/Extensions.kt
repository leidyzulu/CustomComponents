package com.example.myapplication.helper

import android.text.TextUtils

fun String?.isNumeric(): Boolean {
    this?.let {
        return TextUtils.isDigitsOnly(this)
    }

    return false
}