package com.example.myapplication

import android.support.design.widget.TextInputLayout
import android.support.test.espresso.Espresso
import android.support.test.espresso.action.ViewActions.*
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.matcher.ViewMatchers
import android.support.test.espresso.matcher.ViewMatchers.*
import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import android.view.View
import com.example.myapplication.formfield.FormField
import com.example.myapplication.formfield.ValidationResult
import com.example.myapplication.helper.*
import org.junit.runner.RunWith
import org.hamcrest.Description
import org.hamcrest.Matcher
import org.hamcrest.TypeSafeMatcher
import org.junit.*

/**
 * @author Oscar Gallon on 2/25/19.
 */
@RunWith(AndroidJUnit4::class)
class EmailEditTextFormFieldTest {


    @get:Rule
    val ruleActivity = ActivityTestRule(MockActivity::class.java)

    @Before
    fun setup() {
        MockActivity.layout = R.layout.activity_main
    }

    @Test
    fun shouldShowAndErrorWithEmptyEmail() {
        Assert.assertEquals(
            ValidationResult(false, VALIDATE_EMPTY),
            (ruleActivity.activity.findViewById<View>(R.id.tlEmail) as? FormField)?.isValid()
        )
    }

    @Test
    fun shouldShowErrorWitheEmailIncorrectPart1() {
        //Given
        val text = "kdfkd"
        val field = (ruleActivity.activity.findViewById<View>(R.id.tlEmail) as? FormField)

        //When
        Espresso.onView(ViewMatchers.withId(R.id.etEmail)).perform(typeText(text))

        //Then
        Assert.assertEquals(
            ValidationResult(false, VALIDATE_EMAIL),
            field?.isValid()
        )
    }

    @Test
    fun shouldShowErrorWitheEmailIncorrectPart2() {
        //Given
        val text = "kdfkd@"
        val field = (ruleActivity.activity.findViewById<View>(R.id.tlEmail) as? FormField)

        //When
        Espresso.onView(ViewMatchers.withId(R.id.etEmail)).perform(typeText(text))

        //Then
        Assert.assertEquals(
            ValidationResult(false, VALIDATE_EMAIL),
            field?.isValid()
        )
    }

    @Test
    fun shouldShowErrorWitheEmailIncorrectPart3() {
        //Given
        val text = "kdfkd@smdms"
        val field = (ruleActivity.activity.findViewById<View>(R.id.tlEmail) as? FormField)

        //When
        Espresso.onView(ViewMatchers.withId(R.id.etEmail)).perform(typeText(text))

        //Then
        Assert.assertEquals(
            ValidationResult(false, VALIDATE_EMAIL),
            field?.isValid()
        )
    }

    @Test
    fun shouldShowErrorWitheEmailIncorrectPart4() {
        //Given
        val text = "kdfkd@smdms."
        val field = (ruleActivity.activity.findViewById<View>(R.id.tlEmail) as? FormField)

        //When
        Espresso.onView(ViewMatchers.withId(R.id.etEmail)).perform(typeText(text))

        //Then
        Assert.assertEquals(
            ValidationResult(false, VALIDATE_EMAIL),
            field?.isValid()
        )
    }

    fun shouldMatch() {
        //Given
        val text = "o@gmail.co"
        val field = (ruleActivity.activity.findViewById<View>(R.id.tlEmail) as? FormField)

        //When
        Espresso.onView(ViewMatchers.withId(R.id.etEmail)).perform(typeText(text))

        //Then
        Assert.assertEquals(
            ValidationResult(true, EMPTY),
            field?.isValid()
        )
    }


    @Ignore
    @Test
    fun shouldShowError() {
        ruleActivity.runOnUiThread {
            (ruleActivity.activity.findViewById<View>(R.id.tvPhone) as? FormField)?.showError(VALIDATE_EMPTY)
        }
        Espresso.onView(withId(R.id.tvPhone)).check(matches(hasErrorText(VALIDATE_EMPTY)))
    }

    fun withErrorText(expectedErrorText: Matcher<String>): Matcher<View> {
        return object : TypeSafeMatcher<View>() {
            override fun matchesSafely(view: View): Boolean {
                if (view !is TextInputLayout) {
                    return false
                }

                val error = view.error ?: return false

                return expectedErrorText.equals(error.toString())
            }

            override fun describeTo(description: Description) {

            }
        }
    }
}
