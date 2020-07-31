package com.visumit.businessboost.utils

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.util.Base64
import java.io.ByteArrayOutputStream
import java.net.URL

fun bitmapToBase64(bitmap: Bitmap) : String {

    val byteArrayOutputStream = ByteArrayOutputStream()

    bitmap.compress(Bitmap.CompressFormat.JPEG, 100, byteArrayOutputStream)

    val imagemArray = byteArrayOutputStream.toByteArray()

    return Base64.encodeToString(imagemArray, Base64.NO_WRAP )
}

fun toBitmap(string: String): Bitmap{
    var bitmap : Bitmap? = null
    var url = emptyArray<String>()

    var inputStream = URL(string).openStream()

    bitmap = BitmapFactory.decodeStream(inputStream)

    return bitmap

}