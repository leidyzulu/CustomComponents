package com.example.myapplication

import android.content.Context
import android.util.AttributeSet


class PhoneNumberEditText(context: Context, attrs: AttributeSet) : CustomEditText(context, attrs) {


    override fun isValid(): ValidationResult {

        return when {
            this.editText?.text.toString().isEmpty() -> ValidationResult(false, "Empty")
            this.editText?.text.toString().count() != 10 -> ValidationResult(false, "Max lenght")
            !this.editText?.text.toString().isNumeric() -> ValidationResult(false, "Should be number")
            else -> ValidationResult(true, "")
        }
    }

    override fun showError(message: String) {
        this.isErrorEnabled = true
        this.error = message
    }

    override fun clearErrorText() {
       this.isErrorEnabled = false
        this.error = EMPTY
    }

    companion object {

        const val EMPTY = ""
    }


}