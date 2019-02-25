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
import com.example.myapplication.formfield.IFormField
import com.example.myapplication.formfield.ValidationResult
import com.example.myapplication.helper.VALIDATE_DATE
import com.example.myapplication.helper.VALIDATE_EMAIL
import com.example.myapplication.helper.VALIDATE_EMPTY
import com.example.myapplication.helper.VALIDATE_LENGTH
import org.junit.runner.RunWith
import org.hamcrest.Description
import org.hamcrest.Matcher
import org.hamcrest.TypeSafeMatcher
import org.junit.*


/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class EditTextFormFieldTest {


    @get:Rule
    val ruleActivity = ActivityTestRule(MockActivity::class.java)

    @Before
    fun setup() {
        MockActivity.layout = R.layout.activity_main
    }

    @Test
    fun shouldFormatPhoneNumber() {
        Espresso.onView(ViewMatchers.withId(R.id.etPhone)).perform(typeText("1234567890"))
            .check(matches(withText("123-456-7890")))
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
        Espresso.onView(ViewMatchers.withId(R.id.etPhone)).perform(typeText("123456"))
        Assert.assertEquals(
            ValidationResult(false, VALIDATE_LENGTH),
            (ruleActivity.activity.findViewById<View>(R.id.tvPhone) as? IFormField)?.isValid()
        )
    }

    @Test
    fun shouldShowErrorWitheEmailIncorrectPart1(){
        Espresso.onView(ViewMatchers.withId(R.id.etEmail)).perform(typeText("kdfkd"))
        Thread.sleep(1000)
        Assert.assertEquals(
            ValidationResult(false, VALIDATE_EMAIL),
            (ruleActivity.activity.findViewById<View>(R.id.tlEmail) as? IFormField)?.isValid()
        )
    }

    @Test
    fun shouldShowErrorWitheEmailIncorrectPart2(){
        Espresso.onView(ViewMatchers.withId(R.id.etEmail)).perform(typeText("kdfkd@"))
        Assert.assertEquals(
            ValidationResult(false, VALIDATE_EMAIL),
            (ruleActivity.activity.findViewById<View>(R.id.tlEmail) as? IFormField)?.isValid()
        )
    }

    @Test
    fun shouldShowErrorWitheEmailIncorrectPart3(){
        Espresso.onView(ViewMatchers.withId(R.id.etEmail)).perform(typeText("kdfkd@smdms"))
        Assert.assertEquals(
            ValidationResult(false, VALIDATE_EMAIL),
            (ruleActivity.activity.findViewById<View>(R.id.tlEmail) as? IFormField)?.isValid()
        )
    }

    @Test
    fun shouldShowErrorWitheEmailIncorrectPart4(){
        Espresso.onView(ViewMatchers.withId(R.id.etEmail)).perform(typeText("kdfkd@smdms."))
        Assert.assertEquals(
            ValidationResult(false, VALIDATE_EMAIL),
            (ruleActivity.activity.findViewById<View>(R.id.tlEmail) as? IFormField)?.isValid()
        )
    }

    @Test
    fun shouldShowErrorWitheDateIncorrectPart1(){
        Espresso.onView(ViewMatchers.withId(R.id.etDate)).perform(typeText("11/DD/2019"))
        Assert.assertEquals(
            ValidationResult(false, VALIDATE_DATE),
            (ruleActivity.activity.findViewById<View>(R.id.tlDate) as? IFormField)?.isValid()
        )
    }

    @Test
    fun shouldShowErrorWitheDateIncorrectPart2(){
        Espresso.onView(ViewMatchers.withId(R.id.etDate)).perform(typeText("M1/01/2019"))
        Assert.assertEquals(
            ValidationResult(false, VALIDATE_DATE),
            (ruleActivity.activity.findViewById<View>(R.id.tlDate) as? IFormField)?.isValid()
        )
    }

    @Test
    fun shouldShowErrorWitheDateIncorrectPart3(){
        Espresso.onView(ViewMatchers.withId(R.id.etDate)).perform(typeText("12/01/2YY9"))
        Assert.assertEquals(
            ValidationResult(false, VALIDATE_DATE),
            (ruleActivity.activity.findViewById<View>(R.id.tlDate) as? IFormField)?.isValid()
        )
    }

    @Test
    fun shouldShowErrorWitheDateIncorrectPart4(){
        Espresso.onView(ViewMatchers.withId(R.id.etDate)).perform(replaceText("MM/DD/YYYY"))
        Assert.assertEquals(
            ValidationResult(false, VALIDATE_DATE),
            (ruleActivity.activity.findViewById<View>(R.id.tlDate) as? IFormField)?.isValid()
        )
    }


    @Ignore
    @Test
    fun shouldShowError() {
        ruleActivity.runOnUiThread {
            (ruleActivity.activity.findViewById<View>(R.id.tvPhone) as? IFormField)?.showError(VALIDATE_EMPTY)
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
