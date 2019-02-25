package com.example.myapplication.customedittext

import android.content.Context
import android.util.AttributeSet
import com.example.myapplication.formfield.ValidationResult
import com.example.myapplication.helper.VALIDATE_CURRENCY
import com.example.myapplication.helper.masks.PriceTextWatcherMask

class EditTextCurrencyField(context: Context, attrs: AttributeSet) : EditTextFormField(context, attrs) {


    override fun onFinishInflate() {
        super.onFinishInflate()

        mEditText = this.editText
        setMaskCurrency()
    }

    private fun setMaskCurrency() {
        this.editText?.apply {
            addTextChangedListener(PriceTextWatcherMask(this))
        }
    }

    override fun getMessage(): ValidationResult {
        return ValidationResult(false, VALIDATE_CURRENCY)
    }
}