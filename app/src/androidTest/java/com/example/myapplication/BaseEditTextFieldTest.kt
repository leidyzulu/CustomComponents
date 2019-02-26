package com.example.myapplication

import android.support.design.widget.TextInputLayout
import android.support.test.espresso.Espresso
import android.support.test.espresso.action.ViewActions.click
import android.support.test.espresso.action.ViewActions.typeText
import android.support.test.espresso.matcher.ViewMatchers
import android.support.test.espresso.matcher.ViewMatchers.withId
import com.example.myapplication.customedittext.BaseEditTextFormField
import com.example.myapplication.helper.VALIDATE_EMPTY_ERROR
import org.junit.Assert
import org.junit.Before
import org.junit.Test

/**
 * @author Oscar Gallon on 2/26/19.
 */
class BaseEditTextFieldTest : MockActivityTest() {

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
    fun shouldNotBeInvalidIfItsNotRequired(){
        MockActivity.layout = R.layout.activity_baseedittext_no_required_test
        restartActivity()

        //Given
        val view = Espresso.onView(withId(R.id.tlBase))
        val editText = Espresso.onView(withId(R.id.etBase))
        val formField = ruleActivity.activity.findViewById<BaseEditTextFormField>(R.id.tlBase)
        formField.setIsRequired(false)
        formField.setMaxLength(5)

        //When
        editText.perform(typeText("156"))
        val result = formField.isValid()

        //Then
        Assert.assertTrue(result.isValid)
    }

    @Test
    fun shouldHaveRegexAndHint() {
        MockActivity.layout = R.layout.activity_baseedittext_with_hint_and_regex
        restartActivity()

        //Given
        val view = Espresso.onView(withId(R.id.tlBase))
        val editText = Espresso.onView(withId(R.id.etBase))
        val formField = ruleActivity.activity.findViewById<BaseEditTextFormField>(R.id.tlBase)
        formField.setIsRequired(true)
        formField.setMaxLength(5)

        //When
        editText.perform(typeText("123456"))
        val result = formField.isValid()

        //Then
        ViewMatchers.withHint("Zip").matches(view)
        ViewMatchers.withText("12345").matches(view)
        Assert.assertTrue(result.isValid)
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
    }

    @Test
    fun lengthShouldBeMatch() {
        MockActivity.layout = R.layout.activity_baseedittextfield_with_hint_test
        restartActivity()

        //Given
        val formField = ruleActivity.activity.findViewById<BaseEditTextFormField>(R.id.tlBase)
        ruleActivity.runOnUiThread {
            formField.setMaxLength(5)
        }

        val view = Espresso.onView(withId(R.id.etBase))

        //When
        view.perform(typeText("123456"))

        //Then
        ViewMatchers.withText("12345").matches(view)
    }

    @Test
    fun shouldValidateRegex() {
        MockActivity.layout = R.layout.activity_baseedittextfield_with_hint_test
        restartActivity()

        //Given
        val formField = ruleActivity.activity.findViewById<BaseEditTextFormField>(R.id.tlBase)
        formField.setRegex("^[0-9]{9}$")
        val view = Espresso.onView(withId(R.id.etBase))

        //When
        formField.setMaxLength(9)
        view.perform(typeText("12345678901"))
        val result = formField.isValid()

        //Then
        ViewMatchers.withText("1234567890").matches(view)
        Assert.assertTrue(result.isValid)
    }

    @Test
    fun shouldNotBeValidIfTextDoesNotMatchRegex() {
        MockActivity.layout = R.layout.activity_baseedittextfield_with_hint_test
        restartActivity()

        //Given
        val formField = ruleActivity.activity.findViewById<BaseEditTextFormField>(R.id.tlBase)
        formField.setIsRequired(true)
        formField.setRegex("^[0-9]{9}$")
        val view = Espresso.onView(withId(R.id.etBase))

        //When
        view.perform(typeText("121"))
        val result = formField.isValid()

        //Then
        Assert.assertFalse(result.isValid)
        Assert.assertEquals(String.format(VALIDATE_EMPTY_ERROR, "Zip"), result.error)
    }

}
