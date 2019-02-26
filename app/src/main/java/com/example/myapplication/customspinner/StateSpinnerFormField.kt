package com.example.myapplication.customspinner

import android.content.Context
import android.util.AttributeSet
import com.example.myapplication.R

/**
 * @author Oscar Gallon on 2/26/19.
 */
class StateSpinnerFormField(context: Context, private val mAttrs: AttributeSet) :
    BaseSpinnerFormField(context, mAttrs) {

    override var mIsRequired: Boolean = false

    override fun setup() {
        super.setup()
        mSpinner?.id = R.id.spState
        mSpinner?.adapter = StateSpinnerAdapter(context, android.R.layout.simple_spinner_dropdown_item)
    }

    fun setStates(states: List<String>) {
        (mSpinner?.adapter as? StateSpinnerAdapter)?.addStates(states.sorted())
    }


}
