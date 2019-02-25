package com.example.myapplication

import android.support.design.widget.TextInputLayout
import android.support.test.espresso.Espresso
import android.support.test.espresso.action.ViewActions.click
import android.support.test.espresso.action.ViewActions.typeText
import android.support.test.espresso.matcher.ViewMatchers
import android.view.View
import com.example.myapplication.customedittext.EditTextCityField
import com.example.myapplication.formfield.FormField
import com.example.myapplication.formfield.ValidationResult
import com.example.myapplication.helper.VALIDATE_CITY_ERROR
import org.junit.Assert
import org.junit.Before
import org.junit.Test

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
    fun shouldDisplayACustomHint() {
        restartActivity()

        ruleActivity.activity.findViewById<TextInputLayout>(R.id.tlCity).hint = "Custom Hint"

        //Given
        val view = Espresso.onView(ViewMatchers.withId(R.id.tlCity))

        //When
        view.perform(click())

        //Then
        ViewMatchers.withHint("Custom Hint").matches(view)
    }

    @Test
    fun shouldReturnErrorIfCityNotBelongToTheState() {
        restartActivity()

        //Given
        val view = Espresso.onView(ViewMatchers.withId(R.id.tlCity))
        Thread.sleep(2000)

        val editTextCityField = (ruleActivity.activity.findViewById<View>(R.id.tlCity) as? EditTextCityField)

        //When
        view.perform(typeText("C"))
        editTextCityField?.setCities(arrayListOf("Medellin", "Sabaneta"))
        editTextCityField?.setStateName("Antioquia")

        //Then
        Assert.assertEquals(ValidationResult(false, VALIDATE_CITY_ERROR), editTextCityField?.isValid())

    }

}
