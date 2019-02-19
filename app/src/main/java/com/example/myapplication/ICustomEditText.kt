package com.example.myapplication

interface ICustomEditText {

    fun isValid(): ValidationResult

    fun showError(message: String)

    fun clearErrorText()


}