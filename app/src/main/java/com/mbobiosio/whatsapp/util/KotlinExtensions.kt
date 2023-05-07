package com.mbobiosio.whatsapp.util

import android.app.Activity
import android.content.Context
import android.content.pm.PackageManager
import android.content.res.Configuration
import android.net.Uri
import android.os.Build
import android.text.Editable
import android.text.TextWatcher
import android.util.Patterns
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.core.content.res.ResourcesCompat
import androidx.core.text.trimmedLength
import com.google.android.material.textfield.TextInputEditText
import com.mbobiosio.whatsapp.R
import www.sanju.motiontoast.MotionToast
import www.sanju.motiontoast.MotionToastStyle

/**
 * @Author Mbuodile Obiosio
 * https://linktr.ee/mbobiosio
 */
fun whatsappUri(phoneNumber: String, message: String): Uri =
    Uri.parse("https://api.whatsapp.com/send?phone=$phoneNumber&text=$message")

fun String.isValidPhone(): Boolean =
    trimmedLength() in (10..13) && Patterns.PHONE.matcher(this).matches()

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

/*
* Text change listener
* */
private val beforeTextChangedStub: (CharSequence, Int, Int, Int) -> Unit = { _, _, _, _ -> }
private val onTextChangedStub: (CharSequence, Int, Int, Int) -> Unit = { _, _, _, _ -> }
private val afterTextChangedStub: (Editable) -> Unit = {}

fun TextInputEditText.textChangeListener(
    beforeTextChanged: (CharSequence, Int, Int, Int) -> Unit = beforeTextChangedStub,
    onTextChanged: (CharSequence, Int, Int, Int) -> Unit = onTextChangedStub,
    afterTextChanged: (Editable) -> Unit = afterTextChangedStub
) = addTextChangedListener(object : TextWatcher {
    override fun beforeTextChanged(charSequence: CharSequence, i: Int, i1: Int, i2: Int) {
        beforeTextChanged(charSequence, i, i1, i2)
    }

    override fun onTextChanged(charSequence: CharSequence, i: Int, i1: Int, i2: Int) {
        onTextChanged(charSequence, i, i1, i2)
    }

    override fun afterTextChanged(editable: Editable) {
        afterTextChanged(editable)
    }
})

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
                this,
                getString(R.string.app_name),
                message,
                status,
                MotionToast.GRAVITY_TOP,
                10000L,
                ResourcesCompat.getFont(this, R.font.montserrat)
            )
        }
        else -> {
            MotionToast.createToast(
                this,
                getString(R.string.app_name),
                message,
                status,
                MotionToast.GRAVITY_TOP,
                10000L,
                ResourcesCompat.getFont(this, R.font.montserrat)
            )
        }
    }
}
