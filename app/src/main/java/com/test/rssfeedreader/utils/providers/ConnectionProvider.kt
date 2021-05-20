package com.test.rssfeedreader.utils.providers

import android.content.Context
import com.test.rssfeedreader.utils.extensions.isNetworkAvailable

class ConnectionProvider(private val context: Context) {

    fun isNetworkAvailable() : Boolean = context.isNetworkAvailable()

}