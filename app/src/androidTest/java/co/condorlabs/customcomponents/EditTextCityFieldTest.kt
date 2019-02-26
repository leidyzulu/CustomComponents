package co.condorlabs.customcomponents

import android.support.design.widget.TextInputLayout
import android.support.test.espresso.Espresso
import android.support.test.espresso.action.ViewActions.click
import android.support.test.espresso.action.ViewActions.typeText
import android.support.test.espresso.matcher.ViewMatchers
import android.view.View
import co.condorlabs.customcomponents.customedittext.EditTextCityField
import co.condorlabs.customcomponents.formfield.ValidationResult
import co.condorlabs.customcomponents.helper.VALIDATE_CITY_ERROR
import co.condorlabs.customcomponents.helper.VALIDATE_EMPTY_ERROR
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
        val view = Espresso.onView(ViewMatchers.withId(R.id.etCity))
        val editTextCityField = (ruleActivity.activity.findViewById<View>(R.id.tlCity) as? EditTextCityField)
        editTextCityField?.setIsRequired(true)

        //When
        view.perform(typeText("C"))
        editTextCityField?.setCities(arrayListOf("Medellin", "Sabaneta"))
        editTextCityField?.setStateName("Antioquia")

        //Then
        Assert.assertEquals(ValidationResult(false, "$VALIDATE_CITY_ERROR Antioquia"), editTextCityField?.isValid())
    }

    @Test
    fun shouldReturnErrorWithEmptyCity() {
        restartActivity()

        //Given
        val editTextCityField = (ruleActivity.activity.findViewById<View>(R.id.tlCity) as? EditTextCityField)
        editTextCityField?.setIsRequired(true)

        //When
        val result = editTextCityField?.isValid()

        //Then
        Assert.assertEquals(ValidationResult(false, VALIDATE_EMPTY_ERROR), result)
    }

    @Test
    fun shouldDisplayErrorWithStateName() {
        restartActivity()

        //Given
        val view = Espresso.onView(ViewMatchers.withId(R.id.etCity))
        val editTextCityField = (ruleActivity.activity.findViewById<View>(R.id.tlCity) as? EditTextCityField)
        editTextCityField?.setIsRequired(true)

        //When
        view.perform(typeText("C"))
        editTextCityField?.setCities(arrayListOf("Medellin", "Sabaneta"))
        editTextCityField?.setStateName("Antioquia")
        editTextCityField?.let {
            showErrorInInputLayout(it, it.isValid().error)
        }

        //Then
        ViewMatchers.hasErrorText("$VALIDATE_CITY_ERROR Antioquia").matches(view)
    }
}
