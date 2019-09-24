package com.serge.usachev.cashflow

import android.Manifest
import android.annotation.SuppressLint
import android.content.pm.PackageManager
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices


class MainActivity : AppCompatActivity() {

    companion object {
        private const val SMS_PERMISSION_CODE = 101
        private const val COARSE_LOCATION_PERMISSION_CODE = 102
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_SMS) != PackageManager.PERMISSION_GRANTED) {
            showRequestPermissionsInfoAlertDialog()
        }

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

            if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.READ_CONTACTS)) {

            } else {
                ActivityCompat.requestPermissions(
                        this,
                        arrayOf(Manifest.permission.ACCESS_COARSE_LOCATION),
                        COARSE_LOCATION_PERMISSION_CODE
                )
            }

        } else {

        }
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        //super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        when (requestCode) {
            SMS_PERMISSION_CODE -> {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.isNotEmpty() && grantResults.first() == PackageManager.PERMISSION_GRANTED) {

                } else {

                }

                return
            }
            COARSE_LOCATION_PERMISSION_CODE -> {
                if (grantResults.isNotEmpty() && grantResults.first() == PackageManager.PERMISSION_GRANTED) {

                } else {

                }
            }
        }
    }

    fun showRequestPermissionsInfoAlertDialog() {
        showRequestPermissionsInfoAlertDialog(true)
    }

    fun showRequestPermissionsInfoAlertDialog(makeSystemRequest: Boolean) {
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Автоматическое чтение СМС") // Your own title
        builder.setMessage(
                "Чтобы Ваши финансовые траты автоматически " +
                        "заносились в список мы читаем смс сообщения от баноков."
        ) // Your own message

        builder.setPositiveButton("Круто, я согласен") { dialog, which ->
            dialog.dismiss()
            // Display system runtime permission request?
            if (makeSystemRequest) {
                requestReadAndSendSmsPermission()
            }
        }

        builder.setCancelable(false)
        builder.show()
    }

    private fun requestReadAndSendSmsPermission() {
        /* if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.READ_SMS)) {
             // You may display a non-blocking explanation here, read more in the documentation:
             // https://developer.android.com/training/permissions/requesting.html
         }*/
        ActivityCompat.requestPermissions(
                this,
                arrayOf(Manifest.permission.READ_SMS, Manifest.permission.RECEIVE_SMS),
                SMS_PERMISSION_CODE
        )
    }
}
