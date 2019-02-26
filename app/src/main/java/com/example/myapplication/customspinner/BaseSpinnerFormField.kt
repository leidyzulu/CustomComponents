package com.example.myapplication.customspinner

import android.content.Context
import android.support.design.widget.TextInputLayout
import android.util.AttributeSet
import android.widget.AdapterView
import android.widget.Spinner
import com.example.myapplication.formfield.FormField
import com.example.myapplication.formfield.ValidationResult
import com.example.myapplication.helper.EMPTY
import com.example.myapplication.helper.VALIDATE_SPINNER_NO_SELECTION_ERROR

/**
 * @author Oscar Gallon on 2/26/19.
 */
abstract class BaseSpinnerFormField(context: Context, attrs: AttributeSet) : TextInputLayout(context, attrs),
    FormField {

    private val mSpinner: Spinner? = null

    abstract fun setup()

    override fun isValid(): ValidationResult {
        mSpinner?.let {
            if (it.selectedItemPosition == AdapterView.INVALID_POSITION) {
                ValidationResult(false, VALIDATE_SPINNER_NO_SELECTION_ERROR)
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
}
