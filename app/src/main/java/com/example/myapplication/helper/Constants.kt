package com.example.myapplication.helper

const val VALIDATE_EMPTY = "This field must not be empty"
const val VALIDATE_NUMERIC = "This field must be numeric"
const val VALIDATE_LENGTH = "This field must have ten digits."
const val VALIDATE_EMAIL = "Email incorrect."
const val VALIDATE_DATE = "Date incorrect."
const val VALIDATE_CURRENCY = "Currency incorrect."
const val EMPTY = ""
const val MAX_LENGHT = 12
const val DIGITS_PHONE = "0123456789-"

/**PHONE NUMBER FORMATS**/
const val PHONE_NUMBER_FORMAT_FIRST_HYPHEN_INDEX = 3
const val PHONE_NUMBER_FORMAT_SECOND_HYPHEN_INDEX = 7
const val PHONE_NUMBER_FORMAT_FIRST_NUMBER_AFTER_HYPHEN_INDEX = 4
const val PHONE_NUMBER_FORMAT_SECOND_NUMBER_AFTER_HYPHEN_INDEX = 8
const val PHONE_NUMBER_FORMAT_NO_HYPHEN_COUNT = 0
const val PHONE_NUMBER_FORMAT_ONE_HYPHEN_COUNT = 1
const val HYPHEN = "-"
const val OPTION_PHONE = 0
const val OPTION_EMAIL = 1
const val OPTION_DATE = 2
const val NO_DIGITS_REGEX = "[^\\d.]|\\."
const val DATE_REGEX = "\\d{2}\\/\\d{2}\\/\\d{4}"
const val SLASH = "/"
const val ONE = 1
const val ZERO = 0


/**DATE FORMATS**/
const val DATE_MASK_DATE_FORMAT_WITHOUT_SLASH = "MMDDYYYY"
const val DATE_MASK_MIN_MONTH_INDEX = 1
const val DATE_MASK_MAX_MONTH_INDEX = 12
const val DATE_MASK_MIN_YEAR = 1900
const val DATE_MASK_MAX_YEAR = 2100
const val DATE_MASK_LOOP_STEP = 2
const val DATE_MASK_LENGTH = 8
const val DATE_MASK_DAY_INITIAL_INDEX = 2
const val DATE_MASK_DAY_FINAL_INDEX = 4
const val DATE_MASK_MONTH_INITIAL_INDEX = 0
const val DATE_MASK_MONTH_FINAL_INDEX = 2
const val DATE_MASK_YEAR_INITIAL_INDEX = 4
const val DATE_MASK_YEAR_FINAL_INDEX = 8
const val DATE_MASK_SELECTION_MIN_INDEX = 0
const val DATE_MASK_MONTH_INDEX_DEFAULT_AGGREGATOR_VALUE = 1
const val DATE_MASK_JUST_DIGITS_LENGTH = 6
const val DATE_MASK_DIGITS_STRING_FORMAT = "%02d%02d%02d"
const val DATE_MASK_MAX_EMS = 10
const val DEFAULT_DATE_FORMAT = "MM/dd/yyyy"
const val HEALTH_PROVIDER_MINIMUM_NUMBER_OF_DAYS_TO_RENEW_VACCINES_ = 30
const val RESPONSE_DATE_FORMAT = "yyyy-MM-dd"
const val DATE_FORMAT_EXPIRATION_DATE = "LLLL yyyy"
const val DATE_FORMAT_EXPIRATION_DATE_OCR = "MM/yyyy"
const val COMPOUND_DRAWABLE_POSITION_ARRAY_SIZE = 2
const val DATE_EDIT_TEXT_RIGHT_COMPOUND_DRAWABLE_POSITION = 0
const val DRAWABLE_RIGHT_POSITION = 2
const val COMPOUND_DRAWABLE_TOUCH_OFF_SET = 32