package com.serge.usachev.cashflow

import android.app.Application
import com.google.firebase.analytics.FirebaseAnalytics
import timber.log.Timber
import com.yandex.metrica.YandexMetrica
import com.yandex.metrica.YandexMetricaConfig



class CashFlowApp : Application() {

    companion object {
        private const val YANDEX_METRIKA_API_KEY = "6def836d-4f40-4d49-8225-26b20e988b22"
    }

    private lateinit var firebaseAnalytics: FirebaseAnalytics

    override fun onCreate() {
        super.onCreate()

        val config = YandexMetricaConfig.newConfigBuilder(YANDEX_METRIKA_API_KEY).build()
        YandexMetrica.activate(applicationContext, config)
        YandexMetrica.enableActivityAutoTracking(this)

        firebaseAnalytics = FirebaseAnalytics.getInstance(this)

        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
    }
}