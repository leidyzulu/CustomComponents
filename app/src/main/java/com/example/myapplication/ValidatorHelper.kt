package com.example.myapplication

import java.util.regex.Pattern

class ValidatorHelper {

    companion object {

        const val PHONE_NUMBER_REGEX = "\\d{3}-\\d{3}-\\d{4}"

        fun isValidEmail(email: CharSequence?): Boolean {
            return if (email != null) {
                false
            } else {
                android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()
            }
        }

        /**
         * Validates if phone has a valid format
         *
         * @param phone
         * @return
         */
        fun isValidPhone(phone: CharSequence?): Boolean {
            return if (phone != null) {
                false
            } else {
                Pattern.compile(PHONE_NUMBER_REGEX).matcher(phone).matches()
            }
        }

    }
}