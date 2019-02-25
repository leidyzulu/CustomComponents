package com.example.myapplication

import android.support.test.espresso.Espresso
import android.support.test.espresso.action.ViewActions
import android.support.test.espresso.assertion.ViewAssertions
import android.support.test.espresso.matcher.ViewMatchers
import android.support.test.rule.ActivityTestRule
import android.view.View
import com.example.myapplication.customedittext.EditTextEmailField
import com.example.myapplication.formfield.FormField
import com.example.myapplication.formfield.ValidationResult
import com.example.myapplication.helper.VALIDATE_EMPTY
import org.junit.*

class EditTextCurrencyFieldTest : MockActivityTest() {


    @Before
    fun setup() {
        MockActivity.layout = R.layout.activity_main
    }

    @Test
    fun shouldShowAndErrorWithEmptyCurrency() {
        restartActivity()

        //Given
        val view = (ruleActivity.activity.findViewById<View>(R.id.tlCurrency) as? FormField)

        //When
        val result = view?.isValid()

        //Then
        Assert.assertEquals(
            ValidationResult(false, VALIDATE_EMPTY),
            result
        )
    }

    @Test
    fun shouldFormatCurrency() {
        restartActivity()

        val view = Espresso.onView(ViewMatchers.withId(R.id.etCurrency))

        //When
        view.perform(ViewActions.typeText("22222"))

        //Then
        ViewMatchers.withText("$22,222").matches(view)
    }
}
