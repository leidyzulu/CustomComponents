package com.example.myapplication.customedittext

import android.content.Context
import android.support.design.widget.TextInputLayout
import android.util.AttributeSet
import android.widget.EditText
import com.example.myapplication.R
import com.example.myapplication.formfield.ValidationResult
import com.example.myapplication.helper.*
import java.util.regex.Pattern

abstract class EditTextFormField constructor(context: Context, attrs: AttributeSet) :
    TextInputLayout(context, attrs), TextFormField {

    protected var mRegex: String? = null
    protected var mEditText: EditText? = null

    init {
        context.theme.obtainStyledAttributes(
            attrs,
            R.styleable.EditTextFormField,
            DEFAULT_STYLE_ATTR, DEFAULT_STYLE_RES
        ).apply {
            try {
                mRegex = getString(R.styleable.EditTextFormField_regex)
            } finally {
                recycle()
            }
        }
    }

    override fun isValid(): ValidationResult {
        return when {
            mEditText?.text.toString().isEmpty() -> ValidationResult(
                false,
                VALIDATE_EMPTY_ERROR
            )
            mRegex != null && !Pattern.compile(mRegex).matcher(mEditText?.text.toString()).matches() -> getErrorValidateResult()
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

    abstract fun getErrorValidateResult(): ValidationResult

    abstract fun setup()

}
