package com.example.myapplication.helper

import android.app.DatePickerDialog
import android.support.v4.content.ContextCompat
import android.text.InputType
import android.text.TextUtils
import android.view.MotionEvent
import android.widget.EditText
import android.widget.TextView
import com.example.myapplication.helper.masks.DateTextWatcherMask
import com.example.myapplication.R
import java.math.RoundingMode
import java.text.NumberFormat
import java.text.SimpleDateFormat
import java.util.*

fun String?.isNumeric(): Boolean {
    this?.let {
        return TextUtils.isDigitsOnly(this)
    }

    return false
}

fun String.toDollarAmount(): String {

    return try {
        val formatter = NumberFormat.getCurrencyInstance(Locale.US)
        formatter.roundingMode = RoundingMode.UNNECESSARY
        formatter.minimumFractionDigits = ZERO
        formatter.format(this.toFloat())
    } catch (exception: Exception) {
        this
    }
}
