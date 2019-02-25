package com.example.myapplication.helper

import android.app.DatePickerDialog
import android.support.v4.content.ContextCompat
import android.support.v7.widget.AppCompatEditText
import android.text.InputType
import android.text.TextUtils
import android.view.MotionEvent
import android.widget.EditText
import android.widget.TextView
import com.evercheck.wallet.utils.masks.DateTextWatcherMask
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


fun EditText.addDatePicker(
    lowerLimit: Long? = null,
    upperLimit: Long? = null
) {
    val dateTextWatcherMask = DateTextWatcherMask(this)
    addTextChangedListener(dateTextWatcherMask)

    setCompoundDrawablesWithIntrinsicBounds(
        null,
        null,
        ContextCompat.getDrawable(context, R.drawable.ic_date_range_black_24dp),
        null
    )

    inputType = InputType.TYPE_CLASS_NUMBER
    maxEms = DATE_MASK_MAX_EMS

    val showDateFormat = context.getString(R.string.show_date_format)

    val datePickerListener =
        DatePickerDialog.OnDateSetListener { _, year, monthOfYear, dayOfMonth ->
            val calendar = Calendar.getInstance()
            calendar.set(Calendar.YEAR, year)
            calendar.set(Calendar.MONTH, monthOfYear)
            calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth)
            removeTextChangedListener(dateTextWatcherMask)
            setText(SimpleDateFormat(showDateFormat, Locale.US).format(calendar.time))
            addTextChangedListener(dateTextWatcherMask)
        }

    setOnTouchListener { v, event ->
        when (event.action) {
            MotionEvent.ACTION_UP -> {
                val pos = IntArray(COMPOUND_DRAWABLE_POSITION_ARRAY_SIZE)
                v.getLocationOnScreen(pos)

                val editTextRightPosition = pos[DATE_EDIT_TEXT_RIGHT_COMPOUND_DRAWABLE_POSITION] +
                        v.width
                val rightDrawable = (v as TextView).compoundDrawables[DRAWABLE_RIGHT_POSITION]
                val drawableWidth = rightDrawable.bounds.width()

                val eventRawX = event.rawX
                if (eventRawX >= editTextRightPosition - drawableWidth -
                    COMPOUND_DRAWABLE_TOUCH_OFF_SET) {
                    val calendar = Calendar.getInstance()

                    val dialogCanBeOpenOnEditTextText = if (text?.isEmpty() == false) {
                        val dateTyped = text?.replace(SLASH.toRegex(), EMPTY)
                        TextUtils.isDigitsOnly(dateTyped)
                    } else {
                        false
                    }

                    if (dialogCanBeOpenOnEditTextText) {
                        calendar.time =
                                SimpleDateFormat(showDateFormat, Locale.US).parse(text.toString())
                    }
                    val datePicker = DatePickerDialog(
                        context, datePickerListener, calendar
                            .get(Calendar.YEAR), calendar.get(Calendar.MONTH),
                        calendar.get(Calendar.DAY_OF_MONTH)
                    )

                    lowerLimit?.let {
                        datePicker.datePicker.minDate = it
                    }

                    upperLimit?.let {
                        datePicker.datePicker.maxDate = it
                    }

                    datePicker.show()

                    true
                } else {
                    false
                }
            }
            else -> false
        }
    }
}