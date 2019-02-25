package com.example.myapplication.helper.masks

import android.text.Editable
import android.text.TextWatcher
import android.view.KeyEvent
import android.widget.EditText
import com.example.myapplication.helper.*

class PhoneNumberTextWatcherMask(private val mReceiver: EditText) : TextWatcher {


    override fun afterTextChanged(s: Editable?) {
        s?.let { text ->
            mReceiver.removeTextChangedListener(this)
            var resultado = s.toString().replace("-", "")

            when (resultado.length) {
                in 0..3 -> {
                    resultado = resultado.replaceFirst("(\\d{3})".toRegex(), "$1")
                }
                in 4..6 -> {
                    resultado = resultado.replaceFirst("(\\d{3})(\\d{0,3})".toRegex(), "$1-$2")
                }
                in 7..10 -> {
                    resultado = resultado.replaceFirst("(\\d{3})(\\d{3})(\\d{0,4})".toRegex(), "$1-$2-$3")
                }
            }

            s.replace(0, s.length, resultado)

            mReceiver.addTextChangedListener(this)
        }
    }

    override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
    }

    override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

    }
}
