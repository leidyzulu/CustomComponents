package com.example.myapplication.customedittext

import android.content.Context
import android.text.InputFilter
import android.text.InputType
import android.util.AttributeSet
import android.util.TypedValue
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

    override var mIsRequired: Boolean = false

    private var mInputType: Int = InputType.TYPE_CLASS_TEXT

    private val mHint: String

    private val mLayoutParams = LinearLayout.LayoutParams(
        LinearLayout.LayoutParams.MATCH_PARENT,
        LinearLayout.LayoutParams.WRAP_CONTENT
    )

    init {
        val typedArray = context.obtainStyledAttributes(
            mAttrs,
            R.styleable.BaseEditTextFormField,
            DEFAULT_STYLE_ATTR, DEFAULT_STYLE_RES
        )

        mHint = typedArray.getString(R.styleable.BaseEditTextFormField_hint)
            ?: context.getString(R.string.default_base_hint)

        mInputType = when (typedArray.getString(R.styleable.BaseEditTextFormField_input_type)) {
            "number" -> InputType.TYPE_CLASS_NUMBER
            "phone" -> InputType.TYPE_CLASS_PHONE
            else -> InputType.TYPE_CLASS_TEXT
        }

        typedArray.recycle()
    }


    override fun onAttachedToWindow() {
        super.onAttachedToWindow()
        setup()
    }

    override fun getErrorValidateResult(): ValidationResult {
        return ValidationResult(false, String.format(VALIDATE_EMPTY_ERROR, mHint))
    }

    override fun setup() {
        mEditText = EditText(context, mAttrs)
        mEditText?.inputType = mInputType

        val _editText = mEditText?.let { it } ?: return

        _editText.apply {
            id = R.id.etBase
            hint = mHint
            setTextSize(TypedValue.COMPLEX_UNIT_PX, context.resources.getDimension(R.dimen.default_text_size))
        }

        addView(_editText, mLayoutParams)
    }

    fun setMaxLength(length: Int) {
        val filter = InputFilter.LengthFilter(length)
        mEditText?.filters = arrayOf(filter)
    }

    fun setRegex(regex: String) {
        mRegex = regex
    }

    fun setIsRequired(required: Boolean) {
        mIsRequired = required
    }
}
