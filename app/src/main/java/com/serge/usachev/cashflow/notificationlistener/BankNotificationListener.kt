package com.serge.usachev.cashflow.notificationlistener

import android.content.IntentFilter
import android.provider.Telephony
import android.service.notification.NotificationListenerService
import android.service.notification.StatusBarNotification
import com.serge.usachev.cashflow.location.TransactionLocationManager
import com.serge.usachev.cashflow.repository.ITransactionRepository
import com.serge.usachev.cashflow.smsreceiver.SmsBroadcastReceiver
import com.serge.usachev.cashflow.textconverter.NotificationTextConverter
import timber.log.Timber

class BankNotificationListener : NotificationListenerService() {

    private lateinit var smsBroadcastReceiver: SmsBroadcastReceiver

    private lateinit var notificationTextConverter: NotificationTextConverter
    private lateinit var transactionLocationManager: TransactionLocationManager
    private lateinit var repository: ITransactionRepository

    override fun onListenerConnected() {
        super.onListenerConnected()
        Timber.d("Service connect")
        Timber.d("Active notifications: ${activeNotifications.map { it.packageName }}")

        smsBroadcastReceiver = SmsBroadcastReceiver()
        registerReceiver(smsBroadcastReceiver, IntentFilter(Telephony.Sms.Intents.SMS_RECEIVED_ACTION))
    }

    override fun onNotificationPosted(sbn: StatusBarNotification) {
        super.onNotificationPosted(sbn)
        Timber.d("onNotificationPosted: Ticker text: ${sbn.notification.tickerText}")

        val transactionModel = notificationTextConverter.convertToDataModel(sbn.notification.tickerText.toString())
        if (transactionModel != null) {
            repository.insertTransactionModel(transactionModel)
            transactionLocationManager.requestCurrentCoordinates { coordinate ->
                repository.insertTransactionCoords(coordinate)
            }
        }
    }

    override fun onNotificationRemoved(sbn: StatusBarNotification) {
        super.onNotificationRemoved(sbn)
        Timber.d("onNotificationRemoved: Ticker text: ${sbn.notification.tickerText}")
    }

    override fun onListenerDisconnected() {
        super.onListenerDisconnected()
        Timber.d( "Service disconnect")
        unregisterReceiver(smsBroadcastReceiver)
    }
}