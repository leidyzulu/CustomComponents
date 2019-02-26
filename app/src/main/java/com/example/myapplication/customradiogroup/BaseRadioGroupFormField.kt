package com.example.myapplication.customradiogroup

import android.content.Context
import android.support.design.widget.TextInputLayout
import android.util.AttributeSet
import android.view.View
import android.widget.LinearLayout
import android.widget.RadioButton
import android.widget.RadioGroup
import com.example.myapplication.R
import com.example.myapplication.customedittext.TextFormField
import com.example.myapplication.formfield.ValidationResult
import com.example.myapplication.helper.DEFAULT_STYLE_ATTR
import com.example.myapplication.helper.DEFAULT_STYLE_RES
import com.example.myapplication.helper.EMPTY
import com.example.myapplication.helper.VALIDATE_RADIOGROUP_NO_SELECTION_ERROR

abstract class BaseRadioGroupFormField(context: Context, private val attrs: AttributeSet) :
    TextInputLayout(context, attrs), TextFormField {

    private var mCountOptions = 0
    protected var textOptions: Array<CharSequence>? = null
    private var mRadioGroup: RadioGroup? = null

    init {
        val typedArray = context.obtainStyledAttributes(
            attrs,
            R.styleable.BaseRadioGroupFormField,
            DEFAULT_STYLE_ATTR, DEFAULT_STYLE_RES
        )
        mCountOptions = typedArray.getInt(R.styleable.BaseRadioGroupFormField_count_options, 0)
        textOptions = typedArray.getTextArray(R.styleable.BaseRadioGroupFormField_values)

        typedArray.recycle()
    }

    override fun onAttachedToWindow() {
        super.onAttachedToWindow()
        setup()
    }

    private fun countIfIsChecked(): Int {
        var count = 0
        for (i in 0 until mCountOptions) {
            if (!(mRadioGroup?.getChildAt(i) as RadioButton).isChecked) {
                count++
            }
        }
        return count
    }

    override fun isValid(): ValidationResult {

        when {
            mIsRequired -> {
                if (mCountOptions == countIfIsChecked()) {
                    return ValidationResult(false, VALIDATE_RADIOGROUP_NO_SELECTION_ERROR)
                }
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

    override fun setup() {
        mRadioGroup = RadioGroup(context, attrs)
        for (i in 0 until mCountOptions) {
            mRadioGroup?.addView(RadioButton(context).apply {
                id = i
                text = textOptions?.get(i) ?: i.toString()
            }, mLayoutParams)
        }
        addView(mRadioGroup, mLayoutParams)
    }

    private val mLayoutParams = LinearLayout.LayoutParams(
        LinearLayout.LayoutParams.MATCH_PARENT,
        LinearLayout.LayoutParams.WRAP_CONTENT
    )

}
