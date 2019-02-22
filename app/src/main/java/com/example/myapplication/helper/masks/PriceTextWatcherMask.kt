/*
 * Copyright (c) Evercheck 2018.
 */

package com.evercheck.wallet.utils.masks

import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText
import com.example.myapplication.helper.isNumeric
import com.example.myapplication.helper.toDollarAmount
import java.text.NumberFormat
import java.util.*

/**
 * @author Oscar Gallon on 12/20/18.
 */
class PriceTextWatcherMask(private val mReceiver: EditText) : TextWatcher {

    private val mFormatter = NumberFormat.getCurrencyInstance(Locale.US)

    override fun afterTextChanged(s: Editable?) {
        s?.let { text ->

        }
    }

    override fun beforeTextChanged(
            s: CharSequence?,
            start: Int,
            count: Int,
            after: Int
    ) {
    }

    override fun onTextChanged(
            s: CharSequence?,
            start: Int,
            before: Int,
            count: Int
    ) {
        s?.toString()
                ?.let { text ->
                    mReceiver.removeTextChangedListener(this)
                    val currentyValue = getPriceFromCurrency(text).toDollarAmount()
                    mReceiver.setText(currentyValue)
                    mReceiver.setSelection(currentyValue.length)
                    mReceiver.addTextChangedListener(this)
                }

    }

    private fun getPriceFromCurrency(text: String): String {
        if (text.isNumeric()) {
            return text
        }

        return try {
            mFormatter.parse(text)
                    .toString()
        } catch (exception: Exception) {
            text
        }
    }
}
