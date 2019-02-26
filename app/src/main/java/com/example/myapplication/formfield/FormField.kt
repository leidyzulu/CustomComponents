package com.example.myapplication.formfield

interface FormField {

    var mIsRequired: Boolean

    fun isValid(): ValidationResult

    fun showError(message: String)

    fun clearError()

    fun setup()
}
