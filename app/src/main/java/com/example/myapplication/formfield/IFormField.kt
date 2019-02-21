package com.example.myapplication.formfield

interface IFormField {

    fun isValid(): ValidationResult

    fun showError(message: String)

    fun clearError()


}