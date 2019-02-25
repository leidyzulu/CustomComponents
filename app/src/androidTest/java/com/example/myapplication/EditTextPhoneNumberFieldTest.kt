package com.example.myapplication

import android.support.test.espresso.Espresso
import android.support.test.espresso.action.ViewActions
import android.support.test.espresso.assertion.ViewAssertions
import android.support.test.espresso.matcher.ViewMatchers
import android.support.test.rule.ActivityTestRule
import android.view.View
import com.example.myapplication.formfield.FormField
import com.example.myapplication.formfield.ValidationResult
import com.example.myapplication.helper.VALIDATE_EMPTY
import com.example.myapplication.helper.VALIDATE_LENGTH
import org.junit.Assert
import org.junit.Rule
import org.junit.Test

/**
 * @author Oscar Gallon on 2/25/19.
 */
class EditTextPhoneNumberFieldTest {

    @get:Rule
    val ruleActivity = ActivityTestRule(MockActivity::class.java)

    @Test
    fun shouldFormatPhoneNumber() {
        Espresso.onView(ViewMatchers.withId(R.id.etPhone)).perform(ViewActions.typeText("1234567890"))
            .check(ViewAssertions.matches(ViewMatchers.withText("123-456-7890")))
    }

    @Test
    fun shouldShowAndErrorWithEmptyText() {
        //Given
        val field = (ruleActivity.activity.findViewById<View>(R.id.tvPhone) as? FormField)
        val resultIsValid : ValidationResult?

        //When
        resultIsValid = field?.isValid()

        //Then
        Assert.assertEquals(
            ValidationResult(false, VALIDATE_EMPTY),resultIsValid

        )
    }

    @Test
    fun shouldShowAndErrorWithLessDigits() {
        //Given
        val phone = "123456"
        val field = (ruleActivity.activity.findViewById<View>(R.id.tvPhone) as? FormField)

        //When
        Espresso.onView(ViewMatchers.withId(R.id.etPhone)).perform(ViewActions.typeText(phone))

        //Then
        Assert.assertEquals(
            ValidationResult(false, VALIDATE_LENGTH),
            field?.isValid()
        )
    }
}
