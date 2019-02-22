package com.example.myapplication.textField

import android.content.Context
import android.util.AttributeSet
import com.example.myapplication.R
import com.example.myapplication.formfield.ValidationResult
import com.example.myapplication.helper.VALIDATE_LENGTH
import com.example.myapplication.helper.masks.PhoneNumberTextWatcherMask
import android.text.InputFilter
import com.example.myapplication.helper.MAX_LENGHT


class EditTextPhoneField(context: Context, attrs: AttributeSet) : EditTextFormField(context, attrs) {

    init {
        context.theme.obtainStyledAttributes(
            attrs,
            R.styleable.EditTextFormField,
            0, 0
        ).apply {
            try {
                mRegex = getString(R.styleable.EditTextFormField_regex)
            } finally {
                recycle()
            }
        }

    }

    override fun onFinishInflate() {
        super.onFinishInflate()

        mEditText = this.editText
        val filterArray = arrayOfNulls<InputFilter>(1)
        filterArray[0] = InputFilter.LengthFilter(MAX_LENGHT)
        mEditText?.filters = filterArray

         this.editText?.apply {
             addTextChangedListener(PhoneNumberTextWatcherMask(this))
         }

    }


    override fun getMessage(): ValidationResult {
       return ValidationResult(false, VALIDATE_LENGTH)
    }




}