package com.example.app3.utils

import android.content.ContentValues.TAG
import android.content.res.Resources
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.drawable.Drawable
import androidx.annotation.ColorInt
import androidx.annotation.DrawableRes
import androidx.core.content.res.ResourcesCompat
import androidx.core.graphics.drawable.DrawableCompat
import androidx.core.graphics.drawable.toBitmap
import com.google.android.gms.maps.model.BitmapDescriptor
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import timber.log.Timber

fun vectorToBitmap(@DrawableRes id : Int, @ColorInt color : Int, resources: Resources, height: Int? = null, width: Int? = null): BitmapDescriptor {
    val vectorDrawable: Drawable? = ResourcesCompat.getDrawable(resources, id, null)
    if (vectorDrawable == null) {
        Timber.tag(TAG).e("Resource not found")
        return BitmapDescriptorFactory.defaultMarker()
    }
    val bitmap = Bitmap.createScaledBitmap(vectorDrawable.toBitmap(),
        width ?: vectorDrawable.intrinsicWidth, height?: vectorDrawable.intrinsicHeight ,true)
    val canvas = Canvas(bitmap)
    vectorDrawable.setBounds(0, 0, width ?: canvas.width, height ?: canvas.height)
    DrawableCompat.setTint(vectorDrawable, color)
    vectorDrawable.draw(canvas)

    return BitmapDescriptorFactory.fromBitmap(bitmap)
}
