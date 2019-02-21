package com.example.myapplication

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.ViewGroup
import com.example.myapplication.formfield.IFormField
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)

        btnRun.setOnClickListener {
          for (i in 0 until (llMain?.childCount ?: 0)){
              ((llMain as? ViewGroup)?.getChildAt(i) as? IFormField)?.let {
                  it.clearError()
                  //Log.i("C", "${it.isValid()}")
                  val result = it.isValid()
                  if (!result.isValid){
                      it.showError( result.error)
                  }
              }

          }
        }
    }
}
