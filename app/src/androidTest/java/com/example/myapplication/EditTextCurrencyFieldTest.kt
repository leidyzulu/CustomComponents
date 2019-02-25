package com.example.myapplication

import android.support.test.espresso.Espresso
import android.support.test.espresso.action.ViewActions
import android.support.test.espresso.assertion.ViewAssertions
import android.support.test.espresso.matcher.ViewMatchers
import android.support.test.rule.ActivityTestRule
import android.view.View
import com.example.myapplication.formfield.FormField
import com.example.myapplication.formfield.ValidationResult
import com.example.myapplication.helper.VALIDATE_EMPTY
import org.junit.*

class EditTextCurrencyFieldTest {

    @get:Rule
    val ruleActivity = ActivityTestRule(MockActivity::class.java)

    @Before
    fun setup() {
        MockActivity.layout = R.layout.activity_main
    }

    @Test
    fun shouldShowAndErrorWithEmptyCurrency() {
        Assert.assertEquals(
            ValidationResult(false, VALIDATE_EMPTY),
            (ruleActivity.activity.findViewById<View>(R.id.tlCurrency) as? FormField)?.isValid()
        )
    }

    @Ignore
    @Test
    fun shouldFormatCurrency() {
        Espresso.onView(ViewMatchers.withId(R.id.etCurrency)).perform(ViewActions.typeText("22222"))
            .check(ViewAssertions.matches(ViewMatchers.withText("$22,222")))
    }


}