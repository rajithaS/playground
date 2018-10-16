package com.rs.imageloader

import android.content.Context

interface ImageLoader {
    fun initialize(context: Context)
    fun deInitialize()
}
