package com.example.myapplication.customedittext

import android.content.Context
import android.util.AttributeSet
import com.example.myapplication.formfield.ValidationResult
import com.example.myapplication.helper.masks.PhoneNumberTextWatcherMask
import android.text.InputFilter
import android.text.InputType
import android.text.method.DigitsKeyListener
import com.example.myapplication.R
import com.example.myapplication.helper.*


class EditTextPhoneField(context: Context, attrs: AttributeSet) : BaseEditTextFormField(context, attrs) {


    override fun setup() {
        super.setup()
        mEditText?.id = R.id.etPhone
        setInputType()
        setMaxLength()
        setPhoneMask()
        setDigits()
    }
    fun setRegex(regex: String){
        mRegex = regex
    }

    private fun setDigits() {
        this.editText?.keyListener = DigitsKeyListener.getInstance(DIGITS_PHONE)
    }

    private fun setInputType() {
        mEditText?.inputType = InputType.TYPE_CLASS_NUMBER
    }

    private fun setPhoneMask() {
        this.editText?.apply {
            addTextChangedListener(PhoneNumberTextWatcherMask(this))
        }
    }

    private fun setMaxLength() {
        val filterArray = arrayOfNulls<InputFilter>(1)
        filterArray[0] = InputFilter.LengthFilter(MAX_LENGHT)
        mEditText?.filters = filterArray
    }

    override fun getErrorValidateResult(): ValidationResult {
        return ValidationResult(false, VALIDATE_LENGTH_ERROR)
    }
}
