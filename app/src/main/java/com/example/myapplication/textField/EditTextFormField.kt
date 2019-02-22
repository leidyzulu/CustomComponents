package com.example.myapplication.textField

import android.content.Context
import android.support.design.widget.TextInputLayout
import android.util.AttributeSet
import android.widget.EditText
import com.evercheck.wallet.utils.masks.DateTextWatcherMask
import com.example.myapplication.*
import com.example.myapplication.formfield.ValidationResult
import com.example.myapplication.helper.*
import com.example.myapplication.helper.masks.PhoneNumberTextWatcherMask


class EditTextFormField constructor(context: Context, attrs: AttributeSet) :
    TextInputLayout(context, attrs), ITextFormField {


    var mTypeField: Int? = null
    var mEditText: EditText? = null

    init {
        context.theme.obtainStyledAttributes(
            attrs,
            R.styleable.EditTextFormField,
            0, 0
        ).apply {
            try {
                mTypeField = getInt(R.styleable.EditTextFormField_regex, -1)
            } finally {
                recycle()
            }
        }

    }

    override fun onFinishInflate() {
        super.onFinishInflate()

        mEditText = this.editText


        when (mTypeField) {
            OPTION_PHONE -> mEditText?.apply {
                addTextChangedListener(PhoneNumberTextWatcherMask(this))
            }
            OPTION_DATE -> mEditText?.apply {
                addTextChangedListener(DateTextWatcherMask(this))
            }
        }

    }

    override fun isValid(): ValidationResult {

        return when {
            mEditText?.text.toString().isEmpty() -> ValidationResult(
                false,
                VALIDATE_EMPTY
            )
            !ValidatorHelper.isValidPhone(mEditText?.text) -> ValidationResult(
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