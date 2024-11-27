package io.pdaa.chilenastats.ui.common

import android.app.Activity
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext

interface BaseStateHolder {

    @Composable
    fun isTablet(): Boolean {
        val configuration = LocalConfiguration.current
        return configuration.screenWidthDp >= Constants.MOBILE_LIMIT_SIZE
    }

    @Composable
    fun getSystemBarColor(): Int{
        val context = LocalContext.current
        val window = (context as Activity).window
        return window.navigationBarColor
    }
}