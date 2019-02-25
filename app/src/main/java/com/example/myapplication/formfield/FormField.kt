package com.example.myapplication.formfield

interface FormField {

    fun isValid(): ValidationResult

    fun showError(message: String)

    fun clearError()


}
