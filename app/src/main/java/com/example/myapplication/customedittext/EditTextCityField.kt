package com.example.myapplication.customedittext

import android.content.Context
import android.util.AttributeSet
import com.example.myapplication.formfield.ValidationResult
import com.example.myapplication.helper.EMPTY
import com.example.myapplication.helper.VALIDATE_CITY_ERROR
import com.example.myapplication.helper.ZERO

/**
 * @author Oscar Gallon on 2/25/19.
 */
class EditTextCityField(context: Context, attrs: AttributeSet) : EditTextFormField(context, attrs) {

    private var mStateName: String? = null
    private var mCities: List<String>? = null

    override fun getMessage(): ValidationResult {
        return ValidationResult(false, "$VALIDATE_CITY_ERROR ${mStateName ?: EMPTY}")
    }

    override fun isValid(): ValidationResult {

        val emptyValidation = super.isValid()

        if(!emptyValidation.isValid){
            return emptyValidation
        }

        if(mCities?.filter {
            it.toLowerCase() == editText?.text?.toString()?.toLowerCase() ?: EMPTY
        }?.count() ?: ZERO <= ZERO){
            return ValidationResult(false, VALIDATE_CITY_ERROR)
        }

        return emptyValidation
    }

    fun setCities(cities: List<String>){
        mCities = cities
    }

    fun setStateName(state: String){
        mStateName = state
    }
}
