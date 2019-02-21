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
            if (text.length == PHONE_NUMBER_FORMAT_FIRST_NUMBER_AFTER_HYPHEN_INDEX &&
                    text.count { it.toString() == HYPHEN } == PHONE_NUMBER_FORMAT_NO_HYPHEN_COUNT) {
                text.insert(PHONE_NUMBER_FORMAT_FIRST_HYPHEN_INDEX, HYPHEN)
            }

            if (text.length == PHONE_NUMBER_FORMAT_SECOND_NUMBER_AFTER_HYPHEN_INDEX &&
                    text.count { it.toString() == HYPHEN } == PHONE_NUMBER_FORMAT_ONE_HYPHEN_COUNT) {
                text.insert(PHONE_NUMBER_FORMAT_SECOND_HYPHEN_INDEX, HYPHEN)
            }
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
