package com.serge.usachev.cashflow.notificationlistener

import android.content.IntentFilter
import android.provider.Telephony
import android.service.notification.NotificationListenerService
import android.service.notification.StatusBarNotification
import android.util.Log
import com.serge.usachev.cashflow.smsreceiver.SmsBroadcastReceiver

class BankNotificationListener : NotificationListenerService() {

    private lateinit var smsBroadcastReceiver: SmsBroadcastReceiver

    override fun onListenerConnected() {
        super.onListenerConnected()
        Log.d("CashFlowTestttt", "Service connect")
        Log.d("CashFlowTestttt", "Active notifications: ${activeNotifications.map { it.packageName }}")
        smsBroadcastReceiver = SmsBroadcastReceiver()
        registerReceiver(smsBroadcastReceiver, IntentFilter(Telephony.Sms.Intents.SMS_RECEIVED_ACTION))
    }

    override fun onNotificationPosted(sbn: StatusBarNotification) {
        super.onNotificationPosted(sbn)
        Log.d("CashFlowTestttt", "onNotificationPosted: Ticker text: ${sbn.notification.tickerText}")
    }

    override fun onNotificationRemoved(sbn: StatusBarNotification) {
        super.onNotificationRemoved(sbn)
        Log.d("CashFlowTestttt", "onNotificationRemoved: Ticker text: ${sbn.notification.tickerText}")
    }

    override fun onListenerDisconnected() {
        super.onListenerDisconnected()
        Log.d("CashFlowTestttt", "Service disconnect")
        unregisterReceiver(smsBroadcastReceiver)
    }
}