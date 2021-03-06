package co.condorlabs.customcomponents.helper.masks

import android.text.Editable
import android.widget.EditText
import co.condorlabs.customcomponents.helper.*

class PhoneNumberTextWatcherMask(private val mReceiver: EditText) : TextWatcherAdapter() {


    override fun afterTextChanged(s: Editable?) {
        s?.let { text ->
            mReceiver.removeTextChangedListener(this)
            var resultado = text.toString().replace(PHONE_NUMBER_SEPARATOR_TOKEN, "")

            when (resultado.length) {
                in PHONE_NUMBER_REGEX_FIRST_GROUP_RANGE_BOTTOM..PHONE_NUMBER_REGEX_FIRST_GROUP_RANGE_TOP -> {
                    resultado = resultado.replaceFirst(
                        PHONE_NUMBER_REGEX_FIRST_AND_SECOND_GROUP_MATCHER.toRegex(),
                        PHONE_NUMBER_REGEX_FIRST_GROUP_REPLACEMENT_MATCHER
                    )
                }
                in PHONE_NUMBER_REGEX_SECOND_GROUP_RANGE_BOTTOM..PHONE_NUMBER_REGEX_SECOND_GROUP_RANGE_TOP -> {
                    resultado = resultado.replaceFirst(
                        "$PHONE_NUMBER_REGEX_FIRST_AND_SECOND_GROUP_MATCHER$PHONE_NUMBER_REGEX_FIRST_AND_SECOND_GROUP_MATCHER".toRegex(),
                        PHONE_NUMBER_REGEX_SECOND_GROUP_REPLACEMENT_MATCHER
                    )
                }
                in PHONE_NUMBER_REGEX_THIRD_GROUP_RANGE_BOTTOM..PHONE_NUMBER_REGEX_THIRD_GROUP_RANGE_TOP -> {
                    resultado = resultado.replaceFirst(
                        ("$PHONE_NUMBER_REGEX_FIRST_AND_SECOND_GROUP_MATCHER" +
                                "$PHONE_NUMBER_REGEX_FIRST_AND_SECOND_GROUP_MATCHER" +
                                "$PHONE_NUMBER_REGEX_THIRD_GROUP_MATCHER").toRegex(),
                        PHONE_NUMBER_REGEX_THIRD_GROUP_REPLACEMENT_MATCHER
                    )
                }
            }

            text.replace(FIRST_EDITTEXT_SELECTION_CHARACTER, text.length, resultado)

            mReceiver.addTextChangedListener(this)
        }
    }

    override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

        s?.let {
            if ((it.length == PHONE_NUMBER_FORMAT_FIRST_HYPHEN_INDEX ||
                        it.length == PHONE_NUMBER_FORMAT_SECOND_HYPHEN_INDEX)
            ) {
                mReceiver.append(HYPHEN)
            }
        }
    }
}
