package com.example.myapplication.customradiogroup

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.LinearLayout
import android.widget.RadioButton
import android.widget.RadioGroup
import com.example.myapplication.customedittext.EditTextFormField
import com.example.myapplication.customedittext.TextFormField
import com.example.myapplication.formfield.ValidationResult
import com.example.myapplication.helper.VALIDATE_EMPTY_ERROR

open class BaseRadioGroupFormField(context: Context, private val attrs: AttributeSet) :
    EditTextFormField(context, attrs), TextFormField {

    protected var countOptions = 0
    protected var textOptions: Array<CharSequence>? = null

    private val mLayoutParams = LinearLayout.LayoutParams(
        LinearLayout.LayoutParams.MATCH_PARENT,
        LinearLayout.LayoutParams.WRAP_CONTENT
    )

    override fun onAttachedToWindow() {
        super.onAttachedToWindow()
        setup()
    }

    override fun getErrorValidateResult(): ValidationResult {
        return ValidationResult(false, VALIDATE_EMPTY_ERROR)
    }

    override fun setup() {
        val radioGroup = RadioGroup(context, attrs)
        val radioButton = RadioButton(context)
        for (i in 0 until countOptions) {
            radioButton.text = textOptions?.get(i)
        }
        radioGroup.addView(radioButton)
        radioGroup.id = View.generateViewId()
        addView(radioGroup, mLayoutParams)
    }


}