package co.condorlabs.customcomponents

import android.support.test.espresso.Espresso
import android.support.test.espresso.action.ViewActions.typeText
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.matcher.ViewMatchers
import android.support.test.espresso.matcher.ViewMatchers.isDisplayed
import android.support.test.espresso.matcher.ViewMatchers.withId
import co.condorlabs.customcomponents.customedittext.BaseEditTextFormField
import co.condorlabs.customcomponents.customedittext.EditTextFormField
import co.condorlabs.customcomponents.helper.VALIDATE_EMPTY_ERROR
import org.junit.Assert
import org.junit.Test

/**
 * @author Oscar Gallon on 2/26/19.
 */
class BaseEditTextFieldTest : MockActivityTest() {

    @Test
    fun shouldDisplayDefaultHint() {
        MockActivity.layout = R.layout.activity_baseedittextfield_test
        restartActivity()

        //Given
        val formField = ruleActivity.activity.findViewById<BaseEditTextFormField>(R.id.tlBase)

        //When


        //Then
        Assert.assertEquals("Enter some text",(formField as? EditTextFormField)?.hint)
    }

    @Test
    fun shouldDisplayZipHint() {
        MockActivity.layout = R.layout.activity_baseedittextfield_with_hint_test
        restartActivity()

        //Given
        val formField = ruleActivity.activity.findViewById<BaseEditTextFormField>(R.id.tlBase)

        //When


        //Then

        Assert.assertEquals("Zip",(formField as? EditTextFormField)?.hint)
    }

    @Test
    fun shouldNotBeInvalidIfItsNotRequired() {
        MockActivity.layout = R.layout.activity_baseedittext_no_required_test
        restartActivity()

        //Given
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
        MockActivity.layout = R.layout.activity_baseedittext_with_hint_and_regex_test
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
        Espresso.onView(ViewMatchers.withText("12345")).check(matches(isDisplayed()))
        Assert.assertEquals("Zip",(formField as? EditTextFormField)?.hint)
        Assert.assertTrue(result.isValid)
    }

    @Test
    fun shouldBeAbleToGetTheInputTypeSetOnTheLayout() {
        MockActivity.layout = R.layout.activity_baseedittextfield_with_hint_test
        restartActivity()

        //Given
        val view = Espresso.onView(withId(R.id.etBase))

        //When
        view.perform(typeText("A"))

        //Then
        Espresso.onView(ViewMatchers.withText("")).check(matches(isDisplayed()))
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
        Thread.sleep(2000)

        //Then
        Espresso.onView(ViewMatchers.withText("123456789")).check(matches(isDisplayed()))
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
