package com.example.myapplication.textField

import android.content.Context
import android.text.InputType
import android.util.AttributeSet
import com.example.myapplication.R
import com.example.myapplication.formfield.ValidationResult
import com.example.myapplication.helper.VALIDATE_EMAIL

class EditTextEmailField(context: Context, attrs: AttributeSet) : EditTextFormField(context, attrs) {


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
        setInputType()

    }

    private fun setInputType() {
        mEditText?.inputType = InputType.TYPE_TEXT_VARIATION_EMAIL_ADDRESS
    }


    override fun getMessage(): ValidationResult {
        return ValidationResult(false, VALIDATE_EMAIL)
    }
}