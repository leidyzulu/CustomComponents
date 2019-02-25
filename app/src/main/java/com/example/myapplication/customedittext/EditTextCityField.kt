package com.example.myapplication.customedittext

import android.content.Context
import android.util.AttributeSet
import com.example.myapplication.formfield.ValidationResult
import com.example.myapplication.helper.EMPTY
import com.example.myapplication.helper.VALIDATE_CITY_ERROR

/**
 * @author Oscar Gallon on 2/25/19.
 */
class EditTextCityField(context: Context, attrs: AttributeSet) : EditTextFormField(context, attrs) {

    private val mStateName: String? = null
    private val mCities: List<String>? = null

    override fun getMessage(): ValidationResult {
        return ValidationResult(false, "$VALIDATE_CITY_ERROR ${mStateName ?: EMPTY}")
    }

    override fun isValid(): ValidationResult {
        if(mCities?.contains(editText?.text ?: EMPTY) == false) {
          return ValidationResult(false, VALIDATE_CITY_ERROR)
        }

        return super.isValid()
    }
}
