package com.google.mlkit.codelab.translate

import androidx.fragment.app.Fragment

interface SelectFragment {
    fun onSelectFragment(fragment: Fragment, isAddToBackStack: Boolean)
}