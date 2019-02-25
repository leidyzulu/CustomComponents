package com.example.myapplication.customedittext

import android.content.Context
import android.support.design.widget.TextInputLayout
import android.util.AttributeSet
import android.widget.EditText
import com.example.myapplication.formfield.ValidationResult
import com.example.myapplication.helper.*
import java.util.regex.Pattern

abstract class EditTextFormField constructor(context: Context, attrs: AttributeSet) :
    TextInputLayout(context, attrs), ITextFormField {


    protected var mRegex: String? = null
    protected var mEditText: EditText? = null

    override fun isValid(): ValidationResult {

        return when {
            mEditText?.text.toString().isEmpty() -> ValidationResult(
                false,
                VALIDATE_EMPTY
            )
            mRegex != null && !Pattern.compile(mRegex).matcher(mEditText?.text.toString()).matches() -> getMessage()
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

    abstract fun getMessage(): ValidationResult

}
