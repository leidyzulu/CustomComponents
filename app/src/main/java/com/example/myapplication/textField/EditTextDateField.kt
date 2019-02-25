package com.example.myapplication.textField

import android.content.Context
import android.util.AttributeSet
import com.evercheck.wallet.utils.masks.DateTextWatcherMask
import com.example.myapplication.formfield.ValidationResult
import com.example.myapplication.helper.VALIDATE_DATE
import com.example.myapplication.helper.addDatePicker

class EditTextDateField(context: Context, attrs: AttributeSet) : EditTextFormField(context, attrs) {


    override fun onFinishInflate() {
        super.onFinishInflate()

        mEditText = this.editText
        setMaskDate()
        addDatePicker()

    }

    private fun setMaskDate() {
        this.editText?.apply {
            addTextChangedListener(DateTextWatcherMask(this))
        }
    }

    private fun addDatePicker() {
        this.editText?.addDatePicker()
    }

    fun setRegex(regex: String) {
        mRegex = regex
    }

    override fun getMessage(): ValidationResult {
        return ValidationResult(false, VALIDATE_DATE)
    }
}