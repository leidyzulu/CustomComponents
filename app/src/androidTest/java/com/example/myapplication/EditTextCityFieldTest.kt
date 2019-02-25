package com.example.myapplication

import android.support.design.widget.TextInputLayout
import android.support.test.espresso.Espresso
import android.support.test.espresso.action.ViewActions.click
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.matcher.ViewMatchers
import org.hamcrest.Matcher
import org.junit.Before
import org.junit.Test
import android.widget.EditText
import android.support.test.espresso.matcher.BoundedMatcher
import android.view.View
import org.hamcrest.Description

import org.hamcrest.Matchers.`is`

/**
 * @author Oscar Gallon on 2/25/19.
 */
class EditTextCityFieldTest : MockActivityTest() {


    @Before
    fun setup() {
        MockActivity.layout = R.layout.activity_edittextcityfield_test
    }

    @Test
    fun shouldHaveDefaultHint() {
        restartActivity()

        //Given
        val view = Espresso.onView(ViewMatchers.withId(R.id.tlCity))

        //When
        view.perform(click())

        //Then
        ViewMatchers.withHint("City").matches(view)
    }

    @Test
    fun shouldDisplayACustomHint(){
        restartActivity()

        ruleActivity.activity.findViewById<TextInputLayout>(R.id.tlCity).hint = "Custom Hint"

        //Given
        val view = Espresso.onView(ViewMatchers.withId(R.id.tlCity))

        //When
        view.perform(click())

        //Then
        ViewMatchers.withHint("Custom Hint").matches(view)

    }

}

object HintMatcher {

    internal fun withHint(substring: String): Matcher<View> {
        return withHint(`is`(substring))
    }

    internal fun withHint(stringMatcher: Matcher<String>): Matcher<View> {
        checkNotNull(stringMatcher)
        return object : BoundedMatcher<View, EditText>(EditText::class.java) {

            public override fun matchesSafely(view: EditText): Boolean {
                val hint = view.hint
                return hint != null && stringMatcher.matches(hint.toString())
            }

            override fun describeTo(description: Description) {
                description.appendText("with hint: ")
                stringMatcher.describeTo(description)
            }
        }
    }
}
