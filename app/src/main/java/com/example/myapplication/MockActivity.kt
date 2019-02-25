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
        setContentView(layout)

    }

    companion object {
        var layout: Int = R.layout.activity_main
    }
}
