package com.visumit.businessboost.utils

import android.graphics.Bitmap
import android.util.Base64
import java.io.ByteArrayOutputStream

fun bitmapToBase64(bitmap: Bitmap) : String {

    val byteArrayOutputStream = ByteArrayOutputStream()

    bitmap.compress(Bitmap.CompressFormat.JPEG, 100, byteArrayOutputStream)

    val imagemArray = byteArrayOutputStream.toByteArray()

    return Base64.encodeToString(imagemArray, Base64.NO_WRAP )
}