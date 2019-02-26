package com.example.myapplication

import android.support.test.espresso.Espresso
import android.support.test.espresso.action.ViewActions
import android.support.test.espresso.matcher.ViewMatchers
import com.example.myapplication.customradiogroup.BaseRadioGroupFormField
import org.junit.Assert
import org.junit.Before
import org.junit.Test

class RadioGroupFieldTest : MockActivityTest() {

    @Before
    fun setup() {
        MockActivity.layout = R.layout.activity_baseradiogroup_test
    }


}