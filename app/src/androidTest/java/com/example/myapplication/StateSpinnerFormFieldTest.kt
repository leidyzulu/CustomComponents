package com.example.myapplication

import android.support.test.espresso.Espresso
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.matcher.ViewMatchers.isDisplayed
import android.support.test.espresso.matcher.ViewMatchers.withId
import org.junit.Before
import org.junit.Test

/**
 * @author Oscar Gallon on 2/26/19.
 */
class StateSpinnerFormFieldTest : MockActivityTest() {


    @Before
    fun setup() {
        MockActivity.layout = R.layout.activity_statespinnerformfield_test
    }

    @Test
    fun shouldDisplaySpinner() {
        restartActivity()

        //Given
        //val view = Espresso.onView(withId(R.id.spState))

        //When


        //Then
            //view.check(matches(isDisplayed()))
    }

}
