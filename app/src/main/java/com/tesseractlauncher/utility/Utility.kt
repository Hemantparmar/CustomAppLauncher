package com.tesseractlauncher.utility

import android.content.Context
import android.content.Intent
import com.tesseractlauncher.models.ApplicationListModel

object Utility {

    fun getApplicationList(context: Context): List<ApplicationListModel> {
        val response = mutableListOf<ApplicationListModel>()
        try {
            val pkgManager = context.packageManager
            val intent = Intent(Intent.ACTION_MAIN, null).addCategory(Intent.CATEGORY_LAUNCHER)
            val appList = pkgManager.queryIntentActivities(intent, 0)
            if (!appList.isNullOrEmpty()) {
                appList.forEach {
                    val pkgInfo = pkgManager.getPackageInfo(it.activityInfo.packageName, 0)
                    val items = ApplicationListModel(
                        appName = it.loadLabel(pkgManager) as String,
                        appPkgName = it.activityInfo.packageName,
                        icon = it.activityInfo.loadIcon(pkgManager),
                        versionCode = pkgInfo?.versionCode.toString(),
                        versionName = pkgInfo?.versionName!!)
                    response.add(items)
                }
            }
            return response.sortedBy { it.appName }
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return response.sortedBy { it.appName }
    }
}