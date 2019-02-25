package com.example.myapplication

import android.support.test.espresso.Espresso
import android.support.test.espresso.action.ViewActions
import android.support.test.espresso.assertion.ViewAssertions
import android.support.test.espresso.matcher.ViewMatchers
import android.support.test.rule.ActivityTestRule
import android.view.View
import com.example.myapplication.formfield.IFormField
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
        Assert.assertEquals(
            ValidationResult(false, VALIDATE_EMPTY),
            (ruleActivity.activity.findViewById<View>(R.id.tvPhone) as? IFormField)?.isValid()
        )
    }

    @Test
    fun shouldShowAndErrorWithLessDigits() {
        Espresso.onView(ViewMatchers.withId(R.id.etPhone)).perform(ViewActions.typeText("123456"))
        Assert.assertEquals(
            ValidationResult(false, VALIDATE_LENGTH),
            (ruleActivity.activity.findViewById<View>(R.id.tvPhone) as? IFormField)?.isValid()
        )
    }
}
