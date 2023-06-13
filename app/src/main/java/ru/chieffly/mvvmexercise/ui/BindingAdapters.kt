package ru.chieffly.mvvmexercise.ui

import androidx.databinding.BindingAdapter
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import ru.chieffly.mvvmexercise.R

/**
 *Created by Bryantsev Anton on 13.06.2023.
 **/

@BindingAdapter("errorInputName")
fun bindErrorInputName(tilName: TextInputLayout, isError: Boolean) {
    val message = if (isError) {
         tilName.context.getString(R.string.error_input_name)
    } else {
        null
    }
    tilName.error = message
}


@BindingAdapter("errorInputCount")
fun bindErrorInputCount(tilName: TextInputLayout, isError: Boolean) {
    val message = if (isError) {
        tilName.context.getString(R.string.error_input_count)
    } else {
        null
    }
    tilName.error = message
}

@BindingAdapter("setIntToString")
fun bindIntToEditText(editText: TextInputEditText, int: Int) {
    editText.setText(int.toString())
}