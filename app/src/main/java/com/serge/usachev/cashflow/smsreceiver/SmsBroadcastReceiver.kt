package com.serge.usachev.cashflow.smsreceiver

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.provider.Telephony
import timber.log.Timber

class SmsBroadcastReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent) {
        if (intent.action == Telephony.Sms.Intents.SMS_RECEIVED_ACTION) {
            for (smsMessage in Telephony.Sms.Intents.getMessagesFromIntent(intent)) {
                Timber.d("Sms body: ${smsMessage.messageBody}")
            }
        }
    }
}