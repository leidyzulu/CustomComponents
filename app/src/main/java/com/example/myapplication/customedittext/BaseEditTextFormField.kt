package com.example.myapplication.customedittext

import android.content.Context
import android.util.AttributeSet
import android.util.TypedValue
import android.view.View
import android.widget.EditText
import android.widget.LinearLayout
import com.example.myapplication.R
import com.example.myapplication.formfield.ValidationResult
import com.example.myapplication.helper.DEFAULT_STYLE_ATTR
import com.example.myapplication.helper.DEFAULT_STYLE_RES
import com.example.myapplication.helper.VALIDATE_EMPTY_ERROR

/**
 * @author Oscar Gallon on 2/26/19.
 */
open class BaseEditTextFormField(context: Context, private val mAttrs: AttributeSet) :
    EditTextFormField(context, mAttrs) {


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
        mEditText = EditText(context, mAttrs)
        val _editText = mEditText?.let { it } ?: return

        _editText.apply {
            hint = context.theme.obtainStyledAttributes(
                mAttrs,
                R.styleable.EditTextFormField,
                DEFAULT_STYLE_ATTR, DEFAULT_STYLE_RES
            )?.getString(R.styleable.BaseEditTextFormField_hint) ?: context.getString(R.string.default_base_hint)
            setTextSize(TypedValue.COMPLEX_UNIT_PX, context.resources.getDimension(R.dimen.default_text_size))
            id = View.generateViewId()
        }

        addView(_editText, mLayoutParams)
    }

    fun setMaxLenght(lenght: Int) {
        mEditText?.maxEms = lenght
    }
}
