package com.example.myapplication

import android.support.design.widget.TextInputLayout
import android.support.test.espresso.Espresso
import android.support.test.espresso.action.ViewActions.click
import android.support.test.espresso.action.ViewActions.typeText
import android.support.test.espresso.matcher.ViewMatchers
import android.support.test.espresso.matcher.ViewMatchers.withId
import org.junit.Before
import org.junit.Test

/**
 * @author Oscar Gallon on 2/26/19.
 */
class BaseEditTextFieldTest : MockActivityTest() {

    @Before
    fun setup() {
        MockActivity.layout = R.layout.activity_baseedittextfield_test
    }

    @Test
    fun shouldDisplayDefaultHint() {
        restartActivity()

        //Given
        val view = Espresso.onView(withId(R.id.tlBase))

        //When
        view.perform(click())

        //Then
        ViewMatchers.withHint("Enter some text").matches(view)
    }

    @Test
    fun shouldDisplayZipHint() {
        MockActivity.layout = R.layout.activity_baseedittextfield_with_hint_test
        restartActivity()

        //Given
        val view = Espresso.onView(withId(R.id.tlBase))

        //When
        view.perform(click())

        //Then
        ViewMatchers.withHint("Zip").matches(view)
    }

    @Test
    fun shouldBeAbleToGetTheInputTypeSetOnTheLayout() {
        MockActivity.layout = R.layout.activity_baseedittextfield_with_hint_test
        restartActivity()

        //Given
        val textInputLayout = ruleActivity.activity.findViewById<TextInputLayout>(R.id.tlBase)
        val id = textInputLayout?.editText?.id ?: 0
        val view = Espresso.onView(withId(id))

        //When
        view.perform(typeText("A"))

        //Then
        ViewMatchers.withHint("").matches(view)
        Thread.sleep(1000)
    }

}
