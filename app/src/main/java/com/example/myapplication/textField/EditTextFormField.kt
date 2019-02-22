package com.example.myapplication.textField

import android.content.Context
import android.support.design.widget.TextInputLayout
import android.text.TextWatcher
import android.util.AttributeSet
import android.widget.EditText
import com.example.myapplication.formfield.ValidationResult
import com.example.myapplication.helper.*
import java.util.regex.Pattern


abstract class EditTextFormField constructor(context: Context, attrs: AttributeSet) :
    TextInputLayout(context, attrs), ITextFormField {


    protected var mRegex: String? = null
    private var mEditText: EditText? = null

    override fun onFinishInflate() {
        super.onFinishInflate()

        mEditText = this.editText

    }

    override fun isValid(): ValidationResult {

        return when {
            mEditText?.text.toString().isEmpty() -> ValidationResult(
                false,
                VALIDATE_EMPTY
            )
            !Pattern.compile(mRegex).matcher(mEditText?.text.toString()).matches() -> getErrorMessage()

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

    abstract fun getErrorMessage(): ValidationResult

}