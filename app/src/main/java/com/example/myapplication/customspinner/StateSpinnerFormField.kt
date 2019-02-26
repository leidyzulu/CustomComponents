package com.example.myapplication.customspinner

import com.example.myapplication.formfield.FormField
import com.example.myapplication.formfield.ValidationResult

/**
 * @author Oscar Gallon on 2/26/19.
 */
class StateSpinnerFormField: FormField{
    override var mIsRequired: Boolean = false

    override fun isValid(): ValidationResult {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun showError(message: String) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun clearError() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }


}
