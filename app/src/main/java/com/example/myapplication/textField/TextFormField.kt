package com.example.myapplication.textField

import android.content.Context
import android.support.design.widget.TextInputLayout
import android.util.AttributeSet
import android.view.View
import android.widget.EditText
import com.example.myapplication.*
import com.example.myapplication.formfield.ValidationResult
import com.example.myapplication.helper.*
import com.example.myapplication.helper.masks.PhoneNumberTextWatcherMask


class TextFormField constructor(context: Context, attrs: AttributeSet) :
    TextInputLayout(context, attrs), ITextFormField {


    var mRegex: Int? = null
    var mEditText: EditText? = null

    init {
        context.theme.obtainStyledAttributes(
            attrs,
            R.styleable.TextFormField,
            0, 0
        ).apply {
            try {
                mRegex = getInt(R.styleable.TextFormField_regex, -1)
            } finally {
                recycle()
            }
        }

    }

    override fun onFinishInflate() {
        super.onFinishInflate()

        mEditText = this.editText


        if (mRegex == OPTION_PHONE) {
            mEditText?.apply {
                addTextChangedListener(PhoneNumberTextWatcherMask(this))
            }
        }

    }

    override fun isValid(): ValidationResult {
        mRegex
        this.editText
        return when {
            mEditText?.text.toString().isEmpty() -> ValidationResult(
                false,
                VALIDATE_EMPTY
            )
            mEditText?.text.toString().count() != MAX_LENGHT -> ValidationResult(
                false,
                VALIDATE_LENGTH
            )
            !mEditText?.text.toString().isNumeric() -> ValidationResult(
                false,
                VALIDATE_NUMERIC
            )
            else -> ValidationResult(true, EMPTY)
        }
    }

    override fun showError(message: String) {
        this.isErrorEnabled = true
        this.error = message
    }

    override fun clearError() {
        this.isErrorEnabled = false
        this.error = EMPTY
    }

}