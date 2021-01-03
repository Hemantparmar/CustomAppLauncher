package com.tesseractlauncher.models

import android.graphics.drawable.Drawable

data class ApplicationListModel(
    val icon: Drawable,
    val appName: String,
    val appPkgName: String,
    val versionCode: String,
    val versionName: String
)