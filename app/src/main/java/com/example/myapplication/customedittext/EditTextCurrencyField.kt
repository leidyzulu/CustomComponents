package com.example.myapplication.customedittext

import android.content.Context
import android.graphics.Canvas
import android.support.design.widget.TextInputLayout
import android.util.AttributeSet
import com.example.myapplication.R
import com.example.myapplication.formfield.ValidationResult
import com.example.myapplication.helper.VALIDATE_CURRENCY_ERROR
import com.example.myapplication.helper.masks.PriceTextWatcherMask

class EditTextCurrencyField(context: Context, attrs: AttributeSet) : BaseEditTextFormField(context, attrs) {

    override fun getErrorValidateResult(): ValidationResult {
        return ValidationResult(false, VALIDATE_CURRENCY_ERROR)
    }

    override fun setup() {
        super.setup()

        val _editText = mEditText?.let { it } ?: return
        _editText.id = R.id.etCurrency
        _editText.addTextChangedListener(PriceTextWatcherMask(_editText))
    }
}
