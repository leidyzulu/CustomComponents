package com.example.myapplication

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.example.myapplication.helper.DATE_REGEX
import kotlinx.android.synthetic.main.activity_main.*

/**
 * @author Oscar Gallon on 2/21/19.
 */

class MockActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        tlDate.setRegex(DATE_REGEX)
    }

    companion object {
        var layout: Int = 0
    }
}
