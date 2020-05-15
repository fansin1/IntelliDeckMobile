package org.fansin.intellideck

import android.content.Context

class AppConfig(context: Context) {

    val spanCount = 4

    val spacing = context.resources.getDimensionPixelSize(R.dimen.grid_spacing)

    val includeEdge = false

    val headerNum = 0
}