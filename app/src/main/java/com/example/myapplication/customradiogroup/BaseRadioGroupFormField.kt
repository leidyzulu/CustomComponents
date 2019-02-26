package com.example.myapplication.customradiogroup

import android.content.Context
import android.support.design.widget.TextInputLayout
import android.util.AttributeSet
import android.view.View
import android.widget.LinearLayout
import android.widget.RadioButton
import android.widget.RadioGroup
import com.example.myapplication.customedittext.TextFormField
import com.example.myapplication.formfield.ValidationResult
import com.example.myapplication.helper.EMPTY
import com.example.myapplication.helper.VALIDATE_RADIOGROUP_NO_SELECTION_ERROR

abstract class BaseRadioGroupFormField(context: Context, private val attrs: AttributeSet) :
    TextInputLayout(context, attrs), TextFormField {

    protected var countOptions = 0
    protected var textOptions: Array<CharSequence>? = null
    private var mRadioGroup: RadioGroup? = null
    private var mrRadioButton: RadioButton? = null

    override fun onAttachedToWindow() {
        super.onAttachedToWindow()
        setup()
    }


    override fun isValid(): ValidationResult {
        var count = 0
        if (mIsRequired) {
            for (i in 0 until countOptions) {
                if (!(mRadioGroup?.getChildAt(i) as RadioButton).isChecked) {
                        count ++
                }

            }
            if (countOptions == count){
                return ValidationResult(false, VALIDATE_RADIOGROUP_NO_SELECTION_ERROR)
            }
        }
        return ValidationResult(true, EMPTY)
    }

    override fun showError(message: String) {
        this.isErrorEnabled = true
        this.error = message
    }

    override fun clearError() {
        this.isErrorEnabled = false
        this.error = EMPTY
    }

    fun setup() {
        mRadioGroup = RadioGroup(context, attrs)
        mrRadioButton = RadioButton(context)
        for (i in 0 until countOptions) {
            mrRadioButton?.text = textOptions?.get(i)
        }
        mRadioGroup?.addView(mrRadioButton)
        mRadioGroup?.id = View.generateViewId()
        addView(mRadioGroup, mLayoutParams)
    }

    private val mLayoutParams = LinearLayout.LayoutParams(
        LinearLayout.LayoutParams.MATCH_PARENT,
        LinearLayout.LayoutParams.WRAP_CONTENT
    )

}