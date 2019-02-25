package com.example.myapplication

import android.support.test.espresso.Espresso
import android.support.test.espresso.action.ViewActions
import android.support.test.espresso.matcher.ViewMatchers
import android.support.test.rule.ActivityTestRule
import android.view.View
import com.example.myapplication.formfield.FormField
import com.example.myapplication.formfield.ValidationResult
import com.example.myapplication.helper.VALIDATE_DATE
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test

/**
 * @author Oscar Gallon on 2/25/19.
 */
class EditTextDateFieldTest {

    @get:Rule
    val ruleActivity = ActivityTestRule(MockActivity::class.java)

    @Before
    fun setup() {
        MockActivity.layout = R.layout.activity_main
    }

    @Test
    fun shouldShowErrorWitheDateIncorrectPart1(){
        Espresso.onView(ViewMatchers.withId(R.id.etDate)).perform(ViewActions.typeText("11/DD/2019"))
        Assert.assertEquals(
            ValidationResult(false, VALIDATE_DATE),
            (ruleActivity.activity.findViewById<View>(R.id.tlDate) as? FormField)?.isValid()
        )
    }

    @Test
    fun shouldShowErrorWitheDateIncorrectPart2(){
        Espresso.onView(ViewMatchers.withId(R.id.etDate)).perform(ViewActions.typeText("M1/01/2019"))
        Assert.assertEquals(
            ValidationResult(false, VALIDATE_DATE),
            (ruleActivity.activity.findViewById<View>(R.id.tlDate) as? FormField)?.isValid()
        )
    }

    @Test
    fun shouldShowErrorWitheDateIncorrectPart3(){
        Espresso.onView(ViewMatchers.withId(R.id.etDate)).perform(ViewActions.typeText("12/01/2YY9"))
        Assert.assertEquals(
            ValidationResult(false, VALIDATE_DATE),
            (ruleActivity.activity.findViewById<View>(R.id.tlDate) as? FormField)?.isValid()
        )
    }

    @Test
    fun shouldShowErrorWitheDateIncorrectPart4(){
        Espresso.onView(ViewMatchers.withId(R.id.etDate)).perform(ViewActions.replaceText("MM/DD/YYYY"))
        Assert.assertEquals(
            ValidationResult(false, VALIDATE_DATE),
            (ruleActivity.activity.findViewById<View>(R.id.tlDate) as? FormField)?.isValid()
        )
    }
}
