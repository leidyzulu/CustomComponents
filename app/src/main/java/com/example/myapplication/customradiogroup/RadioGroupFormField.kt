package com.example.myapplication.customradiogroup

import android.content.Context
import android.util.AttributeSet
import com.example.myapplication.R
import com.example.myapplication.formfield.ValidationResult
import com.example.myapplication.helper.DEFAULT_STYLE_ATTR
import com.example.myapplication.helper.DEFAULT_STYLE_RES

class RadioGroupFormField(context: Context, private val attrs: AttributeSet) : BaseRadioGroupFormField(context, attrs) {


    init {
        context.theme.obtainStyledAttributes(
            attrs,
            R.styleable.EditTextFormField,
            DEFAULT_STYLE_ATTR, DEFAULT_STYLE_RES
        ).apply {
            try {
                countOptions = getInt(R.styleable.RadioGroupFormField_options, 0)
                textOptions = getTextArray(R.styleable.RadioGroupFormField_values)
            } finally {
                recycle()
            }
        }
    }






}