@file:Suppress("NOTHING_TO_INLINE")

package com.test.rssfeedreader.utils.extensions

import android.content.Context
import android.net.ConnectivityManager
import android.os.Build
import java.util.*


@SuppressWarnings("NewApi")
fun Context.getLocale(): Locale {
    return if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
        resources.configuration.locales[0]
    } else {
        resources.configuration.locale
    }
}


fun Context.isNetworkAvailable(): Boolean {
    val connectivityManager = (getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager)
    return connectivityManager.activeNetworkInfo?.isAvailable ?: false
}
