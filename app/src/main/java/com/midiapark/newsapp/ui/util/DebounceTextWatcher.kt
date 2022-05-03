package com.midiapark.newsapp.ui.util

import android.os.Handler
import android.os.Looper
import android.text.Editable

import android.text.TextWatcher


class DebounceTextWatcher(private val threshold: Int = 5, private val action: (String) -> Unit) :
    TextWatcher {
    private val handler = Handler(Looper.getMainLooper())
    private var runnable: Runnable? = null
    override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
        runnable?.apply { handler.removeCallbacks(this) }
    }

    override fun afterTextChanged(s: Editable) {
        runnable = Runnable {
            if (s.length >= threshold) action(s.toString())
        }
        handler.postDelayed(runnable!!, 500)
    }

    override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}
}