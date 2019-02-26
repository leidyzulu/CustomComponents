package com.example.myapplication.customedittext

import android.app.DatePickerDialog
import android.content.Context
import android.support.v4.content.ContextCompat
import android.text.InputType
import android.text.TextUtils
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import android.widget.DatePicker
import android.widget.TextView
import com.example.myapplication.R
import com.example.myapplication.helper.masks.DateTextWatcherMask
import com.example.myapplication.formfield.ValidationResult
import com.example.myapplication.helper.*
import java.lang.Exception
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

class EditTextDateField(context: Context, attrs: AttributeSet) : BaseEditTextFormField(context, attrs),
    DatePickerDialog.OnDateSetListener {

    private var mIconDrawableId = R.drawable.ic_date
    private var mDateTextWatcherMask: DateTextWatcherMask? = null
    private var mLowerLimit: Long? = null
    private var mUpperLimit: Long? = null

    init {
        context.theme.obtainStyledAttributes(
            attrs,
            R.styleable.EditTextFormField,
            DEFAULT_STYLE_ATTR, DEFAULT_STYLE_RES
        ).apply {
            try {
                mIconDrawableId = getInt(R.styleable.EditTextDateField_picker_icon, R.drawable.ic_date)
            } finally {
                recycle()
            }
        }

        if (mRegex == null) {
            mRegex = context.getString(R.string.show_date_format)
        }
    }

    override fun setup() {
        super.setup()
        mEditText?.id = R.id.etDate
        setupPicker()
    }

    fun setRegex(regex: String) {
        mRegex = regex
    }

    override fun getErrorValidateResult(): ValidationResult {
        return ValidationResult(false, VALIDATE_DATE_ERROR)
    }

    override fun onDateSet(view: DatePicker?, year: Int, monthOfYear: Int, dayOfMonth: Int) {

        val receiver = mEditText?.let { it } ?: return
        val dateTextWatcherMask = mDateTextWatcherMask?.let { it } ?: return

        val calendar = Calendar.getInstance()
        calendar.set(Calendar.YEAR, year)
        calendar.set(Calendar.MONTH, monthOfYear)
        calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth)
        receiver.removeTextChangedListener(dateTextWatcherMask)
        receiver.setText(
            SimpleDateFormat(
                context.getString(R.string.show_date_format),
                Locale.US
            ).format(calendar.time)
        )

        receiver.addTextChangedListener(dateTextWatcherMask)
    }

    fun setLowerLimit(limit: Long) {
        mLowerLimit = limit
    }

    fun setLowerLimit(limit: String, format: String = context.getString(R.string.show_date_format)) {
        try {
            mLowerLimit = parseDate(format, limit)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    fun setUpperLimit(limit: Long) {
        mUpperLimit = limit
    }

    fun setUpperLimit(limit: String, format: String = context.getString(R.string.show_date_format)) {
        try {
            mUpperLimit = parseDate(format, limit)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    fun getLowerLimit(): Long? = mLowerLimit

    fun getUpperLimit(): Long? = mUpperLimit

    @Throws(ParseException::class)
    private fun parseDate(format: String, candidate: String): Long {
        return SimpleDateFormat(format, Locale.getDefault()).parse(candidate).time
    }

    private fun setupPicker() {

        val receiver = mEditText?.let { it } ?: return

        mDateTextWatcherMask = DateTextWatcherMask(receiver).apply {
            receiver.addTextChangedListener(this)
        }

        receiver.setCompoundDrawablesWithIntrinsicBounds(
            null,
            null,
            ContextCompat.getDrawable(context, mIconDrawableId),
            null
        )

        receiver.inputType = InputType.TYPE_CLASS_NUMBER
        receiver.maxEms = DATE_MASK_MAX_EMS

        val showDateFormat = context.getString(R.string.show_date_format)

        this.editText?.setOnTouchListener { v, event ->
            when (event.action) {
                MotionEvent.ACTION_UP -> {
                    if (wasCalendarDrawableClicked(v, event)) {
                        val calendar = Calendar.getInstance()

                        val dialogCanBeOpenOnEditTextText = if (receiver.text?.isEmpty() == false) {
                            val dateTyped = receiver.text?.replace(SLASH.toRegex(), EMPTY)
                            TextUtils.isDigitsOnly(dateTyped)
                        } else {
                            false
                        }

                        if (dialogCanBeOpenOnEditTextText) {
                            calendar.time =
                                SimpleDateFormat(showDateFormat, Locale.US).parse(receiver.text.toString())
                        }

                        val datePicker = DatePickerDialog(
                            context, this, calendar
                                .get(Calendar.YEAR), calendar.get(Calendar.MONTH),
                            calendar.get(Calendar.DAY_OF_MONTH)
                        )

                        mLowerLimit?.let {
                            datePicker.datePicker.minDate = it
                        }

                        mUpperLimit?.let {
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

    private fun wasCalendarDrawableClicked(touchedView: View, event: MotionEvent): Boolean {
        val pos = IntArray(COMPOUND_DRAWABLE_POSITION_ARRAY_SIZE)

        touchedView.getLocationOnScreen(pos)

        val editTextRightPosition = pos[DATE_EDIT_TEXT_RIGHT_COMPOUND_DRAWABLE_POSITION] +
                touchedView.width

        val rightDrawable =
            (touchedView as? TextView)?.compoundDrawables?.get(DRAWABLE_RIGHT_POSITION)?.let { it } ?: return false


        val drawableWidth = rightDrawable.bounds.width()

        val eventRawX = event.rawX

        return eventRawX >= editTextRightPosition - drawableWidth - COMPOUND_DRAWABLE_TOUCH_OFF_SET
    }
}
