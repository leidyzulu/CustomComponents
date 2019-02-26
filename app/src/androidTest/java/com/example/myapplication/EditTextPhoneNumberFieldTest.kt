package com.example.myapplication

import android.support.test.espresso.Espresso
import android.support.test.espresso.action.ViewActions
import android.support.test.espresso.assertion.ViewAssertions
import android.support.test.espresso.matcher.ViewMatchers
import android.view.View
import com.example.myapplication.customedittext.EditTextPhoneField
import com.example.myapplication.formfield.FormField
import com.example.myapplication.formfield.ValidationResult
import com.example.myapplication.helper.PHONE_NUMBER_REGEX
import com.example.myapplication.helper.VALIDATE_EMPTY_ERROR
import com.example.myapplication.helper.VALIDATE_LENGTH_ERROR
import org.junit.Assert
import org.junit.Before
import org.junit.Test

/**
 * @author Oscar Gallon on 2/25/19.
 */
class EditTextPhoneNumberFieldTest : MockActivityTest() {

    @Before
    fun setup() {
        MockActivity.layout = R.layout.activity_phoneedittext_test
    }

    @Test
    fun shouldFormatPhoneNumber() {
        restartActivity()
        Espresso.onView(ViewMatchers.withId(R.id.etPhone)).perform(ViewActions.typeText("1234567890"))
            .check(ViewAssertions.matches(ViewMatchers.withText("123-456-7890")))
    }

    @Test
    fun shouldShowAndErrorWithEmptyText() {
        restartActivity()
        //Given
        val field = (ruleActivity.activity.findViewById<View>(R.id.tlPhone) as? FormField)
        val txtInputLayout = (ruleActivity.activity.findViewById<View>(R.id.tlPhone) as? EditTextPhoneField)
        val resultIsValid : ValidationResult?

        //When
        resultIsValid = field?.isValid()

        //Then
        txtInputLayout?.setRegex(PHONE_NUMBER_REGEX)
        Assert.assertEquals(
            ValidationResult(false, VALIDATE_EMPTY_ERROR),resultIsValid

        )
    }

    @Test
    fun shouldShowAndErrorWithLessDigits() {
        restartActivity()
        //Given
        val phone = "123456"
        val field = (ruleActivity.activity.findViewById<View>(R.id.tlPhone) as? FormField)
        val txtInputLayout = (ruleActivity.activity.findViewById<View>(R.id.tlPhone) as? EditTextPhoneField)

        //When
        txtInputLayout?.setRegex(PHONE_NUMBER_REGEX)
        Espresso.onView(ViewMatchers.withId(R.id.etPhone)).perform(ViewActions.typeText(phone))

        //Then
        Assert.assertEquals(
            ValidationResult(false, VALIDATE_LENGTH_ERROR),
            field?.isValid()
        )
    }
}
