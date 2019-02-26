package com.example.myapplication.customedittext

import android.content.Context
import android.text.InputType
import android.util.AttributeSet
import com.example.myapplication.R
import com.example.myapplication.formfield.ValidationResult
import com.example.myapplication.helper.VALIDATE_EMAIL_ERROR

class EditTextEmailField(context: Context, attrs: AttributeSet) : BaseEditTextFormField(context, attrs) {

    override fun setup() {
        super.setup()
        mEditText?.id = R.id.etEmail
        setInputType()
        setRegex(android.util.Patterns.EMAIL_ADDRESS.toString())
    }

    fun setRegex(regex: String) {
        mRegex = regex
    }

    private fun setInputType() {
        mEditText?.inputType = InputType.TYPE_TEXT_VARIATION_EMAIL_ADDRESS
    }


    override fun getErrorValidateResult(): ValidationResult {
        return ValidationResult(false, VALIDATE_EMAIL_ERROR)
    }
}
