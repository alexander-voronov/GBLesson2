package ru.gb.course2.gblesson2

import android.view.View
import com.google.android.material.snackbar.Snackbar

fun View.showSnackBar(
    text: String,
    actionText: String,
    action: (View) -> Unit,
    length: Int = Snackbar.LENGTH_INDEFINITE
) {
    Snackbar.make(this, text, length).setAction(actionText, action).show()
}

fun View.hide() {
    if (visibility != View.GONE)
        visibility = View.GONE
}

fun View.show() {
    if (visibility != View.VISIBLE)
        visibility = View.VISIBLE
}