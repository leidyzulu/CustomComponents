package com.example.myapplication.customspinner

import android.content.Context
import android.util.AttributeSet

/**
 * @author Oscar Gallon on 2/26/19.
 */
class StateSpinnerFormField(context: Context, attrs: AttributeSet) :
    BaseSpinnerFormField(context, attrs) {
    override var mIsRequired: Boolean = false

    override fun setup() {

    }


}
