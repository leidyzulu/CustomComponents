package com.example.myapplication

import android.content.Context
import android.support.design.widget.TextInputLayout
import android.util.AttributeSet

abstract class CustomEditText @JvmOverloads constructor(context: Context, attrs: AttributeSet) :
    TextInputLayout(context, attrs), ICustomEditText {


}