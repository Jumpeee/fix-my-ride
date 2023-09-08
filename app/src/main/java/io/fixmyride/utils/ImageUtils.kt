package io.fixmyride.utils

import android.content.Context
import android.net.Uri
import android.provider.MediaStore

object ImageUtils {

    fun getRealPathFromUri(ctx: Context, uri: Uri?): String? {
        if (uri == null) return null
        val projection = arrayOf(MediaStore.Images.Media.DATA)
        val cursor = ctx.contentResolver.query(uri, projection, null, null, null)
        cursor?.use {
            val columnIndex = it.getColumnIndexOrThrow(MediaStore.Images.Media.DATA)
            if (it.moveToFirst()) {
                return it.getString(columnIndex)
            }
        }
        return null
    }
}