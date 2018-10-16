package com.rs.imageloader

import android.content.Context
import com.facebook.drawee.backends.pipeline.Fresco

class FrescoImageLoader : ImageLoader {

    override fun deInitialize() {
        Fresco.shutDown()
    }

    override fun initialize(context: Context) {
        Fresco.initialize(context)
    }
}
