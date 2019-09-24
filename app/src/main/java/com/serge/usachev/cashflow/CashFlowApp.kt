package com.serge.usachev.cashflow

import android.app.Application
import timber.log.Timber

class CashFlowApp : Application() {

    override fun onCreate() {
        super.onCreate()

        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
    }
}