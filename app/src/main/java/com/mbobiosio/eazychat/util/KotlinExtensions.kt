package com.mbobiosio.eazychat.util

import android.app.Activity
import android.content.Context
import android.content.pm.PackageManager
import android.content.res.Configuration
import android.net.Uri
import android.os.Build
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.core.content.res.ResourcesCompat
import com.mbobiosio.eazychat.R
import www.sanju.motiontoast.MotionToast
import www.sanju.motiontoast.MotionToastStyle

/**
 * @Author Mbuodile Obiosio
 * https://linktr.ee/mbobiosio
 */
fun whatsappUri(phoneNumber: String, message: String): Uri =
    Uri.parse("https://api.whatsapp.com/send?phone=$phoneNumber&text=$message")

/**
 * Try to hide the keyboard and returns whether it worked
 * https://stackoverflow.com/questions/1109022/close-hide-the-android-soft-keyboard
 */
fun View.hideKeyboard(): Boolean {
    try {
        val inputMethodManager =
            context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        return inputMethodManager.hideSoftInputFromWindow(windowToken, 0)
    } catch (ignored: RuntimeException) {
    }
    return false
}

fun Context.isAppInstalled(packageName: String): Boolean {
    return try {
        val packageManager = this.packageManager

        when {
            Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU -> {
                packageManager.getPackageInfo(packageName, PackageManager.PackageInfoFlags.of(0))
            }

            else -> {
                @Suppress("DEPRECATION") packageManager.getPackageInfo(packageName, 0)
            }
        }
        true
    } catch (e: PackageManager.NameNotFoundException) {
        false
    }
}

fun Context.isDarkThemeOn(): Boolean {
    return resources.configuration.uiMode and Configuration.UI_MODE_NIGHT_MASK == Configuration.UI_MODE_NIGHT_YES
}

/*
* Begin Motion toast
* */
// toast status

fun Activity.showToast(message: String, status: MotionToastStyle) {
    when {
        isDarkThemeOn() -> {
            MotionToast.darkColorToast(
                context = this,
                title = getString(R.string.app_name),
                message = message,
                style = status,
                position = MotionToast.GRAVITY_TOP,
                duration = 10000L,
                font = ResourcesCompat.getFont(this, R.font.montserrat)
            )
        }

        else -> {
            MotionToast.createToast(
                context = this,
                title = getString(R.string.app_name),
                message = message,
                style = status,
                position = MotionToast.GRAVITY_TOP,
                duration = 10000L,
                font = ResourcesCompat.getFont(this, R.font.montserrat)
            )
        }
    }
}
