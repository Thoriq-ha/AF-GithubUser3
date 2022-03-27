package com.thor.githubuser3.Utils

import android.content.res.ColorStateList
import android.content.res.Resources
import androidx.annotation.ColorRes
import androidx.core.content.ContextCompat
import com.google.android.material.floatingactionbutton.FloatingActionButton

fun FloatingActionButton.setColortint(@ColorRes tintColor: Int, theme: Resources.Theme? = null) {
    imageTintList = if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.M) {
        ColorStateList.valueOf(resources.getColor(tintColor, theme))
    } else {
        ColorStateList.valueOf(ContextCompat.getColor(context, tintColor))
    }
}