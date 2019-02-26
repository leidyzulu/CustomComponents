package com.example.myapplication.customradiogroup

import android.content.Context
import android.util.AttributeSet
import com.example.myapplication.R
import com.example.myapplication.helper.DEFAULT_STYLE_ATTR
import com.example.myapplication.helper.DEFAULT_STYLE_RES

class RadioGroupFormField(context: Context, attrs: AttributeSet) : BaseRadioGroupFormField(context, attrs) {


    override var mIsRequired: Boolean = false

    fun setArrayTextOption(options: Array<CharSequence>){
        textOptions = options
    }

}