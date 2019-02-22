package com.example.myapplication.textField

import android.content.Context
import android.util.AttributeSet
import com.example.myapplication.R
import com.example.myapplication.formfield.ValidationResult
import com.example.myapplication.helper.masks.PhoneNumberTextWatcherMask

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

         this.editText?.apply {
             addTextChangedListener(PhoneNumberTextWatcherMask(this))
         }

    }


    override fun getErrorMessage(): ValidationResult {
       return ValidationResult(false, "phone")
    }




}