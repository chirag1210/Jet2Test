package com.chirag.retrofitcoroutine.util

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*
import kotlin.math.ln
import kotlin.math.pow

object Utility {

    fun isInternetConnected(context: Context): Boolean {

        val connectivityManager =
            context.getSystemService(android.content.Context.CONNECTIVITY_SERVICE)
                    as ConnectivityManager

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            val networkCapabilities = connectivityManager.activeNetwork ?: return false
            val activeNetwork =
                connectivityManager.getNetworkCapabilities(networkCapabilities) ?: return false

            return when {

                activeNetwork.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) ||
                        activeNetwork.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) ||
                        activeNetwork.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
                else -> false
            }
        } else {
            return connectivityManager.activeNetworkInfo != null &&
                    connectivityManager.activeNetworkInfo!!.isConnectedOrConnecting
        }
    }

    fun convertUTCToLocal(articleData: String): String {
        try {
            val dateFormat = SimpleDateFormat("yyyy/MM/dd HH:mm:ss")
            val date1 = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'").parse(articleData)
            val fromGmt =
                Date(date1.time + TimeZone.getDefault().getOffset(date1.time))
            val articleDateString = dateFormat.format(fromGmt)
            val currentDeviceTimeString = dateFormat.format(Date())

            val articleDate: Date = dateFormat.parse(articleDateString)
            val currentDate: Date = dateFormat.parse(currentDeviceTimeString)
            val timeDiff: Long = currentDate.time - articleDate.time

            val minutes = timeDiff / (60 * 1000) % 60
            val hours = timeDiff / (60 * 60 * 1000) % 24
            val days = timeDiff / (24 * 60 * 60 * 1000)
            if (days > 0) {
                return "$days days"
            } else if (hours > 0) {
                return "$hours hr"
            } else if (minutes > 0) {
                return "$minutes min"
            }

        } catch (e: ParseException) {
            e.printStackTrace()
        }
        return "0 min";
    }

    fun getFormattedNumber(count: Int): String {
        if (count < 1000) return "" + count
        val exp = (ln(count.toDouble()) / ln(1000.0)).toInt()
        return String.format("%.1f%c", count / 1000.0.pow(exp.toDouble()), "kMGTPE"[exp - 1])
    }
}