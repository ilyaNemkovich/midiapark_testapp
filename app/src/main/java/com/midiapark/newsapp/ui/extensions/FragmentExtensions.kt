package com.midiapark.newsapp.ui.extensions

import androidx.fragment.app.Fragment

inline fun <reified T> Fragment.findListenerByParent(): T? {
    var fragment = this
    while (fragment.parentFragment != null) {
        fragment = fragment.requireParentFragment()
        if (T::class.java.isInstance(fragment)) {
            return fragment as T
        }
    }
    return if (T::class.java.isInstance(activity)) activity as T? else null
}

inline fun <reified T> Fragment.requireListener(): T {
    return findListenerByParent()
        ?: error("Not targetFragment, neither parentFragment (or activity) implements listener ${T::class.java.simpleName}")
}