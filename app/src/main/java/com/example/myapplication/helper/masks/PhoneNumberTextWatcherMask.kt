package com.example.myapplication.helper.masks

import android.text.Editable
import android.text.TextWatcher
import android.view.KeyEvent
import android.widget.EditText
import com.example.myapplication.helper.*

class PhoneNumberTextWatcherMask(private val mReceiver: EditText) : TextWatcher {

    private var mIsDeleting = false

    init {
        mReceiver.setOnKeyListener { _, keyCode, _ ->
            mIsDeleting = when (keyCode) {
                KeyEvent.KEYCODE_DEL -> true
                else -> false
            }
            false
        }
    }

    override fun afterTextChanged(s: Editable?) {
        s?.let { text ->
            mReceiver.removeTextChangedListener(this)
            var resultado = s.toString().replace("_", "")

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
        if (mIsDeleting) {
            mIsDeleting = false
            return
        }

        s?.let {
            if ((it.length == PHONE_NUMBER_FORMAT_FIRST_HYPHEN_INDEX ||
                            it.length == PHONE_NUMBER_FORMAT_SECOND_HYPHEN_INDEX)) {
                mReceiver.append(HYPHEN)
            }
        }
    }
}
